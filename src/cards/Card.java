package cards;

public class Card {
	protected String suit;
	protected int value;
	protected String representation;
	protected boolean visible;
	
	public static Card builder(String representation) {
		String suit = representation.substring(0, 1);
		if (representation.substring(1).equalsIgnoreCase("k")) {
			return new King(suit);
		}
		else if (representation.substring(1).equalsIgnoreCase("q")) {
			return new Queen(suit);
		}
		else if (representation.substring(1).equalsIgnoreCase("j")) {
			return new Jack(suit);
		}
		else if (representation.substring(1).equalsIgnoreCase("a")) {
			return new Ace(suit);
		}
		else {	// Should be an int
			return new Card(suit, Integer.parseInt(representation.substring(1)));
		}
	}
	
	public Card(String suit, int value) {
		this.suit = suit;
		this.visible = true;
		this.value = value;
		this.representation = suit + value;
	}

	
	public String getSuit() { return suit; }
	public int getValue() { return value; }
	public String getRepresentation() { return representation; }
	public boolean isVisible() { return this.visible; }
	public void setVisible(boolean v) { this.visible = v; }
	
	public String getSuitName() {
		if (this.suit.equalsIgnoreCase("h")) {
			return "hearts";
		}
		else if (this.suit.equalsIgnoreCase("c")) {
			return "clubs";
		}
		else if (this.suit.equalsIgnoreCase("d")) {
			return "diamonds";
		}
		else {
			return "spades";
		}
	}
	
	public boolean isEquivalent(Card c) {
		if (c instanceof King || c instanceof Queen || c instanceof Jack) return false;
		return (this.value == c.getValue());
	}
	
	@Override
	public boolean equals(Object o) {
		Card c = (Card)o;
		return ((this.getValue() == c.getValue()) && this.getSuit().equals(c.getSuit()));
	}
	
	@Override
	public String toString() {
		return (value + " of " + this.getSuitName());
	}
}
