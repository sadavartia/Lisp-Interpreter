Name : Priyanka Sadavartia
Email ID : sadavartia.1@osu.edu

Details:
Classes created:
1. myScanner.java:
	The myScanner class essentially scans the input which is read and validates them to be as valid literals or numeric atoms. It also 		validates the type of special characters allowed which could be '+','-','.','(',')' and also checks that the atom allowed right after 		a '+' or '-' is a numeric atom. It also checks that a literal declaration cannot contain a '.' notation in it.

2.tok.java
	This class is used to get the next token entered from the current line and it also sets the value for the token with a value we want 
	to assign it to.

3.Node.java
	This class essentially describes all the properties attached with the input. This is to make the tree structure after the input is 		parsed by the LL(1) parser in the computeTree.java class. The properties it deals with is mostly setting the value and type of the 		current node,whether its an atom or not,whether its a list or not, whether its a list or not, whether its a number or not and the left 		and right child associated with the node.  

4.computeTree.java
	This is the class which validates the input grammar against the grammar and prints it accordingly. The grammar used is the one 		provided in the project. It then sets the isList values for each node when a '(' is encountered.

5.DList.java
	This class is to make sure that the declaration list computed which would be of the user defined functions is global and it gets 	updated as soon as a new function declaration is encountered. 

6.helpers.java
	This class holds all the evaluation rule and is called by myInterpreter.java . It reads the input and based on the type of function 		call, validates it and makes sure execution happens in the proper sequence and in accordance with all the rules. All the primitive 
	declaration of function as given in the project description are also taken care of.

7. myInterpreter.java
	This class is the main handler. It makes sure read-eval-print happens. The input is read line by line and as soon as one line of input 		is obtained , that is , an individual atom or a balanced s-exp(which would mean equal number of opening and closing paranthesis), it 		immediately validates the tokens obtained and if valid sends it to computeTree where parsing happens and the tree structure is built .
	With this tree evaluation is done with the help of Dlist.java and helpers.java which apply all the rules on the input and compute the 		result if it is valid. The result again goes back to myInterpreter which prints the result using the pretty printing algorithm.
	If an error is encountered the excecution would terminate there and there would be exit from computation. 


