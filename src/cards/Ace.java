package cards;

public class Ace extends Card {
	
	public Ace(String suit) {
		super(suit, 11);
	}
	
	public void setValue(int v) {
		this.value = v;
	}
	
	@Override
	public String toString() {
		return ("Ace of " + this.getSuitName());
	}
}
