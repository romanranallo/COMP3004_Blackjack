package cards;

public class King extends FaceCard {

	public King(String suit) {
		super(suit);
	}
	
	@Override
	public String toString() {
		return ("King of " + this.getSuitName());
	}

}
