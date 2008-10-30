package org.connectbot.terminal;

import java.util.Properties;

import de.mud.terminal.VDUBuffer;
import de.mud.terminal.VDUInput;

public class xterm extends VDUBuffer implements VDUInput {
	private final static int NPARAM = 30; // Maximum number of parameters
	
	private final static int CASE_GROUND_STATE = 0;
	private final static int CASE_IGNORE = 1;
	private final static int CASE_BELL = 2;
	private final static int CASE_BS = 3;
	private final static int CASE_CR = 4;
	private final static int CASE_ESC = 5;
	private final static int CASE_VMOT = 6;
	private final static int CASE_TAB = 7;
	private final static int CASE_SI = 8;
	private final static int CASE_SO = 9;
	private final static int CASE_SCR_STATE = 10;
	private final static int CASE_SCS0_STATE = 11;
	private final static int CASE_SCS1_STATE = 12;
	private final static int CASE_SCS2_STATE = 13;
	private final static int CASE_SCS3_STATE = 14;
	private final static int CASE_ESC_IGNORE = 15;
	private final static int CASE_ESC_DIGIT = 16;
	private final static int CASE_ESC_SEMI = 17;
	private final static int CASE_DEC_STATE = 18;
	private final static int CASE_ICH = 19;
	private final static int CASE_CUU = 20;
	private final static int CASE_CUD = 21;
	private final static int CASE_CUF = 22;
	private final static int CASE_CUB = 23;
	private final static int CASE_CUP = 24;
	private final static int CASE_ED = 25;
	private final static int CASE_EL = 26;
	private final static int CASE_IL = 27;
	private final static int CASE_DL = 28;
	private final static int CASE_DCH = 29;
	private final static int CASE_DA1 = 30;
	private final static int CASE_TRACK_MOUSE = 31;
	private final static int CASE_TBC = 32;
	private final static int CASE_SET = 33;
	private final static int CASE_RST = 34;
	private final static int CASE_SGR = 35;
	private final static int CASE_CPR = 36;
	private final static int CASE_DECSTBM = 37;
	private final static int CASE_DECREQTPARM = 38;
	private final static int CASE_DECSET = 39;
	private final static int CASE_DECRST = 40;
	private final static int CASE_DECALN = 41;
	private final static int CASE_GSETS = 42;
	private final static int CASE_DECSC = 43;
	private final static int CASE_DECRC = 44;
	private final static int CASE_DECKPAM = 45;
	private final static int CASE_DECKPNM = 46;
	private final static int CASE_IND = 47;
	private final static int CASE_NEL = 48;
	private final static int CASE_HTS = 49;
	private final static int CASE_RI = 50;
	private final static int CASE_SS2 = 51;
	private final static int CASE_SS3 = 52;
	private final static int CASE_CSI_STATE = 53;
	private final static int CASE_OSC = 54;
	private final static int CASE_RIS = 55;
	private final static int CASE_LS2 = 56;
	private final static int CASE_LS3 = 57;
	private final static int CASE_LS3R = 58;
	private final static int CASE_LS2R = 59;
	private final static int CASE_LS1R = 60;
	private final static int CASE_PRINT = 61;
	private final static int CASE_XTERM_SAVE = 62;
	private final static int CASE_XTERM_RESTORE = 63;
	private final static int CASE_XTERM_TITLE = 64;
	private final static int CASE_DECID = 65;
	private final static int CASE_HP_MEM_LOCK = 66;
	private final static int CASE_HP_MEM_UNLOCK = 67;
	private final static int CASE_HP_BUGGY_LL = 68;
	private final static int CASE_HPA = 69;
	private final static int CASE_VPA = 70;
	private final static int CASE_XTERM_WINOPS = 71;
	private final static int CASE_ECH = 72;
	private final static int CASE_CHT = 73;
	private final static int CASE_CPL = 74;
	private final static int CASE_CNL = 75;
	private final static int CASE_CBT = 76;
	private final static int CASE_SU = 77;
	private final static int CASE_SD = 78;
	private final static int CASE_S7C1T = 79;
	private final static int CASE_S8C1T = 80;
	private final static int CASE_ESC_SP_STATE = 81;
	private final static int CASE_ENQ = 82;
	private final static int CASE_DECSCL = 83;
	private final static int CASE_DECSCA = 84;
	private final static int CASE_DECSED = 85;
	private final static int CASE_DECSEL = 86;
	private final static int CASE_DCS = 87;
	private final static int CASE_PM = 88;
	private final static int CASE_SOS = 89;
	private final static int CASE_ST = 90;
	private final static int CASE_APC = 91;
	private final static int CASE_EPA = 92;
	private final static int CASE_SPA = 93;
	private final static int CASE_CSI_QUOTE_STATE = 94;
	private final static int CASE_DSR = 95;
	private final static int CASE_ANSI_LEVEL_1 = 96;
	private final static int CASE_ANSI_LEVEL_2 = 97;
	private final static int CASE_ANSI_LEVEL_3 = 98;
	private final static int CASE_MC = 99;
	private final static int CASE_DEC2_STATE = 100;
	private final static int CASE_DA2 = 101;
	private final static int CASE_DEC3_STATE = 102;
	private final static int CASE_DECRPTUI = 103;
	private final static int CASE_VT52_CUP = 104;
	private final static int CASE_REP = 105;
	private final static int CASE_CSI_EX_STATE = 106;
	private final static int CASE_DECSTR = 107;
	private final static int CASE_DECDHL = 108;
	private final static int CASE_DECSWL = 109;
	private final static int CASE_DECDWL = 110;
	private final static int CASE_DEC_MC = 111;
	private final static int CASE_ESC_PERCENT = 112;
	private final static int CASE_UTF8 = 113;
	private final static int CASE_CSI_TICK_STATE = 114;
	private final static int CASE_DECELR = 115;
	private final static int CASE_DECRQLP = 116;
	private final static int CASE_DECEFR = 117;
	private final static int CASE_DECSLE = 118;
	private final static int CASE_CSI_IGNORE = 119;
	private final static int CASE_VT52_IGNORE = 120;
	private final static int CASE_VT52_FINISH = 121;
	private final static int CASE_CSI_DOLLAR_STATE = 122;
	private final static int CASE_DECCRA = 123;
	private final static int CASE_DECERA = 124;
	private final static int CASE_DECFRA = 125;
	private final static int CASE_DECSERA = 126;
	private final static int CASE_DECSACE = 127;
	private final static int CASE_DECCARA = 128;
	private final static int CASE_DECRARA = 129;
	private final static int CASE_CSI_STAR_STATE = 130;
	private final static int CASE_SET_MOD_FKEYS = 131;
	private final static int CASE_SET_MOD_FKEYS0 = 132;
	private final static int CASE_HIDE_POINTER = 133;
	private final static int CASE_SCS1A_STATE = 134;
	private final static int CASE_SCS2A_STATE = 135;
	private final static int CASE_SCS3A_STATE = 136;

