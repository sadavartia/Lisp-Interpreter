import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class helpers {
	
	// Defining the eval function
	
	static Node nil= new Node("NIL");
	static Node trueNode = new Node("T");
	public static Hashtable<String, Integer> numParamofUserFunc = new Hashtable<String, Integer>();
	public Node deflist = new Node("NIL");
	
	//Node DLIST = new Node("NIL");
	//Node aList = new Node();
	
	public static Node evaluate(Node a)
	{
		
		//print.printTree(DList.deflist);
		return eval(a,nil,DList.deflist);
	}
	
	public static Node eval(Node node,Node a,Node d)
	{	
		
		//print.printTree(node);
		if(node.isAtom == true)
		{
			
			
			//node is an integer
			if(node.type.equalsIgnoreCase("NUMERICATOM"))
			{
				return node;
			}
			// node is an Atom
			else if(node.type.equalsIgnoreCase("ATOM"))
			{
				//node is T
				if(node.value.equalsIgnoreCase("T"))
				{
					return  trueNode;
				}
				//node is NIL
				else if(node.value.equalsIgnoreCase("NIL"))
				{
					return  nil;
				
				}
			//To find if the aList contains atom node
			
			
				else 
				{
					if (bound(node, a))
					{ 
						return getval(node,a);
					}
					else
					{
				
						System.out.println("ERROR: Unbound variable");
						System.exit(1);
						return nil;
					}
				}
			}
			else
			{	
				return nil;
			}
			
		}
		else
		{
			//All the checks ofcourse will happen with the CAR of the list
			
			String car = node.left.value;
			//System.out.println(car.value + "Inside islist==true");
			//BTreePrinter print = new BTreePrinter();
			//print.printTree(node);
			
			// In case the list contains a Quote then Quote is evaluated
			if(car.equalsIgnoreCase("QUOTE"))
			{
				
				//print.printTree(node);
					int params = FindNumberOfParams(node.right);
					if(params == 1)
					{
						return CAR(CDR(node));
					}
					else
					{
						System.out.println("ERROR: in Quote Declaration");
						System.exit(1);
						return nil;
					}
					
			
			}
			// In case the list contains a COND then COND is evaluated
			else if(car.equalsIgnoreCase("COND"))
			{ 
				return COND( CDR(node),a,d);
			}
			// In case the list contains a DEFUN then DEFUN is evaluated and the result is added to the dList
			else if(car.equalsIgnoreCase("DEFUN"))
			{ 
				return DEFUN(node);
			}
			
			else
			{
				//call to apply function will be here
				return apply(CAR(node),evlist(CDR(node),a,d),a,d);
			}
			
			
		}
		
		
	}

	// definition of evlist
	private static Node evlist(Node node, Node aList,Node d) {
		
		if(node.value.equalsIgnoreCase("NIL"))
		{
			return  nil;
		}
		else
		{
			return  CONS(eval(CAR(node),aList,d),evlist(CDR(node),aList,d));
		}
	}

	//definition of getval
	private static Node getval(Node node, Node aList) {
		
			if(aList == nil)
			{
				return  nil;
			}
			else
			{
				if(EQ(node,CAR(CAR(aList))).value.equalsIgnoreCase("T"))
				{
					return  (CDR(CAR(aList)));  // if eq[node,caar[aList]] -> cdar[alist]
				}
				else
					return getval(node, CDR(aList));
			}
	}

	//definition of bound
	private static boolean bound(Node node, Node aList) {
			
			
			if(aList == nil || node == null || aList.left == null)
			{
				return false;
			}
			else
			{
				if( CAR(aList) != null &&  CAR( CAR(aList)) != null)
				{
					if(node.value !=null && node.value.equalsIgnoreCase(( CAR( CAR(aList))).value))
					{
						return true;
					}
					else
					{
						return bound(node, CDR(aList));
					}
				}
				else
				{
					return false;
				}
			}
			
	}
	
	//definition of apply
	private static Node apply(Node f, Node x, Node a, Node d) {
		
		//This is for number of param checks for each call
		//System.out.println(x.value + "=value of x");
		int numOfParams =  FindNumberOfParams(x);
		if(f.isAtom == true)
		{
		
			if(f.value.equalsIgnoreCase("CAR"))
			{
				if(numOfParams ==1)
				{
					return  CAR(CAR(x));
				}
			}
			else if(f.value.equalsIgnoreCase("CDR"))
			{
				if(numOfParams ==1)
				{
					return  CDR(CAR(x));
				}
				
			}
			else if(f.value.equalsIgnoreCase("CONS"))
			{
				if(numOfParams == 2)
				{
				return  CONS(CAR(x),CAR(CDR(x)));
				}
				
			}
			else if(f.value.equalsIgnoreCase("ATOM"))
			{
				if(numOfParams ==1)
				{
					return  ATOM(CAR(x));
				}
				
			}
			else if(f.value.equalsIgnoreCase("EQ"))
			{
				if(numOfParams == 2)
				{
					return  EQ(CAR(x), CAR(CDR(x)));
				}
				
			}
			else if(f.value.equalsIgnoreCase("INT"))
			{
				if(numOfParams ==1)
				{
					return  INT(CAR(x));
				}
				
			}
			else if(f.value.equalsIgnoreCase("NULL"))
			{
				if(numOfParams ==1)
				{
					return  NULL(CAR(x));
				}
				
			}
			else if(f.value.equalsIgnoreCase("PLUS"))
			{
				if(numOfParams ==2)
				{
					return  PLUS(CAR(x), CAR(CDR(x)));
				}
				
			}
			else if(f.value.equalsIgnoreCase("MINUS"))
			{
				if(numOfParams ==2)
				{
				
					return  MINUS(CAR(x), CAR(CDR(x)));
				}
				
			}
			else if(f.value.equalsIgnoreCase("TIMES"))
			{
				if(numOfParams ==2)
				{
				
					return  TIMES(CAR(x),CAR(CDR(x)));
				}
				
			}
			else if(f.value.equalsIgnoreCase("QUOTIENT"))
			{
				if(numOfParams ==2)
				{
				
					return  QUOTIENT(CAR(x), CAR(CDR(x)));
				}
				
			}
			else if(f.value.equalsIgnoreCase("REMAINDER"))
			{
				if(numOfParams ==2)
				{
				
					return  REMAINDER(CAR(x),CAR(CDR(x)));
				}
				
			}
			else if(f.value.equalsIgnoreCase("LESS"))
			{
				if(numOfParams ==2)
				{
				
					return  LESS(CAR(x) , CAR(CDR(x)));
				}
				
			}
			else if(f.value.equalsIgnoreCase("GREATER"))
			{
				if(numOfParams ==2)
				{
				
					return  GREATER(CAR(x), CAR(CDR(x)));
				}
				
			}
			else
			{
				//System.out.println(f.value + " ");
				if(searchdeflist(f, d))
				{
					//boolean b =numParamofUserFunc.containsValue(f.value);
					//System.out.println("numparam contains f during 2nd call "+ b);
					
					//boolean n =numParamofUserFunc.isEmpty();
					//System.out.println("numparam is empty "+n);
					
					if(numParamofUserFunc.get(f.value) == numOfParams)
					{
						return eval( CDR(getval(f,d)),addpairs(CAR(getval(f,d)),x,a),d);
					}
					else
					{
						System.out.println("ERROR:Wrong number of parameters entered while calling the function ");
						System.exit(1);
					}
				}
				else
				{
					System.out.println("ERROR: Calling an undefined entity");
					System.exit(1);
				}
				
			}
			System.out.println("ERROR: Wrong input declaration");
			System.exit(1);
			return nil;
		}
		else
		{
			return nil;
		}
		
		
	}

	private static boolean searchdeflist(Node exp, Node list) {
		if(list.value.equalsIgnoreCase("NIL") || exp.value.equalsIgnoreCase("NIL"))
		{
			return false;
		}
		if( EQ(exp,  CAR( CAR(list))).value.equalsIgnoreCase("T"))
		{
			return true;
		}
		else
		{
			return searchdeflist(exp,  CDR(list));
		}
	}

	private static Node addpairs(Node varList, Node valueList, Node oldList) {
		
			
			if(varList.value.equalsIgnoreCase("NIL"))
			{
				return oldList;
			}
			else
			{
				
				return  (CONS( CONS( CAR(varList),  CAR(valueList)), addpairs( CDR(varList), CDR(valueList),oldList)));
			}
	}
	
	
	/* The primitives to be handled are:
	 * T * NIL * CAR * CDR * CONS * ATOM * EQ * NULL * INT * PLUS * MINUS * TIMES * QUOTIENT * REMAINDER * LESS * GREATER
	 * COND * DEFUN
	 */

	// Function defining T
	
	//Creating helper class instance
	
	public static Node T(Node node)
	{
				node.isNill = false;
				node.isList=false;
				node.isNumber=false;
				node.value ="T";
				return node; 
		  			
	}
	
	//Function defining NIL
	
	public static Node NIL(Node node)
	{
				node.isNill = true;
				node.isList=false;
				node.isNumber=false;
				node.value ="NIL";
				return node; 
		  			
	}
	
	//Function defining car
	
	public static Node CAR(Node node)
	{
	
			if(node.isAtom == true)
			{
				System.out.println("ERROR:CAR can only be called on a List");
				System.exit(1);
			
			}
			return node.left;
	}
	
	//Function defining cdr
	
	public static Node CDR(Node node)
	{
		
			if(node.isAtom == true)
			{
				System.out.println("ERROR:CDR can only be called on a List");
				System.exit(1);
				
			}
			return node.right;
		
	}
	
	//Function defining cons
	
	public static Node CONS(Node node,Node node1)
	{
				Node cons = new Node(node,node1);
				return cons;
		
	}
	
	//Function defining atom
	
	public static Node ATOM(Node node)
	{
				//If the parameter has a sub-expression thus would not be an atom
				if(node.isAtom == true && node.right == null)
				{
					return trueNode;
					
				}
				//The Node is an atom as isList is false
				else
				
					return nil;

				
			  			
	}
	
	// Function defining eq
	
	public static Node EQ(Node node,Node node1)
	{
				//The only two parameters that can be passed need to be atoms
				if(node.isAtom == true && node1.isAtom == true)
				{
					// Check if values are equal
					if(node.value.equalsIgnoreCase(node1.value))
					{
						return trueNode;
					}
					else
					{
						return nil;
					}
					
				}
				else
				{
					//Except atoms parameter type is invalid
					System.out.println("ERROR:EQ definition wrong : The two parameters need to be ATOMS");
					System.exit(1);
					return nil;
				}
					
	}
	
	// Function defining Null
	
	public static Node NULL(Node node)
	{
				if(node.value.contentEquals("NIL"))
				{
					return trueNode;
					
				}
				else
				{
					return nil;
				}		
			  			
	}
	
	//Function defining Int
	
	public static Node INT(Node node)
	{
				if(node.isAtom == true && node.type.equalsIgnoreCase("NUMERICATOM"))
				{
					return trueNode;
				}
				else
				{
					System.out.println("ERROR:INT only defined for atoms");
					System.exit(1);
					return nil;
				}
				
	}
	
	//Function defining Plus
	
	public static Node PLUS(Node node1,Node node2)
	{
		
		if(node1.type.equalsIgnoreCase("NUMERICATOM") && node2.type.equalsIgnoreCase("NUMERICATOM"))
		{
			int a = Integer.parseInt(node1.value) + Integer.parseInt(node2.value);
			Node result = new Node(a);
			return result;
			
		}
		else
		{
			// Plus cannot handle parameters which are not integer and not atoms
			System.out.println("ERROR:PLUS cannot handle inputs you have provided !! Check input");
			System.exit(1);
			
		}
		return nil;
	}
	
	//Function defining Minus
	
	public static Node MINUS(Node node1,Node node2)
	{
			if(node1.type.equalsIgnoreCase("NUMERICATOM") && node2.type.equalsIgnoreCase("NUMERICATOM"))
			{
				int a = Integer.parseInt(node1.value) - Integer.parseInt(node2.value);
				Node result = new Node(a);
				return result;
				
			}
			else
			{
				//Minus cannot handle parameters which are not integer and not atoms
				System.out.println("ERROR:MINUS cannot handle inputs you have provided !! Check input");
				System.exit(1);
				
			}
			return nil;
			
	}
	
	//Function defining Times
	
	public static Node TIMES(Node node1,Node node2)
	{
				if(node1.type.equalsIgnoreCase("NUMERICATOM") && node2.type.equalsIgnoreCase("NUMERICATOM"))
				{
					int a = Integer.parseInt(node1.value) * Integer.parseInt(node2.value);
					Node result = new Node(a);
					return result;
					
				}
				else
				{
					//Times cannot handle parameters which are not integer and not atoms
					System.out.println("ERROR:TIMES cannot handle inputs you have provided !! Check input");
					System.exit(1);
					
				}
				return nil;
				
	}
	
	//Function defining Quotient
	
	public static Node QUOTIENT(Node node1,Node node2)
	{
				if(node1.type.equalsIgnoreCase("NUMERICATOM") && node2.type.equalsIgnoreCase("NUMERICATOM"))
				{
					int a = Integer.parseInt(node1.value) / Integer.parseInt(node2.value);
					Node result = new Node(a);
					return result;
					
				}
				else
				{
					// Quotient cannot handle parameters which are not integer and not atoms
					System.out.println("ERROR:QUOTIENT cannot handle inputs you have provided !! Check input");
					System.exit(1);
					
				}
				return nil;
				
	}
	
	//Function defining Remainder
	
	public static Node REMAINDER(Node node1,Node node2)
	{
				
				if(node1.type.equalsIgnoreCase("NUMERICATOM") && node2.type.equalsIgnoreCase("NUMERICATOM"))
				{
					int a = Integer.parseInt(node1.value) % Integer.parseInt(node2.value);
					Node result = new Node(a);
					return result;
					
				}
				else
				{
					//Remainder cannot handle parameters which are not integer and not atoms
					System.out.println("ERROR:REMAINDER cannot handle inputs you have provided !! Check input");
					System.exit(1);
					
				}
				return nil;
				
	}
	
	//Function defining Less
	
	public static Node LESS(Node node1,Node node2)
	{
					//System.out.println(node1.type + node2.type);
					//System.out.println("node1 value:"+node1.value+"node2 value"+node2.value);
					if(node1.type.equalsIgnoreCase("NUMERICATOM") && node2.type.equalsIgnoreCase("NUMERICATOM"))
					{
						int a = Integer.parseInt(node1.value);
						int b =Integer.parseInt(node2.value);
						if(a < b)
						{
							return trueNode;
						}
						else
						{
							return nil;
						}
						
					}
					else
					{
						// Less cannot handle parameters which are not integer and not atoms
						System.out.println("ERROR:LESS cannot handle inputs you have provided !! Check input");
						System.exit(1);
						return nil;
					}
					
					
	}
		
	//Function defining Greater
	
	public static Node GREATER(Node node1,Node node2)
	{
						if(node1.type.equalsIgnoreCase("NUMERICATOM") && node2.type.equalsIgnoreCase("NUMERICATOM"))
						{
							int a = Integer.parseInt(node1.value);
							int b =Integer.parseInt(node2.value);
							if(a>b)
							{
								return trueNode;
							}
							else
							{
								return nil;
							}
							
						}
						else
						{
							// Greater cannot handle parameters which are not integer and not atoms
							System.out.println("ERROR:GREATER cannot handle inputs you have provided !! Check input");
							System.exit(1);
							return nil;
						}
						
	}
	
	
	//Function defining Defun
	
	public static void addtodeflist(Node node)
	{
		Node temp =CONS(CAR(node), CONS(CAR(CDR(node)), CAR(CDR(CDR(node)))));
		DList.deflist = CONS(temp, DList.deflist);
	}
	
	public static Node DEFUN(Node node)
	{
		
					//Implies that the function declaration is valid
					// Thus we need to add stuff to d-list which in our case would be the Node itself which contains defun
					if(checkfunc(node))
					{
						addtodeflist(CDR(node));
					
						int numberOfParams = FindNumberOfParams(CDR(node));
						if(numberOfParams == 3 && checkfunc(node))
						{
							
							return new Node(CAR(CDR(node)).value);
						}
						else
						{
							System.out.println("ERROR:Defun has extra parameters");
							System.exit(1);
							return nil;
						}
					
					}
					else
					{
						//Function has not been defined correctly
						System.out.println("ERROR:DEFUN has not been defined correctly !! Check declaration ");
						System.exit(1);
						return nil;
					}
					
		
	}
	
	//Function defining Cond
	
	private static boolean checkfunc(Node node)
	{
		Node functionname = CAR(CDR(node));
		
		//Dividing and obtaining the function name,formals -- that again into the two parameters and function body
		
		//Check that F needs to be a Literal Atom
		if(functionname.isAtom == true && functionname.type.equalsIgnoreCase("ATOM"))
		{
				Node param = CAR(CDR(CDR(node)));
				Node temp= param;
				ArrayList<String> paramList = new ArrayList<String>();
				
				if(ATOM(temp).value.equalsIgnoreCase("T") && !temp.value.equalsIgnoreCase("NIL"))
				{
					System.out.println("ERROR:Parameter to be bound in a list");
					System.exit(1);
				}
					while(temp !=null)
					{
						if(temp.left !=null)
						{
							if(temp.left.type.equalsIgnoreCase("ATOM"))
							{
								String b = temp.left.value;
								if(b.equalsIgnoreCase("NIL") || b.equalsIgnoreCase("T"))
								{
									System.out.println("ERROR:Invalid parameter : Cannot be T or NIL");
									System.exit(1);
								}
								else
								{
									paramList.add(b);
								}
							}
							else
							{
								System.out.println("ERROR:Only literal atoms allowed");
								System.exit(1);
							}
							
						}
						else
						{
							
						}
						temp=temp.right;
					}
					//The parameters have been added to the ArrayList
					int originalNumberofParams = paramList.size();
					
					//System.out.println("The original number of parameters are: "+ originalNumberofParams);
					

					numParamofUserFunc.put(functionname.value,originalNumberofParams);
					
					//boolean b =numParamofUserFunc.isEmpty();
					//System.out.println("numparam is empty "+b);
					
					
					
					HashSet<String> p = new HashSet<String>(paramList);
					int newNumberofParams = p.size();
					
					if(originalNumberofParams != newNumberofParams)
					{
						System.out.println("ERROR:Duplicate parameters ");
						numParamofUserFunc.remove(functionname);

						System.exit(1);
					}
		}
		else
		{
			System.out.println("ERROR:Invalid Function Name");
			System.exit(1);
		}
		return true;
	}

	public static int FindNumberOfParams(Node cdr)
	{
		Node tempNode = cdr;
		int numOfParams = 0;
		
		while(tempNode != null ) 
		{
			if(tempNode.left != null)
			{
				numOfParams++;
			}
			tempNode = tempNode.right;
		}
		return numOfParams;
	}

	public static Node COND(Node node,Node aList,Node dList)
	{
		//If cond is encountered then we call the next evaluation with cdr(node) --> cdr(exp)
		// Basically the functionality of evcon function
		if(node.value.equalsIgnoreCase("NIL"))
		{
			System.out.println("ERROR:In Cond !! All are false");
			System.exit(1);
			return nil;
		}
		else 
		{
			if(eval(CAR(CAR(node)), aList, dList).value.equalsIgnoreCase("T"))
			{
				//true;
				return eval(CAR(CDR(CAR(node))),aList,dList);
			}
			else
			{
				return COND(CDR(node),aList,dList);
			}
			
		}
			
		
		
		
	}

}
