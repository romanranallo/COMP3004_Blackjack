package cards;

public class Queen extends FaceCard {

	public Queen(String suit) {
		super(suit);
	}
	
	@Override
	public String toString() {
		return ("Queen of " + this.getSuitName());
	}

}
