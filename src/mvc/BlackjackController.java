package mvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
		case (Constants.PLAYER_DECIDE_SPLIT):
			view.printPlayerTurn();
			playerTurn();
			break;
		case (Constants.DEALER_TURN):
			view.printDealerTurn();
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
	
	public String[] readFile(String filename) throws FileNotFoundException {
	    File file = new File(filename); 
	    Scanner sc = new Scanner(file); 
	    String fileInput = sc.nextLine();
	    return fileInput.split(" ");
	}
	
	public void setInputType(String input) {
		if (input.equalsIgnoreCase("c")) {
			model = new ConsoleBlackjackGame();
			gameStatus = Constants.INITIAL_DEAL;
			this.consoleInput = true;
		}
		else if (input.equalsIgnoreCase("f")) {
			String [] fileIn;
			gameStatus = Constants.INITIAL_DEAL;
			this.consoleInput = false;
			try {
				fileIn = readFile(view.promptUserForFile());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;	// sadness
			}
			model = new FileBlackjackGame(fileIn);
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
		}
		else {
			if (model.canSplit(model.getUser())) {
				gameStatus = Constants.PLAYER_DECIDE_SPLIT;
			}
			else {
				gameStatus = Constants.PLAYER_TURN;
			}
		}
		controller();
	}
	
	public void playerTurn() {
		while (gameStatus == Constants.PLAYER_DECIDE_SPLIT) {
			if (consoleInput) {
				hitOrStandOrSplit(view.getPlayerDecisionWithSplit());
			}
			else {
				hitOrStandOrSplit(((FileBlackjackGame)model).getHitOrStandOrSplit());
			}
			
		}
		if (gameStatus == Constants.PLAYER_SPLIT) {
			split();
		}
		
		while (gameStatus == Constants.PLAYER_TURN) {
			if (consoleInput)
				hitOrStand(view.getPlayerDecision());
			else {
				hitOrStand(((FileBlackjackGame)model).getHitOrStandOrSplit());
			}
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
			view.printHits(model.getUser());
			model.dealCard(model.getUser());
			view.printPlayerHand(model.getUser(), true);
			if (model.isBusted(model.getUser())) {
				model.setWinner(model.getDealer());
				view.printBusted(model.getUser());
				gameStatus = Constants.GAME_OVER;
			}
		}
		else if (input.equalsIgnoreCase("s")) {
			view.printStands(model.getUser());
			gameStatus = Constants.DEALER_TURN;
		}
		else {
			view.printErrorMessage();
		}
	}
	
	public void hitOrStandOrSplit(String input) {
		if (input.equalsIgnoreCase("h") || input.equalsIgnoreCase("s")) {
			gameStatus = Constants.PLAYER_TURN;
			hitOrStand(input);
		}
		else if (input.equalsIgnoreCase("d")) {
			gameStatus = Constants.PLAYER_SPLIT;
		}
		else {
			view.printErrorMessage();
		}
	}
	
	
	public void split() {
		int handIndex = 0;
		
		model.split(model.getUser());
		model.dealCard(model.getUser());
		view.printHandNumber(handIndex);
		view.printPlayerHand(model.getUser(), true);
		
		// First turn
		boolean currentTurn = true;
		boolean bustedFirstHand = false;

		
		while (currentTurn) {
			String input = "";
			if (consoleInput)
				input = view.getPlayerDecision();
			else
				input = ((FileBlackjackGame)model).getHitOrStandOrSplit();
			if (input.equalsIgnoreCase("h")) {
				view.printHandNumber(handIndex);
				view.printHits(model.getUser());
				model.dealCard(model.getUser());
				view.printHandNumber(handIndex);
				view.printPlayerHand(model.getUser(), true);
				if (model.isBusted(model.getUser())) {
					view.printHandNumber(handIndex);
					view.printBusted(model.getUser());
					bustedFirstHand = true;
					currentTurn = false;
					handIndex++;
				}
			}
			else if (input.equalsIgnoreCase("s")) {
				view.printHandNumber(handIndex);
				view.printStands(model.getUser());
				currentTurn = false;
				handIndex++;
			}
			else {
				view.printErrorMessage();
			}
		}
		
		// Second hand
		currentTurn = true;
		model.switchHand(model.getUser());
		model.dealCard(model.getUser());
		view.printHandNumber(handIndex);
		view.printPlayerHand(model.getUser(), true);
		
		while (currentTurn) {
			String input = "";
			if (consoleInput)
				input = view.getPlayerDecision();
			else
				input = ((FileBlackjackGame)model).getHitOrStandOrSplit();
			if (input.equalsIgnoreCase("h")) {
				view.printHandNumber(handIndex);
				view.printHits(model.getUser());
				view.printHandNumber(handIndex);
				model.dealCard(model.getUser());
				view.printPlayerHand(model.getUser(), true);
				if (model.isBusted(model.getUser())) {
					view.printHandNumber(handIndex);
					view.printBusted(model.getUser());
					if (bustedFirstHand) {
						model.setWinner(model.getDealer());
						currentTurn = false;
						gameStatus = Constants.GAME_OVER;
					}


				}
			}
			else if (input.equalsIgnoreCase("s")) {
				view.printHandNumber(handIndex);
				view.printStands(model.getUser());
				currentTurn = false;
				gameStatus = Constants.DEALER_TURN;
			}
			else {
				view.printErrorMessage();
			}
		}
	}
	
	public void hitOrStandWithSplit(String input) {
		if (input.equalsIgnoreCase("h")) {
			view.printHits(model.getUser());
			model.dealCard(model.getUser());
			view.printPlayerHand(model.getUser(), true);
			if (model.isBusted(model.getUser())) {
				view.printBusted(model.getUser());
				gameStatus = Constants.GAME_OVER;
			}
		}
		else if (input.equalsIgnoreCase("s")) {
			view.printStands(model.getUser());
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
