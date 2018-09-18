package mvc;

import org.junit.BeforeClass;
import org.junit.Test;

import cards.Ace;
import cards.Card;

import org.junit.After;
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
		blackjack.getUser().addCard(new Card("SK"));
		blackjack.getUser().addCard(new Card("SQ"));
		blackjack.getUser().addCard(new Card("SJ"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(30, blackjack.getUser().getScore());
		assertTrue(blackjack.isBusted(blackjack.getUser()));
		assertEquals(blackjack.getWinner(), blackjack.getDealer());
	}
	
	@Test
	public void testAceLogic() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(new Ace("SA"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(blackjack.getUser().getScore(), 11);
		
		blackjack.getUser().addCard(new Ace("HA"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(blackjack.getUser().getScore(), 12);
		
		blackjack.getUser().addCard(new Card("DJ"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(blackjack.getUser().getScore(), 12);
	}
	

	@Test
	public void testPlayerBlackjack() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(new Card("SK"));
		blackjack.getUser().addCard(new Ace("SA"));
		blackjack.updateScore(blackjack.getUser());
		
		assertTrue(blackjack.isBlackjack());
		assertEquals(blackjack.getWinner(), blackjack.getUser());
	}
	
	@Test
	public void testDealerBlackjack() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(new Card("SK"));
		blackjack.getUser().addCard(new Ace("SA"));
		blackjack.updateScore(blackjack.getUser());
		
		blackjack.getDealer().addCard(new Card("SQ"));
		blackjack.getDealer().addCard(new Ace("DA"));
		blackjack.updateScore(blackjack.getDealer());
		
		assertTrue(blackjack.isBlackjack());
		assertEquals(blackjack.getWinner(), blackjack.getDealer());
	}
	
	
	@Test
	public void testDealerLogic() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getDealer().addCard(new Card("S2"));
		blackjack.getDealer().addCard(new Card("D4"));
		blackjack.updateScore(blackjack.getDealer());
		assertTrue(blackjack.dealerShouldHit());
		
		blackjack.getDealer().addCard(new Ace("SA"));
		blackjack.updateScore(blackjack.getDealer());
		assertTrue(blackjack.dealerHasSoft17());
		assertTrue(blackjack.dealerShouldHit());
		
		blackjack.getDealer().addCard(new Card("SK"));
		blackjack.updateScore(blackjack.getDealer());
		assertFalse(blackjack.dealerShouldHit());

		blackjack.getDealer().addCard(new Card("DK"));
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
		
		blackjack.getUser().addCard(new Card("SK"));
		blackjack.getUser().addCard(new Card("S6"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(16, blackjack.getUser().getScore());
		
		blackjack.getDealer().addCard(new Card("SQ"));
		blackjack.getDealer().addCard(new Card("S5"));
		blackjack.updateScore(blackjack.getDealer());
		assertEquals(15, blackjack.getUser().getScore());
		
		blackjack.endDealerTurn();
		assertEquals(blackjack.getWinner(), blackjack.getUser());
	}
	
	@Test
	public void testDealerHigherScore() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(new Card("SK"));
		blackjack.getUser().addCard(new Card("S4"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(14, blackjack.getUser().getScore());
		
		blackjack.getDealer().addCard(new Card("SQ"));
		blackjack.getDealer().addCard(new Card("S5"));
		blackjack.updateScore(blackjack.getDealer());
		assertEquals(15, blackjack.getUser().getScore());
		
		blackjack.endDealerTurn();
		assertEquals(blackjack.getWinner(), blackjack.getDealer());
	}
	
	@Test
	public void testDealerWinsWhenTied() {
		blackjack = new ConsoleBlackjackGame();
		
		blackjack.getUser().addCard(new Card("SK"));
		blackjack.getUser().addCard(new Card("D5"));
		blackjack.updateScore(blackjack.getUser());
		assertEquals(15, blackjack.getUser().getScore());
		
		blackjack.getDealer().addCard(new Card("SQ"));
		blackjack.getDealer().addCard(new Card("S5"));
		blackjack.updateScore(blackjack.getDealer());
		assertEquals(15, blackjack.getUser().getScore());
		
		blackjack.endDealerTurn();
		assertEquals(blackjack.getWinner(), blackjack.getDealer());
	}
	
	

	
}
