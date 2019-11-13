import java.util.*;
import java.io.*;

public class Turing {

	public static void main (String[] args) {
		//This example adds 1 to the tape
		int tape [] = {0,1,1,1,1,0};
		int n = 4;
		int m = 2;
		int headPos = 0;
		int[][] state = { {1,1},{2,1},{3,2},{3,3} };
		int[][] symbol = { {0,0},{1,1},{0,1},{0,0} };
		char[][] lr = {{'R', 'R'} , {'L', 'R'}, {'\0', 'L'}, {'\0','\0'}};

		tape = turingMachine(n,m, tape, state, symbol, lr, headPos);
		System.out.println(Arrays.toString(tape));
	}
	
	public static int[] turingMachine(int n, int m, int[] tape, int[][] state, int[][] symbol, char[][] lr, int headPos){
		int curSymbol = 0;
		int curState = 0;
		
		int newState = 0;
		int newSymbol = 0;
		int move = 0;
		
		while (curState < (n-2)){
			curSymbol = tape[headPos];
			newSymbol = symbol[curState][curSymbol];
			newState = state[curState][curSymbol];
			move = lr[curState][curSymbol];
			
			curState = newState;
			tape[headPos] = newSymbol;
			
			if (move == 'L')
				headPos--;
			else if (move == 'R')
				headPos++;
			
		}
		return tape;
	}

}