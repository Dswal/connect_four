package connect_four.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

		// Initialize Game board.
		for(int i = 0; i < 7; i++){
	        for(int j = 0; j < 7; j++){
	        	game.board[i][j] = ".";
	        }
	        System.out.print("\n");
	    }
	}

	private void handleClick(final int row, final int column) {

	if (playerTurn == 1) {
		int col = column;
		game.putPlayerOnGrid(col, "X");
		playerTurn = 2;
		ui.printGrid();

	    for(int i = 0; i < 8; i++){

	        String pos = game.board[i][col];

	        // If the column does not equal ., then place it above that tile
	        if(!pos.equals(".")) {

	            if (i  - 1 >= 0) {
	                button[i - 1][col].setText("X");
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

	    for(int i = 0; i < 7; i++){

	        String pos = game.board[i][col];

	        // If the column does not equal ., then place it above that tile
		        if(!pos.equals(".")) {

		            if (i  - 1 >= 0) {
		                button[i - 1][col].setText("O");
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


	public void restartGUI(){
		game = new ConnectFour();
		ui = new TextUI(game);
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 6; col++) {
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

	private void gameOver() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText("Game Over");
		alert.setContentText("Player " + playerTurn + " wins!");
		alert.showAndWait();
	}


	public static void main(String[] args) {
		Application.launch(args);
	}

}
