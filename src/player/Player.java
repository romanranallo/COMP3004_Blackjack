package player;

import java.util.ArrayList;

import cards.Card;

public abstract class Player {
	
	private ArrayList<Card> hand;
	private int score;
	
	public Player() { 
		this.hand = new ArrayList<Card>();
		this.score = 0;
	}
	
	public ArrayList<Card> getHand() { return this.hand; }
	public int getScore() { return this.score; }
	public void setScore(int score) { this.score = score; }
	
	public void addCard(Card c) {
		this.hand.add(c);
	}
	
}
