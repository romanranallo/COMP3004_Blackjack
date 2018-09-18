package cards;

public class Jack extends FaceCard {

	public Jack(String suit) {
		super(suit);
	}
	
	@Override
	public String toString() {
		return ("Jack of " + this.getSuitName());
	}

}
