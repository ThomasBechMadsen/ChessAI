package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import controller.BoardController;
import pieces.Bishop;
import pieces.Knight;
import pieces.Type;

public class KnightMoveTest {
	BoardController b ;
	
	
	@Before
	public void setUp() throws Exception {
		b= new BoardController();
		b.spawnChessPiece(1, 0, new Knight(true));  // Sætter hvid bonde i næst sidste række
		b.spawnChessPiece(1, 7, new Knight(false)); // sætter sort bonde i næst sidste række
		b.spawnChessPiece(2, 7, new Bishop(false));
		b.spawnChessPiece(2, 0, new Bishop(true));	
		b.printBoard();
	}


	@Test
	public void KightType() {
		b.selectChessPiece(1, 0);
		assertEquals(b.getSelectedPiece().getType(), Type.Knight);
	}
	
	@Test
	public void MoveUpLeft() {
		b.selectChessPiece(1, 0);
		b.moveChessPiece(0, 2);
		b.printBoard();
		assertEquals(b.chessPieces[0][2].getType(), Type.Knight);
		assertEquals(b.chessPieces[1][0], null);
	}
	
	@Test
	public void MoveDownLeft() {
		b.selectChessPiece(1, 0);
		b.printBoard();
		assertEquals(b.moveChessPiece(0, -2), false);
		assertEquals(b.chessPieces[1][0].getType(), Type.Knight);
	}

}
