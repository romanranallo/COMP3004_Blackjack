package cards;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import static org.junit.Assert.*;


public class DeckTest {
	
	@Test
	public void testDeckSize() {
		Deck d = new Deck();
		
		assertEquals(52, d.getSize());
	}
	
	@Test
	public void testShuffle() {
		Deck d1 = new Deck();
		Deck d2 = new Deck();
		
		d1.shuffle();
		d2.shuffle();
		
		assertFalse(d1.equals(d2));
	}
	
	@Test
	public void testGetCard() {
		
		Deck d = new Deck();
		
		Card c1 = d.getCard();
		Card c2 = d.getCard();
		
		assertFalse(c1.equals(c2));
		
	}
	
	
}