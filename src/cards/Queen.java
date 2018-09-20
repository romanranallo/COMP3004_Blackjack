package cards;

public class Queen extends FaceCard {

	public Queen(String suit) {
		super(suit);
	}
	
	@Override
	public String toString() {
		return ("Queen of " + this.getSuitName());
	}
	
	@Override
	public boolean isEquivalent(Card c) {
		return (c instanceof Queen);
	}
	
	@Override
	public String getRepresentation() { return suit + "Q"; }
}
