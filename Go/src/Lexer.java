import java.lang.System;
import java.io.*;
import java.util.Stack;
class Lexer {
	Yylex tokenizer;
	boolean end = false;
	public  Lexer(String fileName) 
	{
	  try
	  {
	  tokenizer=new Yylex(new BufferedReader(new FileReader(fileName)));
	  }
	  catch(Exception e)
	  {
	  }	 
	}
	public Token nextToken()
	{
		Token next=null;
		try
		{
			//if(!end) {
				 next=  tokenizer.getToken();
				 if(next.getTokenType() == 48) {
			 		end = true;
			 	}		
			//}
		}
		catch(Exception e)
		{
		}
		return next;
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

private int Quote_count = 0;
private static String Quoted ="";
	//initialize  variables to be used by class
	private Stack<Character> stack_char = new Stack<Character>();
	boolean paran = false;
	boolean square = false;
	boolean curly = false;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
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
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

//Add code to be executed on initialization of the lexer
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
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
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
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
		/* 45 */ YY_NOT_ACCEPT,
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
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NOT_ACCEPT,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NOT_ACCEPT,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NOT_ACCEPT,
		/* 116 */ YY_NOT_ACCEPT,
		/* 117 */ YY_NOT_ACCEPT,
		/* 118 */ YY_NOT_ACCEPT,
		/* 119 */ YY_NOT_ACCEPT,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NOT_ACCEPT,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NOT_ACCEPT,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NOT_ACCEPT,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NOT_ACCEPT,
		/* 128 */ YY_NOT_ACCEPT,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NOT_ACCEPT,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NOT_ACCEPT,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NOT_ACCEPT,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NOT_ACCEPT,
		/* 149 */ YY_NOT_ACCEPT,
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
		/* 184 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"2:8,23:2,24,2:2,41,2:18,23,2,1,2:2,38,37,2,43,46,30,28,39,29,49,32,4:10,35," +
"36,25,27,26,2:2,5:26,44,40,47,2:2,3,7,5,8,20,11,21,10,19,18,5,9,22,33,31,34" +
",6,5,15,14,12,16,42,17,5,13,5,45,2,48,2:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,185,
"0,1,2,1,3,4,5,1,6:2,7,1:2,8,9,1:10,10,11,5,7,12,1:2,11,5:12,13,14,15,1,16,1" +
"7,18,19,20,21,19,1,22,23,1,24,25,26,27,28,29,30,31,32,33,3,34,35,36,10,37,3" +
"8,39,16,40,41,42,43,44,45,46,47,48,49,11,30,50,51,52,53,29,54,28,55,56,33,5" +
"7,58,24,59,21,60,25,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,7" +
"9,80,81,82,18,83,84,85,86,87,88,89,90,91,92,93,94,41,10,62,72,74,95,18,96,9" +
"7,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116" +
",117,118,119,120,121,122,123,124,125,126,127,128,129,130")[0];

	private int yy_nxt[][] = unpackFromString(131,50,
"1,2,3,4,5,6,183,6,162,6:2,163,164,6,179,180,6:2,47,6,184,165,6,7:2,8,48,9,1" +
"0,11,12,6,13,6:2,14,15,46,16,17,3,-1,142,18,19,20,21,22,23,24,-1:51,25,45,5" +
"2,45:20,-1,45:14,143,57,62,45:8,-1:2,67,26,70,148:18,67,-1,67:6,148,67,148:" +
"2,67:4,-1,72,67,148,67:7,-1:4,5,-1:49,53,6:18,-1:8,6,-1,6:2,-1:7,6,-1:34,48" +
",-1:50,28,-1:53,29,-1:44,30,-1:23,50,74,52,74:20,-1,74:14,52,76,78,74:8,-1:" +
"2,89,90,89:20,-1,89:14,-1,91,89:9,-1,29:23,-1,29:16,-1,29:8,-1,144,45,52,45" +
":20,-1,45:14,143,57,62,45:8,-1:37,31,-1:16,53,6:16,27,6,-1:8,6,-1,176,6,-1:" +
"7,6,-1:8,100,78,-1,78:20,-1,78:14,-1,101,78:9,-1,144,95,52,95:20,-1,95:15,9" +
"6,97,95:8,-1:2,149,145,149:20,-1,149:14,-1,112,149:9,-1,55,52:22,-1,52:16,-" +
"1,52:8,-1:4,53,-1:46,146,105,-1,105:20,-1,105:14,62,125,105:9,-1,60,45:21,8" +
"4,86,45:14,143,57,62,45:8,-1:4,53,6:10,33,6:7,-1:8,6,-1,6:2,-1:7,6,-1:8,151" +
",103,52,103:20,-1,103:14,143,104,105,103:8,-1:2,107,56,107:20,-1,107:14,-1," +
"108,107:9,-1,49,62,-1,62:20,-1,62:15,88,62:9,-1:4,53,6:6,34,6:11,-1:8,6,-1," +
"6:2,-1:7,6,-1:8,59,97,-1,97:20,-1,97:15,115,97:9,-1,55,95,52,95:20,-1,95:15" +
",96,97,95:8,-1:2,107,32,107:20,-1,107:14,-1,108,107:9,-1:2,67,32,67:20,-1,6" +
"7:14,-1,72,67:9,-1:4,53,6:6,35,6:11,-1:8,6,-1,6:2,-1:7,6,-1:8,49,97,-1,97:2" +
"0,-1,97:15,115,97:9,-1:4,53,6:6,36,6:11,-1:8,6,-1,6:2,-1:7,6,-1:8,67:2,51,6" +
"7:19,93,94,67:14,-1,72,67:9,-1:4,53,6:3,37,6:14,-1:8,6,-1,6:2,-1:7,6,-1:11," +
"53,6:7,38,6:10,-1:8,6,-1,6:2,-1:7,6,-1:8,151,74:21,98,99,74:14,52,76,78,74:" +
"8,-1:4,53,6:7,39,6:10,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:14,40,6:3,-1:8,6,-1,6" +
":2,-1:7,6,-1:8,144,80,52,80:20,-1,80:14,143,82,62,80:8,-1:4,53,6:18,-1:8,41" +
",-1,6:2,-1:7,6,-1:8,147,80:21,102,86,80:14,143,82,62,80:8,-1:4,53,6:7,42,6:" +
"10,-1:8,6,-1,6:2,-1:7,6,-1:8,144,45,52,45:19,84,86,45:14,143,57,62,45:8,-1:" +
"4,53,6:6,43,6:11,-1:8,6,-1,6:2,-1:7,6,-1:30,86:2,-1:15,62,-1:13,53,6:7,44,6" +
":10,-1:8,6,-1,6:2,-1:7,6,-1:8,54,62:21,106,86,62:15,88,62:9,-1,89:2,109,89:" +
"19,110,111,89:14,-1,91,89:9,-1:2,67,26,92,67:19,-1,67:14,-1,72,67:9,-1:2,67" +
",32,67:19,93,94,67:14,-1,72,67:9,-1:23,94:2,-1:15,67,-1:10,65,95:21,113,114" +
",95:15,96,97,95:8,-1,50,74,52,74:19,98,99,74:14,52,76,78,74:8,-1:23,99:2,-1" +
":15,78,-1:10,119,78:21,120,99,78:14,-1,101,78:9,-1,144,80,52,80:19,102,86,8" +
"0:14,143,82,62,80:8,-1,60,103:21,123,124,103:14,143,104,105,103:8,-1,49,62," +
"-1,62:19,106,86,62:15,88,62:9,-1,107:2,61,107:19,126,127,107:14,-1,108,107:" +
"9,-1:2,128,145,128:20,-1,128:14,-1,129,128:9,-1:2,89,90,89:19,110,111,89:14" +
",-1,91,89:9,-1:23,111:2,-1:15,89,-1:10,130,149,51,149:19,131,132,149:14,-1," +
"112,149:9,-1,55,95,52,95:19,113,114,95:15,96,97,95:8,-1:23,114:2,-1:15,97,-" +
"1:10,64,97:21,133,114,97:15,115,97:9,-1,50,116,52,116:20,-1,116:14,95,117,1" +
"18,116:8,-1,151,116:21,134,135,116:14,95,117,118,116:8,-1,69,118,-1,118:20," +
"-1,118:14,97,136,118:9,-1,146,118,-1,118:20,-1,118:14,97,136,118:9,-1,100,7" +
"8,-1,78:19,120,99,78:14,-1,101,78:9,-1,151,121,52,121:20,-1,121:14,143,122," +
"105,121:8,-1,147,121:21,137,124,121:14,143,122,105,121:8,-1,151,103,52,103:" +
"19,123,124,103:14,143,104,105,103:8,-1:23,124:2,-1:15,105,-1:10,54,105:21,1" +
"38,124,105:14,62,125,105:9,-1:2,107,56,107:19,126,127,107:14,-1,108,107:9,-" +
"1:23,127:2,-1:15,107,-1:11,128,66,128:20,-1,128:14,-1,129,128:9,-1,128:2,14" +
"5,128:19,139,140,128:14,-1,129,128:9,-1:2,149,145,149:19,131,132,149:14,-1," +
"112,149:9,-1:23,132:2,-1:15,130,-1:10,59,97,-1,97:19,133,114,97:15,115,97:9" +
",-1,50,116,52,116:19,134,135,116:14,95,117,118,116:8,-1:23,135:2,-1:15,118," +
"-1:10,146,118:21,141,135,118:14,97,136,118:9,-1,151,121,52,121:19,137,124,1" +
"21:14,143,122,105,121:8,-1,146,105,-1,105:19,138,124,105:14,62,125,105:9,-1" +
":2,128,66,128:19,139,140,128:14,-1,129,128:9,-1:23,140:2,-1:15,128,-1:10,69" +
",118,-1,118:19,141,135,118:14,97,136,118:9,-1:4,53,6:2,58,6:15,-1:8,6,-1,6:" +
"2,-1:7,6,-1:9,67,26,92,148:18,67,-1,67:6,148,67,148:2,67:4,-1,72,67,148,67:" +
"7,-1:4,53,6:9,63,6:8,-1:8,6,-1,6:2,-1:7,6,-1:8,151,116,52,116:20,-1,116:14," +
"95,117,118,116:8,-1:4,53,6:9,68,6:8,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6,71,6:16" +
",-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:18,-1:8,73,-1,6:2,-1:7,6,-1:11,53,6:9,75,6" +
":8,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:3,77,6:14,-1:8,6,-1,6:2,-1:7,6,-1:11,53," +
"6:3,79,6:14,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:10,81,6:7,-1:8,6,-1,6:2,-1:7,6," +
"-1:11,53,6:10,83,6:7,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:5,85,6:12,-1:8,6,-1,6:" +
"2,-1:7,6,-1:11,53,6:17,87,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:2,150,6:15,-1:8,6" +
",-1,6,166,-1:7,6,-1:11,53,6:17,152,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:8,153,6:" +
"9,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:11,154,6:6,-1:8,6,-1,6:2,-1:7,6,-1:11,53," +
"6:18,-1:8,155,-1,6:2,-1:7,6,-1:11,53,6:11,156,6:6,-1:8,6,-1,6:2,-1:7,6,-1:1" +
"1,53,6:7,157,6:10,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:11,158,6:6,-1:8,6,-1,6:2," +
"-1:7,6,-1:11,53,6:18,-1:8,6,-1,6,159,-1:7,6,-1:11,53,6:2,160,6:15,-1:8,6,-1" +
",6:2,-1:7,6,-1:11,53,6:11,161,6:6,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:10,167,6:" +
"7,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:13,168,6:4,-1:8,6,-1,6:2,-1:7,6,-1:11,53," +
"6:7,169,6:10,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6,170,6:16,-1:8,6,-1,6:2,-1:7,6," +
"-1:11,53,6:4,171,6:13,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:2,172,6:15,-1:8,6,-1," +
"6:2,-1:7,6,-1:11,53,6:7,173,6:4,174,6:5,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:6,1" +
"75,6:11,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:3,177,6:14,-1:8,6,-1,6:2,-1:7,6,-1:" +
"11,53,6:16,178,6,-1:8,6,-1,6:2,-1:7,6,-1:11,53,6:2,181,6:15,-1:8,6,-1,6:2,-" +
"1:7,6,-1:11,53,6:6,182,6:11,-1:8,6,-1,6:2,-1:7,6,-1:7");

	public Token getToken ()
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

//Add code to be executed when the end of the file is reached
	{
		if(paran) {
			return (new Token(Token.EOF, "There is some ( that is not closed"));
		}
		if(square) {
			return (new Token(Token.EOF, "There is some [ that is not closed"));
		}
		if(curly) {
			return (new Token(Token.EOF, "There is some {} that is not closed"));
		}
		return (new Token(Token.EOF,"Done"));
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
						{
	String str =  yytext().substring(1,yytext().length());
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -3:
						break;
					case 3:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -4:
						break;
					case 4:
						{
	String str =  yytext().substring(1,yytext().length());
return (new Token(Token.ERROR, "Invalid input: "+ yytext()  +" in line: "+yyline));
}
					case -5:
						break;
					case 5:
						{ return (new Token(Token.INT_LIT,yytext()));}
					case -6:
						break;
					case 6:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -7:
						break;
					case 7:
						{}
					case -8:
						break;
					case 8:
						{ return (new Token(Token.REL_OP,yytext()));}
					case -9:
						break;
					case 9:
						{ return (new Token(Token.EQUAL,yytext()));}
					case -10:
						break;
					case 10:
						{ return (new Token(Token.PLUS,yytext()));}
					case -11:
						break;
					case 11:
						{ return (new Token(Token.MINUS,yytext()));}
					case -12:
						break;
					case 12:
						{ return (new Token(Token.ASTRISK,yytext()));}
					case -13:
						break;
					case 13:
						{ return (new Token(Token.SLASH,yytext()));}
					case -14:
						break;
					case 14:
						{ return (new Token(Token.COLON,yytext()));}
					case -15:
						break;
					case 15:
						{ return (new Token(Token.SEMI_COLON,yytext()));}
					case -16:
						break;
					case 16:
						{ return (new Token(Token.PERCENT,yytext()));}
					case -17:
						break;
					case 17:
						{ return (new Token(Token.COMMA,yytext()));}
					case -18:
						break;
					case 18:
						{ stack_char.push('('); return (new Token(Token.OPEN_PARAN,yytext()));}
					case -19:
						break;
					case 19:
						{ stack_char.push('[');return (new Token(Token.OPEN_SQUARE,yytext()));}
					case -20:
						break;
					case 20:
						{ stack_char.push('{');return (new Token(Token.OPEN_CURLY,yytext()));}
					case -21:
						break;
					case 21:
						{ 
		Character temp = stack_char.pop();
		if(temp == '(') {
			return (new Token(Token.CLOSE_PARAN,yytext()));
		} else {
			if(temp =='[') {
				square = true;
			}
			if(temp == '{') {
				curly = true;
			}
			return (new Token(Token.ERROR, "You have a missing bracket in line" + + (yyline+1)));
		}
	}
					case -22:
						break;
					case 22:
						{ 
		Character temp = stack_char.pop();
			if(temp == '[') {
				return (new Token(Token.CLOSE_SQUARE,yytext()));
			} else {
				if(temp == '(') {
					paran = true;
				}
				if(temp == '{') {
					curly = true;
				}
				return ( new Token(Token.ERROR, "You have a missing bracket in line" + + (yyline+1)));
			}
		}
					case -23:
						break;
					case 23:
						{ 
		Character temp = stack_char.pop();
		if(temp == '{') {
			return (new Token(Token.CLOSE_CURLY,yytext()));
		} else {
			if(temp =='[') {
				square = true;
			}
			if(temp == '(') {
				paran = true;
			}
			return (new Token(Token.ERROR, "You have a missing bracket in line " + (yyline+1) ));
		}
	}
					case -24:
						break;
					case 24:
						{ return (new Token(Token.DOT,yytext()));}
					case -25:
						break;
					case 25:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -26:
						break;
					case 26:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -27:
						break;
					case 27:
						{ return (new Token(Token.IF,yytext()));}
					case -28:
						break;
					case 28:
						{ return (new Token(Token.INCREMENT,yytext()));}
					case -29:
						break;
					case 29:
						{}
					case -30:
						break;
					case 30:
						{ return (new Token(Token.COLON_EQUAL,yytext()));}
					case -31:
						break;
					case 31:
						{ return (new Token(Token.AND_OP,yytext()));}
					case -32:
						break;
					case 32:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -33:
						break;
					case 33:
						{ return (new Token(Token.VAR,yytext()));}
					case -34:
						break;
					case 34:
						{ return (new Token(Token.CASE,yytext()));}
					case -35:
						break;
					case 35:
						{ return (new Token(Token.ELSE,yytext()));}
					case -36:
						break;
					case 36:
						{ return (new Token(Token.TYPE,yytext()));}
					case -37:
						break;
					case 37:
						{ return (new Token(Token.FUNC,yytext()));}
					case -38:
						break;
					case 38:
						{ return (new Token(Token.CONST,yytext()));}
					case -39:
						break;
					case 39:
						{ return (new Token(Token.STRUCT,yytext()));}
					case -40:
						break;
					case 40:
						{ return (new Token(Token.SWITCH,yytext()));}
					case -41:
						break;
					case 41:
						{ return (new Token(Token.RETURN,yytext()));}
					case -42:
						break;
					case 42:
						{ return (new Token(Token.IMPORT,yytext()));}
					case -43:
						break;
					case 43:
						{ return (new Token(Token.PACKAGE,yytext()));}
					case -44:
						break;
					case 44:
						{ return (new Token(Token.DEFAULT,yytext()));}
					case -45:
						break;
					case 46:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -46:
						break;
					case 47:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -47:
						break;
					case 48:
						{ return (new Token(Token.REL_OP,yytext()));}
					case -48:
						break;
					case 49:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -49:
						break;
					case 50:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -50:
						break;
					case 51:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -51:
						break;
					case 53:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -52:
						break;
					case 54:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -53:
						break;
					case 55:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -54:
						break;
					case 56:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -55:
						break;
					case 58:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -56:
						break;
					case 59:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -57:
						break;
					case 60:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -58:
						break;
					case 61:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -59:
						break;
					case 63:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -60:
						break;
					case 64:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -61:
						break;
					case 65:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -62:
						break;
					case 66:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -63:
						break;
					case 68:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -64:
						break;
					case 69:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -65:
						break;
					case 71:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -66:
						break;
					case 73:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -67:
						break;
					case 75:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -68:
						break;
					case 77:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -69:
						break;
					case 79:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -70:
						break;
					case 81:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -71:
						break;
					case 83:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -72:
						break;
					case 85:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -73:
						break;
					case 87:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -74:
						break;
					case 142:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -75:
						break;
					case 144:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -76:
						break;
					case 145:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -77:
						break;
					case 146:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -78:
						break;
					case 147:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -79:
						break;
					case 150:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -80:
						break;
					case 151:
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -81:
						break;
					case 152:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -82:
						break;
					case 153:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -83:
						break;
					case 154:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -84:
						break;
					case 155:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -85:
						break;
					case 156:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -86:
						break;
					case 157:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -87:
						break;
					case 158:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -88:
						break;
					case 159:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -89:
						break;
					case 160:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -90:
						break;
					case 161:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -91:
						break;
					case 162:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -92:
						break;
					case 163:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -93:
						break;
					case 164:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -94:
						break;
					case 165:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -95:
						break;
					case 166:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -96:
						break;
					case 167:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -97:
						break;
					case 168:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -98:
						break;
					case 169:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -99:
						break;
					case 170:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -100:
						break;
					case 171:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -101:
						break;
					case 172:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -102:
						break;
					case 173:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -103:
						break;
					case 174:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -104:
						break;
					case 175:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -105:
						break;
					case 176:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -106:
						break;
					case 177:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -107:
						break;
					case 178:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -108:
						break;
					case 179:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -109:
						break;
					case 180:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -110:
						break;
					case 181:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -111:
						break;
					case 182:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -112:
						break;
					case 183:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -113:
						break;
					case 184:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -114:
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
