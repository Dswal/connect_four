package connect_four;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;


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

	public static String[][] board = new String[7][7]; //7 down 7 wide

	public static void putPlayerOnGrid(int position, String playerXorO){

	    // Check if row is occupied at all
	    for(int i = 0; i < 7; i++){

	        String pos = board[i][position];

	        // If the column does not equal ., then place it above that tile
	        if(!pos.equals(".")) {

	            System.out.println("Placing: " + playerXorO + " above another slot");

	            if (i  - 1 >= 0) {
	                board[i - 1][position] = playerXorO;
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
	        }
	    }
	}

	public static int checkWin(){

		// Make sure that the values of verticalLine and horizontalLine are empty before checking who wins
	    String verticalLine = "";
	    String horizontalLine = "";

        // Check for Vertical win in each column
	    for(int j = 0; j < 7; j++) {

	        for(int i = 0; i < 7; i++) {

	            verticalLine += board[i][j];
	            if(verticalLine.contains("OOOO")){
	                System.out.println("O Wins! Through a verticle line!");
	                return 1;
	            }
	            if(verticalLine.contains("XXXX")){
	                System.out.println("X Wins! Through a verticle line!");
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
	                return 1;
	            }
	            if(horizontalLine.contains("XXXX")){
	                System.out.println("X Wins! Through a horizontal line!");
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
	                return 2;
	    		}

	    	}
	    }

		return 0;
	}

	public static void saveTo(String fileName) throws FileNotFoundException{
		try (PrintWriter out = new PrintWriter(fileName)) {
			String[] rows = TextUI.printGrid();
			for (String r : rows) {
				out.print(r.replaceAll("\\.", "."));
				out.println();
			}
		}

	}  // automatically closes 'out' here

	public static void loadFrom(String fileName) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(fileName));

		while(in.hasNext()){
			for(int i = 0; i < 7; i++){
				for(int j = 0; j < 7; j++){
					ConnectFour.board[i][j] = in.next();
				}
			}
		}
	}
}
