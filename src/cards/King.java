package cards;

public class King extends FaceCard {

	public King(String suit) {
		super(suit);
	}
	
	@Override
	public String toString() {
		return ("King of " + this.getSuitName());
	}
		
	@Override
	public boolean isEquivalent(Card c) {
		return (c instanceof King);
	}
	
	@Override
	public String getRepresentation() { return suit + "K"; }
}
