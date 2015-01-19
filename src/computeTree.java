import java.io.IOException;


public class computeTree {

	
	public Node root;
	private static tok tokens;
	private static String current;
	
	computeTree(String line)
	{
		root = null;
		tokens= new tok(line);
		current=line;
	}
	
	
	public void sEval() throws IOException
	{
		
		 
		
		boolean valid = myScanner.isValid(current);
		if(valid == true)
		{
				root = new Node();
				root.isNonTerminal=true;
				parseE(root);
				computeIsList(root);
				
				/*
				
				
				a = p.CONS(root,root);*/
				//helpers h = new helpers();
				//print.printTree(root);
				//Node a = new Node();
				//a=h.eval(root, NULL, NULL);
				
				
		}
		else
		{
			System.out.println("ERROR:Wrong Input!!");
			System.exit(1);
		}
				
	}
		

	
	
	/*
	 *  The given grammar is as follows:
	 *  <S>::=<E>      <E>::= atom    <E>::=(<X>
	 *  <X>::=<E><Y>   <X>::=) 	      <Y>::=.<E>)
	 *  <Y>::=<R>)     <R>::=NIL      <R>::=<E><R> 
	 */
	
	// Rule is <S>::=<E>
	
	private void parseE(Node node) {
			
		String a = tokens.getTokenVals();
		//node.value = a;
		if(a.equals("("))
		{
			//Rule is <E>::=(<X>
			tokens.init(null);
			//node.isList=true;
			node.value = "";
			
			ParseX(node);
			
			
		}
		else
		{
			//Rule is <E>::= atom
			if(myScanner.isValidNumeric(a))
			{
				
				if(a.charAt(0) == '+')
				{
					a = a.substring(1);
				}
				
				node.value = a;
				node.isAtom = true;
				node.isNill = false;
				node.type = "NUMERICATOM";
				node.isNumber = true;
				node.isNonTerminal = false;
			}
			
			
			else if(myScanner.isValidLiteral(a))
			{
				
				node.value = a;
				node.isAtom = true;
				node.isNill = false;
				
				node.isNumber = false;
				node.isNonTerminal = false;
				node.type = "ATOM";
			}
			else
			{
				System.out.println("ERROR:Error Token!!");
				System.exit(1);
			}
			tokens.init(null);
			
		}
		
	}



	private void ParseX(Node node) {
		String a = tokens.getTokenVals();
		//node.value = a;
		
		if (a.equals(")"))
		{
			//Rule is <X>::=)
			tokens.init(null);
			node.isAtom=true;
			node.value="NIL";
			node.isNill=true;
			node.type = "ATOM";
			node.isNonTerminal = true;
		}
		else
		{
			//Rule is <X>::=<E><Y>
			tokens.init(a);
			Node left = new Node();
			node.left = left;
			node.value ="";
			node.isNonTerminal = true;
			parseE(left);
			//node.value = "NIL";
			
			Node right = new Node();
			node.isNonTerminal = true;
			node.value ="";
			node.right = right;
			parseY(right);
		}
		
	}



	private void parseY(Node node) 
	{
		

		String a = tokens.getTokenVals();
	//	node.value = a; 
		if(a.equals("."))
		{
			//Rule is <Y>::=.<E>)
			tokens.init(null);
			node.value ="";
			parseE(node);
			
			String b=tokens.getTokenVals();
			
			
			if(b.equals(")"))
			{
				
				tokens.init(null);
			}
			else
			{
				System.out.println("ERROR:Error in Rule - <Y>::=.<E>");
			}
		}
		else
		{
			//Rule is <Y>::=<R>)
			tokens.init(a);
			parseR(node);
			
			String b = tokens.getTokenVals();
			
			if(b.equals(")"))
			{
				
				tokens.init(null);
			}
			else
			{
				System.out.println("ERROR:Problem in rule-- <Y>::=<R>) ");
				tokens.init(b);
				
			}
		}
				
	}



	private void parseR(Node node) 
	{
		String a = tokens.getTokenVals();
		//node.value = a; 
		if((a!=null) && (!a.equals(")")))
		{
				//Rule is <R>::=<E><R>
				Node left = new Node();
				node.left = left;
				node.value ="";
				node.isNonTerminal = true;
				parseE(left);

				Node right = new Node();
				node.right = right;
				node.value ="";
				node.isNonTerminal = true;
				parseR(right);
		}
		else
		{
			//System.out.println("\nInside R");
			//Reached end of string, parsing complete
			//Rule is <R>::=NIL
			node.value = "NIL";
			node.isNill = true;
			node.isList=false;
			node.isAtom = true;
			node.type= "ATOM";
			node.isNonTerminal = false;
			
		}
		
	}
	



private static void computeIsList(Node result)
{
	if (result != null && rightMostChildIsNil(result)) 
	{
		result.isList = true;
	}
	if (result.left != null && result.left.isNonTerminal == true) 
	{
		
		computeIsList(result.left);
	}
	while (result.right != null) 
	{
		result = result.right;
		if(result.left != null && result.left.isNonTerminal == true) 
		{
			
			computeIsList(result.left);
		}
	}
	
}

private static boolean rightMostChildIsNil(Node root) 
{
	while (root.right != null) 
	{
		root = root.right;
	}
	
	if(root.value == "NIL") 
	{
		return true;
	}
	return false;
}
		
	
}