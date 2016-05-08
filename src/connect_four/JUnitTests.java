package connect_four;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the TicTacToe class.
 *
 * @author Mark Utting
 */
public class JUnitTests {

	protected TextUI game;

	@Before
	public void setUp() {
		game = new TextUI();
		// Create the board, the reason we do this in the test is the way the board
		// gets setup in the actual code. *Can't figure out how to assign an input to a scanner*
		for(int i = 0; i < 7; i++){
	        for(int j = 0; j < 7; j++){
	        	ConnectFour.board[i][j] = ".";
	}
		}
	}

	@Test
	public void testEmpty() {
		// check that the bottom row is empty
		assertEquals(".", ConnectFour.board[5][6]);
		assertEquals(".", ConnectFour.board[5][5]);
		assertEquals(".", ConnectFour.board[5][4]);
		assertEquals(".", ConnectFour.board[5][3]);
		assertEquals(".", ConnectFour.board[5][2]);
		assertEquals(".", ConnectFour.board[5][1]);
		assertEquals(".", ConnectFour.board[5][0]);
	}

	/** A correct game where player 1 wins along a row. */
	@Test
	public void testWinner1Row() {
		ConnectFour.putPlayerOnGrid(0,"O");
		ConnectFour.putPlayerOnGrid(1,"O");
		ConnectFour.putPlayerOnGrid(2,"O");
		ConnectFour.putPlayerOnGrid(3,"O");
		assertEquals(1, ConnectFour.checkWin());
	}
//
//	/** A correct game where player 2 wins down a column. */
	@Test
	public void testWinner2Col() {
		ConnectFour.putPlayerOnGrid(0,"X");
		ConnectFour.putPlayerOnGrid(0,"X");
		ConnectFour.putPlayerOnGrid(0,"X");
		ConnectFour.putPlayerOnGrid(0,"X");
		assertEquals(2, ConnectFour.checkWin());
	}

	/** A correct game where player 1 wins along a diagonal. */
//	@Test
//	public void testWinner1Diag() {
//		ConnectFour.putPlayerOnGrid(0,"O");
//		ConnectFour.putPlayerOnGrid(1,"X");
//		ConnectFour.putPlayerOnGrid(1,"O");
//		ConnectFour.putPlayerOnGrid(2,"O");
//		ConnectFour.putPlayerOnGrid(2,"O");
//		ConnectFour.putPlayerOnGrid(2,"O");
//		ConnectFour.putPlayerOnGrid(3,"X");
//		ConnectFour.putPlayerOnGrid(3,"X");
//		ConnectFour.putPlayerOnGrid(3,"X");
//		ConnectFour.putPlayerOnGrid(3,"O");
//		assertEquals(2, ConnectFour.checkWin());
//	}


	@Test
	public void testDisplay() {
		ConnectFour.putPlayerOnGrid(0,"X");
		ConnectFour.putPlayerOnGrid(0,"O");
		ConnectFour.putPlayerOnGrid(3,"X");
		ConnectFour.putPlayerOnGrid(3,"O");
		ConnectFour.putPlayerOnGrid(5,"X");
		ConnectFour.putPlayerOnGrid(5,"O");
		ConnectFour.putPlayerOnGrid(5,"X");
		// check the display of this game
		String[] strs = TextUI.printGrid();
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
