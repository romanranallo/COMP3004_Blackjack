package cards;

import java.util.ArrayList;

public class Hand {

	public ArrayList<Card> cards;
	public int score;
	
	public Hand() {
		this.cards = new ArrayList<Card>();
		this.score = 0;
	}
	
	public Hand(Card c) {
		this();
		cards.add(c);
		score = c.getValue();
	}
	
	public ArrayList<Card> getCards() { return this.cards; }
	public int size() { return this.cards.size(); }
	public void addCard(Card c) { this.cards.add(c); }
	public int getScore() { return this.score; }
	public void setScore(int s) { this.score = s; }

	public void moveCardTo(Card c, Hand h) {
		int index = cards.indexOf(c);
		if (index < 0) return;
		h.addCard(cards.get(index));
		cards.remove(index);
	}
}
