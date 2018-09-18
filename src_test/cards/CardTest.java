package cards;

import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {
	
	@Test
	public void testBuilder() {
		
		Card c = Card.builder("H2");
		assertTrue(c.getSuit().equals("H") && c.getValue() == 2);
		assertTrue(c.getSuitName().equalsIgnoreCase("Hearts"));
		
		c = Card.builder("SK");
		assertTrue(c.getSuit().equals("S") && c.getValue() == 10);
		assertTrue(c.getSuitName().equalsIgnoreCase("spades"));
		assertTrue(c instanceof King);
		
		c = Card.builder("CQ");
		assertTrue(c.getSuit().equals("C") && c.getValue() == 10);
		assertTrue(c.getSuitName().equalsIgnoreCase("clubs"));
		assertTrue(c instanceof Queen);
		
		c = Card.builder("HJ");
		assertTrue(c.getSuit().equals("H") && c.getValue() == 10);
		assertTrue(c.getSuitName().equalsIgnoreCase("Hearts"));
		assertTrue(c instanceof Jack);
		
		c = Card.builder("H10");
		assertTrue(c.getSuit().equals("H") && c.getValue() == 10);
		assertTrue(c.getSuitName().equalsIgnoreCase("Hearts"));
		
		c = Card.builder("DA");
		// Ace should default to 11
		assertTrue(c.getSuit().equals("D") && c.getValue() == 11);
		assertTrue(c instanceof Ace);
		assertTrue(c.getSuitName().equalsIgnoreCase("diamonds"));
	}
	
	@Test
	public void testVisibility() {
		Card c = Card.builder("SA");
		assertTrue(c.isVisible());
		c.setVisible(false);
		assertFalse(c.isVisible());
		
	}

}
