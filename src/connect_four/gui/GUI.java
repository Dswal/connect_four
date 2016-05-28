package connect_four.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JOptionPane;

import connect_four.ConnectFour;
import connect_four.TextUI;



public class GUI extends Application{

	private ConnectFour game = new ConnectFour();
	private TextUI ui = new TextUI(game);

	private int playerTurn = 1;

	public Button[][] button = new Button[7][7];

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		pane.setGridLinesVisible(true);
		pane.setStyle("-fx-background-color:red");


		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				Button but = new Button("");
				but.setStyle("-fx-font: 48px 'sans-serif';");
				but.setPrefWidth(100.0);
				but.setPrefHeight(100.0);
				but.setMaxWidth(Double.MAX_VALUE);
				but.setMaxHeight(Double.MAX_VALUE);
				// we need to make row and col final so that we can
				// use them in the event handler lambda expression.
				final int butRow = row;
				final int butCol = col;
				but.setOnAction(ev -> handleClick(butRow, butCol));
				pane.add(but, col, row);
				button[row][col] = but;
			}
		}
		Scene scene = new Scene(pane, 700, 600);
		primaryStage.setScene(scene);
		primaryStage.show();


		Object[] options = {"No", "Yes"};

		//Choice dialogue box (Component, Obeject message, String title, int option, int messageType, Icon, Object[] options, Object initial value)

		int choice = JOptionPane.showOptionDialog(null,
				"Would you like to load a game?",
				"Load Game",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.DEFAULT_OPTION,
				null,
				options,
				options[1]);

		// If the user chooses No
		if(choice == 0){
			// Initialize Game board.
			for(int i = 0; i < 7; i++){
		        for(int j = 0; j < 7; j++){
		        	game.board[i][j] = ".";
		        }
		        System.out.print("\n");
		    }
		}
		// User Chooses Yes
		else{
			//Load games for the parameters/text and the assign text to the GUI
			game.loadFrom("save.txt");
			loadFromGUI("save.txt");
		}

	}

	private void handleClick(final int row, final int column) {

	if (playerTurn == 1) {
		int col = column;
		game.putPlayerOnGrid(col, "X");
		playerTurn = 2;
		ui.printGrid();
		try {
			game.saveTo("save.txt");
		} catch (FileNotFoundException e) {
			System.err.println("Could not save game: " + e.getLocalizedMessage());
			e.printStackTrace();
		}

	    for(int i = 0; i < 8; i++){

	        String pos = game.board[i][col];

	        // If the column does not equal ., then place it above that tile
	        if(!pos.equals(".")) {

	            if (i  - 1 >= 0) {
	                button[i - 1][col].setText("X");
	                // If a player is a winner, show game over and restart the game
	                if (game.checkWin() > 0){
	                	gameOver();
	                	restartGUI();
	                }
	            } else {
	                System.out.println("Invalid Move!");
	            }
	        return;
	        }
	    }
	}
	else {
		int col = column;
		game.putPlayerOnGrid(col, "O");
		playerTurn = 1;
		ui.printGrid();
		try {
			game.saveTo("save.txt");
		} catch (FileNotFoundException e) {
			System.err.println("Could not save game: " + e.getLocalizedMessage());
			e.printStackTrace();
		}

	    for(int i = 0; i < 7; i++){

	        String pos = game.board[i][col];

	        // If the column does not equal ., then place it above that tile
		        if(!pos.equals(".")) {

		            if (i  - 1 >= 0) {
		                button[i - 1][col].setText("O");
		                // If a player is a winner, show game over and restart the game
		                if (game.checkWin() > 0){
		                	gameOver();
		                	restartGUI();
		                }

		            } else {
		                System.out.println("Invalid Move!");
		            }
		        return;
		        }
		    }
		}
	}

	// Restart GUI creates new instances of the game and reassigns the text and game board variables

	public void restartGUI(){
		game = new ConnectFour();
		ui = new TextUI(game);
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				button[row][col].setText("");
			}
		}
		for(int i = 0; i < 7; i++){
	        for(int j = 0; j < 7; j++){
	        	game.board[i][j] = ".";
	        }
	        System.out.print("\n");
	    }
	}

	// Game over is an dialogue box that displays the information of the winner
	private void gameOver() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText("Game Over");
		alert.setContentText("Player " + playerTurn + " wins!");
		alert.showAndWait();
	}

	// Load from reads the file "save.txt" and assigns all text variables to the buttons
	public void loadFromGUI(String fileName) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(fileName));

		while(in.hasNext()){
			in.nextLine();
			for(int i = 0; i < 7; i++){
				for(int j = 0; j < 7; j++){
					button[i][j].setText(in.next());
				}
				// Need to break out of the loop or will create errors
				if (i == 5){
					return;
				}
			}
		}
	}


	public static void main(String[] args) {
		Application.launch(args);
	}

}
