package connect_four.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import connect_four.ConnectFour;
import connect_four.TextUI;



public class GUI extends Application{

	private ConnectFour game = new ConnectFour();

	private int player = 1;

	private Button[][] button = new Button[7][7];

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		pane.setGridLinesVisible(true);
		pane.setStyle("-fx-background-color:red");


		for (int row = 0; row < 7; row++) {
			for (int col = 0; col < 7; col++) {
				Button but = new Button(".");
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
		Scene scene = new Scene(pane, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void handleClick(final int row, final int column) {

//	if (player == 1) {
//		int col = column;
//		game.putPlayerOnGrid(col, "X");
//		player = 1;
//	} 
//	else {
//		int col = column;
//		game.putPlayerOnGrid(col, "O");
//		player = 2;
//		}

		
		
		
		

	}




	public static void main(String[] args) {
		Application.launch(args);
	}

}
