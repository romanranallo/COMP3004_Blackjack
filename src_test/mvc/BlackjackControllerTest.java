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

}
