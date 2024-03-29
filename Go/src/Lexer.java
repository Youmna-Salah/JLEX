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
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
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
		/* 106 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"28:8,18:2,19,28:2,39,28:18,18,28,36,28:2,34,33,28,43,46,25,23,35,24,49,27,4" +
"2:10,31,32,20,22,21,28:2,41:26,44,37,47,28:2,38,2,41,3,15,6,16,5,14,13,41,4" +
",17,29,26,30,1,41,10,9,7,11,40,12,41,8,41,45,28,48,28:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,107,
"0,1,2,1,3:2,4,1:2,5,1,6,1:3,7,8,9,1:7,10,4,11,1:2,10,1:2,10:12,12,13,1,14,1" +
"2,15,16,17,15,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,3" +
"8,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,6" +
"3,64,65,66,67,68,10,69")[0];

	private int yy_nxt[][] = unpackFromString(70,50,
"1,2,105,85,105:2,86,87,105,101,102,105:2,46,105,106,72,105,3:2,4,47,5,6,7,8" +
",105,9,10,105:2,11,12,48,13,14,15,10,16,-1,73,105,17,18,19,20,21,22,23,24,-" +
"1:51,105,103,105:15,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:29,47,-1:50,26,-1:" +
"53,27,-1:44,28,-1:28,45:18,-1,45:16,-1,51,-1,45:11,-1,53:18,-1,53:16,-1,55," +
"-1,53:11,-1:42,17,-1:8,105:17,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,27:18," +
"-1,27:19,-1,27:10,-1,45:18,-1,45:16,31,51,-1,45:11,-1,105:15,25,105,-1:8,10" +
"5,-1:2,98,105,-1:9,105:2,52,-1:40,29,-1:17,53:18,-1,53:16,-1,55,32,53:11,-1" +
",45:17,57,59,45:16,49,51,45:12,-1:42,52,-1:8,105:9,30,105:7,-1:8,105,-1:2,1" +
"05:2,-1:9,105:2,52,-1:8,53:17,61,63,53:17,55,50,53:11,-1,105:9,33,105:7,-1:" +
"8,105,-1:2,105:2,-1:9,105:2,52,-1:8,45:17,57,59,45:16,31,51,-1,45:11,-1,105" +
":5,34,105:11,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:25,59:2,-1:17,45,-1:13,10" +
"5:5,35,105:11,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,53:17,61,63,53:16,-1,5" +
"5,32,53:11,-1,105:5,36,105:11,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:25,63:2," +
"-1:17,53,-1:13,105:2,37,105:14,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:6" +
",38,105:10,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:6,39,105:10,-1:8,105," +
"-1:2,105:2,-1:9,105:2,52,-1:8,105:13,40,105:3,-1:8,105,-1:2,105:2,-1:9,105:" +
"2,52,-1:8,105:17,-1:8,41,-1:2,105:2,-1:9,105:2,52,-1:8,105:6,42,105:10,-1:8" +
",105,-1:2,105:2,-1:9,105:2,52,-1:8,105:5,43,105:11,-1:8,105,-1:2,105:2,-1:9" +
",105:2,52,-1:8,105:6,44,105:10,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:1" +
"0,77,105:6,-1:8,105,-1:2,105,54,-1:9,105:2,52,-1:8,105,56,105:15,-1:8,105,-" +
"1:2,105:2,-1:9,105:2,52,-1:8,105:8,58,105:8,-1:8,105,-1:2,105:2,-1:9,105:2," +
"52,-1:8,105:8,60,105:8,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,62,105:16,-1:" +
"8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:17,-1:8,64,-1:2,105:2,-1:9,105:2,52" +
",-1:8,105:8,65,105:8,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:2,66,105:14" +
",-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:2,67,105:14,-1:8,105,-1:2,105:2" +
",-1:9,105:2,52,-1:8,105:9,68,105:7,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,1" +
"05:9,69,105:7,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:4,70,105:12,-1:8,1" +
"05,-1:2,105:2,-1:9,105:2,52,-1:8,105:16,71,-1:8,105,-1:2,105:2,-1:9,105:2,5" +
"2,-1:8,105,74,105:15,-1:8,105,-1:2,105,88,-1:9,105:2,52,-1:8,105:16,75,-1:8" +
",105,-1:2,105:2,-1:9,105:2,52,-1:8,105:7,76,105:9,-1:8,105,-1:2,105:2,-1:9," +
"105:2,52,-1:8,105:17,-1:8,78,-1:2,105:2,-1:9,105:2,52,-1:8,105:10,79,105:6," +
"-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:6,80,105:10,-1:8,105,-1:2,105:2," +
"-1:9,105:2,52,-1:8,105:10,81,105:6,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,1" +
"05:17,-1:8,105,-1:2,105,82,-1:9,105:2,52,-1:8,105,83,105:15,-1:8,105,-1:2,1" +
"05:2,-1:9,105:2,52,-1:8,105:10,84,105:6,-1:8,105,-1:2,105:2,-1:9,105:2,52,-" +
"1:8,105:9,89,105:7,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:12,90,105:4,-" +
"1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:6,91,105:10,-1:8,105,-1:2,105:2,-" +
"1:9,105:2,52,-1:8,92,105:16,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:3,93" +
",105:13,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105,94,105:15,-1:8,105,-1:2," +
"105:2,-1:9,105:2,52,-1:8,105:6,95,105:4,96,105:5,-1:8,105,-1:2,105:2,-1:9,1" +
"05:2,52,-1:8,105:5,97,105:11,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:2,9" +
"9,105:14,-1:8,105,-1:2,105:2,-1:9,105:2,52,-1:8,105:15,100,105,-1:8,105,-1:" +
"2,105:2,-1:9,105:2,52,-1:8,105:5,104,105:11,-1:8,105,-1:2,105:2,-1:9,105:2," +
"52,-1:7");

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
			return (new Token(Token.EOF, "There is some { that is not closed"));
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
					case -10:
						break;
					case 10:
						{
  return new Token(Token.ERROR, "Invalid input " + yytext());
}
					case -11:
						break;
					case 11:
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
return (new Token(Token.ERROR, "Invalid input: "+yytext()  +" in line "+(yyline+1)));
}
					case -16:
						break;
					case 16:
						{
	String str =  yytext().substring(1,yytext().length());
return (new Token(Token.ERROR, "Invalid input: "+ yytext()  +" in line "+yyline));
}
					case -17:
						break;
					case 17:
						{ return (new Token(Token.INT_LIT,yytext()));}
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
			Stack<Character> temp_stack = new Stack<Character>();
			temp_stack.push(temp);
			boolean close_paran = true;
			while(!stack_char.isEmpty()) {
				temp = stack_char.pop();
				temp_stack.push(temp);
				if(temp == '(') {
					close_paran = false;
				}
			}
			while(!temp_stack.isEmpty()) {
				stack_char.push(temp_stack.pop());
			}
			if(close_paran) {
				return (new Token(Token.ERROR, ") has no matching ( in line " + (yyline+1)));
			} else {
				if(temp == '{') {
					curly = true;
				}
				if(temp == '[') {
					square = true;
				}
				return (new Token(Token.ERROR, "You have a missing bracket in line " + (yyline+1)));
			}		
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
				Stack<Character> temp_stack = new Stack<Character>();
				temp_stack.push(temp);
				boolean close_square = true;
				while(!stack_char.isEmpty()) {
					temp = stack_char.pop();
					temp_stack.push(temp);
					if(temp == '[') {
						close_square = false;
					}
				}
				while(!temp_stack.isEmpty()) {
					stack_char.push(temp_stack.pop());
				}
				if(close_square) {
					return (new Token(Token.ERROR, "] has no matching [ in line " + (yyline+1)));
				} else {
					if(temp == '(') {
						paran = true;
					}
					if(temp == '{') {
						curly = true;
					}					
					return (new Token(Token.ERROR, "You have a missing bracket in line " + (yyline+1)));
				} 
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
			Stack<Character> temp_stack = new Stack<Character>();
			temp_stack.push(temp);
			boolean close_curly = true;
			while(!stack_char.isEmpty()) {
				temp = stack_char.pop();
				temp_stack.push(temp);
				if(temp == '{') {
					close_curly = false;
				}
			}
			while(!temp_stack.isEmpty()) {
				stack_char.push(temp_stack.pop());
			}
			if(close_curly) {
				return (new Token(Token.ERROR, "} has no matching { in line " + (yyline+1)));
			} else {
				temp = stack_char.peek();
				if(temp == '(') {
					paran = true;
				}
				if(temp == '[') {
					square = true;
				}
				return (new Token(Token.ERROR, "You have a missing bracket in line " + (yyline+1)));
			}
		}
	}
					case -24:
						break;
					case 24:
						{ return (new Token(Token.DOT,yytext()));}
					case -25:
						break;
					case 25:
						{ return (new Token(Token.IF,yytext()));}
					case -26:
						break;
					case 26:
						{ return (new Token(Token.INCREMENT,yytext()));}
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
						{ return (new Token(Token.FOR,yytext()));}
					case -31:
						break;
					case 31:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input: "+yytext()  +" in line "+(yyline+1)));
}
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
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -46:
						break;
					case 47:
						{ return (new Token(Token.REL_OP,yytext()));}
					case -47:
						break;
					case 48:
						{
  return new Token(Token.ERROR, "Invalid input " + yytext());
}
					case -48:
						break;
					case 49:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	if(str.length() == yytext().length() - 2)
	return (new Token(Token.STRING_LIT, yytext()));
	else
return (new Token(Token.ERROR, "Invalid input: "+yytext()  +" in line "+(yyline+1)));
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
					case 54:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -52:
						break;
					case 56:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -53:
						break;
					case 58:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -54:
						break;
					case 60:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -55:
						break;
					case 62:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -56:
						break;
					case 64:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -57:
						break;
					case 65:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -58:
						break;
					case 66:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -59:
						break;
					case 67:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -60:
						break;
					case 68:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -61:
						break;
					case 69:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -62:
						break;
					case 70:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -63:
						break;
					case 71:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -64:
						break;
					case 72:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -65:
						break;
					case 73:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -66:
						break;
					case 74:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -67:
						break;
					case 75:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -68:
						break;
					case 76:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -69:
						break;
					case 77:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -70:
						break;
					case 78:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -71:
						break;
					case 79:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -72:
						break;
					case 80:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -73:
						break;
					case 81:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -74:
						break;
					case 82:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -75:
						break;
					case 83:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -76:
						break;
					case 84:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -77:
						break;
					case 85:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -78:
						break;
					case 86:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -79:
						break;
					case 87:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -80:
						break;
					case 88:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -81:
						break;
					case 89:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -82:
						break;
					case 90:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -83:
						break;
					case 91:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -84:
						break;
					case 92:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -85:
						break;
					case 93:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -86:
						break;
					case 94:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -87:
						break;
					case 95:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -88:
						break;
					case 96:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -89:
						break;
					case 97:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -90:
						break;
					case 98:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -91:
						break;
					case 99:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -92:
						break;
					case 100:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -93:
						break;
					case 101:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -94:
						break;
					case 102:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -95:
						break;
					case 103:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -96:
						break;
					case 104:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -97:
						break;
					case 105:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -98:
						break;
					case 106:
						{ return (new Token(Token.IDENTIFIER,yytext()));}
					case -99:
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
