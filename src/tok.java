import java.util.StringTokenizer;

public class tok
{
	// public String type;
	 //public String value;
	 public StringTokenizer tokenizer;
	 public String current;
	 public String currentLine;
	
	public tok()
	{
		//type = new String();
		//value = new String();
		tokenizer=null;
		current=null;
		currentLine =null;
	}
	public tok(String line) {
			currentLine = line;
			//Delimiters - '(', ')' , ' ', '\t', '\n' and dot taken care of
	    	tokenizer = new StringTokenizer(currentLine, "() \t\n", true);
	    	current = null;
	  }
	public void init(String a)
	{
		current = a;
	
	}
	
	public String getTokenVals()
	{
		
		if(current != null) 
		{
			return current;
    	}
		else 
		{
			if(tokenizer.hasMoreTokens()) 
			{
				current = tokenizer.nextToken();
			
				while(Character.isWhitespace(current.charAt(0)) )
				{
						if(tokenizer.hasMoreTokens())
						current = tokenizer.nextToken();
				}
			}
			else
			{
				current = null;
			}
		}
		return current;
		
		/*
		tok b = new tok();
		String s= a.peek();
		
		if(s.startsWith("A",0))
		{
			 b.type = "ATOM";
			 b.value = s.substring(5);
			 
		}
		
		else
			if(s.startsWith("N",0))
			{
				 b.type = "NUMERICATOM";
				 b.value = s.substring(12);
				 
				 if(b.value.contains(".0"))
				 {
					 int d=b.value.indexOf(".");
					 b.value = b.value.substring(0,d);
					 
				 }
				 
			}
		else
			if(s.startsWith("O",0))
				{
					 b.type = "(";
					 b.value = "(";
				}
		else
			if(s.startsWith("C",0))
				{
					 b.type = ")";
					 b.value = ")";
				}
		else
				if(s.startsWith("$",0))
					{
						 b.type = "$";
						 b.value = "$";
					}
		else
				if(s.startsWith("D",0))
				{
						b.type = ".";
						b.value = ".";
					
				}
				else
					if(s.startsWith("V",0))
					{
						 b.type = "VALID";
						 b.value = "VALID";
					}
		else
		{
				b.type= "Err";
				b.value="Err";
		}
		
				
		
		return b;*/
			
		
	}
	
}
