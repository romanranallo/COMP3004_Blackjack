package mvc;

import org.junit.BeforeClass;
import org.junit.Test;

import cards.Card;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BlackjackTest {
	private BlackjackGame blackjack = new ConsoleBlackjackGame();
	
	
	@Test
	public void testInitialDealToUser() {
		blackjack.dealCards(blackjack.getUser());

		assertEquals(2, blackjack.getUser().getHand().size());
		assertTrue(blackjack.getUser().getHand().get(0).isVisible() && blackjack.getUser().getHand().get(0).isVisible());
	}
	
	@Test
	public void testInitialDealToDealer() {
		blackjack.dealCards(blackjack.getDealer());

		assertEquals(2, blackjack.getDealer().getHand().size());
		assertTrue(blackjack.getDealer().getHand().get(0).isVisible() && !(blackjack.getDealer().getHand().get(1).isVisible()));
	}
	
	@Test
	public void testHit() {
		assertEquals(blackjack.getTurn(), blackjack.getUser());
		blackjack.dealCard(blackjack.getPlayer());
		assertEquals(3, blackjack.getPlayer().getHand().size());
		blackjack.dealCard(blackjack.getPlayer());
		assertEquals(4, blackjack.getPlayer().getHand().size());
		assertEquals(2, blackjack.getDealer().getHand().size());
	}
	
	@Test
	public void testPlayerStand() {
		assertTrue(blackjack.getDealer().getHand().get(0).isVisible() && blackjack.getDealer().getHand().get(1).isVisible()
			&& blackjack.getDealer().getHand().get(2).isVisible() && blackjack.getDealer().getHand().get(3).isVisible());
		blackjack.stand();
		assertEquals(2, blackjack.getDealer().getHand().size());
		assertEquals(blackjack.getDealer(), blackjack.getTurn());
	}
	
	@Test
	public void testBust() {
		BlackjackController controller = new BlackjackController();
		blackjack = new FileBlackjackGame();
		controller.readFile("testPlayerBust.txt");
		assertTrue(blackjack.isBusted(blackjack.getUser()));
		assertTrue(blackjack.getWinner(), blackjack.getDealer());
	}
	
	@Test
	public void testDealerLogic() {
		BlackjackController controller = new BlackjackController();
		blackjack = new FileBlackjackGame();
		controller.readFile("testDealer.txt");
		assertTrue(blackjack.getDealer().getHand().size() == 5);
		for (Card c: blackjack.getDealer().getHand()) {
			assertTrue(c.isVisible());
		}
	}
	
	@Test
	public void testAceLogic() {
		BlackjackController controller = new BlackjackController();
		blackjack = new FileBlackjackGame();
		controller.readFile("testAces.txt");
		assertTrue();
	}
	
	@Test
	public void testPlayerBlackjack() {
		
	}
	
	@Test
	public void testDealerBlackjack() {
		
	}
	
}
