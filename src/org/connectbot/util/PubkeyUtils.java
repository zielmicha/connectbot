/*
	ConnectBot: simple, powerful, open-source SSH client for Android
	Copyright (C) 2007-2008 Kenny Root, Jeffrey Sharkey
	
	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.connectbot.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.generators.OpenSSLPBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import com.trilead.ssh2.crypto.Base64;
import com.trilead.ssh2.signature.DSASHA1Verify;
import com.trilead.ssh2.signature.RSASHA1Verify;

public class PubkeyUtils {
	public static String formatKey(Key key){
		String algo = key.getAlgorithm();
		String fmt = key.getFormat();
		byte[] encoded = key.getEncoded();
		return "Key[algorithm=" + algo + ", format=" + fmt +
			", bytes=" + encoded.length + "]";
	}
	
	public static String describeKey(Key key, boolean encrypted) {
		String desc = null;
		if (key instanceof RSAPublicKey) {
			int bits = ((RSAPublicKey)key).getModulus().bitLength();
			desc = "RSA " + String.valueOf(bits) + "-bit";
		} else if (key instanceof DSAPublicKey) {
			desc = "DSA 1024-bit";
		} else {
			desc = "Unknown Key Type";
		}
		
		if (encrypted)
			desc += " (encrypted)";
		
		return desc;
	}
	
	public static byte[] sha256(byte[] data) throws NoSuchAlgorithmException {
		return MessageDigest.getInstance("SHA-256").digest(data);
	}
	
	public static byte[] cipher(int mode, byte[] data, byte[] secret) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(sha256(secret), "AES");
		Cipher c = Cipher.getInstance("AES");
		c.init(mode, secretKeySpec);
		return c.doFinal(data);
	}	
	
	public static byte[] encrypt(byte[] cleartext, String secret) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		return cipher(Cipher.ENCRYPT_MODE, cleartext, secret.getBytes());
	}
	
	public static byte[] decrypt(byte[] ciphertext, String secret) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return cipher(Cipher.DECRYPT_MODE, ciphertext, secret.getBytes());
	}
	
	public static byte[] getEncodedPublic(PublicKey pk) {
		return new X509EncodedKeySpec(pk.getEncoded()).getEncoded();
	}
	
	public static byte[] getEncodedPrivate(PrivateKey pk) {
		return new PKCS8EncodedKeySpec(pk.getEncoded()).getEncoded();
	}
	
	public static byte[] getEncodedPrivate(PrivateKey pk, String secret) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		if (secret.length() > 0)
			return encrypt(getEncodedPrivate(pk), secret);
		else
			return getEncodedPrivate(pk);
	}
	
	public static PrivateKey decodePrivate(byte[] encoded, String keyType) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(encoded);
		KeyFactory kf = KeyFactory.getInstance(keyType);
		return kf.generatePrivate(privKeySpec);
	}
	
	public static PrivateKey decodePrivate(byte[] encoded, String keyType, String secret) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		if (secret != null && secret.length() > 0)
			return decodePrivate(decrypt(encoded, secret), keyType);
		else
			return decodePrivate(encoded, keyType);
	}
	
	public static PublicKey decodePublic(byte[] encoded, String keyType) throws NoSuchAlgorithmException, InvalidKeySpecException {
		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encoded);
		KeyFactory kf = KeyFactory.getInstance(keyType);
		return kf.generatePublic(pubKeySpec);
	}
	
	/*
	 * Trilead compatibility methods
	 */
	
	public static Object convertToTrilead(PublicKey pk) {
		if (pk instanceof RSAPublicKey) {
			return new com.trilead.ssh2.signature.RSAPublicKey(
					((RSAPublicKey) pk).getPublicExponent(),
					((RSAPublicKey) pk).getModulus());
		} else if (pk instanceof DSAPublicKey) {
			DSAParams dp = ((DSAPublicKey) pk).getParams();
			return new com.trilead.ssh2.signature.DSAPublicKey(
						dp.getP(), dp.getQ(), dp.getG(), ((DSAPublicKey) pk).getY());
		}
		
		throw new IllegalArgumentException("PublicKey is not RSA or DSA format");
	}
	
	public static Object convertToTrilead(PrivateKey priv, PublicKey pub) {
		if (priv instanceof RSAPrivateKey) {
			return new com.trilead.ssh2.signature.RSAPrivateKey(
					((RSAPrivateKey) priv).getPrivateExponent(),
					((RSAPublicKey) pub).getPublicExponent(),
					((RSAPrivateKey) priv).getModulus());
		} else if (priv instanceof DSAPrivateKey) {
			DSAParams dp = ((DSAPrivateKey) priv).getParams();
			return new com.trilead.ssh2.signature.DSAPrivateKey(
						dp.getP(), dp.getQ(), dp.getG(), ((DSAPublicKey) pub).getY(),
						((DSAPrivateKey) priv).getX());
		}
		
		throw new IllegalArgumentException("Key is not RSA or DSA format");
	}
	
	/*
	 * OpenSSH compatibility methods 
	 */
	
	public static String convertToOpenSSHFormat(PublicKey pk, String nickname) throws IOException, InvalidKeyException {
		if (nickname == null)
			nickname = "connectbot@android";

		if (pk instanceof RSAPublicKey) {
			String data = "ssh-rsa ";
			data += String.valueOf(Base64.encode(RSASHA1Verify.encodeSSHRSAPublicKey(
					(com.trilead.ssh2.signature.RSAPublicKey)convertToTrilead(pk))));
			return data + " " + nickname;
		} else if (pk instanceof DSAPublicKey) {
			String data = "ssh-dss ";
			data += String.valueOf(Base64.encode(DSASHA1Verify.encodeSSHDSAPublicKey(
					(com.trilead.ssh2.signature.DSAPublicKey)convertToTrilead(pk))));
			return data + " " + nickname;
		}
		
		throw new InvalidKeyException("Unknown key type");
	}
	
	public static String exportPEM(PrivateKey key, String secret) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, IOException {		
		StringBuilder sb = new StringBuilder();
		
		String type;
		byte[] data = null;
		if (key instanceof RSAPrivateCrtKey) {
			type = "RSA";
			
			RSAPrivateCrtKey pck = (RSAPrivateCrtKey) key;
			
			ASN1EncodableVector vector = new ASN1EncodableVector();
			vector.add(new DERInteger(0));
			vector.add(new DERInteger(pck.getModulus()));
			vector.add(new DERInteger(pck.getPublicExponent()));
			vector.add(new DERInteger(pck.getPrivateExponent()));
			vector.add(new DERInteger(pck.getPrimeP()));
			vector.add(new DERInteger(pck.getPrimeQ()));
			vector.add(new DERInteger(pck.getPrimeExponentP()));
			vector.add(new DERInteger(pck.getPrimeExponentQ()));
			vector.add(new DERInteger(pck.getCrtCoefficient()));

			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ASN1OutputStream aout = new ASN1OutputStream(bout);
			aout.writeObject(new DERSequence(vector));
			aout.close();
			data = bout.toByteArray();
			bout.close();
		} else if (key instanceof DSAPrivateKey) {
			type = "DSA";
			
			DSAPrivateKey k = (DSAPrivateKey) key;
            DSAParams p = k.getParams();

			ASN1EncodableVector v = new ASN1EncodableVector();

            v.add(new DERInteger(0));
            v.add(new DERInteger(p.getP()));
            v.add(new DERInteger(p.getQ()));
            v.add(new DERInteger(p.getG()));

            BigInteger x = k.getX();
            BigInteger y = p.getG().modPow(x, p.getP());

            v.add(new DERInteger(y));
            v.add(new DERInteger(x));

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ASN1OutputStream aout = new ASN1OutputStream(bout);
			aout.writeObject(new DERSequence(v));
			aout.close();
			data = bout.toByteArray();
			bout.close();
		} else
			throw new IllegalArgumentException("Key is not one of RSA or DSA");

		sb.append("-----BEGIN " + type + " PRIVATE KEY-----\n");
		
		if (secret != null) {
			byte[] salt = new byte[8];
			SecureRandom random = new SecureRandom();
			random.nextBytes(salt);
			
			int keyLength = 24;
			String algorithm = "DESede";
			OpenSSLPBEParametersGenerator pGen = new OpenSSLPBEParametersGenerator();
			pGen.init(PBEParametersGenerator.PKCS5PasswordToBytes(secret.toCharArray()), salt);
			SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(
					((KeyParameter) pGen.generateDerivedParameters(keyLength * 8)).getKey(),
					algorithm); 
			
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(salt));
			
			sb.append("Proc-Type: 4,ENCRYPTED\n");
			sb.append("DEK-Info: DES-EDE3-CBC,");
			sb.append(new String(Hex.encode(salt)));
			sb.append("\n\n");
			
			byte[] encrypted = c.doFinal(data);
			data = encrypted;
		}
		
		int i = sb.length();
		sb.append(Base64.encode(data));
		for (i += 63; i < sb.length(); i += 64) {
			sb.insert(i, "\n");
		}
		
		sb.append("\n-----END " + type + " PRIVATE KEY-----\n");

		return sb.toString();
	} 
}