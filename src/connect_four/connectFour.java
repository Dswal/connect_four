package connect_four;

import java.util.Scanner;


// TODO Implement proper turns
// TODO Find a way to work out diagonal winner
// TODO Find a way to stop X's or O's overwriting other values in the field below its supposed to drop on
// TODO Implement Save and Loading
// TODO Fix multiple winner messages
// TODO If no character is entered when dropping a piece it crashes the game
// TODO Remove test system.out.prints



public class connectFour {

	public static String[][] board = new String[6][7]; //6 down 7 wide

	public static void main(String[] args) {

		// Creates the initial board visually, setting all values to .
		// This creates an elegant looking board visually
		// Without creating this board first, during the scanner any input submitted will not be valid
		for(int i = 0; i < 6; i++){
	        for(int j = 0; j < 7; j++){
	            board[i][j] = ".";
	            System.out.print(board[i][j] + " ");
	        }
	        System.out.print("\n");
	    }

		// This is to display the lines/characters to select
	    System.out.print("\n");
        System.out.print("a b c d e f g");
	    // Opens up the scanner to allow input for the user
	    Scanner in = new Scanner(System.in);

	    while(in.hasNextLine()){

	        String input = in.nextLine();
	        // Forcing an input down to only one character allowed to be entered
	        char pos = input.toCharArray()[0];
	        int ascii = (int)pos;

	        // If the character used is a capital it places an X
	        if(ascii >= 65 && ascii <= 71){
	        	// Uses the ascii codes of the letters ABCDEFG and converts them into an integer by taking 65
	        	// This is used to place an character into the arrays 0123456
	            int column = ascii -65;
	            System.out.println("X , Column: " + column);
	            putPlayerOnGrid(column, "X");
	        }

	        // If the character used is a small character it places an O
	        if(ascii >= 97 && ascii <= 103){
	        	// Uses the ascii codes of the letters abcdefg and converts them into an integer by taking 97
	        	// This is used to place an character into the arrays 0123456
	            int column = ascii-97;
	            System.out.println("Y , Column: "+ column);
	            putPlayerOnGrid(column,"O");
	        }

	        printGrid();

	    }


	}

	public static void printGrid(){

		// Loop to print out the grid

	    for(int i = 0; i < 6; i++){
	        for(int j = 0; j<7; j++){
	            System.out.print(board[i][j] + " ");
	        }
	        System.out.print("\n");
	    }
	    System.out.print("\n");
        System.out.print("a b c d e f g");
	}

	public static void putPlayerOnGrid(int position, String playerXorO){

	    // Check if row is occupied at all
	    for(int i = 0; i < 6; i++){

	        String pos = board[i][position];

	        // If the column does not equal . eg. O or X, then place it above that tile
	        if(!pos.equals(".")) {

	            System.out.println("Placing: " + playerXorO + " above another slot");

	            if (i  - 1 >= 0) {
	                board[i - 1][position] = playerXorO;
	            } else {
	                System.out.println("Invalid Move!");
	            }
	        }
// 			This checks to see if the  column selected is equal to the bottom column, do not subtract 1 to the column
//	        This avoids missing the row at the bottom due to arrays being 1 below a number. Eg. 0/1/2/3/4/5, instead of 1/2/3/4/5/6
	        if(i == 5 && pos.equals(".")){
	        	System.out.println("Placing shit");
	            board[i][position] = playerXorO;
	        }
	    }
	    checkWin();
	}

	public static void checkWin(){

		// Make sure that the values of verticalLine and horizontalLine are empty before checking who wins
	    String verticalLine = "";
	    String horizontalLine = "";

        // Check for Vertical win in each column
	    for(int j = 0; j < 7; j++) {

	        for(int i = 0; i < 6; i++) {

	            verticalLine += board[i][j];
	            if(verticalLine.contains("OOOO")){
	                System.out.println("O Wins! Through a verticle line!");
	                return;
	            }
	            if(verticalLine.contains("XXXX")){
	                System.out.println("X Wins! Through a verticle line!");
	                return;
	            }

	        }
	    }

//	    Check horizontally for for in a row
	    for(int j = 0; j < 6; j++) {

	        for(int i = 0; i < 7; i++) {
	            horizontalLine += board[j][i];
	            if(horizontalLine.contains("OOOO")){
	                System.out.println("O Wins! Through a horizontal line!");
	                return;
	            }
	            if(horizontalLine.contains("XXXX")){
	                System.out.println("X Wins! Through a horizontal line!");
	                return;
	            }

	        }
	    }
	}
}
