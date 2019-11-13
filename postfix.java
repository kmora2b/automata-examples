import java.io.*; 
import java.util.*; 

public class postfix {
	
	public static void main (String[] args) {
		Stack stack = new Stack();
		String exp = "1 * 2";
		System.out.println("EXPRESSION: " + exp);
		
		//Convert expression to postfix
		String postfixExp = convertToPostFix(exp);
		System.out.println("POSTFIX: " + postfixExp);
		
		//Get postfix and get value
		int value = calculatePostFix(postfixExp);
		System.out.println("VALUE: " + value);
		
	}
	
	//Converts expression to postfix
	public static String convertToPostFix(String exp) {
		Stack<Character> postfixStack = new Stack<>(); 
		String collect = "";
		
		//Go through all of expression
		for(int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			
			//If c is a variable or number
			if (Character.isLetterOrDigit(c)) 
				collect += c;
			
			//If c is ( then push 
			else if ( c == '(' ) 
				postfixStack.push(c); 
			
			//If c is ) then we either
			//-If the stack is not empty and we find another ( then we pop the stack 
			//-If its another ) then its invalid
			//-Pop if anything else
			else if (c == ')') { 
				while ( !postfixStack.empty() && postfixStack.peek() != '(' ) 
					collect += postfixStack.pop(); 
				  
				if ( !postfixStack.empty() && postfixStack.peek() != '(' ) 
					return "Invalid Expression";  
				else
					postfixStack.pop(); 
			} 
			
			//If its other chars or operators then we calculate precedence to determine popping
			else { 
				while ( !postfixStack.empty() && precedence(c) <= precedence(postfixStack.peek()) ){ 
					//Another ) then its invalid
					if(postfixStack.peek() == '(') 
						return "Expression is not valid"; 
					
					//Add to collect
					collect += postfixStack.pop(); 
				} 
				
				//If stack is empty then just push
				postfixStack.push(c); 
			} 
			
			//To display what is going on in stack
			System.out.println(collect);
	   
		} 
	   
		//If there are any operators left then keep popping until stack is empty and add to string
		while (!postfixStack.empty()){ 
			//We find ( then we have extra parentheses and return an error
			if(postfixStack.peek() == '(') 
				return "Expression is not valid"; 
			
			collect += postfixStack.pop(); 
		} 
		
		return collect;

	}	
	
	//Helps with determining precedence of operations based on PEMDAS 
	public static int precedence(char c){
		//PEMDAS
		switch (c) { 
			case '+': 
			case '-': 
				return 1; 

			case '*': 
			case '/': 
				return 2; 

			case '^': 
				return 3; 
		} 
		return -1; 
		
	}
	
	public static int calculatePostFix(String postfixExp){
		Stack<Integer> stack=new Stack<>(); 

		//Go through all of expression
		for(int i = 0; i < postfixExp.length(); i++) {
			char c = postfixExp.charAt(i);
			
			//If the char is 0123456789 then push to stack
			if (Character.isDigit(c)) {
				//c - '0' is to convert char to int  
				stack.push(c - '0'); 
			}
			
			//If the char is operator then pop 2 values from stack and do operation associated with operator
			//Get result then push to stack
			else {
				int val1 = stack.pop();
				int val2 = stack.pop();
				int result = mathOperation(c, val1, val2);
				stack.push(result);

			}
			
			//To display what is going on in stack
			System.out.println(stack.peek());
			
		}
		
		//Return result by popping stack
		return stack.pop();
	}
	
	//Does operation that operator is associated with
	public static int mathOperation(char operator, int val1, int val2){
		int val = 0;
		
		switch(operator) { 
			case '+': 
				val = val2 + val1; 
				break; 
			  
			case '-': 
				val = val2 - val1; 
				break; 
			  
			case '/': 
				val = val2 / val1; 
				break; 
			  
			case '*': 
				val = val2 * val1; 
				break; 
		} 
		return val;
	}
}