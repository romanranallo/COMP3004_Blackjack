package mvc;

import org.junit.Test;
import static org.junit.Assert.*;

import util.Constants;

public class BlackjackControllerTest {
	
	@Test
	public void testUserInputWithConsole() {
		BlackjackController controller = new BlackjackController(true);
		controller.setInputType("c");
		assertTrue(controller.getModel() instanceof ConsoleBlackjackGame);
	}
	
	@Test
	public void testUserInputWithFile() {
		BlackjackController controller = new BlackjackController(true);
		controller.setInputType("f");
		assertTrue(controller.getModel() instanceof FileBlackjackGame);
	}
	
	@Test
	public void testUserInputWithBadInput() {
		BlackjackController controller = new BlackjackController(true);
		controller.setInputType("fdkfhsfdj");
		assertNull(controller.getModel());
	}
	
	@Test
	public void testUserInputHit() {
		BlackjackController controller = new BlackjackController(true);
		controller.setModel(new ConsoleBlackjackGame());
		assertEquals(0, controller.getModel().getUser().getHand().size());
		controller.hitOrStand("h");
		assertEquals(1, controller.getModel().getUser().getHand().size());
	}
	
	@Test
	public void testUserInputStand() {
		BlackjackController controller = new BlackjackController(true);
		controller.setModel(new ConsoleBlackjackGame());
		assertEquals(0, controller.getModel().getUser().getHand().size());
		controller.hitOrStand("s");
		assertEquals(0, controller.getModel().getUser().getHand().size());
		assertEquals(controller.getGameStatus(), Constants.DEALER_TURN);
	}
	
	@Test
	public void testBadUserInputHitOrStand() {
		BlackjackController controller = new BlackjackController(true);
		controller.setModel(new ConsoleBlackjackGame());
		assertEquals(0, controller.getModel().getUser().getHand().size());
		controller.hitOrStand("sdfsdfkjh");
		assertEquals(0, controller.getModel().getUser().getHand().size());
	}
	
	
}
