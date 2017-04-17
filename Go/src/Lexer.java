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
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
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
		/* 87 */ YY_NOT_ACCEPT,
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
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NOT_ACCEPT,
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
		/* 160 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
<<<<<<< HEAD
"2:8,12:2,13,2:2,0,2:18,12,2,1,2:2,32,31,2,35,38,18,16,33,17,41,42,4:10,29,3" +
"0,14,15,14,2:2,5:26,36,2,39,2:2,3,7,5,8,5,11,28,10,5,23,5,9,26,24,22,25,6,5" +
",19,27,20,21,34,5:4,37,2,40,2:2,43:2")[0];

	private int yy_rmap[] = unpackFromString(1,71,
"0,1,2,3,4:2,5,4:3,6,4:13,3,4:2,3:7,7,4,8,7,9:2,10,11,12,13,14,15,16,17,18,1" +
"9,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40")[0];

	private int yy_nxt[][] = unpackFromString(41,44,
"-1,1,35,39,2,3,70,3,64,3:2,58,4:2,5,6,7,8,9,68,3:3,36,3:4,59,10,11,42,12,13" +
",50,14,15,16,17,18,19,20,21,22,-1,34:12,-1,34:29,-1:5,2,-1:43,40,3:7,-1:7,3" +
":10,-1:5,3,-1:68,5,-1:43,25,-1:29,37,34:11,-1,34:29,-1:5,40,3:7,-1:7,3:5,66" +
",3:3,24,-1:5,3,-1:12,23,38,51:7,-1:7,51:10,-1:5,51,-1:13,40,-1:42,23,41,-1:" +
"70,26,-1:16,40,3:7,-1:7,27,3:9,-1:5,3,-1:13,40,3:6,28,-1:7,3:10,-1:5,3,-1:1" +
"3,40,3:3,29,3:3,-1:7,3:10,-1:5,3,-1:13,40,3:7,-1:7,3,30,3:8,-1:5,3,-1:13,40" +
",3:7,-1:7,3:3,31,3:6,-1:5,3,-1:13,40,3:7,-1:7,3,32,3:8,-1:5,3,-1:13,40,3:6," +
"33,-1:7,3:10,-1:5,3,-1:13,40,3:2,43,3:4,-1:7,3:10,-1:5,3,-1:12,23,41,51:7,-" +
"1:7,51:10,-1:5,51,-1:13,40,3:7,-1:7,3:8,44,3,-1:5,3,-1:13,40,3:7,-1:7,3:3,4" +
"5,3:6,-1:5,3,-1:13,40,3:7,-1:7,3:8,46,3,-1:5,3,-1:13,40,3:7,-1:7,47,3:9,-1:" +
"5,3,-1:13,40,3:7,-1:7,48,3:9,-1:5,3,-1:13,40,3:5,49,3,-1:7,3:10,-1:5,3,-1:1" +
"3,40,3:7,-1:7,3:7,52,3:2,-1:5,3,-1:13,40,3:7,-1:7,3:2,53,3:7,-1:5,3,-1:13,4" +
"0,3:7,-1:7,3:3,54,3:6,-1:5,3,-1:13,40,3:7,-1:7,3:2,55,3:7,-1:5,3,-1:13,40,3" +
":7,-1:7,3:6,56,3:3,-1:5,3,-1:13,40,3:2,57,3:4,-1:7,3:10,-1:5,3,-1:13,40,3:7" +
",-1:7,3:6,60,3:3,-1:5,3,-1:13,40,3:7,-1:7,3,61,3:8,-1:5,3,-1:13,40,3,62,3:5" +
",-1:7,3:10,-1:5,3,-1:13,40,3:4,63,3:2,-1:7,3:10,-1:5,3,-1:13,40,3:6,65,-1:7" +
",3:10,-1:5,3,-1:13,40,3:3,67,3:3,-1:7,3:10,-1:5,3,-1:13,40,3:2,69,3:4,-1:7," +
"3:10,-1:5,3,-1:9");
=======
"28:8,18:2,19,28:2,39,28:18,18,28,36,28:2,34,33,28,41,42,25,23,35,24,47,27,4" +
"8:10,31,32,20,22,21,28:2,49:26,43,37,44,28:2,38,2,49,3,15,6,16,5,14,13,49,4" +
",17,29,26,30,1,49,10,9,7,11,40,12,49,8,49,45,28,46,28:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,161,
"0,1,2,1,3:2,4,1:2,5,1,6,1:3,7,8,1:7,9,10,4,11,1:2,12,13,10:13,7,14,1,15,16," +
"17,18,19,20,1,8,21,1,22,23,24,25,26,27,28,29,30,31,12,32,29,33,34,35,36,37," +
"38,39,40,41,42,43,13,44,45,46,26,47,48,25,49,50,51,20,16,52,17,53,54,55,56," +
"22,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,27,58," +
"56,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,1" +
"02,103,104,105,106,107,108,109,110,10,111")[0];

	private int yy_nxt[][] = unpackFromString(112,50,
"1,2,159,139,159:2,140,141,159,155,156,159:2,46,159,160,123,159,3:2,4,47,5,6" +
",7,8,159,9,10,159:2,11,12,48,13,14,15,10,16,-1,127,17,18,19,20,21,22,23,24," +
"159,-1:51,159,157,159:15,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1:22,47," +
"-1:50,26,-1:53,27,-1:44,28,-1:28,45:18,-1,45:15,124,30,51,-1,45:11,-1,55:18" +
",-1,55:15,-1:2,59,31,55:11,-1:48,24,-1:2,159:17,-1:8,159,-1:2,159:2,-1:9,15" +
"9,-1:7,52,159,-1,27:18,-1,27:19,-1,27:10,-1,68:18,-1,68:15,-1,70,72,-1,68:1" +
"1,-1,82:18,-1,82:15,-1:2,84,86,82:11,-1,159:15,25,159,-1:8,159,-1:2,152,159" +
",-1:9,159,-1:7,52,159,-1:33,29,-1:17,94:18,-1,94:15,124,53,95,-1,94:11,-1,9" +
"6:18,-1,96:15,-1:2,97,126,96:11,-1,45:17,74,76,45:15,124,49,51,45:12,-1:48," +
"52,-1:2,107:18,-1,107:15,89,53,108,-1,107:11,-1,159:9,32,159:7,-1:8,159,-1:" +
"2,159:2,-1:9,159,-1:7,52,159,-1,101:18,-1,101:15,-1:2,102,54,101:11,-1,55:1" +
"7,78,80,55:15,-1,55,59,50,55:11,-1,159:9,33,159:7,-1:8,159,-1:2,159:2,-1:9," +
"159,-1:7,52,159,-1,89:18,-1,89:16,57,90,-1,89:11,-1,101:18,-1,101:15,-1:2,1" +
"02,31,101:11,-1,63:18,-1,63:15,124,30,66,-1,63:11,-1,159:5,34,159:11,-1:8,1" +
"59,-1:2,159:2,-1:9,159,-1:7,52,159,-1,89:18,-1,89:16,30,90,-1,89:11,-1,63:1" +
"7,87,88,63:15,124,125,66,63:12,-1,159:5,35,159:11,-1:8,159,-1:2,159:2,-1:9," +
"159,-1:7,52,159,-1,159:5,36,159:11,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159" +
",-1,159:2,37,159:14,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,68:17,91,92" +
",68:15,-1,93,72,68:12,-1,159:6,38,159:10,-1:8,159,-1:2,159:2,-1:9,159,-1:7," +
"52,159,-1,45:17,74,76,45:15,124,30,51,-1,45:11,-1,159:6,39,159:10,-1:8,159," +
"-1:2,159:2,-1:9,159,-1:7,52,159,-1:18,76:2,-1:17,45,-1:13,159:13,40,159:3,-" +
"1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,55:17,78,80,55:15,-1:2,59,31,55:" +
"11,-1,159:17,-1:8,41,-1:2,159:2,-1:9,159,-1:7,52,159,-1:18,80:2,-1:17,55,-1" +
":13,159:6,42,159:10,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:5,43,15" +
"9:11,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,82:17,98,99,82:15,-1,82,84" +
",100,82:11,-1,159:6,44,159:10,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,6" +
"3:17,87,88,63:15,124,30,66,-1,63:11,-1:18,88:2,-1:17,63,-1:13,89:17,105,106" +
",89:16,61,90,89:12,-1,68:17,91,92,68:15,-1,70,72,-1,68:11,-1:18,92:2,-1:17," +
"68,-1:13,94:17,109,110,94:15,124,49,95,94:12,-1,96:17,111,112,96:15,-1,96,9" +
"7,50,96:11,-1,82:17,98,99,82:15,-1:2,84,86,82:11,-1:18,99:2,-1:17,82,-1:13," +
"113:18,-1,113:15,-1:2,114,126,113:11,-1,101:17,115,116,101:15,-1,101,102,58" +
",101:11,-1,103:18,-1,103:15,124,53,104,-1,103:11,-1,103:17,117,118,103:15,1" +
"24,125,104,103:12,-1,89:17,105,106,89:16,57,90,-1,89:11,-1:18,106:2,-1:17,8" +
"9,-1:13,107:18,-1,107:15,89,65,108,-1,107:11,-1,107:17,119,120,107:15,89,53" +
",108,107:12,-1,94:17,109,110,94:15,124,53,95,-1,94:11,-1:18,110:2,-1:17,94," +
"-1:13,96:17,111,112,96:15,-1:2,97,126,96:11,-1:18,112:2,-1:17,96,-1:13,113:" +
"18,-1,113:15,-1:2,114,62,113:11,-1,113:17,121,122,113:15,-1,113,114,126,113" +
":11,-1,101:17,115,116,101:15,-1:2,102,54,101:11,-1:18,116:2,-1:17,101,-1:13" +
",103:17,117,118,103:15,124,53,104,-1,103:11,-1:18,118:2,-1:17,103,-1:13,107" +
":17,119,120,107:15,89,65,108,-1,107:11,-1:18,120:2,-1:17,107,-1:13,113:17,1" +
"21,122,113:15,-1:2,114,62,113:11,-1:18,122:2,-1:17,113,-1:13,159:10,131,159" +
":6,-1:8,159,-1:2,159,56,-1:9,159,-1:7,52,159,-1,159,60,159:15,-1:8,159,-1:2" +
",159:2,-1:9,159,-1:7,52,159,-1,159:8,64,159:8,-1:8,159,-1:2,159:2,-1:9,159," +
"-1:7,52,159,-1,159:8,67,159:8,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,6" +
"9,159:16,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:17,-1:8,71,-1:2,15" +
"9:2,-1:9,159,-1:7,52,159,-1,159:8,73,159:8,-1:8,159,-1:2,159:2,-1:9,159,-1:" +
"7,52,159,-1,159:2,75,159:14,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159" +
":2,77,159:14,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:9,79,159:7,-1:" +
"8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:9,81,159:7,-1:8,159,-1:2,159:2" +
",-1:9,159,-1:7,52,159,-1,159:4,83,159:12,-1:8,159,-1:2,159:2,-1:9,159,-1:7," +
"52,159,-1,159:16,85,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159,128,159" +
":15,-1:8,159,-1:2,159,142,-1:9,159,-1:7,52,159,-1,159:16,129,-1:8,159,-1:2," +
"159:2,-1:9,159,-1:7,52,159,-1,159:7,130,159:9,-1:8,159,-1:2,159:2,-1:9,159," +
"-1:7,52,159,-1,159:17,-1:8,132,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:10,13" +
"3,159:6,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:6,134,159:10,-1:8,1" +
"59,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:10,135,159:6,-1:8,159,-1:2,159:2," +
"-1:9,159,-1:7,52,159,-1,159:17,-1:8,159,-1:2,159,136,-1:9,159,-1:7,52,159,-" +
"1,159,137,159:15,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:10,138,159" +
":6,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:9,143,159:7,-1:8,159,-1:" +
"2,159:2,-1:9,159,-1:7,52,159,-1,159:12,144,159:4,-1:8,159,-1:2,159:2,-1:9,1" +
"59,-1:7,52,159,-1,159:6,145,159:10,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159" +
",-1,146,159:16,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:3,147,159:13" +
",-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159,148,159:15,-1:8,159,-1:2,1" +
"59:2,-1:9,159,-1:7,52,159,-1,159:6,149,159:4,150,159:5,-1:8,159,-1:2,159:2," +
"-1:9,159,-1:7,52,159,-1,159:5,151,159:11,-1:8,159,-1:2,159:2,-1:9,159,-1:7," +
"52,159,-1,159:2,153,159:14,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:" +
"15,154,159,-1:8,159,-1:2,159:2,-1:9,159,-1:7,52,159,-1,159:5,158,159:11,-1:" +
"8,159,-1:2,159:2,-1:9,159,-1:7,52,159");
>>>>>>> 4f585a2128e2223726d9955e3341b8ee99c27559

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
<<<<<<< HEAD
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -2:
						break;
					case 2:
						{ return (new Token(Token.INT_LIT,yytext()));}
					case -3:
						break;
					case 3:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -4:
						break;
					case 4:
						{}
					case -5:
						break;
					case 5:
						{ return (new Token(Token.REL_OP,yytext()));}
					case -6:
						break;
					case 6:
						{ return (new Token(Token.EQUAL,yytext()));}
					case -7:
						break;
					case 7:
						{ return (new Token(Token.PLUS,yytext()));}
					case -8:
						break;
					case 8:
						{ return (new Token(Token.MINUS,yytext()));}
					case -9:
						break;
					case 9:
						{ return (new Token(Token.ASTRISK,yytext()));}
=======
						
					case -2:
						break;
					case 2:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{ return (new Token(Token.REL_OP,yytext()));}
					case -5:
						break;
					case 5:
						{ return (new Token(Token.EQUAL,yytext()));}
					case -6:
						break;
					case 6:
						{ return (new Token(Token.PLUS,yytext()));}
					case -7:
						break;
					case 7:
						{ return (new Token(Token.MINUS,yytext()));}
					case -8:
						break;
					case 8:
						{ return (new Token(Token.ASTRISK,yytext()));}
					case -9:
						break;
					case 9:
						{ return (new Token(Token.SLASH,yytext()));}
>>>>>>> 4f585a2128e2223726d9955e3341b8ee99c27559
					case -10:
						break;
					case 10:
						{ return (new Token(Token.COLON,yytext()));}
					case -11:
						break;
					case 11:
<<<<<<< HEAD
						{ return (new Token(Token.SEMI_COLON,yytext()));}
					case -12:
						break;
					case 12:
						{ return (new Token(Token.PERCENT,yytext()));}
					case -13:
						break;
					case 13:
						{ return (new Token(Token.COMMA,yytext()));}
					case -14:
						break;
					case 14:
						{ stack_char.push('('); return (new Token(Token.OPEN_PARAN,yytext()));}
					case -15:
						break;
					case 15:
						{ stack_char.push('[');return (new Token(Token.OPEN_SQUARE,yytext()));}
					case -16:
						break;
					case 16:
						{ stack_char.push('{');return (new Token(Token.OPEN_CURLY,yytext()));}
					case -17:
						break;
					case 17:
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
					case -18:
						break;
					case 18:
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
					case -19:
						break;
					case 19:
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
=======
						{ return (new Token(Token.COLON,yytext()));}
					case -12:
						break;
					case 12:
						{ return (new Token(Token.SEMI_COLON,yytext()));}
					case -13:
						break;
					case 13:
						{ return (new Token(Token.PERCENT,yytext()));}
					case -14:
						break;
					case 14:
						{ return (new Token(Token.COMMA,yytext()));}
					case -15:
						break;
					case 15:
						{
	String str =  yytext().substring(1,yytext().length());
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -16:
						break;
					case 16:
						{
	String str =  yytext().substring(1,yytext().length());
return (new Token(Token.ERROR, "Invalid input: "+ yytext()  +" in line: "+yyline));
}
					case -17:
						break;
					case 17:
						{ return (new Token(Token.OPEN_PARAN,yytext()));}
					case -18:
						break;
					case 18:
						{ return (new Token(Token.CLOSE_PARAN,yytext()));}
					case -19:
						break;
					case 19:
						{ return (new Token(Token.OPEN_SQUARE,yytext()));}
>>>>>>> 4f585a2128e2223726d9955e3341b8ee99c27559
					case -20:
						break;
					case 20:
						{ return (new Token(Token.CLOSE_SQUARE,yytext()));}
					case -21:
						break;
					case 21:
						{ return (new Token(Token.OPEN_CURLY,yytext()));}
					case -22:
						break;
					case 22:
						{ return (new Token(Token.CLOSE_CURLY,yytext()));}
					case -23:
						break;
					case 23:
<<<<<<< HEAD
						{ return (new Token(Token.STRING_LIT,yytext()));}
					case -24:
						break;
					case 24:
						{ return (new Token(Token.IF,yytext()));}
					case -25:
						break;
					case 25:
						{ return (new Token(Token.COLON_EQUAL,yytext()));}
					case -26:
						break;
					case 26:
						{ return (new Token(Token.AND_OP,yytext()));}
=======
						{ return (new Token(Token.DOT,yytext()));}
					case -24:
						break;
					case 24:
						{ return (new Token(Token.INT_LIT,yytext()));}
					case -25:
						break;
					case 25:
						{ return (new Token(Token.IF,yytext()));}
					case -26:
						break;
					case 26:
						{ return (new Token(Token.INCREMENT,yytext()));}
>>>>>>> 4f585a2128e2223726d9955e3341b8ee99c27559
					case -27:
						break;
					case 27:
						{}
					case -28:
						break;
					case 28:
						{ return (new Token(Token.COLON_EQUAL,yytext()));}
					case -29:
						break;
					case 29:
						{ return (new Token(Token.AND_OP,yytext()));}
					case -30:
						break;
					case 30:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -31:
						break;
					case 31:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -32:
						break;
					case 32:
						{ return (new Token(Token.FOR,yytext()));}
					case -33:
						break;
					case 33:
						{ return (new Token(Token.VAR,yytext()));}
					case -34:
						break;
<<<<<<< HEAD
					case 35:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -35:
						break;
					case 36:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
=======
					case 34:
						{ return (new Token(Token.CASE,yytext()));}
					case -35:
						break;
					case 35:
						{ return (new Token(Token.ELSE,yytext()));}
>>>>>>> 4f585a2128e2223726d9955e3341b8ee99c27559
					case -36:
						break;
					case 36:
						{ return (new Token(Token.TYPE,yytext()));}
					case -37:
						break;
<<<<<<< HEAD
					case 39:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -38:
						break;
					case 40:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -39:
						break;
					case 42:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -40:
						break;
					case 43:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
=======
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
>>>>>>> 4f585a2128e2223726d9955e3341b8ee99c27559
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
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -46:
						break;
					case 47:
						{ return (new Token(Token.REL_OP,yytext()));}
					case -47:
						break;
					case 48:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
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
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -50:
						break;
					case 52:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -51:
						break;
					case 53:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -52:
						break;
					case 54:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -53:
						break;
					case 56:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -54:
						break;
					case 57:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -55:
						break;
					case 58:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -56:
						break;
					case 60:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -57:
						break;
					case 61:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -58:
						break;
					case 62:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -59:
						break;
					case 64:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -60:
						break;
					case 65:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -61:
						break;
					case 67:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -62:
						break;
					case 69:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -63:
						break;
					case 71:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -64:
						break;
					case 73:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -65:
						break;
					case 75:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -66:
						break;
					case 77:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -67:
						break;
					case 79:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -68:
						break;
					case 81:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -69:
						break;
					case 83:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -70:
						break;
					case 85:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -71:
						break;
					case 123:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -72:
						break;
					case 125:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input:  "+yytext()  +" in line "+(yyline+1)));
}
					case -73:
						break;
					case 126:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
	return (new Token(Token.ERROR, "STRING ERROR"));
}
					case -74:
						break;
					case 127:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -75:
						break;
					case 128:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -76:
						break;
					case 129:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -77:
						break;
					case 130:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -78:
						break;
					case 131:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -79:
						break;
					case 132:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -80:
						break;
					case 133:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -81:
						break;
					case 134:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -82:
						break;
					case 135:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -83:
						break;
					case 136:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -84:
						break;
					case 137:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -85:
						break;
					case 138:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -86:
						break;
					case 139:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -87:
						break;
					case 140:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -88:
						break;
					case 141:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -89:
						break;
					case 142:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -90:
						break;
					case 143:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -91:
						break;
					case 144:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -92:
						break;
					case 145:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -93:
						break;
					case 146:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -94:
						break;
					case 147:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -95:
						break;
					case 148:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -96:
						break;
					case 149:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -97:
						break;
					case 150:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -98:
						break;
					case 151:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -99:
						break;
					case 152:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -100:
						break;
					case 153:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -101:
						break;
					case 154:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -102:
						break;
					case 155:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -103:
						break;
					case 156:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -104:
						break;
					case 157:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -105:
						break;
					case 158:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -106:
						break;
					case 159:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -107:
						break;
					case 160:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -108:
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
