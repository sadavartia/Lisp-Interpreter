

public class myScanner 
{
	
	
	public static boolean isValid(String input)
	{
		boolean valid = true;
		int pos=0;
		char cur;
		
		while(pos < input.length())
		{
			cur = input.charAt(pos);
			if ( (cur >= 48 && cur <=57) || (cur >=65 && cur<=90)
					|| (cur == '(' )|| (cur == ')' ) || (cur == '.' )	
					|| (cur == '-' )|| (cur == '+' )|| (cur == ' ' )
					|| (cur == '\n')|| (cur == '\t' ))
			{
				//Need to check for + and -
				if(cur == '+' || cur == '-')
				{
					if(input.charAt(pos+1) == ' ')// As we know + or - can be followed only by a numericAtom like +12,-12
					{
						System.out.println("ERROR: Illegal character in input.");
						System.out.println("ERROR:'+,-' not handled correctly");
						valid = false;
						System.exit(1);
					}
				}
				
			}
			else
			{
				//Not allowed
				valid = false;
				return valid;
			}
			pos++;
			
		}
		
		return valid;
	}
	
	public static boolean isValidLiteral(String input)
	{
		if (input.isEmpty()) 
		{
			return false;
		}
		if (!Character.isJavaIdentifierStart(input.charAt(0))) 
		{
			return false;
		}
		for (int i = 1; i < input.length(); i++) {
			if(input.charAt(i)== '.')
			{
				System.out.println("ERROR: in DOT notation. Not a valid Literal");
				return false;
			}
			if (!Character.isJavaIdentifierPart(input.charAt(i))) {
				return false;
			}
		}
		return true;
		
	}
	
	public static boolean isValidNumeric(String input)
	{
		boolean valid = false;
		//Check for '+' and '-' valid inputs
		if (input.charAt(0)== '+' || input.charAt(0)== '-')
		{
			int next = 1;
			while (next<input.length())
			{
				if(input.charAt(next) >= 48 && input.charAt(next) <= 57) 
				{
					next++;
					continue;
				}
				else
				{
					valid = false;
					break;
				}
			}
			if(next == input.length())
				valid = true;
		}
		else 
		{
			//To check for valid integers
			int next = 0;
			while (next<input.length())
			{
				if(input.charAt(next) >= 48 && input.charAt(next) <= 57)
				{
					next++;
					continue;
				}
				else 
				{
					valid = false;
					break;
				}
				
			}
			if (next == input.length()) 
				valid = true;
		}
		return valid;
	}
} 



	/*
	 * 
	 * Changed approach from StreamTokenizer to StringTokenizer 
	public ArrayList<String> scannerReturn(StreamTokenizer st) throws IOException{
		int open = 0;
		
		
		ArrayList<String> tokens =new ArrayList<String>();
		try
		{
			//Reader in = new BufferedReader(new InputStreamReader(System.in));
		//	InputStream b = new InputStream();
			//InputStreamReader a=new InputStreamReader(b);
			//StreamTokenizer st1 = new StreamTokenizer(a);
			
			st.eolIsSignificant(true);
			st.ordinaryChars(' ','.');
			
			boolean eof = false;
			do 
			{
				
				//int token=st.nextToken();
				st.nextToken(); 
        	
				if (st.ttype == StreamTokenizer.TT_EOF)
				{
					tokens.add("EndOfFile");
					eof = true;
				}
				else if (st.ttype == StreamTokenizer.TT_EOL){
					tokens.add("EndOfLine");


				}
				else if (st.ttype == StreamTokenizer.TT_WORD){
        		
    				tokens.add("Atom:" + (st.sval).toUpperCase());
				}
        	
				else if (st.ttype == StreamTokenizer.TT_NUMBER){
					
							tokens.add("NumericAtom:" + st.nval);
        					
        		}
				else if((char)st.ttype == '(')
				{
					open++;
					tokens.add("OpenParanthesis");
				}
				else if((char)st.ttype == ')')
				{
					if(open>0)
					{
						open--;
						tokens.add("ClosingParanthesis");

					}
					else
					{
						System.out.println("Unequal number of Paranthesis");
						tokens.add("ErrorToken");
						System.exit(1);
					}
        		
				}
				
				
				else if(st.ttype == 45 || st.ttype == 43)
				{
					
					if(st.ttype == 45)
					{
						
						//char sym= (char)st.nval;
						//st1=st;
						//st1.nextToken();
						//st1.nextToken();
						
						if(st.ttype == StreamTokenizer.TT_NUMBER)
						{
							tokens.add("NumericAtom:" + -st.nval);
						}
						
						
						else
						{
							System.out.println("\nError Token : Illegal use of Minus");
							
							
						}
							
						
						
							
					}
					if(st.ttype == 43)
					{
						
						tokens.add("+");
							
					}
					
													
					
				} 
				
				else if(st.ttype==' ')
				{
						tokens.add("Space");
						
				}
				
				else if (st.ttype == '.' )
				{
					
					tokens.add("Dot");
					
        		}
        	else
				{
        		System.out.println("\nError Token");
				System.exit(1);

					
				}
				
			if(open==0)
				return tokens;
				
			} while(true);


        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
		return tokens;
	
		
		
	}

	*/
	



