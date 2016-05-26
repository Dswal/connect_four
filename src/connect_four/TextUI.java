package connect_four;

/**
 * TODO There is an issue with input / output, where the game will only run if there is a
 * space after the intial "Do you want to load a game" dialogue. If the input/output
 * files are removed from run parameters then the game will load normally with a few
 * changes back of the code.
 * - Reopening/reseting the scanner
 */




import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextUI {

	public int turn;
	private ConnectFour game;

	public TextUI(ConnectFour g) {
		 game = g;
	}

	public static void main(String[] args) {
		ConnectFour game = new ConnectFour();
		TextUI textUI = new TextUI(game);
		textUI.playGame();

	}


	/**
	 * playGame is the main bulk of the code of the entire program.
	 *
	 * The program initialises and asks for a load save, if any value other than "yes", the game will create a new board.
	 *
	 * This then reopens player input to start playing the game using the supplied commands. By converting the ascii numerals
	 * you can use these to place it into the column array list, allowing peices to fall onto the grid.
	 *
	 * After each turn the game saves.
	 */

	public void playGame(){
		Scanner startingIn = new Scanner(System.in);

		System.out.println("Welcome to Connect Four.");
		System.out.println("Would you like to load a game?");
		String startingInput = startingIn.next();

		// Load Game
		if (startingInput.equalsIgnoreCase("yes")) {
			try {
				game.loadFrom("save.txt");
				startingInput = startingInput + "aaaa\n";
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
		        	game.board[i][j] = ".";
		        }
		        System.out.print("\n");
		    };
//		startingInput = startingInput + "aaaa\n";
		turn = 1;
		printGrid();

//		startingInput = startingIn.next();

	    // Opens up the scanner to allow input for the user
		Scanner in = startingIn;

//		in = startingIn.next();
		/**
		 * This is a reminder, what is going on is the out/in text files wont write, so what I did was change the scanners to link to each other.
		 * For the errors not to occur, you need to add a character after you type yes/no when asking to load a game. It will allow the game to continue as normal
		 * If you do not, it errors during the while loop
		 */

//	    Scanner in = new Scanner(System.in);

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
	            game.putPlayerOnGrid(column, "X");
	            turn = 1;
	        }

	        // If the character used is a small character it places an O
	        else if(ascii >= 97 && ascii <= 103 && turn == 1){
	        	// Uses the ascii codes of the letters abcdefg and converts them into an integer by taking 97
	        	// This is used to place an character into the arrays 0123456
	            int column = ascii-97;
//	            System.out.println("O , Column: "+ column);
	            game.putPlayerOnGrid(column,"O");
	            turn = 2;
	        }

	        else{
	        	System.out.println("Invalid Command, please use another Character.\n");
	        }

			try {
				game.saveTo("save.txt");
			} catch (FileNotFoundException e) {
				System.err.println("Could not save game: " + e.getLocalizedMessage());
				e.printStackTrace();
			}
			game.checkWin();
	    }
	}

	/**
	 *
	 * printGrid uses a loop to build board and display it as a system output.
	 * Also uses a stringbuilder to save information and return lines to allow for saving in another method.
	 * @return
	 */

	public String[] printGrid(){

		// Loop to print out the grid
		String lines[] = new String[7];


	    for(int i = 0; i < 7; i++){
	    	StringBuilder str = new StringBuilder();
	        for(int j = 0; j < 7; j++){
	            System.out.print(game.board[i][j] + " ");
	            str.append(game.board[i][j] + " ");
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
