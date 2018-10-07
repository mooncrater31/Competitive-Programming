class example
{
	public static void main(String argv[]) throws java.io.IOException
	{
		Yylex yy = new Yylex(System.in) ;
		Integer t ;
		while((t = yy.yylex())!=null)
		{
			System.out.println("Another one!") ;
			System.out.println(t) ;
		}
	}
}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

	int nestedCommentCount = 0 ;
    boolean stringTooLong=false;
    boolean nullInString=false;
    boolean stringError=false ;
    boolean eof_encountered=false;
	StringBuffer string_buf = new StringBuffer();
	private int curr_lineno = 1;
	static int MAX_STR_CONST = 1025;
	boolean temp_skip = false ;
	final boolean debug = false ;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int STRING = 2;
	private final int BLOCK_COMMENT = 1;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0,
		73,
		106
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NO_ANCHOR,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NO_ANCHOR,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NO_ANCHOR,
		/* 199 */ YY_NO_ANCHOR,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NO_ANCHOR,
		/* 202 */ YY_NO_ANCHOR,
		/* 203 */ YY_NO_ANCHOR,
		/* 204 */ YY_NO_ANCHOR,
		/* 205 */ YY_NO_ANCHOR,
		/* 206 */ YY_NO_ANCHOR,
		/* 207 */ YY_NO_ANCHOR,
		/* 208 */ YY_NO_ANCHOR,
		/* 209 */ YY_NO_ANCHOR,
		/* 210 */ YY_NO_ANCHOR,
		/* 211 */ YY_NO_ANCHOR,
		/* 212 */ YY_NO_ANCHOR,
		/* 213 */ YY_NO_ANCHOR,
		/* 214 */ YY_NO_ANCHOR,
		/* 215 */ YY_NO_ANCHOR,
		/* 216 */ YY_NO_ANCHOR,
		/* 217 */ YY_NO_ANCHOR,
		/* 218 */ YY_NO_ANCHOR,
		/* 219 */ YY_NO_ANCHOR,
		/* 220 */ YY_NO_ANCHOR,
		/* 221 */ YY_NO_ANCHOR,
		/* 222 */ YY_NO_ANCHOR,
		/* 223 */ YY_NO_ANCHOR,
		/* 224 */ YY_NO_ANCHOR,
		/* 225 */ YY_NO_ANCHOR,
		/* 226 */ YY_NO_ANCHOR,
		/* 227 */ YY_NO_ANCHOR,
		/* 228 */ YY_NO_ANCHOR,
		/* 229 */ YY_NO_ANCHOR,
		/* 230 */ YY_NO_ANCHOR,
		/* 231 */ YY_NO_ANCHOR,
		/* 232 */ YY_NO_ANCHOR,
		/* 233 */ YY_NO_ANCHOR,
		/* 234 */ YY_NO_ANCHOR,
		/* 235 */ YY_NO_ANCHOR,
		/* 236 */ YY_NO_ANCHOR,
		/* 237 */ YY_NO_ANCHOR,
		/* 238 */ YY_NO_ANCHOR,
		/* 239 */ YY_NO_ANCHOR,
		/* 240 */ YY_NO_ANCHOR,
		/* 241 */ YY_NO_ANCHOR,
		/* 242 */ YY_NO_ANCHOR,
		/* 243 */ YY_NO_ANCHOR,
		/* 244 */ YY_NO_ANCHOR,
		/* 245 */ YY_NO_ANCHOR,
		/* 246 */ YY_NO_ANCHOR,
		/* 247 */ YY_NO_ANCHOR,
		/* 248 */ YY_NO_ANCHOR,
		/* 249 */ YY_NO_ANCHOR,
		/* 250 */ YY_NO_ANCHOR,
		/* 251 */ YY_NO_ANCHOR,
		/* 252 */ YY_NO_ANCHOR,
		/* 253 */ YY_NO_ANCHOR,
		/* 254 */ YY_NO_ANCHOR,
		/* 255 */ YY_NO_ANCHOR,
		/* 256 */ YY_NO_ANCHOR,
		/* 257 */ YY_NO_ANCHOR,
		/* 258 */ YY_NO_ANCHOR,
		/* 259 */ YY_NO_ANCHOR,
		/* 260 */ YY_NO_ANCHOR,
		/* 261 */ YY_NO_ANCHOR,
		/* 262 */ YY_NO_ANCHOR,
		/* 263 */ YY_NO_ANCHOR,
		/* 264 */ YY_NO_ANCHOR,
		/* 265 */ YY_NO_ANCHOR,
		/* 266 */ YY_NO_ANCHOR,
		/* 267 */ YY_NO_ANCHOR,
		/* 268 */ YY_NO_ANCHOR,
		/* 269 */ YY_NO_ANCHOR,
		/* 270 */ YY_NO_ANCHOR,
		/* 271 */ YY_NO_ANCHOR,
		/* 272 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"39:9,42,41,45,39,44,39:18,40,39,69,39:5,28,30,27,33,31,2,35,32,46:10,36,29," +
"1,34,39:2,37,7,22,5,19,9,17,25,13,16,24,68,6,26,14,11,12,68,20,8,10,68,18,1" +
"5,68,23,68,39:4,21,39,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,57,63" +
",64,65,57,43,66,57,67,57,3,39,4,38,39,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,273,
"0,1,2,3,1:2,4,1:2,5,1:5,6,1:7,7,1:2,8,9,1:3,10,11,12,13,12:2,1:2,14,12:2,15" +
",12:6,16,12:13,1,17,1:8,18,19,20,21,7,22,7:2,23,7:2,24,7:6,25,7:13,26,27,28" +
",29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53" +
",54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78" +
",79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102" +
",103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,12" +
"1,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,1" +
"40,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,12,156,157,1" +
"58,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176," +
"177,178,179,180,181,182,183,184,185,186,7,187,188,189,190,191")[0];

	private int yy_nxt[][] = unpackFromString(192,70,
"1,2,3,4,5,6,74,218,226,229,231,107,233,235,157,237,109,111,235:3,7,239,235:" +
"4,8,9,10,11,12,13,14,15,16,17,18,19,7,20,21,22,23,24,25,26,27,219,227,267,2" +
"30,75,267:2,108,267:2,110,267,158,112,232,267,271,234,252,267,235,28,-1:72," +
"29,-1:31,30,-1:37,31,-1:72,235,241,159,235:19,-1:16,235,-1:2,235,159,235:10" +
",241,235:10,-1:28,37,-1:76,38,-1:40,267:22,-1:16,267,-1:2,267:23,-1:47,26,-" +
"1:28,267:3,254,267:18,-1:16,267,-1:2,267:18,254,267:4,-1:2,31:40,-1,31:2,-1" +
",31:25,-1:5,235:5,39,235:16,-1:16,235,-1:2,235:19,39,235:3,-1:6,235:22,-1:1" +
"6,235,-1:2,235:23,-1:6,235:5,42,235:2,228,235:13,-1:16,235,-1:2,235:8,228,2" +
"35:10,42,235:3,-1:6,235:16,197,235:5,-1:16,235,-1:2,235:23,-1:6,235:16,268," +
"235:5,-1:16,235,-1:2,235:23,-1:6,235:16,208,235:5,-1:16,235,-1:2,235:23,-1:" +
"31,67,-1:39,1,63:26,64,105,63,65,63:10,66,63:28,-1:5,235:4,32,235,161,235:1" +
"5,-1:16,235,-1:2,235:5,32,235:9,161,235:7,-1:6,267:11,80,267:10,-1:16,267,-" +
"1:2,267:9,80,267:13,-1:6,267:5,81,267:16,-1:16,267,-1:2,267:19,81,267:3,-1:" +
"6,267:5,84,267:2,259,267:13,-1:16,267,-1:2,267:8,259,267:10,84,267:3,-1:6,2" +
"67:16,262,267:5,-1:16,267,-1:2,267:23,-1:6,267:16,260,267:5,-1:16,267,-1:2," +
"267:23,-1:6,267:16,272,267:5,-1:16,267,-1:2,267:23,-1:28,68,-1:42,1,69:40,7" +
"0,69:2,71,69:24,72,-1:5,235:12,33,235:4,175,235:4,-1:16,235,-1:2,235:2,175," +
"235:3,33,235:16,-1:6,267:3,255,267:5,78,267:2,79,267:9,-1:16,267,-1:2,267:6" +
",79,267:7,78,267:3,255,267:4,-1:6,235:3,181,235:5,34,235:2,35,235:9,-1:16,2" +
"35,-1:2,235:6,35,235:7,34,235:3,181,235:4,-1:6,267:4,76,267,168,267:15,-1:1" +
"6,267,-1:2,267:5,76,267:9,168,267:7,-1:6,235:11,36,235:10,-1:16,235,-1:2,23" +
"5:9,36,235:13,-1:6,267:12,77,267:4,256,267:4,-1:16,267,-1:2,267:2,256,267:3" +
",77,267:16,-1:6,235:10,40,235:11,-1:16,235,-1:2,235:20,40,235:2,-1:6,267:10" +
",82,267:11,-1:16,267,-1:2,267:20,82,267:2,-1:6,235:5,41,235:16,-1:16,235,-1" +
":2,235:19,41,235:3,-1:6,267:5,83,267:16,-1:16,267,-1:2,267:19,83,267:3,-1:6" +
",235:4,43,235:17,-1:16,235,-1:2,235:5,43,235:17,-1:6,267,91,267:20,-1:16,26" +
"7,-1:2,267:12,91,267:10,-1:6,235:7,44,235:14,-1:16,235,-1:2,235:16,44,235:6" +
",-1:6,267:4,85,267:17,-1:16,267,-1:2,267:5,85,267:17,-1:6,235:4,45,235:17,-" +
"1:16,235,-1:2,235:5,45,235:17,-1:6,267:4,87,267:17,-1:16,267,-1:2,267:5,87," +
"267:17,-1:6,46,235:21,-1:16,235,-1:2,235:3,46,235:19,-1:6,88,267:21,-1:16,2" +
"67,-1:2,267:3,88,267:19,-1:6,235:9,47,235:12,-1:16,235,-1:2,235:14,47,235:8" +
",-1:6,267:7,86,267:14,-1:16,267,-1:2,267:16,86,267:6,-1:6,235,48,235:20,-1:" +
"16,235,-1:2,235:12,48,235:10,-1:6,267,90,267:20,-1:16,267,-1:2,267:12,90,26" +
"7:10,-1:6,235,49,235:20,-1:16,235,-1:2,235:12,49,235:10,-1:6,267:9,89,267:1" +
"2,-1:16,267,-1:2,267:14,89,267:8,-1:6,235:3,50,235:18,-1:16,235,-1:2,235:18" +
",50,235:4,-1:6,267:3,92,267:18,-1:16,267,-1:2,267:18,92,267:4,-1:6,235:15,5" +
"1,235:6,-1:16,235,-1:2,235:17,51,235:5,-1:6,267:15,93,267:6,-1:16,267,-1:2," +
"267:17,93,267:5,-1:6,235:4,52,235:17,-1:16,235,-1:2,235:5,52,235:17,-1:6,26" +
"7:4,94,267:17,-1:16,267,-1:2,267:5,94,267:17,-1:6,235:9,53,235:12,-1:16,235" +
",-1:2,235:14,53,235:8,-1:6,267:9,95,267:12,-1:16,267,-1:2,267:14,95,267:8,-" +
"1:6,235:20,54,235,-1:16,235,-1:2,235:7,54,235:15,-1:6,267:14,98,267:7,-1:16" +
",267,-1:2,267:4,98,267:18,-1:6,235:14,55,235:7,-1:16,235,-1:2,235:4,55,235:" +
"18,-1:6,267:20,96,267,-1:16,267,-1:2,267:7,96,267:15,-1:6,235:14,56,235:7,-" +
"1:16,235,-1:2,235:4,56,235:18,-1:6,267:14,97,267:7,-1:16,267,-1:2,267:4,97," +
"267:18,-1:6,235:5,57,235:16,-1:16,235,-1:2,235:19,57,235:3,-1:6,267:3,101,2" +
"67:18,-1:16,267,-1:2,267:18,101,267:4,-1:6,235:14,58,235:7,-1:16,235,-1:2,2" +
"35:4,58,235:18,-1:6,267:5,99,267:16,-1:16,267,-1:2,267:19,99,267:3,-1:6,235" +
":3,59,235:18,-1:16,235,-1:2,235:18,59,235:4,-1:6,267:14,100,267:7,-1:16,267" +
",-1:2,267:4,100,267:18,-1:6,235:5,60,235:16,-1:16,235,-1:2,235:19,60,235:3," +
"-1:6,267:5,103,267:16,-1:16,267,-1:2,267:19,103,267:3,-1:6,235:5,61,235:16," +
"-1:16,235,-1:2,235:19,61,235:3,-1:6,267:5,102,267:16,-1:16,267,-1:2,267:19," +
"102,267:3,-1:6,235:5,62,235:16,-1:16,235,-1:2,235:19,62,235:3,-1:6,267:5,10" +
"4,267:16,-1:16,267,-1:2,267:19,104,267:3,-1:6,235:4,113,235,115,235:15,-1:1" +
"6,235,-1:2,235:5,113,235:9,115,235:7,-1:6,267:4,114,267,116,267:15,-1:16,26" +
"7,-1:2,267:5,114,267:9,116,267:7,-1:6,235:3,117,235:18,-1:16,235,-1:2,235:1" +
"8,117,235:4,-1:6,267:6,118,267:15,-1:16,267,-1:2,267:15,118,267:7,-1:6,235:" +
"6,119,235:15,-1:16,235,-1:2,235:15,119,235:7,-1:6,267:3,120,267:18,-1:16,26" +
"7,-1:2,267:18,120,267:4,-1:6,235:3,221,235:18,-1:16,235,-1:2,235:18,221,235" +
":4,-1:6,267:3,122,267:18,-1:16,267,-1:2,267:18,122,267:4,-1:6,235:15,187,23" +
"5:6,-1:16,235,-1:2,235:17,187,235:5,-1:6,267:2,124,267:19,-1:16,267,-1:2,26" +
"7,124,267:21,-1:6,235:3,121,235:18,-1:16,235,-1:2,235:18,121,235:4,-1:6,267" +
":6,126,267:15,-1:16,267,-1:2,267:15,126,267:7,-1:6,235:2,123,235:19,-1:16,2" +
"35,-1:2,235,123,235:21,-1:6,267:6,128,267:15,-1:16,267,-1:2,267:15,128,267:" +
"7,-1:6,235:4,125,235:17,-1:16,235,-1:2,235:5,125,235:17,-1:6,267:4,130,267:" +
"17,-1:16,267,-1:2,267:5,130,267:17,-1:6,235:7,191,235:14,-1:16,235,-1:2,235" +
":16,191,235:6,-1:6,267:3,132,267:18,-1:16,267,-1:2,267:18,132,267:4,-1:6,23" +
"5:19,222,235:2,-1:16,235,-1:2,235:10,222,235:12,-1:6,267:6,134,267:15,-1:16" +
",267,-1:2,267:15,134,267:7,-1:6,235:6,127,235:15,-1:16,235,-1:2,235:15,127," +
"235:7,-1:6,267,136,267:20,-1:16,267,-1:2,267:12,136,267:10,-1:6,235:11,193," +
"235:10,-1:16,235,-1:2,235:9,193,235:13,-1:6,267:20,138,267,-1:16,267,-1:2,2" +
"67:7,138,267:15,-1:6,235:13,195,235:8,-1:16,195,-1:2,235:23,-1:6,267:11,140" +
",267:10,-1:16,267,-1:2,267:9,140,267:13,-1:6,235:6,129,235:15,-1:16,235,-1:" +
"2,235:15,129,235:7,-1:6,267:9,142,267:12,-1:16,267,-1:2,267:14,142,267:8,-1" +
":6,235:3,131,235:18,-1:16,235,-1:2,235:18,131,235:4,-1:6,267:11,144,267:10," +
"-1:16,267,-1:2,267:9,144,267:13,-1:6,235:11,200,235:4,201,235:5,-1:16,235,-" +
"1:2,235:9,200,235:13,-1:6,267:5,146,267:16,-1:16,267,-1:2,267:19,146,267:3," +
"-1:6,235:6,133,235:15,-1:16,235,-1:2,235:15,133,235:7,-1:6,267:21,148,-1:16" +
",267,-1:2,267:13,148,267:9,-1:6,235:4,202,235:17,-1:16,235,-1:2,235:5,202,2" +
"35:17,-1:6,267:11,150,267:10,-1:16,267,-1:2,267:9,150,267:13,-1:6,235,135,2" +
"35:20,-1:16,235,-1:2,235:12,135,235:10,-1:6,267:3,152,267:18,-1:16,267,-1:2" +
",267:18,152,267:4,-1:6,235:6,203,235:15,-1:16,235,-1:2,235:15,203,235:7,-1:" +
"6,267:3,154,267:18,-1:16,267,-1:2,267:18,154,267:4,-1:6,235:3,205,235:18,-1" +
":16,235,-1:2,235:18,205,235:4,-1:6,267:3,156,267:18,-1:16,267,-1:2,267:18,1" +
"56,267:4,-1:6,235:20,137,235,-1:16,235,-1:2,235:7,137,235:15,-1:6,235:9,139" +
",235:12,-1:16,235,-1:2,235:14,139,235:8,-1:6,206,235:21,-1:16,235,-1:2,235:" +
"3,206,235:19,-1:6,235:11,141,235:10,-1:16,235,-1:2,235:9,141,235:13,-1:6,23" +
"5:11,143,235:10,-1:16,235,-1:2,235:9,143,235:13,-1:6,235:15,207,235:6,-1:16" +
",235,-1:2,235:17,207,235:5,-1:6,235:5,209,235:16,-1:16,235,-1:2,235:19,209," +
"235:3,-1:6,235:6,210,235:15,-1:16,235,-1:2,235:15,210,235:7,-1:6,235:11,212" +
",235:10,-1:16,235,-1:2,235:9,212,235:13,-1:6,213,235:21,-1:16,235,-1:2,235:" +
"3,213,235:19,-1:6,235:21,145,-1:16,235,-1:2,235:13,145,235:9,-1:6,235:9,214" +
",235:12,-1:16,235,-1:2,235:14,214,235:8,-1:6,235:11,147,235:10,-1:16,235,-1" +
":2,235:9,147,235:13,-1:6,235:5,149,235:16,-1:16,235,-1:2,235:19,149,235:3,-" +
"1:6,235:6,216,235:15,-1:16,235,-1:2,235:15,216,235:7,-1:6,235:3,151,235:18," +
"-1:16,235,-1:2,235:18,151,235:4,-1:6,235:3,153,235:18,-1:16,235,-1:2,235:18" +
",153,235:4,-1:6,235:9,217,235:12,-1:16,235,-1:2,235:14,217,235:8,-1:6,235:3" +
",155,235:18,-1:16,235,-1:2,235:18,155,235:4,-1:6,235:3,163,235:18,-1:16,235" +
",-1:2,235:18,163,235:4,-1:6,267:6,160,267:15,-1:16,267,-1:2,267:15,160,267:" +
"7,-1:6,235:15,189,235:6,-1:16,235,-1:2,235:17,189,235:5,-1:6,235:11,199,235" +
":10,-1:16,235,-1:2,235:9,199,235:13,-1:6,235:4,223,235:17,-1:16,235,-1:2,23" +
"5:5,223,235:17,-1:6,224,235:21,-1:16,235,-1:2,235:3,224,235:19,-1:6,235:5,2" +
"11,235:16,-1:16,235,-1:2,235:19,211,235:3,-1:6,235:9,215,235:12,-1:16,235,-" +
"1:2,235:14,215,235:8,-1:6,235:5,165,235:16,-1:16,235,-1:2,235:19,165,235:3," +
"-1:6,267,236,162,267:19,-1:16,267,-1:2,267,162,267:10,236,267:10,-1:6,235:4" +
",204,235:17,-1:16,235,-1:2,235:5,204,235:17,-1:6,235,167,235,169,235:11,220" +
",235:6,-1:16,235,-1:2,235:12,167,235:4,220,169,235:4,-1:6,267,164,267,166,2" +
"67:11,238,267:6,-1:16,267,-1:2,267:12,164,267:4,238,166,267:4,-1:6,235:8,17" +
"1,235:9,173,235:3,-1:16,235,-1:2,235:8,171,235:12,173,235,-1:6,267:6,170,26" +
"7:15,-1:16,267,-1:2,267:15,170,267:7,-1:6,235:6,177,235:15,-1:16,235,-1:2,2" +
"35:15,177,235:7,-1:6,267:8,172,267:9,258,267:3,-1:16,267,-1:2,267:8,172,267" +
":12,258,267,-1:6,267:2,174,267:19,-1:16,267,-1:2,267,174,267:21,-1:6,235:8," +
"179,235:13,-1:16,235,-1:2,235:8,179,235:14,-1:6,267:15,176,267:6,-1:16,267," +
"-1:2,267:17,176,267:5,-1:6,235:6,183,235:15,-1:16,235,-1:2,235:15,183,235:7" +
",-1:6,267:11,178,267:10,-1:16,267,-1:2,267:9,178,267:13,-1:6,235:2,185,235:" +
"19,-1:16,235,-1:2,235,185,235:21,-1:6,267:11,180,267:10,-1:16,267,-1:2,267:" +
"9,180,267:13,-1:6,267:6,182,267:15,-1:16,267,-1:2,267:15,182,267:7,-1:6,267" +
":11,184,267:4,270,267:5,-1:16,267,-1:2,267:9,184,267:13,-1:6,267:4,186,267:" +
"17,-1:16,267,-1:2,267:5,186,267:17,-1:6,267:11,188,267:10,-1:16,267,-1:2,26" +
"7:9,188,267:13,-1:6,267:5,190,267:16,-1:16,267,-1:2,267:19,190,267:3,-1:6,2" +
"67:5,192,267:16,-1:16,267,-1:2,267:19,192,267:3,-1:6,267:9,194,267:12,-1:16" +
",267,-1:2,267:14,194,267:8,-1:6,267:9,196,267:12,-1:16,267,-1:2,267:14,196," +
"267:8,-1:6,267:9,198,267:12,-1:16,267,-1:2,267:14,198,267:8,-1:6,267:8,240," +
"267:13,-1:16,267,-1:2,267:8,240,267:14,-1:6,235:6,225,235:15,-1:16,235,-1:2" +
",235:15,225,235:7,-1:6,267:3,242,267:18,-1:16,267,-1:2,267:18,242,267:4,-1:" +
"6,267:13,243,267:8,-1:16,243,-1:2,267:23,-1:6,267:19,269,267:2,-1:16,267,-1" +
":2,267:10,269,267:12,-1:6,267:15,244,267:6,-1:16,267,-1:2,267:17,244,267:5," +
"-1:6,267:7,245,267:14,-1:16,267,-1:2,267:16,245,267:6,-1:6,267:4,261,267:17" +
",-1:16,267,-1:2,267:5,261,267:17,-1:6,264,267:21,-1:16,267,-1:2,267:3,264,2" +
"67:19,-1:6,267:15,246,267:6,-1:16,267,-1:2,267:17,246,267:5,-1:6,267:3,247," +
"267:18,-1:16,267,-1:2,267:18,247,267:4,-1:6,248,267:21,-1:16,267,-1:2,267:3" +
",248,267:19,-1:6,267:6,249,267:15,-1:16,267,-1:2,267:15,249,267:7,-1:6,267:" +
"6,250,267:15,-1:16,267,-1:2,267:15,250,267:7,-1:6,267:6,251,267:15,-1:16,26" +
"7,-1:2,267:15,251,267:7,-1:6,253,235:21,-1:16,235,-1:2,235:3,253,235:19,-1:" +
"6,267:4,263,267:17,-1:16,267,-1:2,267:5,263,267:17,-1:6,265,267:21,-1:16,26" +
"7,-1:2,267:3,265,267:19,-1:6,267:5,257,267:16,-1:16,267,-1:2,267:19,257,267" +
":3,-1:6,266,267:21,-1:16,267,-1:2,267:3,266,267:19,-1");

	public Integer yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

    switch(yy_lexical_state) 
	{
		case YYINITIAL:
			break;
		case STRING:
		  eof_encountered=true;
		  System.out.println("EOF encountered in String.") ;
		  break ;
		case BLOCK_COMMENT:
		  eof_encountered=true;
		  System.out.println("EOF encountered in block comment.") ;
    }
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{System.out.println(yytext()) ;}
					case -3:
						break;
					case 3:
						{System.out.println(yytext()) ;}
					case -4:
						break;
					case 4:
						{System.out.println(yytext()) ;}
					case -5:
						break;
					case 5:
						{System.out.println(yytext()) ;}
					case -6:
						break;
					case 6:
						{System.out.println("An typeid :"+yytext());}
					case -7:
						break;
					case 7:
						{System.out.println("ERROR: Not recognized.") ;}
					case -8:
						break;
					case 8:
						{System.out.println(yytext()) ;}
					case -9:
						break;
					case 9:
						{System.out.println(yytext()) ;}
					case -10:
						break;
					case 10:
						{System.out.println(yytext()) ;}
					case -11:
						break;
					case 11:
						{System.out.println(yytext()) ;}
					case -12:
						break;
					case 12:
						{System.out.println(yytext()) ;}
					case -13:
						break;
					case 13:
						{System.out.println(yytext()) ;}
					case -14:
						break;
					case 14:
						{System.out.println(yytext()) ;}
					case -15:
						break;
					case 15:
						{System.out.println(yytext()) ;}
					case -16:
						break;
					case 16:
						{System.out.println(yytext()) ;}
					case -17:
						break;
					case 17:
						{System.out.println(yytext()) ;}
					case -18:
						break;
					case 18:
						{System.out.println(yytext()) ;}
					case -19:
						break;
					case 19:
						{System.out.println(yytext()) ;}
					case -20:
						break;
					case 20:
						{ /* Get rid of whitespace */ }
					case -21:
						break;
					case 21:
						{ }
					case -22:
						break;
					case 22:
						{ }
					case -23:
						break;
					case 23:
						{ }
					case -24:
						break;
					case 24:
						{ }
					case -25:
						break;
					case 25:
						{ }
					case -26:
						break;
					case 26:
						{System.out.println("This is a digit :"+yytext()) ;}
					case -27:
						break;
					case 27:
						{System.out.println("An objectid  :"+yytext()); }
					case -28:
						break;
					case 28:
						{ 
  string_buf.delete(0, string_buf.length());
  yybegin(STRING); 
  stringTooLong=false;
  nullInString=false;
  stringError=false;
  temp_skip = false ;
 }
					case -29:
						break;
					case 29:
						{System.out.println(yytext()) ;}
					case -30:
						break;
					case 30:
						{System.out.println(yytext()) ;}
					case -31:
						break;
					case 31:
						{ /* End of Line comment: Do nothing */ }
					case -32:
						break;
					case 32:
						{System.out.println(yytext()) ;}
					case -33:
						break;
					case 33:
						{System.out.println(yytext()) ;}
					case -34:
						break;
					case 34:
						{System.out.println(yytext()) ;}
					case -35:
						break;
					case 35:
						{System.out.println(yytext()) ;}
					case -36:
						break;
					case 36:
						{System.out.println(yytext()) ;}
					case -37:
						break;
					case 37:
						{ yybegin(BLOCK_COMMENT); }
					case -38:
						break;
					case 38:
						{System.out.println(yytext()) ;}
					case -39:
						break;
					case 39:
						{System.out.println(yytext()) ;}
					case -40:
						break;
					case 40:
						{System.out.println(yytext()) ;}
					case -41:
						break;
					case 41:
						{System.out.println(yytext()) ;}
					case -42:
						break;
					case 42:
						{System.out.println(yytext()) ;}
					case -43:
						break;
					case 43:
						{System.out.println(yytext()) ;}
					case -44:
						break;
					case 44:
						{System.out.println(yytext()) ;}
					case -45:
						break;
					case 45:
						{System.out.println(yytext()) ;}
					case -46:
						break;
					case 46:
						{System.out.println(yytext()) ;}
					case -47:
						break;
					case 47:
						{System.out.println(yytext()) ;}
					case -48:
						break;
					case 48:
						{System.out.println(yytext()) ;}
					case -49:
						break;
					case 49:
						{System.out.println(yytext()) ;}
					case -50:
						break;
					case 50:
						{System.out.println(yytext()) ;}
					case -51:
						break;
					case 51:
						{System.out.println(yytext()) ;}
					case -52:
						break;
					case 52:
						{System.out.println(yytext()) ;}
					case -53:
						break;
					case 53:
						{System.out.println(yytext()) ;}
					case -54:
						break;
					case 54:
						{System.out.println(yytext()) ;}
					case -55:
						break;
					case 55:
						{System.out.println(yytext()) ;}
					case -56:
						break;
					case 56:
						{System.out.println(yytext()) ;}
					case -57:
						break;
					case 57:
						{System.out.println(yytext()) ;}
					case -58:
						break;
					case 58:
						{System.out.println(yytext()) ;}
					case -59:
						break;
					case 59:
						{System.out.println(yytext()) ;}
					case -60:
						break;
					case 60:
						{System.out.println(yytext()) ;}
					case -61:
						break;
					case 61:
						{System.out.println(yytext()) ;}
					case -62:
						break;
					case 62:
						{System.out.println(yytext()) ;}
					case -63:
						break;
					case 63:
						{ /* Do Nothing */ }
					case -64:
						break;
					case 64:
						{ /* Do nothing */ }
					case -65:
						break;
					case 65:
						{ /* Do nothing */ }
					case -66:
						break;
					case 66:
						{  }
					case -67:
						break;
					case 67:
						{
  if(nestedCommentCount!=0) {
    nestedCommentCount--;
  } else {
    // yybegin(YYINITIAL);
	System.out.println("Comment completed!") ;
	nestedCommentCount = 0 ;
	yybegin(YYINITIAL) ;
  }
}
					case -68:
						break;
					case 68:
						{ nestedCommentCount++; }
					case -69:
						break;
					case 69:
						{ 
	// System.out.println("MEWATCHA") ;
  // Code for handling other characters encountered in strings
  // including special cases for escaped characters
  if(string_buf.length()+1>=MAX_STR_CONST) 
  {
    stringTooLong=true;
  }
  else if(string_buf.length()==0) 
  {
    string_buf.append(yytext());
  }
  else 
  {  
    // Check if null character appears in input stream
    if(yytext().charAt(0)=='\0' || nullInString) {
      nullInString=true;
    }
	else 
	{
      int length=string_buf.length();
      if(string_buf.charAt(string_buf.length()-1)=='\\') 
	  {
		  // System.out.println("yytext inside all= "+(int)yytext().charAt(0)) ;
		if(debug) System.out.println("temp_skip :"+this.temp_skip+" for :"+yytext().charAt(0)+" : "+(int)yytext().charAt(0)) ;  
		if(this.temp_skip)
		{
			string_buf.append(yytext()) ;
			if(debug) System.out.println("Skipped :"+(int)yytext().charAt(0)) ;
			this.temp_skip = false ;
		}
		else
		{
			switch(yytext().charAt(0)) 
			{
				case '\b':
				  string_buf.setCharAt(length-1, '\b');
				  break;
				case '\t':
				  string_buf.setCharAt(length-1, '\t');
				  break;
				case '\n':
				  string_buf.setCharAt(length-1, '\n');
				  break;
				case '\f':
				  string_buf.setCharAt(length-1, '\f');
				  break;
				case '\r':
				  string_buf.setCharAt(length-1,'\r');
				  break ;
				case '\\':
				  string_buf.setCharAt(length-1, '\\');
				  if(debug) System.out.println("temp_skip is made true.") ;
				  this.temp_skip = true ;
				  break;
				default:
				  string_buf.setCharAt(length-1,yytext().charAt(0)) ;
			}
		}
      } 
	  else 
	  {
		string_buf.append(yytext());
      }
    }
  }
 }
					case -70:
						break;
					case 70:
						{ // Code for newline characters
  // If a newline appears in a string, it must be escaped, else error
  if(string_buf.length()==0 || temp_skip|| (string_buf.charAt(string_buf.length()-1)!='\\' && string_buf.charAt(string_buf.length()-1)!='\r'))
  {
    curr_lineno++;
    // return new Symbol(TokenConstants.ERROR, "Unescaped Newline character in String: Line " + (curr_lineno-1));
	System.out.println("Unescaped Newline character in String: Line " + (curr_lineno-1)) ;
  }
  else 
  {
    // Replace '\' character in string buffer with newline
    string_buf.setCharAt(string_buf.length()-1, '\n');
  }
}
					case -71:
						break;
					case 71:
						{
	if(string_buf.length()==0 || string_buf.charAt(string_buf.length()-1)!='\\')
	{
		System.out.println("Unescaped linefeed character in string: line "+(curr_lineno-1)) ;
	}
	else
	{
		string_buf.setCharAt(string_buf.length()-1,'\r') ;
	}
}
					case -72:
						break;
					case 72:
						{ // If an escape character appears before a quote, we must put a quote into the string
  if(stringTooLong)
  {
    // return new Symbol(TokenConstants.ERROR, "String too long");
	System.out.println("String too long") ;
  }
  else if(nullInString)
  {
    // return new Symbol(TokenConstants.ERROR, "Null character appeared in string");
	// System.out.println()
	System.out.println("Null character appeared in string") ;
  }
  else if(stringError)
  {
    // return new Symbol(TokenConstants.ERROR, "Error occurred while parsing string");
	System.out.println("Error occurred while parsing string") ;
  }
  if(!temp_skip && string_buf.length()>0 && string_buf.charAt(string_buf.length()-1)=='\\') 
  {
    string_buf.setCharAt(string_buf.length()-1, '\"') ;
  } else 
  {
    yybegin(YYINITIAL); 
	System.out.println("String found :"+string_buf.toString()) ;
    // return new Symbol(TokenConstants.STR_CONST, AbstractTable.stringtable.addString(string_buf.toString())); 
  }
}
					case -73:
						break;
					case 74:
						{System.out.println("An typeid :"+yytext());}
					case -74:
						break;
					case 75:
						{System.out.println("An objectid  :"+yytext()); }
					case -75:
						break;
					case 76:
						{System.out.println(yytext()) ;}
					case -76:
						break;
					case 77:
						{System.out.println(yytext()) ;}
					case -77:
						break;
					case 78:
						{System.out.println(yytext()) ;}
					case -78:
						break;
					case 79:
						{System.out.println(yytext()) ;}
					case -79:
						break;
					case 80:
						{System.out.println(yytext()) ;}
					case -80:
						break;
					case 81:
						{System.out.println(yytext()) ;}
					case -81:
						break;
					case 82:
						{System.out.println(yytext()) ;}
					case -82:
						break;
					case 83:
						{System.out.println(yytext()) ;}
					case -83:
						break;
					case 84:
						{System.out.println(yytext()) ;}
					case -84:
						break;
					case 85:
						{System.out.println(yytext()) ;}
					case -85:
						break;
					case 86:
						{System.out.println(yytext()) ;}
					case -86:
						break;
					case 87:
						{System.out.println(yytext()) ;}
					case -87:
						break;
					case 88:
						{System.out.println(yytext()) ;}
					case -88:
						break;
					case 89:
						{System.out.println(yytext()) ;}
					case -89:
						break;
					case 90:
						{System.out.println(yytext()) ;}
					case -90:
						break;
					case 91:
						{System.out.println(yytext()) ;}
					case -91:
						break;
					case 92:
						{System.out.println(yytext()) ;}
					case -92:
						break;
					case 93:
						{System.out.println(yytext()) ;}
					case -93:
						break;
					case 94:
						{System.out.println(yytext()) ;}
					case -94:
						break;
					case 95:
						{System.out.println(yytext()) ;}
					case -95:
						break;
					case 96:
						{System.out.println(yytext()) ;}
					case -96:
						break;
					case 97:
						{System.out.println(yytext()) ;}
					case -97:
						break;
					case 98:
						{System.out.println(yytext()) ;}
					case -98:
						break;
					case 99:
						{System.out.println(yytext()) ;}
					case -99:
						break;
					case 100:
						{System.out.println(yytext()) ;}
					case -100:
						break;
					case 101:
						{System.out.println(yytext()) ;}
					case -101:
						break;
					case 102:
						{System.out.println(yytext()) ;}
					case -102:
						break;
					case 103:
						{System.out.println(yytext()) ;}
					case -103:
						break;
					case 104:
						{System.out.println(yytext()) ;}
					case -104:
						break;
					case 105:
						{ /* Do Nothing */ }
					case -105:
						break;
					case 107:
						{System.out.println("An typeid :"+yytext());}
					case -106:
						break;
					case 108:
						{System.out.println("An objectid  :"+yytext()); }
					case -107:
						break;
					case 109:
						{System.out.println("An typeid :"+yytext());}
					case -108:
						break;
					case 110:
						{System.out.println("An objectid  :"+yytext()); }
					case -109:
						break;
					case 111:
						{System.out.println("An typeid :"+yytext());}
					case -110:
						break;
					case 112:
						{System.out.println("An objectid  :"+yytext()); }
					case -111:
						break;
					case 113:
						{System.out.println("An typeid :"+yytext());}
					case -112:
						break;
					case 114:
						{System.out.println("An objectid  :"+yytext()); }
					case -113:
						break;
					case 115:
						{System.out.println("An typeid :"+yytext());}
					case -114:
						break;
					case 116:
						{System.out.println("An objectid  :"+yytext()); }
					case -115:
						break;
					case 117:
						{System.out.println("An typeid :"+yytext());}
					case -116:
						break;
					case 118:
						{System.out.println("An objectid  :"+yytext()); }
					case -117:
						break;
					case 119:
						{System.out.println("An typeid :"+yytext());}
					case -118:
						break;
					case 120:
						{System.out.println("An objectid  :"+yytext()); }
					case -119:
						break;
					case 121:
						{System.out.println("An typeid :"+yytext());}
					case -120:
						break;
					case 122:
						{System.out.println("An objectid  :"+yytext()); }
					case -121:
						break;
					case 123:
						{System.out.println("An typeid :"+yytext());}
					case -122:
						break;
					case 124:
						{System.out.println("An objectid  :"+yytext()); }
					case -123:
						break;
					case 125:
						{System.out.println("An typeid :"+yytext());}
					case -124:
						break;
					case 126:
						{System.out.println("An objectid  :"+yytext()); }
					case -125:
						break;
					case 127:
						{System.out.println("An typeid :"+yytext());}
					case -126:
						break;
					case 128:
						{System.out.println("An objectid  :"+yytext()); }
					case -127:
						break;
					case 129:
						{System.out.println("An typeid :"+yytext());}
					case -128:
						break;
					case 130:
						{System.out.println("An objectid  :"+yytext()); }
					case -129:
						break;
					case 131:
						{System.out.println("An typeid :"+yytext());}
					case -130:
						break;
					case 132:
						{System.out.println("An objectid  :"+yytext()); }
					case -131:
						break;
					case 133:
						{System.out.println("An typeid :"+yytext());}
					case -132:
						break;
					case 134:
						{System.out.println("An objectid  :"+yytext()); }
					case -133:
						break;
					case 135:
						{System.out.println("An typeid :"+yytext());}
					case -134:
						break;
					case 136:
						{System.out.println("An objectid  :"+yytext()); }
					case -135:
						break;
					case 137:
						{System.out.println("An typeid :"+yytext());}
					case -136:
						break;
					case 138:
						{System.out.println("An objectid  :"+yytext()); }
					case -137:
						break;
					case 139:
						{System.out.println("An typeid :"+yytext());}
					case -138:
						break;
					case 140:
						{System.out.println("An objectid  :"+yytext()); }
					case -139:
						break;
					case 141:
						{System.out.println("An typeid :"+yytext());}
					case -140:
						break;
					case 142:
						{System.out.println("An objectid  :"+yytext()); }
					case -141:
						break;
					case 143:
						{System.out.println("An typeid :"+yytext());}
					case -142:
						break;
					case 144:
						{System.out.println("An objectid  :"+yytext()); }
					case -143:
						break;
					case 145:
						{System.out.println("An typeid :"+yytext());}
					case -144:
						break;
					case 146:
						{System.out.println("An objectid  :"+yytext()); }
					case -145:
						break;
					case 147:
						{System.out.println("An typeid :"+yytext());}
					case -146:
						break;
					case 148:
						{System.out.println("An objectid  :"+yytext()); }
					case -147:
						break;
					case 149:
						{System.out.println("An typeid :"+yytext());}
					case -148:
						break;
					case 150:
						{System.out.println("An objectid  :"+yytext()); }
					case -149:
						break;
					case 151:
						{System.out.println("An typeid :"+yytext());}
					case -150:
						break;
					case 152:
						{System.out.println("An objectid  :"+yytext()); }
					case -151:
						break;
					case 153:
						{System.out.println("An typeid :"+yytext());}
					case -152:
						break;
					case 154:
						{System.out.println("An objectid  :"+yytext()); }
					case -153:
						break;
					case 155:
						{System.out.println("An typeid :"+yytext());}
					case -154:
						break;
					case 156:
						{System.out.println("An objectid  :"+yytext()); }
					case -155:
						break;
					case 157:
						{System.out.println("An typeid :"+yytext());}
					case -156:
						break;
					case 158:
						{System.out.println("An objectid  :"+yytext()); }
					case -157:
						break;
					case 159:
						{System.out.println("An typeid :"+yytext());}
					case -158:
						break;
					case 160:
						{System.out.println("An objectid  :"+yytext()); }
					case -159:
						break;
					case 161:
						{System.out.println("An typeid :"+yytext());}
					case -160:
						break;
					case 162:
						{System.out.println("An objectid  :"+yytext()); }
					case -161:
						break;
					case 163:
						{System.out.println("An typeid :"+yytext());}
					case -162:
						break;
					case 164:
						{System.out.println("An objectid  :"+yytext()); }
					case -163:
						break;
					case 165:
						{System.out.println("An typeid :"+yytext());}
					case -164:
						break;
					case 166:
						{System.out.println("An objectid  :"+yytext()); }
					case -165:
						break;
					case 167:
						{System.out.println("An typeid :"+yytext());}
					case -166:
						break;
					case 168:
						{System.out.println("An objectid  :"+yytext()); }
					case -167:
						break;
					case 169:
						{System.out.println("An typeid :"+yytext());}
					case -168:
						break;
					case 170:
						{System.out.println("An objectid  :"+yytext()); }
					case -169:
						break;
					case 171:
						{System.out.println("An typeid :"+yytext());}
					case -170:
						break;
					case 172:
						{System.out.println("An objectid  :"+yytext()); }
					case -171:
						break;
					case 173:
						{System.out.println("An typeid :"+yytext());}
					case -172:
						break;
					case 174:
						{System.out.println("An objectid  :"+yytext()); }
					case -173:
						break;
					case 175:
						{System.out.println("An typeid :"+yytext());}
					case -174:
						break;
					case 176:
						{System.out.println("An objectid  :"+yytext()); }
					case -175:
						break;
					case 177:
						{System.out.println("An typeid :"+yytext());}
					case -176:
						break;
					case 178:
						{System.out.println("An objectid  :"+yytext()); }
					case -177:
						break;
					case 179:
						{System.out.println("An typeid :"+yytext());}
					case -178:
						break;
					case 180:
						{System.out.println("An objectid  :"+yytext()); }
					case -179:
						break;
					case 181:
						{System.out.println("An typeid :"+yytext());}
					case -180:
						break;
					case 182:
						{System.out.println("An objectid  :"+yytext()); }
					case -181:
						break;
					case 183:
						{System.out.println("An typeid :"+yytext());}
					case -182:
						break;
					case 184:
						{System.out.println("An objectid  :"+yytext()); }
					case -183:
						break;
					case 185:
						{System.out.println("An typeid :"+yytext());}
					case -184:
						break;
					case 186:
						{System.out.println("An objectid  :"+yytext()); }
					case -185:
						break;
					case 187:
						{System.out.println("An typeid :"+yytext());}
					case -186:
						break;
					case 188:
						{System.out.println("An objectid  :"+yytext()); }
					case -187:
						break;
					case 189:
						{System.out.println("An typeid :"+yytext());}
					case -188:
						break;
					case 190:
						{System.out.println("An objectid  :"+yytext()); }
					case -189:
						break;
					case 191:
						{System.out.println("An typeid :"+yytext());}
					case -190:
						break;
					case 192:
						{System.out.println("An objectid  :"+yytext()); }
					case -191:
						break;
					case 193:
						{System.out.println("An typeid :"+yytext());}
					case -192:
						break;
					case 194:
						{System.out.println("An objectid  :"+yytext()); }
					case -193:
						break;
					case 195:
						{System.out.println("An typeid :"+yytext());}
					case -194:
						break;
					case 196:
						{System.out.println("An objectid  :"+yytext()); }
					case -195:
						break;
					case 197:
						{System.out.println("An typeid :"+yytext());}
					case -196:
						break;
					case 198:
						{System.out.println("An objectid  :"+yytext()); }
					case -197:
						break;
					case 199:
						{System.out.println("An typeid :"+yytext());}
					case -198:
						break;
					case 200:
						{System.out.println("An typeid :"+yytext());}
					case -199:
						break;
					case 201:
						{System.out.println("An typeid :"+yytext());}
					case -200:
						break;
					case 202:
						{System.out.println("An typeid :"+yytext());}
					case -201:
						break;
					case 203:
						{System.out.println("An typeid :"+yytext());}
					case -202:
						break;
					case 204:
						{System.out.println("An typeid :"+yytext());}
					case -203:
						break;
					case 205:
						{System.out.println("An typeid :"+yytext());}
					case -204:
						break;
					case 206:
						{System.out.println("An typeid :"+yytext());}
					case -205:
						break;
					case 207:
						{System.out.println("An typeid :"+yytext());}
					case -206:
						break;
					case 208:
						{System.out.println("An typeid :"+yytext());}
					case -207:
						break;
					case 209:
						{System.out.println("An typeid :"+yytext());}
					case -208:
						break;
					case 210:
						{System.out.println("An typeid :"+yytext());}
					case -209:
						break;
					case 211:
						{System.out.println("An typeid :"+yytext());}
					case -210:
						break;
					case 212:
						{System.out.println("An typeid :"+yytext());}
					case -211:
						break;
					case 213:
						{System.out.println("An typeid :"+yytext());}
					case -212:
						break;
					case 214:
						{System.out.println("An typeid :"+yytext());}
					case -213:
						break;
					case 215:
						{System.out.println("An typeid :"+yytext());}
					case -214:
						break;
					case 216:
						{System.out.println("An typeid :"+yytext());}
					case -215:
						break;
					case 217:
						{System.out.println("An typeid :"+yytext());}
					case -216:
						break;
					case 218:
						{System.out.println("An typeid :"+yytext());}
					case -217:
						break;
					case 219:
						{System.out.println("An objectid  :"+yytext()); }
					case -218:
						break;
					case 220:
						{System.out.println("An typeid :"+yytext());}
					case -219:
						break;
					case 221:
						{System.out.println("An typeid :"+yytext());}
					case -220:
						break;
					case 222:
						{System.out.println("An typeid :"+yytext());}
					case -221:
						break;
					case 223:
						{System.out.println("An typeid :"+yytext());}
					case -222:
						break;
					case 224:
						{System.out.println("An typeid :"+yytext());}
					case -223:
						break;
					case 225:
						{System.out.println("An typeid :"+yytext());}
					case -224:
						break;
					case 226:
						{System.out.println("An typeid :"+yytext());}
					case -225:
						break;
					case 227:
						{System.out.println("An objectid  :"+yytext()); }
					case -226:
						break;
					case 228:
						{System.out.println("An typeid :"+yytext());}
					case -227:
						break;
					case 229:
						{System.out.println("An typeid :"+yytext());}
					case -228:
						break;
					case 230:
						{System.out.println("An objectid  :"+yytext()); }
					case -229:
						break;
					case 231:
						{System.out.println("An typeid :"+yytext());}
					case -230:
						break;
					case 232:
						{System.out.println("An objectid  :"+yytext()); }
					case -231:
						break;
					case 233:
						{System.out.println("An typeid :"+yytext());}
					case -232:
						break;
					case 234:
						{System.out.println("An objectid  :"+yytext()); }
					case -233:
						break;
					case 235:
						{System.out.println("An typeid :"+yytext());}
					case -234:
						break;
					case 236:
						{System.out.println("An objectid  :"+yytext()); }
					case -235:
						break;
					case 237:
						{System.out.println("An typeid :"+yytext());}
					case -236:
						break;
					case 238:
						{System.out.println("An objectid  :"+yytext()); }
					case -237:
						break;
					case 239:
						{System.out.println("An typeid :"+yytext());}
					case -238:
						break;
					case 240:
						{System.out.println("An objectid  :"+yytext()); }
					case -239:
						break;
					case 241:
						{System.out.println("An typeid :"+yytext());}
					case -240:
						break;
					case 242:
						{System.out.println("An objectid  :"+yytext()); }
					case -241:
						break;
					case 243:
						{System.out.println("An objectid  :"+yytext()); }
					case -242:
						break;
					case 244:
						{System.out.println("An objectid  :"+yytext()); }
					case -243:
						break;
					case 245:
						{System.out.println("An objectid  :"+yytext()); }
					case -244:
						break;
					case 246:
						{System.out.println("An objectid  :"+yytext()); }
					case -245:
						break;
					case 247:
						{System.out.println("An objectid  :"+yytext()); }
					case -246:
						break;
					case 248:
						{System.out.println("An objectid  :"+yytext()); }
					case -247:
						break;
					case 249:
						{System.out.println("An objectid  :"+yytext()); }
					case -248:
						break;
					case 250:
						{System.out.println("An objectid  :"+yytext()); }
					case -249:
						break;
					case 251:
						{System.out.println("An objectid  :"+yytext()); }
					case -250:
						break;
					case 252:
						{System.out.println("An objectid  :"+yytext()); }
					case -251:
						break;
					case 253:
						{System.out.println("An typeid :"+yytext());}
					case -252:
						break;
					case 254:
						{System.out.println("An objectid  :"+yytext()); }
					case -253:
						break;
					case 255:
						{System.out.println("An objectid  :"+yytext()); }
					case -254:
						break;
					case 256:
						{System.out.println("An objectid  :"+yytext()); }
					case -255:
						break;
					case 257:
						{System.out.println("An objectid  :"+yytext()); }
					case -256:
						break;
					case 258:
						{System.out.println("An objectid  :"+yytext()); }
					case -257:
						break;
					case 259:
						{System.out.println("An objectid  :"+yytext()); }
					case -258:
						break;
					case 260:
						{System.out.println("An objectid  :"+yytext()); }
					case -259:
						break;
					case 261:
						{System.out.println("An objectid  :"+yytext()); }
					case -260:
						break;
					case 262:
						{System.out.println("An objectid  :"+yytext()); }
					case -261:
						break;
					case 263:
						{System.out.println("An objectid  :"+yytext()); }
					case -262:
						break;
					case 264:
						{System.out.println("An objectid  :"+yytext()); }
					case -263:
						break;
					case 265:
						{System.out.println("An objectid  :"+yytext()); }
					case -264:
						break;
					case 266:
						{System.out.println("An objectid  :"+yytext()); }
					case -265:
						break;
					case 267:
						{System.out.println("An objectid  :"+yytext()); }
					case -266:
						break;
					case 268:
						{System.out.println("An typeid :"+yytext());}
					case -267:
						break;
					case 269:
						{System.out.println("An objectid  :"+yytext()); }
					case -268:
						break;
					case 270:
						{System.out.println("An objectid  :"+yytext()); }
					case -269:
						break;
					case 271:
						{System.out.println("An objectid  :"+yytext()); }
					case -270:
						break;
					case 272:
						{System.out.println("An objectid  :"+yytext()); }
					case -271:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
