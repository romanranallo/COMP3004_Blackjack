package cards;

public class Ace extends Card {
	
	public Ace(String representation) {
		super(representation);
		this.value = 11;
	}
	
	public Ace(String representation, boolean visible) {
		super(representation, visible);
	}
	
	public void setValue(int v) {
		this.value = v;
	}
}
