package player;

import java.util.ArrayList;

import cards.Card;
import cards.Hand;

public abstract class Player {
	
	protected ArrayList<Hand> hands;
	protected Hand currentHand;
	
	public Player() { 
		this.hands = new ArrayList<Hand>();
		this.hands.add(new Hand());
		currentHand = hands.get(0);
	}
	
	public ArrayList<Hand> getHands() { return this.hands; }	
	public Hand getHand(int i) { return this.hands.get(i); }
	public ArrayList<Card> getHand() { return currentHand.getCards(); }
	public void setHand(Hand h) { currentHand = h; } 
	public void addCard(Card c) { currentHand.addCard(c); };
	public void addHand() { hands.add(new Hand()); }
	public void addHand(Card c) { hands.add(new Hand(c)); }
	public int getScore() { return currentHand.getScore(); }
	public void setScore(int s) { currentHand.setScore(s); }
	
}
