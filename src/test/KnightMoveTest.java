package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import controller.BoardController;
import pieces.Bishop;
import pieces.Knight;
import pieces.Type;

public class KnightMoveTest {
	BoardController b;
	int x, y, deltaX, deltaY;
	
	
	
	@Before
	public void setUp() throws Exception {
		b= new BoardController();
		b.spawnChessPiece(1, 0, new Knight(true));  
		b.spawnChessPiece(3, 4, new Knight(true)); 
		b.spawnChessPiece(0, 7, new Knight(true));
		b.spawnChessPiece(2, 6, new Bishop(false));
		b.spawnChessPiece(3, 1, new Bishop(true));	
		b.printBoard();
	}


	@Test
	public void KnightType() {
		b.selectChessPiece(1, 0);
		assertEquals(b.getSelectedPiece().getType(), Type.Knight);
	}
	
	@Test
	public void MoveUpLeft() {
		b.selectChessPiece(3,4);
		x = b.getSelectedPiece().currentX;
		y = b.getSelectedPiece().currentY;
		deltaX = -1;
		deltaY = 2;
		b.moveChessPiece(x+deltaX, y+deltaY);
		b.printBoard();
		assertEquals(b.board.getBoard()[x+deltaX][y+deltaY].getType(), Type.Knight);
		assertEquals(b.board.getBoard()[x][y], null);
	}
	
	@Test
	public void MoveDownLeft() {
		b.selectChessPiece(3,4);
		x = b.getSelectedPiece().currentX;
		y = b.getSelectedPiece().currentY;
		deltaX = -1;
		deltaY = -2;
		b.moveChessPiece(x+deltaX, y+deltaY);
		b.printBoard();
		assertEquals(b.board.getBoard()[x+deltaX][y+deltaY].getType(), Type.Knight);
		assertEquals(b.board.getBoard()[x][y], null);
	}
	
	@Test
	public void MoveUpRight() {
		b.selectChessPiece(3,4);
		x = b.getSelectedPiece().currentX;
		y = b.getSelectedPiece().currentY;
		deltaX = 1;
		deltaY = 2;
		b.moveChessPiece(x+deltaX, y+deltaY);
		b.printBoard();
		assertEquals(b.board.getBoard()[x+deltaX][y+deltaY].getType(), Type.Knight);
		assertEquals(b.board.getBoard()[x][y], null);
	}
	
	@Test
	public void MoveDownRight() {
		b.selectChessPiece(3,4);
		x = b.getSelectedPiece().currentX;
		y = b.getSelectedPiece().currentY;
		deltaX = 1;
		deltaY = -2;
		b.moveChessPiece(x+deltaX, y+deltaY);
		b.printBoard();
		assertEquals(b.board.getBoard()[x+deltaX][y+deltaY].getType(), Type.Knight);
		assertEquals(b.board.getBoard()[x][y], null);
	}
	
	@Test
	public void MoveOutOfBoard() {
		b.selectChessPiece(1, 0);
		x = b.getSelectedPiece().currentX;
		y = b.getSelectedPiece().currentY;
		deltaX = -1;
		deltaY = -2;
		b.printBoard();
		assertEquals(b.moveChessPiece(x+deltaX, y+deltaY), false);
		assertEquals(b.board.getBoard()[x][y].getType(), Type.Knight);
	}
	@Test
	public void MoveOutOfBoardUp() {
		System.out.println("Moveup");
		b.selectChessPiece(0, 7);
		System.out.println(b.getSelectedPiece().currentX);
		x = b.getSelectedPiece().currentX;
		y = b.getSelectedPiece().currentY;
		deltaX = 1;
		deltaY = 2;
		b.printBoard();
		assertEquals(b.moveChessPiece(x+deltaX, y+deltaY), false);
		assertEquals(b.board.getBoard()[x][y].getType(), Type.Knight);
	}
	
	@Test
	public void hitEnemy(){
		b.selectChessPiece(3, 4);
		int arraySizeBefore = b.activeChessPieces.size();
		x = b.getSelectedPiece().currentX;
		y = b.getSelectedPiece().currentY;
		deltaX = -1;
		deltaY = 2;
		b.moveChessPiece(x+deltaX, y+deltaY);
		System.out.println(b.activeChessPieces.size());
		assertEquals(b.board.getBoard()[x+deltaX][y+deltaY].getType(), Type.Knight);
		assertEquals(b.board.getBoard()[x][y], null);
		assertNotEquals(arraySizeBefore, b.activeChessPieces.size());
	}
	
	@Test
	public void hitFriendly(){
		b.selectChessPiece(1, 0);
		int arraySizeBefore = b.activeChessPieces.size();
		System.out.println(arraySizeBefore);
		x = b.getSelectedPiece().currentX;
		y = b.getSelectedPiece().currentY;
		deltaX = 2;
		deltaY = 1;
		b.moveChessPiece(x+deltaX, y+deltaY);
		System.out.println(b.activeChessPieces.size());
		assertNotEquals(b.board.getBoard()[x+deltaX][y+deltaY].getType(), Type.Knight);
		assertEquals(b.board.getBoard()[x][y].getType(), Type.Knight );
		assertEquals(arraySizeBefore, b.activeChessPieces.size());
	}
	
}
