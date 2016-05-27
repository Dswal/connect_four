package connect_four;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

//import connect_four.gui.GUI;

/**
 * This is connect four for two players.
 *
 * The goal is to 4 cells in a row either horizontally, vertically or diagonally.
 * There is a save and load feature. The game will automatically save after each turn
 *
 *
 * @author David Swallow
 */

public class ConnectFour {

	public String[][] board = new String[7][7]; //7 down 7 wide
	
//	private GUI gui = new GUI();


	/**
	 * putPlayerOnGrid does exactly what it says. It checks through the rows to find an unoccupied space
	 * denoted by the character "." when the character is found it will place an X or an O onto the space.
	 * The X or O comes from the user input in the TextUI section.
	 *
	 * The loop starts at the top of the array and works itself downwards towards the bottom where the peices are.
	 * This allows when the check finds an X or an O to place the peice -1 spaces above it.
	 *
	 * @param position
	 * @param playerXorO
	 */

	public String getCell(int row, int col) {
		return board[row][col];
	}
	
	public void putPlayerOnGrid(int position, String playerXorO){

		
	    // Check if row is occupied at all
	    for(int i = 0; i < 7; i++){

	        String pos = board[i][position];

	        // If the column does not equal ., then place it above that tile
	        if(!pos.equals(".")) {

	            System.out.println("Placing: " + playerXorO + " above another slot");

	            if (i  - 1 >= 0) {
	                board[i - 1][position] = playerXorO;
//	                gui.button[i - 1][position].setText(playerXorO);
	            } else {
	                System.out.println("Invalid Move!");
	            }
	        return;

	        }
// 			This checks to see if the  column selected is equal to the bottom column, do not subtract 1 to the column
//	        This avoids missing the row at the bottom due to arrays being 1 below a number. Eg. 0/1/2/3/4/5, instead of 1/2/3/4/5/6
	        if(i == 6 && pos.equals(".")){
	        	System.out.println("Placing Piece");
	            board[i][position] = playerXorO;
//	            buttonPosition = board[i][position]
//	            gui.button[i][position].setText(playerXorO);
	        }
	    }
	}

	/**
	 *
	 * checkWin loops through the colomns searching for the winning combination of 4 X's or O's.
	 * However in the diagonal check, it works slightly different where it needs to loop through a smaller set of values
	 * because you can't board check by using -1/-2/-3 of where the original peice is being checked. *It will try and check a negative
	 * array value causing issues.
	 *
	 */

	public int checkWin(){

		// Make sure that the values of verticalLine and horizontalLine are empty before checking who wins
	    String verticalLine = "";
	    String horizontalLine = "";

        // Check for Vertical win in each column
	    for(int j = 0; j < 7; j++) {

	        for(int i = 0; i < 7; i++) {

	            verticalLine += board[i][j];
	            if(verticalLine.contains("OOOO")){
	                System.out.println("O Wins! Through a verticle line!");
	                restartGame();
	                return 1;
	            }
	            if(verticalLine.contains("XXXX")){
	                System.out.println("X Wins! Through a verticle line!");
	                restartGame();
	                return 2;
	            }

	        }
	    }

//	    Check horizontally for for in a row
	    for(int j = 0; j < 7; j++) {

	        for(int i = 0; i < 7; i++) {
	            horizontalLine += board[j][i];
	            if(horizontalLine.contains("OOOO")){
	                System.out.println("O Wins! Through a horizontal line!");
	                restartGame();
	                return 1;
	            }
	            if(horizontalLine.contains("XXXX")){
	                System.out.println("X Wins! Through a horizontal line!");
	                restartGame();
	                return 2;
	            }

	        }
	    }

//	    Check diagonally for for in a row
	    // Upper Left to Lower Right
	    for(int i = 0; i < 4; i++){
	    	for(int j = 0; j < 4; j++){
	    		if(board[j][i] == "O" && board[j][i] == board[j+1][i+1]
	    								&& board[j][i] == board[j+2][i+2]
	    								&& board[j][i] == board[j+3][i+3]){
	                System.out.println("O Wins! Through a Diagonal line up to the left!");
	                restartGame();
	                return 1;
	    		}

	    	}
	    }
	    // Upper Left to Lower Right
	    for(int i = 0; i < 4; i++){
	    	for(int j = 0; j < 4; j++){
	    		if(board[j][i] == "X" && board[j][i] == board[j+1][i+1]
	    								&& board[j][i] == board[j+2][i+2]
	    								&& board[j][i] == board[j+3][i+3]){
	                System.out.println("X Wins! Through a Diagonal line up to the left!");
	                restartGame();
	                return 2;
	    		}

	    	}
	    }
	    //Lower left to Upper Right
	    for(int i = 6; i >= 3; i--){
	    	for(int j = 0; j < 4; j++){
	    		if(board[j][i] == "O" && board[j][i] == board[j+1][i-1]
	    								&& board[j][i] == board[j+2][i-2]
	    								&& board[j][i] == board[j+3][i-3]){
	                System.out.println("O Wins! Through a Diagonal line up to the right!");
	                restartGame();
	                return 1;
	    		}

	    	}
	    }
	    //Lower left to Upper Right
	    for(int i = 6; i >= 3; i--){
	    	for(int j = 0; j < 4; j++){
	    		if(board[j][i] == "X" && board[j][i] == board[j+1][i-1]
	    								&& board[j][i] == board[j+2][i-2]
	    								&& board[j][i] == board[j+3][i-3]){
	                System.out.println("X Wins! Through a Diagonal line up to the right!");
	                restartGame();
	                return 2;
	    		}

	    	}
	    }

		return 0;
	}


	/**
	 *
	 * Simple saving method to write the entire array list into a textfile saved in the directory.
	 * Grabs fileName from an input over in TextUI
	 *
	 * @param fileName
	 * @throws FileNotFoundException
	 */


	public void saveTo(String fileName) throws FileNotFoundException{
		try (PrintWriter out = new PrintWriter(fileName)) {
			String[] rows = new TextUI(this).printGrid();
			for (String r : rows) {
				out.print(r.replaceAll("\\.", "."));
				out.println();
			}
		}

	}  // automatically closes 'out' here

	/**
	 *
	 * loadFrom uses the filereader to read the text and assign the values into the board array
	 *
	 * @param fileName
	 * @throws FileNotFoundException
	 */

	public void loadFrom(String fileName) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(fileName));

		while(in.hasNext()){
			for(int i = 0; i < 7; i++){
				for(int j = 0; j < 7; j++){
					board[i][j] = in.next();
				}
			}
		}
	}

	public void restartGame(){
		for(int i = 0; i < 7; i++){
	        for(int j = 0; j < 7; j++){
	        	board[i][j] = ".";
	        }
	        System.out.print("\n");
	    };
        System.out.print("\n");
        System.out.print("Welcome to a new game!\n");
	    for(int i = 0; i < 7; i++){
	        for(int j = 0; j < 7; j++){
	            System.out.print(board[i][j] + " ");
	        }
	        System.out.print("\n");
	    }
	    System.out.print("\n");
        System.out.print("a b c d e f g \n");
	    System.out.print("\n");
	    System.out.print("Player 1 use lowercase a/b/c/d/e/f/g to place a peice\n");
	    System.out.print("Player 2 use UPPERCASE A/B/C/D/E/F/G to place a peice\n");
	    System.out.print("\n");
	}
}
