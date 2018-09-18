package cards;

import java.util.*;

public class Deck {
	private List<Card> deck = new ArrayList<>();
	
	/**
	 * S - Spades; C - Clubs, H - hearts, D - diamonds
	 */
	public Deck() {

		deck.add(Card.builder("SA"));
		deck.add(Card.builder("CA"));
		deck.add(Card.builder("HA"));
		deck.add(Card.builder("DA"));
		
		for (int i = 2; i <= 10; i++) {
			deck.add(Card.builder("S" + i));
			deck.add(Card.builder("C" + i));
			deck.add(Card.builder("H" + i));
			deck.add(Card.builder("D" + i));
		}
		
		deck.add(Card.builder("SK"));
		deck.add(Card.builder("CK"));
		deck.add(Card.builder("HK"));
		deck.add(Card.builder("DK"));

		deck.add(Card.builder("SQ"));
		deck.add(Card.builder("CQ"));
		deck.add(Card.builder("HQ"));
		deck.add(Card.builder("DQ"));
		
		deck.add(Card.builder("SJ"));
		deck.add(Card.builder("CJ"));
		deck.add(Card.builder("HJ"));
		deck.add(Card.builder("DJ"));
	}
	
	// Shuffle the deck
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public int getSize() { return deck.size(); }
	public List<Card> getDeck() { return this.deck; }
	
	/**
	 * Gets and removes the next available <code>Card</code> in the deck
	 */
	public Card getCard() {
		Card c = deck.get(0);
		deck.remove(0);
		return c;
	}
	
	@Override
	public boolean equals(Object o) {
		Deck d = (Deck)o;
		if (d.getSize() != this.deck.size()) return false;
		
		for (int i = 0; i < this.deck.size(); i++) {
			if (!d.getDeck().get(i).equals(this.deck.get(i))) return false;
		}
		return true;
	}
}