	private static final char ansi_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		$				%				&				'		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		(				)				*				+		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		,				-				.				/		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		0				1				2				3		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		4				5				6				7		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		8				9				:				;		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		<				=				>				?		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		@				A				B				C		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		D				E				F				G		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		H				I				J				K		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		L				M				N				O		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		P				Q				R				S		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		T				U				V				W		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		X				Y				Z				[		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		\				]				^				_		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		`				a				b				c		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		d				e				f				g		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		h				i				j				k		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		l				m				n				o		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		p				q				r				s		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		t				u				v				w		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		x				y				z				{		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		|				}				~				DEL		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      currency        yen             brokenbar       section         */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      notsign         hyphen          registered      macron          */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      eth             ntilde          ograve          oacute          */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
	};

	private static final char csi_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_CSI_IGNORE,		CASE_CSI_EX_STATE,		CASE_CSI_QUOTE_STATE,		CASE_CSI_IGNORE,
		/*		$				%				&				'		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_TICK_STATE,
		/*		(				)				*				+		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		,				-				.				/		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		0				1				2				3		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*		4				5				6				7		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*		8				9				:				;		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_CSI_IGNORE,		CASE_ESC_SEMI,
		/*		<				=				>				?		*/
		CASE_CSI_IGNORE,		CASE_DEC3_STATE,		CASE_DEC2_STATE,		CASE_DEC_STATE,
		/*		@				A				B				C		*/
		CASE_ICH,		CASE_CUU,		CASE_CUD,		CASE_CUF,
		/*		D				E				F				G		*/
		CASE_CUB,		CASE_CNL,		CASE_CPL,		CASE_HPA,
		/*		H				I				J				K		*/
		CASE_CUP,		CASE_CHT,		CASE_ED,		CASE_EL,
		/*		L				M				N				O		*/
		CASE_IL,		CASE_DL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_DCH,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SU,
		/*		T				U				V				W		*/
		CASE_TRACK_MOUSE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_ECH,		CASE_GROUND_STATE,		CASE_CBT,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_HPA,		CASE_GROUND_STATE,		CASE_REP,		CASE_DA1,
		/*		d				e				f				g		*/
		CASE_VPA,		CASE_GROUND_STATE,		CASE_CUP,		CASE_TBC,
		/*		h				i				j				k		*/
		CASE_SET,		CASE_MC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_RST,		CASE_SGR,		CASE_CPR,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECSTBM,		CASE_DECSC,
		/*		t				u				v				w		*/
		CASE_XTERM_WINOPS,		CASE_DECRC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_DECREQTPARM,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_CSI_IGNORE,		CASE_CSI_EX_STATE,		CASE_CSI_QUOTE_STATE,		CASE_CSI_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_TICK_STATE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_CSI_IGNORE,		CASE_ESC_SEMI,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_CSI_IGNORE,		CASE_DEC3_STATE,		CASE_DEC2_STATE,		CASE_DEC_STATE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_ICH,		CASE_CUU,		CASE_CUD,		CASE_CUF,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_CUB,		CASE_CNL,		CASE_CPL,		CASE_HPA,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_CUP,		CASE_CHT,		CASE_ED,		CASE_EL,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_IL,		CASE_DL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_DCH,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SU,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_TRACK_MOUSE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_ECH,		CASE_GROUND_STATE,		CASE_CBT,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_HPA,		CASE_GROUND_STATE,		CASE_REP,		CASE_DA1,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_VPA,		CASE_GROUND_STATE,		CASE_CUP,		CASE_TBC,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_SET,		CASE_MC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_RST,		CASE_SGR,		CASE_CPR,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECSTBM,		CASE_DECSC,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_XTERM_WINOPS,		CASE_DECRC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_DECREQTPARM,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char csi2_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_CSI_IGNORE,		CASE_CSI_EX_STATE,		CASE_CSI_QUOTE_STATE,		CASE_CSI_IGNORE,
		/*		$				%				&				'		*/
		CASE_CSI_DOLLAR_STATE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_TICK_STATE,
		/*		(				)				*				+		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_STAR_STATE,		CASE_CSI_IGNORE,
		/*		,				-				.				/		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		0				1				2				3		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*		4				5				6				7		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*		8				9				:				;		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_CSI_IGNORE,		CASE_ESC_SEMI,
		/*		<				=				>				?		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		@				A				B				C		*/
		CASE_ICH,		CASE_CUU,		CASE_CUD,		CASE_CUF,
		/*		D				E				F				G		*/
		CASE_CUB,		CASE_CNL,		CASE_CPL,		CASE_HPA,
		/*		H				I				J				K		*/
		CASE_CUP,		CASE_CHT,		CASE_ED,		CASE_EL,
		/*		L				M				N				O		*/
		CASE_IL,		CASE_DL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_DCH,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SU,
		/*		T				U				V				W		*/
		CASE_TRACK_MOUSE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_ECH,		CASE_GROUND_STATE,		CASE_CBT,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_HPA,		CASE_GROUND_STATE,		CASE_REP,		CASE_DA1,
		/*		d				e				f				g		*/
		CASE_VPA,		CASE_GROUND_STATE,		CASE_CUP,		CASE_TBC,
		/*		h				i				j				k		*/
		CASE_SET,		CASE_MC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_RST,		CASE_SGR,		CASE_CPR,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECSTBM,		CASE_DECSC,
		/*		t				u				v				w		*/
		CASE_XTERM_WINOPS,		CASE_DECRC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_DECREQTPARM,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_CSI_IGNORE,		CASE_CSI_EX_STATE,		CASE_CSI_QUOTE_STATE,		CASE_CSI_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_TICK_STATE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_CSI_IGNORE,		CASE_ESC_SEMI,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_ICH,		CASE_CUU,		CASE_CUD,		CASE_CUF,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_CUB,		CASE_CNL,		CASE_CPL,		CASE_HPA,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_CUP,		CASE_CHT,		CASE_ED,		CASE_EL,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_IL,		CASE_DL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_DCH,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SU,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_TRACK_MOUSE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_ECH,		CASE_GROUND_STATE,		CASE_CBT,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_HPA,		CASE_GROUND_STATE,		CASE_REP,		CASE_DA1,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_VPA,		CASE_GROUND_STATE,		CASE_CUP,		CASE_TBC,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_SET,		CASE_MC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_RST,		CASE_SGR,		CASE_CPR,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECSTBM,		CASE_DECSC,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_XTERM_WINOPS,		CASE_DECRC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_DECREQTPARM,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char csi_ex_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		$				%				&				'		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		(				)				*				+		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		,				-				.				/		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		0				1				2				3		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		4				5				6				7		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		8				9				:				;		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		<				=				>				?		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_DECSTR,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_DECSTR,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char csi_quo_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		$				%				&				'		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		(				)				*				+		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		,				-				.				/		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		0				1				2				3		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		4				5				6				7		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		8				9				:				;		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		<				=				>				?		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_DECSCL,		CASE_DECSCA,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_DECSCL,		CASE_DECSCA,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char csi_tick_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		$				%				&				'		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		(				)				*				+		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		,				-				.				/		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		0				1				2				3		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		4				5				6				7		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		8				9				:				;		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		<				=				>				?		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECEFR,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECELR,		CASE_DECSLE,
		/*		|				}				~				DEL		*/
		CASE_DECRQLP,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*		nobreakspace		exclamdown		cent				sterling		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		currency		yen				brokenbar		section				*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		diaeresis		copyright		ordfeminine		guillemotleft		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		notsign				hyphen				registered		macron				*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		degree				plusminus		twosuperior		threesuperior		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		acute				mu				paragraph		periodcentered		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		cedilla				onesuperior		masculine		guillemotright		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		onequarter		onehalf				threequarters		questiondown		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		Agrave				Aacute				Acircumflex		Atilde				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Adiaeresis		Aring				AE				Ccedilla		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Egrave				Eacute				Ecircumflex		Ediaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Igrave				Iacute				Icircumflex		Idiaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Eth				Ntilde				Ograve				Oacute				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Ocircumflex		Otilde				Odiaeresis		multiply		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Ooblique		Ugrave				Uacute				Ucircumflex		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Udiaeresis		Yacute				Thorn				ssharp				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		agrave				aacute				acircumflex		atilde				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		adiaeresis		aring				ae				ccedilla		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		egrave				eacute				ecircumflex		ediaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		igrave				iacute				icircumflex		idiaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		eth				ntilde				ograve				oacute				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		ocircumflex		otilde				odiaeresis		division		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECEFR,
		/*		oslash				ugrave				uacute				ucircumflex		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECELR,		CASE_DECSLE,
		/*		udiaeresis		yacute				thorn				ydiaeresis		*/
		CASE_DECRQLP,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char csi_dollar_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		$				%				&				'		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		(				)				*				+		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		,				-				.				/		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		0				1				2				3		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		4				5				6				7		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		8				9				:				;		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		<				=				>				?		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECCARA,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_DECRARA,		CASE_GROUND_STATE,		CASE_DECCRA,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_DECFRA,		CASE_GROUND_STATE,		CASE_DECERA,		CASE_DECSERA,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*		nobreakspace		exclamdown		cent				sterling		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		currency		yen				brokenbar		section				*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		diaeresis		copyright		ordfeminine		guillemotleft		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		notsign				hyphen				registered		macron				*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		degree				plusminus		twosuperior		threesuperior		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		acute				mu				paragraph		periodcentered		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		cedilla				onesuperior		masculine		guillemotright		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		onequarter		onehalf				threequarters		questiondown		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		Agrave				Aacute				Acircumflex		Atilde				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Adiaeresis		Aring				AE				Ccedilla		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Egrave				Eacute				Ecircumflex		Ediaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Igrave				Iacute				Icircumflex		Idiaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Eth				Ntilde				Ograve				Oacute				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Ocircumflex		Otilde				Odiaeresis		multiply		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Ooblique		Ugrave				Uacute				Ucircumflex		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Udiaeresis		Yacute				Thorn				ssharp				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		agrave				aacute				acircumflex		atilde				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		adiaeresis		aring				ae				ccedilla		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		egrave				eacute				ecircumflex		ediaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		igrave				iacute				icircumflex		idiaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		eth				ntilde				ograve				oacute				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECCARA,		CASE_GROUND_STATE,
		/*		ocircumflex		otilde				odiaeresis		division		*/
		CASE_DECRARA,		CASE_GROUND_STATE,		CASE_DECCRA,		CASE_GROUND_STATE,
		/*		oslash				ugrave				uacute				ucircumflex		*/
		CASE_DECFRA,		CASE_GROUND_STATE,		CASE_DECERA,		CASE_DECSERA,
		/*		udiaeresis		yacute				thorn				ydiaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
	};

	private static final char csi_star_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		$				%				&				'		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		(				)				*				+		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		,				-				.				/		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		0				1				2				3		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		4				5				6				7		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		8				9				:				;		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		<				=				>				?		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_DECSACE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*		nobreakspace		exclamdown		cent				sterling		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		currency		yen				brokenbar		section				*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		diaeresis		copyright		ordfeminine		guillemotleft		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		notsign				hyphen				registered		macron				*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		degree				plusminus		twosuperior		threesuperior		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		acute				mu				paragraph		periodcentered		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		cedilla				onesuperior		masculine		guillemotright		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		onequarter		onehalf				threequarters		questiondown		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		Agrave				Aacute				Acircumflex		Atilde				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Adiaeresis		Aring				AE				Ccedilla		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Egrave				Eacute				Ecircumflex		Ediaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Igrave				Iacute				Icircumflex		Idiaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Eth				Ntilde				Ograve				Oacute				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Ocircumflex		Otilde				Odiaeresis		multiply		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Ooblique		Ugrave				Uacute				Ucircumflex		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		Udiaeresis		Yacute				Thorn				ssharp				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		agrave				aacute				acircumflex		atilde				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		adiaeresis		aring				ae				ccedilla		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		egrave				eacute				ecircumflex		ediaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		igrave				iacute				icircumflex		idiaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		eth				ntilde				ograve				oacute				*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		ocircumflex		otilde				odiaeresis		division		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		oslash				ugrave				uacute				ucircumflex		*/
		CASE_DECSACE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		udiaeresis		yacute				thorn				ydiaeresis		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
	};

	private static final char dec_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		$				%				&				'		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		(				)				*				+		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		,				-				.				/		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		0				1				2				3		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*		4				5				6				7		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*		8				9				:				;		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_CSI_IGNORE,		CASE_ESC_SEMI,
		/*		<				=				>				?		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECSED,		CASE_DECSEL,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_DECSET,		CASE_DEC_MC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_DECRST,		CASE_GROUND_STATE,		CASE_DSR,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_XTERM_RESTORE,		CASE_XTERM_SAVE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_CSI_IGNORE,		CASE_ESC_SEMI,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECSED,		CASE_DECSEL,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_DECSET,		CASE_DEC_MC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_DECRST,		CASE_GROUND_STATE,		CASE_DSR,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_XTERM_RESTORE,		CASE_XTERM_SAVE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char dec2_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		$				%				&				'		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		(				)				*				+		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		,				-				.				/		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		0				1				2				3		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*		4				5				6				7		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*		8				9				:				;		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_CSI_IGNORE,		CASE_ESC_SEMI,
		/*		<				=				>				?		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DA2,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_SET_MOD_FKEYS,		CASE_SET_MOD_FKEYS0,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_HIDE_POINTER,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_CSI_IGNORE,		CASE_ESC_SEMI,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DA2,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char dec3_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		$				%				&				'		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		(				)				*				+		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		,				-				.				/		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		0				1				2				3		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*		4				5				6				7		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*		8				9				:				;		*/
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_CSI_IGNORE,		CASE_ESC_SEMI,
		/*		<				=				>				?		*/
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECRPTUI,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_ESC_DIGIT,		CASE_ESC_DIGIT,		CASE_CSI_IGNORE,		CASE_ESC_SEMI,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,		CASE_CSI_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECRPTUI,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char cigtable[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		$				%				&				'		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		(				)				*				+		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		,				-				.				/		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		0				1				2				3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		4				5				6				7		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		8				9				:				;		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		<				=				>				?		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char eigtable[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		$				%				&				'		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		(				)				*				+		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		,				-				.				/		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		0				1				2				3		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		4				5				6				7		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		8				9				:				;		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		<				=				>				?		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char esc_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_ESC_SP_STATE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_SCR_STATE,
		/*		$				%				&				'		*/
		CASE_ESC_IGNORE,		CASE_ESC_PERCENT,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		(				)				*				+		*/
		CASE_SCS0_STATE,		CASE_SCS1_STATE,		CASE_SCS2_STATE,		CASE_SCS3_STATE,
		/*		,				-				.				/		*/
		CASE_ESC_IGNORE,		CASE_SCS1A_STATE,		CASE_SCS2A_STATE,		CASE_SCS3A_STATE,
		/*		0				1				2				3		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		4				5				6				7		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECSC,
		/*		8				9				:				;		*/
		CASE_DECRC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		<				=				>				?		*/
		CASE_GROUND_STATE,		CASE_DECKPAM,		CASE_DECKPNM,		CASE_GROUND_STATE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_IND,		CASE_NEL,		CASE_HP_BUGGY_LL,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*		P				Q				R				S		*/
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_XTERM_TITLE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*		X				Y				Z				[		*/
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*		\				]				^				_		*/
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_RIS,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_HP_MEM_LOCK,		CASE_HP_MEM_UNLOCK,		CASE_LS2,		CASE_LS3,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_LS3R,		CASE_LS2R,		CASE_LS1R,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_ESC_SP_STATE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_SCR_STATE,
		/*      currency        yen             brokenbar       section         */
		CASE_ESC_IGNORE,		CASE_ESC_PERCENT,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_SCS0_STATE,		CASE_SCS1_STATE,		CASE_SCS2_STATE,		CASE_SCS3_STATE,
		/*      notsign         hyphen          registered      macron          */
		CASE_ESC_IGNORE,		CASE_SCS1A_STATE,		CASE_SCS2A_STATE,		CASE_SCS3A_STATE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECSC,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_DECRC,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_GROUND_STATE,		CASE_DECKPAM,		CASE_DECKPNM,		CASE_GROUND_STATE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_IND,		CASE_NEL,		CASE_HP_BUGGY_LL,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_XTERM_TITLE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_RIS,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_HP_MEM_LOCK,		CASE_HP_MEM_UNLOCK,		CASE_LS2,		CASE_LS3,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_LS3R,		CASE_LS2R,		CASE_LS1R,		CASE_IGNORE,
	};

	private static final char esc_sp_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		$				%				&				'		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		(				)				*				+		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		,				-				.				/		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		0				1				2				3		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		4				5				6				7		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		8				9				:				;		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		<				=				>				?		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_S7C1T,		CASE_S8C1T,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_ANSI_LEVEL_1,		CASE_ANSI_LEVEL_2,		CASE_ANSI_LEVEL_3,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_S7C1T,		CASE_S8C1T,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_ANSI_LEVEL_1,		CASE_ANSI_LEVEL_2,		CASE_ANSI_LEVEL_3,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char scrtable[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		$				%				&				'		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		(				)				*				+		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		,				-				.				/		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		0				1				2				3		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECDHL,
		/*		4				5				6				7		*/
		CASE_DECDHL,		CASE_DECSWL,		CASE_DECDWL,		CASE_GROUND_STATE,
		/*		8				9				:				;		*/
		CASE_DECALN,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		<				=				>				?		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_DECDHL,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_DECDHL,		CASE_DECSWL,		CASE_DECDWL,		CASE_GROUND_STATE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_DECALN,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char scstable[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		$				%				&				'		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		(				)				*				+		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		,				-				.				/		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		0				1				2				3		*/
		CASE_GSETS,		CASE_GSETS,		CASE_GSETS,		CASE_GROUND_STATE,
		/*		4				5				6				7		*/
		CASE_GSETS,		CASE_GSETS,		CASE_GSETS,		CASE_GSETS,
		/*		8				9				:				;		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		<				=				>				?		*/
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GSETS,		CASE_GSETS,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GSETS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GSETS,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GSETS,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GSETS,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_GSETS,		CASE_GSETS,		CASE_GSETS,		CASE_GROUND_STATE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_GSETS,		CASE_GSETS,		CASE_GSETS,		CASE_GSETS,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GSETS,		CASE_GSETS,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GSETS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GSETS,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GSETS,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GSETS,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char scs96table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		$				%				&				'		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		(				)				*				+		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		,				-				.				/		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		0				1				2				3		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		4				5				6				7		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		8				9				:				;		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		<				=				>				?		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_GROUND_STATE,		CASE_GSETS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};


	private static final char sos_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		NP				CR				SO				SI		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		$				%				&				'		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		(				)				*				+		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		,				-				.				/		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		0				1				2				3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		4				5				6				7		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		8				9				:				;		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		<				=				>				?		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		@				A				B				C		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		D				E				F				G		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		H				I				J				K		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		L				M				N				O		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		P				Q				R				S		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		T				U				V				W		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		X				Y				Z				[		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		\				]				^				_		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		`				a				b				c		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		d				e				f				g		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		h				i				j				k		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		l				m				n				o		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		p				q				r				s		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		t				u				v				w		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		x				y				z				{		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		|				}				~				DEL		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
	};

	private static final char esc_pct_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_SO,		CASE_SI,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		$				%				&				'		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		(				)				*				+		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		,				-				.				/		*/
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*		0				1				2				3		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		4				5				6				7		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		8				9				:				;		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		<				=				>				?		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		@				A				B				C		*/
		CASE_UTF8,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_UTF8,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IND,		CASE_NEL,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_HTS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_GROUND_STATE,		CASE_RI,		CASE_SS2,		CASE_SS3,
		/*      0x90            0x91            0x92            0x93    */
		CASE_DCS,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_SPA,		CASE_EPA,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_SOS,		CASE_GROUND_STATE,		CASE_DECID,		CASE_CSI_STATE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_ST,		CASE_OSC,		CASE_PM,		CASE_APC,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,		CASE_ESC_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_UTF8,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_UTF8,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
	};

	private static final char vt52_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_IGNORE,		CASE_IGNORE,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		$				%				&				'		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		(				)				*				+		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		,				-				.				/		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		0				1				2				3		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		4				5				6				7		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		8				9				:				;		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		<				=				>				?		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		@				A				B				C		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		D				E				F				G		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		H				I				J				K		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		L				M				N				O		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		P				Q				R				S		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		T				U				V				W		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		X				Y				Z				[		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		\				]				^				_		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		`				a				b				c		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		d				e				f				g		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		h				i				j				k		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		l				m				n				o		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		p				q				r				s		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		t				u				v				w		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		x				y				z				{		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,
		/*		|				}				~				DEL		*/
		CASE_PRINT,		CASE_PRINT,		CASE_PRINT,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x90            0x91            0x92            0x93    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
	};

	private static final char vt52_esc_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_IGNORE,		CASE_IGNORE,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,
		/*		$				%				&				'		*/
		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,
		/*		(				)				*				+		*/
		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,
		/*		,				-				.				/		*/
		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,		CASE_VT52_IGNORE,
		/*		0				1				2				3		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		4				5				6				7		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		8				9				:				;		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		<				=				>				?		*/
		CASE_VT52_FINISH,		CASE_DECKPAM,		CASE_DECKPNM,		CASE_GROUND_STATE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_CUU,		CASE_CUD,		CASE_CUF,
		/*		D				E				F				G		*/
		CASE_CUB,		CASE_GROUND_STATE,		CASE_SO,		CASE_SI,
		/*		H				I				J				K		*/
		CASE_CUP,		CASE_RI,		CASE_ED,		CASE_EL,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_VT52_CUP,		CASE_DECID,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x90            0x91            0x92            0x93    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
	};

	private static final char vt52_ignore_table[] =
	{
		/*		NUL				SOH				STX				ETX		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		EOT				ENQ				ACK				BEL		*/
		CASE_IGNORE,		CASE_ENQ,		CASE_IGNORE,		CASE_BELL,
		/*		BS				HT				NL				VT		*/
		CASE_BS,		CASE_TAB,		CASE_VMOT,		CASE_VMOT,
		/*		NP				CR				SO				SI		*/
		CASE_VMOT,		CASE_CR,		CASE_IGNORE,		CASE_IGNORE,
		/*		DLE				DC1				DC2				DC3		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		DC4				NAK				SYN				ETB		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		CAN				EM				SUB				ESC		*/
		CASE_GROUND_STATE,		CASE_IGNORE,		CASE_GROUND_STATE,		CASE_ESC,
		/*		FS				GS				RS				US		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		SP				!				"				#		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		$				%				&				'		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		(				)				*				+		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		,				-				.				/		*/
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*		0				1				2				3		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		4				5				6				7		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		8				9				:				;		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		<				=				>				?		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		@				A				B				C		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		D				E				F				G		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		H				I				J				K		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		L				M				N				O		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		P				Q				R				S		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		T				U				V				W		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		X				Y				Z				[		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		\				]				^				_		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		`				a				b				c		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		d				e				f				g		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		h				i				j				k		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		l				m				n				o		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		p				q				r				s		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		t				u				v				w		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		x				y				z				{		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,
		/*		|				}				~				DEL		*/
		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_GROUND_STATE,		CASE_IGNORE,
		/*      0x80            0x81            0x82            0x83    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x84            0x85            0x86            0x87    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x88            0x89            0x8a            0x8b    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x8c            0x8d            0x8e            0x8f    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x90            0x91            0x92            0x93    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x94            0x95            0x96            0x97    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x98            0x99            0x9a            0x9b    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      0x9c            0x9d            0x9e            0x9f    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      nobreakspace    exclamdown      cent            sterling        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      currency        yen             brokenbar       section         */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      diaeresis       copyright       ordfeminine     guillemotleft   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      notsign         hyphen          registered      macron          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      degree          plusminus       twosuperior     threesuperior   */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      acute           mu              paragraph       periodcentered  */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      cedilla         onesuperior     masculine       guillemotright  */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      onequarter      onehalf         threequarters   questiondown    */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Agrave          Aacute          Acircumflex     Atilde          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Adiaeresis      Aring           AE              Ccedilla        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Egrave          Eacute          Ecircumflex     Ediaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Igrave          Iacute          Icircumflex     Idiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Eth             Ntilde          Ograve          Oacute          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Ocircumflex     Otilde          Odiaeresis      multiply        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Ooblique        Ugrave          Uacute          Ucircumflex     */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      Udiaeresis      Yacute          Thorn           ssharp          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      agrave          aacute          acircumflex     atilde          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      adiaeresis      aring           ae              ccedilla        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      egrave          eacute          ecircumflex     ediaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      igrave          iacute          icircumflex     idiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      eth             ntilde          ograve          oacute          */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      ocircumflex     otilde          odiaeresis      division        */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      oslash          ugrave          uacute          ucircumflex     */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
		/*      udiaeresis      yacute          thorn           ydiaeresis      */
		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,		CASE_IGNORE,
	};
	
	private int nparam;
	private int param[];
	private char[] groundtable;
	private char[] parsestate;
	private int scstype;
	private int scssize;
	private boolean private_function;
	private int string_mode;
	private int lastchar;
	private int nextstate;
	private int last_was_wide;
	
	public xterm() {
		groundtable = ansi_table;
		parsestate = ansi_table;
	}
	
	private void select_charset(int type, int size) {
		scstype = type;
		scssize = size;
		
		if (size == 94) {
			parsestate = scstable;
		} else {
			parsestate = scs96table;
		}
	}
	
	private void parse(int c, boolean doshowcursor) {
		int rows = getRows(); //statusline
		int columns = getColumns();
		int tm = getTopMargin();
		int bm = getBottomMargin();
		// byte msg[];
		boolean mapped = false;
	}
		    
	public void keyPressed(int keyCode, char keyChar, int modifiers) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(int keyCode, char keyChar, int modifiers) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(int x, int y, int modifiers) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(int x, int y, int modifiers) {
		// TODO Auto-generated method stub

	}

	public void setKeyCodes(Properties codes) {
		// TODO Auto-generated method stub

	}

	public void write(byte[] b) {
		// TODO Auto-generated method stub

	}

}		

