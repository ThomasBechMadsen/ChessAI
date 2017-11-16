package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.BoardController;
import pieces.Bishop;
import pieces.Pawn;
import pieces.Type;

public class PawnPromotionTest {

	BoardController b;

	@Before
	public void setUp() throws Exception {
		b = new BoardController();
		b.spawnChessPiece(1, 6, new Pawn(true));  // Sætter hvid bonde i næst sidste række
		b.spawnChessPiece(1, 1, new Pawn(false)); // sætter sort bonde i næst sidste række
		b.spawnChessPiece(2, 7, new Bishop(false));
		b.spawnChessPiece(2, 0, new Bishop(true));
		b.printBoard();
	}

	//Tester om hvid bonde bliver promotet og den gamle typen ændres
	@Test
	public void WhitePawnPromotion() { 
		//ryk hvid bonde frem
		b.selectChessPiece(1, 6);
		b.moveChessPiece(1, 7);
		assertEquals(b.board.getBoard()[1][7].type, Type.Queen);
		assertEquals(b.board.getBoard()[1][6], null);
		b.printBoard();
	}
	
	@Test
	public void BlackPawnPromotion(){
		// giv sort lov til at rykke
		b.isWhiteTurn = false;
		//ryk sort bonde frem
		b.selectChessPiece(1, 1);
		b.moveChessPiece(1, 0);
		assertEquals(b.board.getBoard()[1][0].type, Type.Queen);
		assertEquals(b.board.getBoard()[1][1], null);
	}
	
	@Test
	public void WhitePawnOnHitPromotion(){
		// giv sort lov til at rykke
		//slå hvid løbere
		b.selectChessPiece(1, 6);
		b.moveChessPiece(2, 7);
		assertEquals(b.board.getBoard()[2][7].type, Type.Queen);
		assertEquals(b.board.getBoard()[1][6], null);
	}
	
	@Test
	public void BlackPawnOnHitPromotion(){
		// giv sort lov til at rykke
		b.isWhiteTurn = false;
		//slå hvid løbere
		b.selectChessPiece(1, 1);
		b.moveChessPiece(2, 0);
		assertEquals(b.board.getBoard()[2][0].type, Type.Queen);
		assertEquals(b.board.getBoard()[1][1], null);
	}
	


}
