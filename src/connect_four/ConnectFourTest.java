package connect_four;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the ConnectFour class.
 *
 * @author David Swallow
 */
public class ConnectFourTest {
	protected TextUI textUI;
	protected ConnectFour game;

	@Before
	public void setUp() {
		game = new ConnectFour();
		textUI = new TextUI(game);
		// Create the board, the reason we do this in the test is the way the board
		// gets setup in the actual code. *Can't figure out how to assign an input to a scanner*
		for(int i = 0; i < 7; i++){
	        for(int j = 0; j < 7; j++){
	        	game.board[i][j] = ".";
	}
		}
	}

	@Test
	public void testEmpty() {
		// check that the bottom row is empty
		assertEquals(".", game.board[5][6]);
		assertEquals(".", game.board[5][5]);
		assertEquals(".", game.board[5][4]);
		assertEquals(".", game.board[5][3]);
		assertEquals(".", game.board[5][2]);
		assertEquals(".", game.board[5][1]);
		assertEquals(".", game.board[5][0]);
	}

	/** A correct game where player 1 wins along a row. */
	@Test
	public void testWinner1Row() {
		game.putPlayerOnGrid(0,"O");
		game.putPlayerOnGrid(1,"O");
		game.putPlayerOnGrid(2,"O");
		game.putPlayerOnGrid(3,"O");
		assertEquals(1, game.checkWin());
	}
//
//	/** A correct game where player 2 wins down a column. */
	@Test
	public void testWinner2Col() {
		game.putPlayerOnGrid(0,"X");
		game.putPlayerOnGrid(0,"X");
		game.putPlayerOnGrid(0,"X");
		game.putPlayerOnGrid(0,"X");
		assertEquals(2, game.checkWin());
	}

	/** A correct game where player 1 wins along a diagonal. */
	@Test
	public void testWinner1Diag() {
		game.putPlayerOnGrid(0,"O");
		game.putPlayerOnGrid(1,"X");
		game.putPlayerOnGrid(1,"O");
		game.putPlayerOnGrid(2,"O");
		game.putPlayerOnGrid(2,"O");
		game.putPlayerOnGrid(2,"O");
		game.putPlayerOnGrid(3,"X");
		game.putPlayerOnGrid(3,"X");
		game.putPlayerOnGrid(3,"X");
		game.putPlayerOnGrid(3,"O");
		textUI.printGrid();
		assertEquals(1, game.checkWin());
	}


	@Test
	public void testDisplay() {
		game.putPlayerOnGrid(0,"X");
		game.putPlayerOnGrid(0,"O");
		game.putPlayerOnGrid(3,"X");
		game.putPlayerOnGrid(3,"O");
		game.putPlayerOnGrid(5,"X");
		game.putPlayerOnGrid(5,"O");
		game.putPlayerOnGrid(5,"X");
		// check the display of this game
		String[] strs = textUI.printGrid();
		assertEquals(7, strs.length);
		assertEquals(". . . . . . . ", strs[0]);
		assertEquals(". . . . . . . ", strs[1]);
		assertEquals(". . . . . . . ", strs[2]);
		assertEquals(". . . . . . . ", strs[3]);
		assertEquals(". . . . . X . ", strs[4]);
		assertEquals("O . . O . O . ", strs[5]);
		assertEquals("X . . X . X . ", strs[6]);
	}

}
