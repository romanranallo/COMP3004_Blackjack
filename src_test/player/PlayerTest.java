package player;

import cards.Card;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

	@Test
	public void testBestScore() {
		Player p = new User();
		
		p.addHand();
		p.setScore(12);
		
		p.setHand(p.getHand(1));
		p.setScore(13);
		
		assertEquals(13, p.getBestScore());
	}

}
