package cards;

public class Card {
	private String suit;
	private int value;
	private String representation;
	
	public Card(String representation) {
		this.suit = representation.substring(0, 1);
		this.representation = representation;
		
		if (representation.substring(1).equalsIgnoreCase("k") || 
			representation.substring(1).equalsIgnoreCase("q") ||
			representation.substring(1).equalsIgnoreCase("j")) {
			this.value = 10;
		}
		else if (representation.substring(1).equalsIgnoreCase("a")){
			// Is an ace
			this.value = 11;
		}
		else {
			// should be numeric if not a face card or an ace
			this.value = Integer.parseInt(representation.substring(1));
		}
	}
	
	public String getSuit() { return suit; }
	public int getValue() { return value; }
	public String getRepresentation() { return representation; }
	
	public boolean equals(Card c) {
		return ((this.getValue() == c.getValue()) && this.getSuit().equals(c.getSuit()));
	}
	
}
