
public class Node {
	public Node left;
	public Node right;
	public String value;
	public String type;
	public int intValue;
	public boolean isNill;
	public boolean isNumber;
	public boolean isAtom;
	public boolean isList;
	public boolean isNonTerminal = false;
	
	public Node(){
		
		left=null;
		right=null;
		value= "";
		type="";
		intValue=0;
		isAtom = false;
		isNill = false;
		isList = false;
		isNumber = false;
	}
	
	public Node(String value1) {
		left=null;
		right=null;
		value = value1;
		isAtom = true;
		intValue=0;
		isList = false;
		type="ATOM";
		if(value1.equalsIgnoreCase("NIL"))
		{
			isNill=true;
		}
		else
			isNill=false;
	}
	public Node(int a)
	{
		left=null;
		right=null;
		value = Integer.toString(a);
		intValue=a;
		isAtom = true;
		isList = false;
		type="NUMERICATOM";
		isNill=false;
		isNumber = true;
	}
	
	public Node(Node l,Node r)
	{
		isAtom = false;
		type="";
		value="";
		isNill= false;
		isList = false;
		left=l;
		right=r;
		intValue=0;
	}
	public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public void setRoot(Node root)
    {
    	//this.left=NULL;
    	//this.right=NULL;
    	this.value="S";
    	
    }
    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getLeft() {
        return left;
    }

    public void setRight(Node right ) {
        this.right = right;
    }

    public Node getRight() {
        return right;
    }
	
    public void preorder(Node root)
    {
    	if(root.left == null && root.right == null)
    	{
    		System.out.print(root.value);
    		System.out.print("\n");
    		return;
    		
    	}
    	
    		
    	else
    	{
    		System.out.print(root.value);
    		System.out.print("\n");
    		preorder(root.left);
    		preorder(root.right);
    	}
    }
}
