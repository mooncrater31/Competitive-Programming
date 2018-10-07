/*
 *  The scanner definition for COOL.
 */
import java_cup.runtime.Symbol;


class CoolLexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

/*  Stuff enclosed in %{ %} is copied verbatim to the lexer class
 *  definition, all the extra variables/functions you want to use in the
 *  lexer actions should go here.  Don't remove or modify anything that
 *  was there initially.  */
    // Integer for counting nested comments
    int nestedCommentCount=0;
    // Boolean value for determining if string is too long
    boolean stringTooLong=false;
    // Boolean value for determining if null character occurs in string
    boolean nullInString=false;
    // Boolean value for some error occuring in string
    boolean stringError=false;
    // Boolean for if an EOF is encountered
    boolean eof_encountered=false;
    // Max size of string constants
    static int MAX_STR_CONST = 1025;
    // For assembling string constants
    StringBuffer string_buf = new StringBuffer();
    private int curr_lineno = 1;
    int get_curr_lineno() {
	return curr_lineno;
    }
	// for special purposes
	boolean skip = false ;
    private AbstractSymbol filename;
    void set_filename(String fname) {
	filename = AbstractTable.stringtable.addString(fname);
    }
    AbstractSymbol curr_filename() {
	return filename;
    }
	public interface Scanner 
	{
		public Symbol next_token() throws java.lang.Exception;
	}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	CoolLexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	CoolLexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private CoolLexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

