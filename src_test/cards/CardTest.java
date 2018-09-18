package cards;

import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {
	
	@Test
	public void testCtr() {
		
		Card c = new Card("H2");
		assertTrue(c.getSuit().equals("H") && c.getValue() == 2);
		
		c = new Card("SK");
		assertTrue(c.getSuit().equals("S") && c.getValue() == 10);
		
		c = new Card("CQ");
		assertTrue(c.getSuit().equals("C") && c.getValue() == 10);
		
		c = new Card("HJ");
		assertTrue(c.getSuit().equals("H") && c.getValue() == 10);
		
		c = new Card("C10");
		assertTrue(c.getSuit().equals("C") && c.getValue() == 10);
		
		c = new Card("DA");
		// Ace should default to 11
		assertTrue(c.getSuit().equals("D") && c.getValue() == 11);

		c = new Card("SK");
		assertTrue(c.getSuit().equals("S") && c.getValue() == 10);
		assertTrue(c instanceof King);
		
		c = new Card("CQ");
		assertTrue(c.getSuit().equals("C") && c.getValue() == 10);
		assertTrue(c instanceof Queen);
		
		c = new Card("HJ");
		assertTrue(c.getSuit().equals("H") && c.getValue() == 10);
		assertTrue(c instanceof Jack);
		
		c = new Card("DA");
		// Ace should default to 11
		assertTrue(c.getSuit().equals("D") && c.getValue() == 11);
		assertTrue(c instanceof Ace);
	}
	
	@Test
	public void testVisibility() {
		Card c = new Card("SA");
		assertTrue(c.isVisible());
		
		c = new Card("DA", true);
		assertTrue(c.isVisible());
		c.setVisible(false);
		assertFalse(c.isVisible());
		
		c = new Card("HJ", false);
		assertFalse(c.isVisible());
		c.setVisible(true);
		assertTrue(c.isVisible());
		 
	}

}
