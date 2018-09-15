package cards;

import java.util.*;

public class Deck {
	private List<Card> deck = new ArrayList<>();
	
	/**
	 * S - Spades; C - Clubs, H - hearts, D - diamonds
	 */
	public Deck() {

		deck.add(new Card("SA"));
		deck.add(new Card("CA"));
		deck.add(new Card("HA"));
		deck.add(new Card("DA"));
		
		for (int i = 2; i <= 10; i++) {
			deck.add(new Card("S" + i));
			deck.add(new Card("C" + i));
			deck.add(new Card("H" + i));
			deck.add(new Card("D" + i));
		}
		
		deck.add(new Card("SK"));
		deck.add(new Card("CK"));
		deck.add(new Card("HK"));
		deck.add(new Card("DK"));

		deck.add(new Card("SQ"));
		deck.add(new Card("CQ"));
		deck.add(new Card("HQ"));
		deck.add(new Card("DQ"));
		
		deck.add(new Card("SJ"));
		deck.add(new Card("CJ"));
		deck.add(new Card("HJ"));
		deck.add(new Card("DJ"));
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
	
	public boolean equals(Deck d) {
		if (d.getSize() != this.deck.size()) return false;
		
		for (int i = 0; i < this.deck.size(); i++) {
			if (!d.getDeck().get(i).equals(this.deck.get(i))) return false;
		}
		return true;
	}
}
