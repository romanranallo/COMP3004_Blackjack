package mvc;

import org.junit.Test;
import static org.junit.Assert.*;

public class BlackjackControllerTest {
	
	@Test
	public void testUserInputWithConsole() {
		BlackjackController controller = new BlackjackController();
		controller.setInputType("c");
		assertTrue(controller.getModel() instanceof ConsoleBlackjackGame);
	}
	
	@Test
	public void testUserInputWithFile() {
		BlackjackController controller = new BlackjackController();
		controller.setInputType("f");
		assertTrue(controller.getModel() instanceof FileBlackjackGame);
	}
	
	@Test
	public void testUserInputWithBadInput() {
		BlackjackController controller = new BlackjackController();
		controller.setInputType("fdkfhsfdj");
		assertNull(controller.getModel());
	}

	@Test
	public void testUserInputHit() {
		BlackjackController controller = new BlackjackController();
		controller.setModel(new ConsoleBlackjackGame());
		assertEquals(0, controller.getModel().getUser().getHand().size());
		controller.hitOrStand("h");
		assertEquals(1, controller.getModel().getUser().getHand().size());
	}
	
	@Test
	public void testUserInputStand() {
		BlackjackController controller = new BlackjackController();
		controller.setModel(new ConsoleBlackjackGame());
		assertEquals(0, controller.getModel().getUser().getHand().size());
		controller.hitOrStand("s");
		assertEquals(0, controller.getModel().getUser().getHand().size());
		assertEquals(controller.getGameStatus(), Constants.DEALER_TURN);
	}
	
	@Test
	public void testBadUserInputHitOrStand() {
		BlackjackController controller = new BlackjackController();
		controller.setModel(new ConsoleBlackjackGame());
		assertEquals(0, controller.getModel().getUser().getHand().size());
		controller.hitOrStand("sdfsdfkjh");
		assertEquals(0, controller.getModel().getUser().getHand().size());
		assertEquals(controller.getGameStatus(), Constants.PLAYER_TURN);
	}
	

}