/*  Stuff enclosed in %init{ %init} is copied verbatim to the lexer
 *  class constructor, all the extra initialization you want to do should
 *  go here.  Don't remove or modify anything that was there initially. */
    // empty for now
	}

	private boolean yy_eof_done = false;
	private final int STRING = 1;
	private final int BLOCK_COMMENT = 2;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0,
		52,
		83
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
		/* 0 */ YY_NO_ANCHOR,
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
		/* 52 */ YY_NOT_ACCEPT,
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
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
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
		/* 106 */ YY_NO_ANCHOR,
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
		/* 172 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"38:9,40,4,42,38,8,38:18,39,38,3,38:5,6,5,7,31,29,27,32,30,62:10,33,28,26,1," +
"2,38,36,14,61,17,23,12,13,61,20,18,61:2,15,61,19,22,24,61,10,16,9,11,21,25," +
"61:3,38:4,60,38,43,44,45,46,47,48,44,49,50,44:2,51,44,52,53,54,44,55,56,57," +
"58,41,59,44:3,34,38,35,37,38,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,173,
"0,1,2,1:4,3,1:2,4,5,6,1:12,7,1,7,1:2,8:2,9,8,1:2,10,8:15,11,1:5,12,1:2,13,1" +
"4,15,7:2,16,7:16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,3" +
"6,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,6" +
"1,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,8" +
"6,87,88,8,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105")[0];

	private int yy_nxt[][] = unpackFromString(106,63,
"1,2,3,4,5,6,7,8,9,10,155:2,157,62,155,119,155,159,85,121,155:2,87,155,161,1" +
"63,11,12,13,14,15,16,17,18,19,20,21,22,3,23,24,25,26,27:2,156,27,158,63,27," +
"86,120,122,88,160,27:2,162,27,169,3,155,61,-1:65,28,-1:67,29,-1:64,155,123," +
"155:9,125,155:5,-1:15,155,-1,155:6,125,155:5,123,155:7,-1,34,-1:25,35,-1:62" +
",36,-1:44,27:17,-1:15,27,-1,27:20,-1:9,155:17,-1:15,155,-1,155:20,-1:9,155:" +
"11,149,155:5,-1:15,155,-1,155:6,149,155:13,-1,36:3,-1,36:3,-1,36:54,1,53:2," +
"54,55,53:58,-1:5,60,-1:119,61,-1:9,155:5,131,155:3,30,155:7,-1:15,155,-1,13" +
"1,155:6,30,155:12,-1:9,27:5,165,27:3,64,27:7,-1:15,27,-1,165,27:6,64,27:12," +
"-1:9,27:11,172,27:5,-1:15,27,-1,27:6,172,27:13,1,56:3,57,3,84,58,56:55,-1:7" +
",59,-1:64,155:4,31,155:2,139,155:2,32,155:6,-1:15,155,-1,155:5,31,155:3,32," +
"155:3,139,155:6,-1:9,27:4,65,27:2,170,27:2,66,27:6,-1:15,27,-1,27:5,65,27:3" +
",66,27:3,170,27:6,-1:9,155:4,33,155:12,-1:15,155,-1,155:5,33,155:14,-1:9,27" +
":4,67,27:12,-1:15,27,-1,27:5,67,27:14,-1:9,37,155:16,-1:15,155,-1,155:14,37" +
",155:5,-1:9,68,27:16,-1:15,27,-1,27:14,68,27:5,-1:9,155:16,38,-1:15,155,-1," +
"155:16,38,155:3,-1:9,27:16,69,-1:15,27,-1,27:16,69,27:3,-1:9,39,155:16,-1:1" +
"5,155,-1,155:14,39,155:5,-1:9,70,27:16,-1:15,27,-1,27:14,70,27:5,-1:9,155:3" +
",40,155:13,-1:15,155,-1,155:4,40,155:15,-1:9,27:3,76,27:13,-1:15,27,-1,27:4" +
",76,27:15,-1:9,155:10,41,155:6,-1:15,155,-1,155:9,41,155:10,-1:9,27:3,73,27" +
":13,-1:15,27,-1,27:4,73,27:15,-1:9,155:3,42,155:13,-1:15,155,-1,155:4,42,15" +
"5:15,-1:9,27:8,74,27:8,-1:15,27,-1,27:2,74,27:17,-1:9,155:8,43,155:8,-1:15," +
"155,-1,155:2,43,155:17,-1:9,27:15,75,27,-1:15,27,-1,27:11,75,27:8,-1:9,155:" +
"15,44,155,-1:15,155,-1,155:11,44,155:8,-1:9,27:6,77,27:10,-1:15,27,-1,27:8," +
"77,27:11,-1:9,155:3,45,155:13,-1:15,155,-1,155:4,45,155:15,-1:9,27:3,71,27:" +
"13,-1:15,27,-1,27:4,71,27:15,-1:9,155:6,46,155:10,-1:15,155,-1,155:8,46,155" +
":11,-1:9,27:10,72,27:6,-1:15,27,-1,27:9,72,27:10,-1:9,155:3,47,155:13,-1:15" +
",155,-1,155:4,47,155:15,-1:9,27:7,79,27:9,-1:15,27,-1,27:13,79,27:6,-1:9,15" +
"5:7,48,155:9,-1:15,155,-1,155:13,48,155:6,-1:9,27:3,78,27:13,-1:15,27,-1,27" +
":4,78,27:15,-1:9,155:3,49,155:13,-1:15,155,-1,155:4,49,155:15,-1:9,27:3,80," +
"27:13,-1:15,27,-1,27:4,80,27:15,-1:9,155:14,50,155:2,-1:15,155,-1,155:3,50," +
"155:16,-1:9,27:14,81,27:2,-1:15,27,-1,27:3,81,27:16,-1:9,155:7,51,155:9,-1:" +
"15,155,-1,155:13,51,155:6,-1:9,27:7,82,27:9,-1:15,27,-1,27:13,82,27:6,-1:9," +
"155:3,89,155:9,133,155:3,-1:15,155,-1,155:4,89,155:5,133,155:9,-1:9,27:3,90" +
",27:9,130,27:3,-1:15,27,-1,27:4,90,27:5,130,27:9,-1:9,155:3,91,155:9,93,155" +
":3,-1:15,155,-1,155:4,91,155:5,93,155:9,-1:9,27:3,92,27:9,94,27:3,-1:15,27," +
"-1,27:4,92,27:5,94,27:9,-1:9,155:2,95,155:14,-1:15,155,-1,155:15,95,155:4,-" +
"1:9,27:7,96,27:9,-1:15,27,-1,27:13,96,27:6,-1:9,155:3,97,155:13,-1:15,155,-" +
"1,155:4,97,155:15,-1:9,27:7,98,27:9,-1:15,27,-1,27:13,98,27:6,-1:9,155:7,99" +
",155:9,-1:15,155,-1,155:13,99,155:6,-1:9,27:5,100,27:11,-1:15,27,-1,100,27:" +
"19,-1:9,155:5,101,155:11,-1:15,155,-1,101,155:19,-1:9,27:13,102,27:3,-1:15," +
"27,-1,27:10,102,27:9,-1:9,155:6,145,155:10,-1:15,155,-1,155:8,145,155:11,-1" +
":9,27:13,104,27:3,-1:15,27,-1,27:10,104,27:9,-1:9,155:13,103,155:3,-1:15,15" +
"5,-1,155:10,103,155:9,-1:9,27:2,106,27:14,-1:15,27,-1,27:15,106,27:4,-1:9,1" +
"55:7,105,155:9,-1:15,155,-1,155:13,105,155:6,-1:9,27:3,108,27:13,-1:15,27,-" +
"1,27:4,108,27:15,-1:9,155:5,147,155:11,-1:15,155,-1,147,155:19,-1:9,27:7,11" +
"0,27:9,-1:15,27,-1,27:13,110,27:6,-1:9,155:12,148,155:4,-1:15,148,-1,155:20" +
",-1:9,27:7,112,27:9,-1:15,27,-1,27:13,112,27:6,-1:9,155:13,107,155:3,-1:15," +
"155,-1,155:10,107,155:9,-1:9,27:6,114,27:10,-1:15,27,-1,27:8,114,27:11,-1:9" +
",155:9,150,155:7,-1:15,155,-1,155:7,150,155:12,-1:9,27:9,116,27:7,-1:15,27," +
"-1,27:7,116,27:12,-1:9,155:7,109,155:9,-1:15,155,-1,155:13,109,155:6,-1:9,1" +
"18,27:16,-1:15,27,-1,27:14,118,27:5,-1:9,155:7,111,155:9,-1:15,155,-1,155:1" +
"3,111,155:6,-1:9,155:13,151,155:3,-1:15,155,-1,155:10,151,155:9,-1:9,155:3," +
"152,155:13,-1:15,155,-1,155:4,152,155:15,-1:9,155:6,113,155:10,-1:15,155,-1" +
",155:8,113,155:11,-1:9,155:9,115,155:7,-1:15,155,-1,155:7,115,155:12,-1:9,1" +
"55,153,155:15,-1:15,155,-1,155:12,153,155:7,-1:9,155:9,154,155:7,-1:15,155," +
"-1,155:7,154,155:12,-1:9,117,155:16,-1:15,155,-1,155:14,117,155:5,-1:9,27:5" +
",124,164,27:10,-1:15,27,-1,124,27:7,164,27:11,-1:9,155:6,127,129,155:9,-1:1" +
"5,155,-1,155:8,127,155:4,129,155:6,-1:9,27:6,126,128,27:9,-1:15,27,-1,27:8," +
"126,27:4,128,27:6,-1:9,155:5,135,137,155:10,-1:15,155,-1,135,155:7,137,155:" +
"11,-1:9,27:13,132,27:3,-1:15,27,-1,27:10,132,27:9,-1:9,155:13,141,155:3,-1:" +
"15,155,-1,155:10,141,155:9,-1:9,27,134,27:9,136,27:5,-1:15,27,-1,27:6,136,2" +
"7:5,134,27:7,-1:9,155:11,143,155:5,-1:15,155,-1,155:6,143,155:13,-1:9,27:5," +
"138,27:11,-1:15,27,-1,138,27:19,-1:9,27:6,140,27:10,-1:15,27,-1,27:8,140,27" +
":11,-1:9,27:9,142,27:7,-1:15,27,-1,27:7,142,27:12,-1:9,27:13,144,27:3,-1:15" +
",27,-1,27:10,144,27:9,-1:9,27:9,146,27:7,-1:15,27,-1,27:7,146,27:12,-1:9,27" +
":11,166,27:5,-1:15,27,-1,27:6,166,27:13,-1:9,27:12,167,27:4,-1:15,167,-1,27" +
":20,-1:9,27,168,27:15,-1:15,27,-1,27:12,168,27:7,-1:9,27:3,171,27:13,-1:15," +
"27,-1,27:4,171,27:15");

	public java_cup.runtime.Symbol next_token ()
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

/*  Stuff enclosed in %eofval{ %eofval} specifies java code that is
 *  executed when end-of-file is reached.  If you use multiple lexical
 *  states and want to do something special if an EOF is encountered in
 *  one of those states, place your code in the switch statement.
 *  Ultimately, you should return the EOF symbol, or your lexer won't
 *  work.  */
    if(eof_encountered) return new Symbol(TokenConstants.EOF);
    switch(yy_lexical_state) 
	{
		case YYINITIAL:
	/* nothing special to do in the initial state */
			break;
	/* If necessary, add code for other states here, e.g:
	   case COMMENT:
	   ...
	   break;
	*/
		case STRING:
		  eof_encountered=true;
		  return new Symbol(TokenConstants.ERROR, "EOF in string constant");
		case BLOCK_COMMENT:
		  eof_encountered=true;
		  return new Symbol(TokenConstants.ERROR, "EOF in comment");
    }
    return new Symbol(TokenConstants.EOF);
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
					case 0:
						{ return new Symbol(TokenConstants.INT_CONST, AbstractTable.inttable.addString(yytext())); }
					case -2:
						break;
					case 1:
						
					case -3:
						break;
					case 2:
						{ return new Symbol(TokenConstants.EQ); }
					case -4:
						break;
					case 3:
						{ /* This rule should be the very last
                                     in your lexical specification and
                                     will match match everything not
                                     matched by other lexical rules. */
                                  System.err.println("LEXER BUG - UNMATCHED: " + yytext()); 
                                  return new Symbol(TokenConstants.ERROR, yytext()); }
					case -5:
						break;
					case 4:
						{ 
  string_buf.delete(0, string_buf.length());
  yybegin(STRING); 
  stringTooLong=false;
  nullInString=false;
  stringError=false;
 }
					case -6:
						break;
					case 5:
						{ }
					case -7:
						break;
					case 6:
						{ return new Symbol(TokenConstants.RPAREN); }
					case -8:
						break;
					case 7:
						{ return new Symbol(TokenConstants.LPAREN); }
					case -9:
						break;
					case 8:
						{ return new Symbol(TokenConstants.MULT);   }
					case -10:
						break;
					case 9:
						{ }
					case -11:
						break;
					case 10:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -12:
						break;
					case 11:
						{ return new Symbol(TokenConstants.LT);     }
					case -13:
						break;
					case 12:
						{ return new Symbol(TokenConstants.MINUS);  }
					case -14:
						break;
					case 13:
						{ return new Symbol(TokenConstants.SEMI);   }
					case -15:
						break;
					case 14:
						{ return new Symbol(TokenConstants.COMMA);  }
					case -16:
						break;
					case 15:
						{ return new Symbol(TokenConstants.DIV);    }
					case -17:
						break;
					case 16:
						{ return new Symbol(TokenConstants.PLUS);   }
					case -18:
						break;
					case 17:
						{ return new Symbol(TokenConstants.DOT);    }
					case -19:
						break;
					case 18:
						{ return new Symbol(TokenConstants.COLON);  }
					case -20:
						break;
					case 19:
						{ return new Symbol(TokenConstants.LBRACE); }
					case -21:
						break;
					case 20:
						{ return new Symbol(TokenConstants.RBRACE); }
					case -22:
						break;
					case 21:
						{ return new Symbol(TokenConstants.AT);     }
					case -23:
						break;
					case 22:
						{ return new Symbol(TokenConstants.NEG);    }
					case -24:
						break;
					case 23:
						{ /* Get rid of whitespace */ }
					case -25:
						break;
					case 24:
						{ }
					case -26:
						break;
					case 25:
						{ }
					case -27:
						break;
					case 26:
						{ }
					case -28:
						break;
					case 27:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -29:
						break;
					case 28:
						{ /* Sample lexical rule for "=>" arrow.
                                     Further lexical rules should be defined
                                     here, after the last %% separator */
                                  return new Symbol(TokenConstants.DARROW); }
					case -30:
						break;
					case 29:
						{ yybegin(BLOCK_COMMENT); }
					case -31:
						break;
					case 30:
						{ return new Symbol(TokenConstants.FI); }
					case -32:
						break;
					case 31:
						{ return new Symbol(TokenConstants.IF); }
					case -33:
						break;
					case 32:
						{ return new Symbol(TokenConstants.IN); }
					case -34:
						break;
					case 33:
						{ return new Symbol(TokenConstants.OF); }
					case -35:
						break;
					case 34:
						{ return new Symbol(TokenConstants.LE);     }
					case -36:
						break;
					case 35:
						{ return new Symbol(TokenConstants.ASSIGN); }
					case -37:
						break;
					case 36:
						{ /* End of Line comment: Do nothing */ }
					case -38:
						break;
					case 37:
						{ return new Symbol(TokenConstants.LET); }
					case -39:
						break;
					case 38:
						{ return new Symbol(TokenConstants.NEW); }
					case -40:
						break;
					case 39:
						{ return new Symbol(TokenConstants.NOT); }
					case -41:
						break;
					case 40:
						{ return new Symbol(TokenConstants.BOOL_CONST, new Boolean(true)); }
					case -42:
						break;
					case 41:
						{ return new Symbol(TokenConstants.THEN); }
					case -43:
						break;
					case 42:
						{ return new Symbol(TokenConstants.ELSE); }
					case -44:
						break;
					case 43:
						{ return new Symbol(TokenConstants.ESAC); }
					case -45:
						break;
					case 44:
						{ return new Symbol(TokenConstants.LOOP); }
					case -46:
						break;
					case 45:
						{ return new Symbol(TokenConstants.CASE); }
					case -47:
						break;
					case 46:
						{ return new Symbol(TokenConstants.POOL); }
					case -48:
						break;
					case 47:
						{ return new Symbol(TokenConstants.BOOL_CONST, new Boolean(false)); }
					case -49:
						break;
					case 48:
						{ return new Symbol(TokenConstants.CLASS); }
					case -50:
						break;
					case 49:
						{ return new Symbol(TokenConstants.WHILE); }
					case -51:
						break;
					case 50:
						{ return new Symbol(TokenConstants.ISVOID); }
					case -52:
						break;
					case 51:
						{ return new Symbol(TokenConstants.INHERITS); }
					case -53:
						break;
					case 53:
						{ 
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
		  if(skip)
		  {
			  string_buf.append(yytext()) ;
			  skip = false ;
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
				  string_buf.setCharAt(length-1,'\r') ;
				  break ;
				case '\\':
				  string_buf.setCharAt(length-1, '\\');
				  skip = true ;
				  break;
				default:
				  string_buf.setCharAt(length-1,yytext.charAt(0)) ;
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
					case -54:
						break;
					case 54:
						{ // If an escape character appears before a quote, we must put a quote into the string
  if(stringTooLong)
  {
    return new Symbol(TokenConstants.ERROR, "String constant too long");
  }
  else if(nullInString)
  {
    return new Symbol(TokenConstants.ERROR, "Null character appeared in string");
  }
  else if(stringError)
  {
    return new Symbol(TokenConstants.ERROR, "Error occurred while parsing string");
  }
  if(!skip && string_buf.length()>0 && string_buf.charAt(string_buf.length()-1)=='\\') 
  {
    string_buf.setCharAt(string_buf.length()-1, '\"');
  } else 
  {
    yybegin(YYINITIAL); 
    return new Symbol(TokenConstants.STR_CONST, AbstractTable.stringtable.addString(string_buf.toString())); 
  }
}
					case -55:
						break;
					case 55:
						{ // Code for newline characters
  // If a newline appears in a string, it must be escaped, else error
  if(string_buf.length()==0 || skip || (string_buf.charAt(string_buf.length()-1)!='\\' && (string_buf.charAt(string_buf.length()-1)!='\r')))
  {
    curr_lineno++;
    return new Symbol(TokenConstants.ERROR, "Unterminated string constant");
  }
  else 
  {
    // Replace '\' character in string buffer with newline
    string_buf.setCharAt(string_buf.length()-1, '\n');
  }
 }
					case -56:
						break;
					case 56:
						{ /* Do Nothing */ }
					case -57:
						break;
					case 57:
						{ curr_lineno++; }
					case -58:
						break;
					case 58:
						{ /* Do nothing */ }
					case -59:
						break;
					case 59:
						{ nestedCommentCount++; }
					case -60:
						break;
					case 60:
						{
  if(nestedCommentCount!=0) {
    nestedCommentCount--;
  } else {
    yybegin(YYINITIAL);
  }
 }
					case -61:
						break;
					case 61:
						{ return new Symbol(TokenConstants.INT_CONST, AbstractTable.inttable.addString(yytext())); }
					case -62:
						break;
					case 62:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -63:
						break;
					case 63:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -64:
						break;
					case 64:
						{ return new Symbol(TokenConstants.FI); }
					case -65:
						break;
					case 65:
						{ return new Symbol(TokenConstants.IF); }
					case -66:
						break;
					case 66:
						{ return new Symbol(TokenConstants.IN); }
					case -67:
						break;
					case 67:
						{ return new Symbol(TokenConstants.OF); }
					case -68:
						break;
					case 68:
						{ return new Symbol(TokenConstants.LET); }
					case -69:
						break;
					case 69:
						{ return new Symbol(TokenConstants.NEW); }
					case -70:
						break;
					case 70:
						{ return new Symbol(TokenConstants.NOT); }
					case -71:
						break;
					case 71:
						{ return new Symbol(TokenConstants.BOOL_CONST, new Boolean(true)); }
					case -72:
						break;
					case 72:
						{ return new Symbol(TokenConstants.THEN); }
					case -73:
						break;
					case 73:
						{ return new Symbol(TokenConstants.ELSE); }
					case -74:
						break;
					case 74:
						{ return new Symbol(TokenConstants.ESAC); }
					case -75:
						break;
					case 75:
						{ return new Symbol(TokenConstants.LOOP); }
					case -76:
						break;
					case 76:
						{ return new Symbol(TokenConstants.CASE); }
					case -77:
						break;
					case 77:
						{ return new Symbol(TokenConstants.POOL); }
					case -78:
						break;
					case 78:
						{ return new Symbol(TokenConstants.BOOL_CONST, new Boolean(false)); }
					case -79:
						break;
					case 79:
						{ return new Symbol(TokenConstants.CLASS); }
					case -80:
						break;
					case 80:
						{ return new Symbol(TokenConstants.WHILE); }
					case -81:
						break;
					case 81:
						{ return new Symbol(TokenConstants.ISVOID); }
					case -82:
						break;
					case 82:
						{ return new Symbol(TokenConstants.INHERITS); }
					case -83:
						break;
					case 84:
						{ /* Do Nothing */ }
					case -84:
						break;
					case 85:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -85:
						break;
					case 86:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -86:
						break;
					case 87:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -87:
						break;
					case 88:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -88:
						break;
					case 89:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -89:
						break;
					case 90:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -90:
						break;
					case 91:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -91:
						break;
					case 92:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -92:
						break;
					case 93:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -93:
						break;
					case 94:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -94:
						break;
					case 95:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -95:
						break;
					case 96:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -96:
						break;
					case 97:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -97:
						break;
					case 98:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -98:
						break;
					case 99:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -99:
						break;
					case 100:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -100:
						break;
					case 101:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -101:
						break;
					case 102:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -102:
						break;
					case 103:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -103:
						break;
					case 104:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -104:
						break;
					case 105:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -105:
						break;
					case 106:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -106:
						break;
					case 107:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -107:
						break;
					case 108:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -108:
						break;
					case 109:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -109:
						break;
					case 110:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -110:
						break;
					case 111:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -111:
						break;
					case 112:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -112:
						break;
					case 113:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -113:
						break;
					case 114:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -114:
						break;
					case 115:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -115:
						break;
					case 116:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -116:
						break;
					case 117:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -117:
						break;
					case 118:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -118:
						break;
					case 119:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -119:
						break;
					case 120:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -120:
						break;
					case 121:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -121:
						break;
					case 122:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -122:
						break;
					case 123:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -123:
						break;
					case 124:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -124:
						break;
					case 125:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -125:
						break;
					case 126:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -126:
						break;
					case 127:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -127:
						break;
					case 128:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -128:
						break;
					case 129:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -129:
						break;
					case 130:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -130:
						break;
					case 131:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -131:
						break;
					case 132:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -132:
						break;
					case 133:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -133:
						break;
					case 134:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -134:
						break;
					case 135:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -135:
						break;
					case 136:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -136:
						break;
					case 137:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -137:
						break;
					case 138:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -138:
						break;
					case 139:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -139:
						break;
					case 140:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -140:
						break;
					case 141:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -141:
						break;
					case 142:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -142:
						break;
					case 143:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -143:
						break;
					case 144:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -144:
						break;
					case 145:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -145:
						break;
					case 146:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -146:
						break;
					case 147:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -147:
						break;
					case 148:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -148:
						break;
					case 149:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -149:
						break;
					case 150:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -150:
						break;
					case 151:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -151:
						break;
					case 152:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -152:
						break;
					case 153:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -153:
						break;
					case 154:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -154:
						break;
					case 155:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -155:
						break;
					case 156:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -156:
						break;
					case 157:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -157:
						break;
					case 158:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -158:
						break;
					case 159:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -159:
						break;
					case 160:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -160:
						break;
					case 161:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -161:
						break;
					case 162:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -162:
						break;
					case 163:
						{ return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
					case -163:
						break;
					case 164:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -164:
						break;
					case 165:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -165:
						break;
					case 166:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -166:
						break;
					case 167:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -167:
						break;
					case 168:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -168:
						break;
					case 169:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -169:
						break;
					case 170:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -170:
						break;
					case 171:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -171:
						break;
					case 172:
						{ return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
					case -172:
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
