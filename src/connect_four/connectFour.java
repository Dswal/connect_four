package connect_four;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;


// TODO Implement proper turns
// TODO Find a way to work out diagonal winner
// TODO If no character is entered when dropping a piece it crashes the game



public class connectFour {

	public static String[][] board = new String[6][7]; //6 down 7 wide

	public static void main(String[] args) {

	    Scanner startingIn = new Scanner(System.in);

		System.out.println("Welcome to Connect Four.");
		System.out.println("Would you like to load a game?");
		String startingInput = startingIn.next();

		// Load Game
		if (startingInput.equalsIgnoreCase("yes")) {
			try {
				loadFrom("save.txt");
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
			for(int i = 0; i < 6; i++){
		        for(int j = 0; j < 7; j++){
		            board[i][j] = ".";
//		            System.out.print(board[i][j] + " ");
		        }
		        System.out.print("\n");
		    };
		printGrid();


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
//	            System.out.println("X , Column: " + column);
	            putPlayerOnGrid(column, "X");
	        }

	        // If the character used is a small character it places an O
	        else if(ascii >= 97 && ascii <= 103){
	        	// Uses the ascii codes of the letters abcdefg and converts them into an integer by taking 97
	        	// This is used to place an character into the arrays 0123456
	            int column = ascii-97;
//	            System.out.println("O , Column: "+ column);
	            putPlayerOnGrid(column,"O");
	        }

	        else{
	        	System.out.println("Invalid Command, please use another Character.\n");
	        }

			try {
				saveTo("save.txt");
			} catch (FileNotFoundException e) {
				System.err.println("Could not save game: " + e.getLocalizedMessage());
				e.printStackTrace();
			}
	    }
	}

	public static String[] printGrid(){

		// Loop to print out the grid
		String lines[] = new String[6];


	    for(int i = 0; i < 6; i++){
	    	StringBuilder str = new StringBuilder();
	        for(int j = 0; j < 7; j++){
	            System.out.print(board[i][j] + " ");
	            str.append(board[i][j] + " ");
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

	public static void putPlayerOnGrid(int position, String playerXorO){

	    // Check if row is occupied at all
	    for(int i = 0; i < 6; i++){

	        String pos = board[i][position];

	        // If the column does not equal ., then place it above that tile
	        if(!pos.equals(".")) {

	            System.out.println("Placing: " + playerXorO + " above another slot");

	            if (i  - 1 > 0) {
	                board[i - 1][position] = playerXorO;
	            } else {
	                System.out.println("Invalid Move!");
	            }
	        return;

	        }
// 			This checks to see if the  column selected is equal to the bottom column, do not subtract 1 to the column
//	        This avoids missing the row at the bottom due to arrays being 1 below a number. Eg. 0/1/2/3/4/5, instead of 1/2/3/4/5/6
	        if(i == 5 && pos.equals(".")){
	        	System.out.println("Placing Piece");
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


	public static void saveTo(String fileName) throws FileNotFoundException{
		try (PrintWriter out = new PrintWriter(fileName)) {
			String[] rows = printGrid();
			for (String r : rows) {
				out.print(r.replaceAll("\\.", "."));
				out.println();
			}
		}

	}  // automatically closes 'out' here

	public static void loadFrom(String fileName) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(fileName));

		while(in.hasNext()){
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 7; j++){
					board[i][j] = in.next();
				}
			}
		}
	}

}
