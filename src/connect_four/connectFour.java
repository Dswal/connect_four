package connect_four;

import java.util.Scanner;


// TODO Implement proper turns
// TODO Find a way to work out diagonal winner
// TODO Find a way to stop X's or O's overwriting other values in the field below its supposed to drop on
// TODO Implement Save and Loading



public class connectFour {

	public static String[][] board = new String[6][7];//6 down 7 wide

	public static void main(String[] args) {

		// Creates the initial board visually, setting all values to .
		// This creates an elegant looking board visually
		// Without creating this board first, during the scanner any input submitted will not be valid
		for(int i =0;i<6;i++){
	        for(int j = 0; j<7; j++){
	            board[i][j] = "N";
	            System.out.print(board[i][j] + " ");
	        }
	        System.out.print("\n");
	    }

	    // Opens up the scanner to allow input for the user
	    Scanner in = new Scanner(System.in);
	    while(in.hasNextLine()){
	        String input = in.nextLine();
	        // Forcing an input down to only one character allowed to be entered
	        char pos = input.toCharArray()[0];
	        int ascii = (int)pos;

	        // If the character used is a capital it places an X
	        if(ascii>=65 && ascii<= 71){//its X
	        	// Uses the ascii codes of the letters ABCDEFG and converts them into an integer by taking 65
	        	// This is used to place an character into the arrays 0123456
	            int column = ascii -65;
	            System.out.println("X , Column: " + column);
	            putPlayerOnGrid(column, "X");
	        }

	        // If the character used is a small character it places an O
	        if(ascii>=97 && ascii<= 103){//its O
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

	    for(int i =0;i<6;i++){
	        for(int j = 0; j<7; j++){
	            System.out.print(board[i][j] + " ");
	        }
	        System.out.print("\n");
	    }
	}

	public static void putPlayerOnGrid(int position, String playerXorO){

	    //check if row is occupied at all
	    for(int i=0; i<6; i++){
	        String pos = board[i][position];
	        if(!pos.equals("N")) {
	            System.out.println("Placing: " + playerXorO + " above another slot");
	            if (i - 1 >= 0) {
	                board[i - 1][position] = playerXorO;
	            } else {
	                System.out.println("Invalid Move!");
	            }
	        }
	        if(i==5 && pos.equals("N")){
	        	System.out.println("Placing shit");
	            board[i][position] = playerXorO;
	        }
	    }
	    checkWin();
	}

	public static void checkWin(){

	    String verticalLine = "";
	    String horizontalLine = "";
	    for(int j =0; j<7; j++) {

	        if(horizontalLine.contains("OOOO")){
	            System.out.println("O Wins!");
	        }
	        if(horizontalLine.contains("XXXX")){
	            System.out.println("X Wins!");
	        }

            //check for Vertical win in each column
	        for(int i =0; i<6; i++) {

	            verticalLine += board[i][j];
	            if(verticalLine.contains("OOOO")){
	                System.out.println("O Wins!");

	            }
	            if(verticalLine.contains("XXXX")){
	                System.out.println("X Wins!");

	            }

	        }
	    }
//	    Check horizontally for for in a row
	    for(int j =0; j<6; j++) {

	        if(horizontalLine.contains("OOOO")){
	            System.out.println("O Wins!");
	        }
	        if(horizontalLine.contains("XXXX")){
	            System.out.println("X Wins!");
	        }
	        for(int i =0;i<7;i++) {
	            //Check for vertical win in each column
	            horizontalLine += board[j][i];
	            if(horizontalLine.contains("OOOO")){
	                System.out.println("O Wins!");

	            }
	            if(horizontalLine.contains("XXXX")){
	                System.out.println("X Wins!");

	            }

	        }
	    }
	}
}
