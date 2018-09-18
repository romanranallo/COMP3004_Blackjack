package mvc;

import util.Constants;

public class BlackjackController {
	
	private BlackjackGame model;
	private BlackjackView view;
	private int gameStatus;
	private boolean consoleInput;
	
	public BlackjackController(boolean isTest) {
		view = new BlackjackView();
		gameStatus = Constants.WAITING_FOR_INPUT;
		if (!isTest) {
			controller();
		}
	}
	
	
	public BlackjackGame getModel() { return this.model; }
	// Set model should only be used by JUnits
	public void setModel(BlackjackGame model) { this.model = model; }
	public int getGameStatus() { return this.gameStatus; }
	public boolean isConsoleInput() { return this.consoleInput; }
	public boolean isFileInput() { return !(this.consoleInput); }
	
	private void controller() {
		switch(gameStatus) {
		case (Constants.WAITING_FOR_INPUT):
			launch();
			break;
		case (Constants.INITIAL_DEAL):
			startGame();
			break;
		case (Constants.PLAYER_TURN):
			playerTurn();
			break;
		case (Constants.DEALER_TURN):
			dealerTurn();
			break;
		case (Constants.GAME_OVER):
			gameOver();
			break;
		}
	}
	
	private void launch() {
		while (this.model == null) {
			setInputType(view.getInputType());
		}
		controller();
	}
	
	private void getFile() {
		
	}
	
	public void readFile(String filename) {
		model.getFile(filename);
	}
	
	public void setInputType(String input) {
		if (input.equalsIgnoreCase("c")) {
			model = new ConsoleBlackjackGame();
			gameStatus = Constants.INITIAL_DEAL;
			this.consoleInput = true;
		}
		else if (input.equalsIgnoreCase("f")) {
			model = new FileBlackjackGame();
			gameStatus = Constants.INITIAL_DEAL;
			this.consoleInput = false;
		}
		else {
			view.printErrorMessage();
		}
	}
	
	private void startGame() {
		model.initialDeal();
		view.printPlayerHand(model.getUser(), true);
		view.printPlayerHand(model.getDealer(), false);
		if (model.isBlackjack()) {
			view.printBlackjack(model.getWinner());
			gameStatus = Constants.GAME_OVER;
			controller();
		}
		gameStatus = Constants.PLAYER_TURN;
		controller();
	}
	
	public void playerTurn() {
		while (gameStatus == Constants.PLAYER_TURN) {
			hitOrStand(view.getPlayerDecision());
		}
		model.endPlayerTurn();
		controller();
		
	}
	
	public void dealerTurn() {
		model.dealerTurn();
		view.printWinner(model.getWinner());
	}
	
	public void hitOrStand(String input) {
		if (input.equalsIgnoreCase("h")) {
			model.dealCard(model.getUser());
			view.printPlayerHand(model.getUser(), true);
			if (model.isBusted(model.getUser())) {
				view.printBusted(model.getUser());
				gameStatus = Constants.GAME_OVER;
			}
		}
		else if (input.equalsIgnoreCase("s")) {
			gameStatus = Constants.DEALER_TURN;
		}
		else {
			view.printErrorMessage();
		}
		
	}
	
	private void gameOver() {
		view.printWinner(model.getWinner());
	}
	
	// Entry point into application
	public static void main(String[] args) {
		new BlackjackController(false);
	}

}
