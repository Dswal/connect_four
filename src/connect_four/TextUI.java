package connect_four;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextUI {

	public static int turn;

	public static void main(String[] args) {


	    Scanner startingIn = new Scanner(System.in);

		System.out.println("Welcome to Connect Four.");
		System.out.println("Would you like to load a game?");
		String startingInput = startingIn.next();

		// Load Game
		if (startingInput.equalsIgnoreCase("yes")) {
			try {
				ConnectFour.loadFrom("save.txt");
				printGrid();
			} catch (FileNotFoundException e) {
				System.err.println("Could not load game: " + e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		else
			// Creates the initial board visually, setting all values to .
			// This creates an elegant looking board visually
			// Without creating this board first, during the scanner any input submitted will not be valid
			for(int i = 0; i < 7; i++){
		        for(int j = 0; j < 7; j++){
		        	ConnectFour.board[i][j] = ".";
//		            System.out.print(board[i][j] + " ");
		        }
		        System.out.print("\n");
		    };
		turn = 1;
		printGrid();


	    // Opens up the scanner to allow input for the user
	    Scanner in = new Scanner(System.in);

	    while(in.hasNextLine()){

	        String input = in.nextLine();
	        // Forcing an input down to only one character allowed to be entered
	        char pos = input.toCharArray()[0];
	        int ascii = (int)pos;

	        // If the character used is a capital it places an X
	        if(ascii >= 65 && ascii <= 71 && turn == 2){
	        	// Uses the ascii codes of the letters ABCDEFG and converts them into an integer by taking 65
	        	// This is used to place an character into the arrays 0123456
	            int column = ascii -65;
//	            System.out.println("X , Column: " + column);
	            ConnectFour.putPlayerOnGrid(column, "X");
	            turn = 1;
	        }

	        // If the character used is a small character it places an O
	        else if(ascii >= 97 && ascii <= 103 && turn == 1){
	        	// Uses the ascii codes of the letters abcdefg and converts them into an integer by taking 97
	        	// This is used to place an character into the arrays 0123456
	            int column = ascii-97;
//	            System.out.println("O , Column: "+ column);
	            ConnectFour.putPlayerOnGrid(column,"O");
	            turn = 2;
	        }

	        else{
	        	System.out.println("Invalid Command, please use another Character.\n");
	        }

			try {
				ConnectFour.saveTo("save.txt");
			} catch (FileNotFoundException e) {
				System.err.println("Could not save game: " + e.getLocalizedMessage());
				e.printStackTrace();
			}
			ConnectFour.checkWin();
	    }
	}

	public static String[] printGrid(){

		// Loop to print out the grid
		String lines[] = new String[7];


	    for(int i = 0; i < 7; i++){
	    	StringBuilder str = new StringBuilder();
	        for(int j = 0; j < 7; j++){
	            System.out.print(ConnectFour.board[i][j] + " ");
	            str.append(ConnectFour.board[i][j] + " ");
	        }
	        System.out.print("\n");
	        lines[i] = str.toString();
	    }
	    System.out.print("\n");
        System.out.print("a b c d e f g \n");
	    System.out.print("\n");
	    System.out.print("Player 1 use lowercase a/b/c/d/e/f/g to place a peice\n");
	    System.out.print("Player 2 use UPPERCASE A/B/C/D/E/F/G to place a peice\n");
	    System.out.print("\n");
		return lines;
	}

}