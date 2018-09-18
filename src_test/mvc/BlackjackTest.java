package mvc;

import org.junit.Test;
import cards.Card;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BlackjackTest {
	
	BlackjackGame blackjack;
	
	@Before
	public void setup() {
		blackjack = new ConsoleBlackjackGame();
	}
	
	
	@Test
	public void testInitialDeal_PlayerHit_PlayerStand() {
		// Initial Deal
		blackjack.initialDeal();

		assertEquals(2, blackjack.getUser().getHand().size());
		assertTrue(blackjack.getUser().getHand().get(0).isVisible() && blackjack.getUser().getHand().get(0).isVisible());
		
		assertEquals(2, blackjack.getDealer().getHand().size());
		assertTrue(blackjack.getDealer().getHand().get(0).isVisible() && !(blackjack.getDealer().getHand().get(1).isVisible()));
		
		// hit
		assertEquals(blackjack.getTurn(), blackjack.getUser());
		blackjack.dealCard(blackjack.getUser());
		assertEquals(3, blackjack.getUser().getHand().size());
		
		assertEquals(blackjack.getTurn(), blackjack.getUser());
		blackjack.dealCard(blackjack.getUser());
		assertEquals(4, blackjack.getUser().getHand().size());
		
		
		// Stand
		blackjack.endPlayerTurn();

		assertEquals(2, blackjack.getDealer().getHand().size());
		assertEquals(blackjack.getDealer(), blackjack.getTurn());
	}
	
	@Test
	public void testBust() {
		blackjack = new ConsoleBlackjackGame();
		blackjack.getUser().addCard(Card.builder("SK"));
		blackjack.getUser().addCard(Card.builder("SQ"));
		blackjack.getUser().addCard(Card.builder("SJ"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(30, blackjack.getUser().getScore());
		assertTrue(blackjack.isBusted(blackjack.getUser()));
		assertEquals(blackjack.getWinner(), blackjack.getDealer());
	}
	
	@Test
	public void testAceLogic() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(Card.builder("SA"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(blackjack.getUser().getScore(), 11);
		
		blackjack.getUser().addCard(Card.builder("HA"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(blackjack.getUser().getScore(), 12);
		
		blackjack.getUser().addCard(Card.builder("DJ"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(blackjack.getUser().getScore(), 12);
	}
	

	@Test
	public void testPlayerBlackjack() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(Card.builder("SK"));
		blackjack.getUser().addCard(Card.builder("SA"));
		blackjack.updateScore(blackjack.getUser());
		
		assertTrue(blackjack.isBlackjack());
		assertEquals(blackjack.getWinner(), blackjack.getUser());
	}
	
	@Test
	public void testDealerBlackjack() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(Card.builder("SK"));
		blackjack.getUser().addCard(Card.builder("SA"));
		blackjack.updateScore(blackjack.getUser());
		
		blackjack.getDealer().addCard(Card.builder("SQ"));
		blackjack.getDealer().addCard(Card.builder("DA"));
		blackjack.updateScore(blackjack.getDealer());
		
		assertTrue(blackjack.isBlackjack());
		assertEquals(blackjack.getWinner(), blackjack.getDealer());
	}
	
	
	@Test
	public void testDealerLogic() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getDealer().addCard(Card.builder("S2"));
		blackjack.getDealer().addCard(Card.builder("D4"));
		blackjack.updateScore(blackjack.getDealer());
		assertTrue(blackjack.dealerShouldHit());
		
		blackjack.getDealer().addCard(Card.builder("SA"));
		blackjack.updateScore(blackjack.getDealer());
		assertTrue(blackjack.dealerHasSoft17());
		assertTrue(blackjack.dealerShouldHit());
		
		blackjack.getDealer().addCard(Card.builder("SK"));
		blackjack.updateScore(blackjack.getDealer());
		assertFalse(blackjack.dealerShouldHit());

		blackjack.getDealer().addCard(Card.builder("DK"));
		blackjack.updateScore(blackjack.getDealer());
		assertFalse(blackjack.dealerShouldHit());
		blackjack.endDealerTurn();
		assertTrue(blackjack.getDealer().getHand().get(0).isVisible() && blackjack.getDealer().getHand().get(1).isVisible()
				&& blackjack.getDealer().getHand().get(2).isVisible() && blackjack.getDealer().getHand().get(3).isVisible()
				&& blackjack.getDealer().getHand().get(3).isVisible());
		assertTrue(blackjack.isBusted(blackjack.getDealer()));
		assertEquals(blackjack.getWinner(), blackjack.getUser());
		
	}
	
	@Test
	public void testPlayerHigherScore() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(Card.builder("SK"));
		blackjack.getUser().addCard(Card.builder("S6"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(16, blackjack.getUser().getScore());
		
		blackjack.getDealer().addCard(Card.builder("SQ"));
		blackjack.getDealer().addCard(Card.builder("S5"));
		blackjack.updateScore(blackjack.getDealer());
		assertEquals(15, blackjack.getDealer().getScore());
		
		blackjack.endDealerTurn();
		assertEquals(blackjack.getWinner(), blackjack.getUser());
	}
	
	@Test
	public void testDealerHigherScore() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(Card.builder("SK"));
		blackjack.getUser().addCard(Card.builder("S4"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(14, blackjack.getUser().getScore());
		
		blackjack.getDealer().addCard(Card.builder("SQ"));
		blackjack.getDealer().addCard(Card.builder("S5"));
		blackjack.updateScore(blackjack.getDealer());
		assertEquals(15, blackjack.getDealer().getScore());
		
		blackjack.endDealerTurn();
		assertEquals(blackjack.getWinner(), blackjack.getDealer());
	}
	
	@Test
	public void testDealerWinsWhenTied() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(Card.builder("SK"));
		blackjack.getUser().addCard(Card.builder("D5"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(15, blackjack.getUser().getScore());
		
		blackjack.getDealer().addCard(Card.builder("SQ"));
		blackjack.getDealer().addCard(Card.builder("S5"));
		blackjack.updateScore(blackjack.getDealer());
		assertEquals(15, blackjack.getUser().getScore());
		
		blackjack.endDealerTurn();
		assertEquals(blackjack.getWinner(), blackjack.getDealer());
	}
	
	@Test
	public void testFileInput() {
		String file = "SK SQ SJ S8 S";
		blackjack = new FileBlackjackGame(file.split(" "));
		
		blackjack.initialDeal();
		assertEquals(2, blackjack.getUser().getHand().size());
		assertEquals(2, blackjack.getDealer().getHand().size());
		assertTrue("h".equalsIgnoreCase(((FileBlackjackGame)blackjack).getHitOrStand()));
	}
	
	@Test
	public void testSplit() {
		blackjack = new ConsoleBlackjackGame();
		blackjack.getUser().addCard(Card.builder("DK"));
		blackjack.getUser().addCard(Card.builder("SK"));
		assertTrue(blackjack.canSplit(blackjack.getUser()));
		
		blackjack.getDealer().addCard(Card.builder("DA"));
		blackjack.getDealer().addCard(Card.builder("SA"));
		assertTrue(blackjack.canSplit(blackjack.getDealer()));
		
		blackjack = new ConsoleBlackjackGame();
		blackjack.getUser().addCard(Card.builder("D6"));
		blackjack.getUser().addCard(Card.builder("H6"));
		assertTrue(blackjack.canSplit(blackjack.getUser()));
		
		blackjack.getDealer().addCard(Card.builder("S8"));
		blackjack.getDealer().addCard(Card.builder("H3"));
		assertFalse(blackjack.canSplit(blackjack.getUser()));
		
	}
	
}
