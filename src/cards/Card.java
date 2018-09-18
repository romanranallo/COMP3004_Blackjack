package cards;

public class Card {
	protected String suit;
	protected int value;
	protected String representation;
	protected boolean visible;
	
	public Card(String representation) {
		this.suit = representation.substring(0, 1);
		this.representation = representation;
		this.visible = true;
		
		if (representation.substring(1).equalsIgnoreCase("k") || 
			representation.substring(1).equalsIgnoreCase("q") ||
			representation.substring(1).equalsIgnoreCase("j")) {
			this.value = 10;
		}
		else if (representation.substring(1).equalsIgnoreCase("a")) {}
		else {
			// should be numeric if not a face card or an ace
			this.value = Integer.parseInt(representation.substring(1));
		}
	}
	
	public Card(String representation, boolean visible) {
		this(representation);
		this.visible = visible;
	}
	
	public String getSuit() { return suit; }
	public int getValue() { return value; }
	public String getRepresentation() { return representation; }
	public boolean isVisible() { return this.visible; }
	public void setVisible(boolean v) { this.visible = v; }
	
	public boolean equals(Card c) {
		return ((this.getValue() == c.getValue()) && this.getSuit().equals(c.getSuit()));
	}
	
}
