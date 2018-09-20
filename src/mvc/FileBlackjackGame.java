package mvc;

import java.util.ArrayList;

import cards.Card;
import player.Player;

public class FileBlackjackGame extends BlackjackGame {
	
	private String[] userFile;
	private int index;
	private ArrayList<Card> cardsPlayed;
	
	public FileBlackjackGame(String[] fileIn) {
		super();
		this.userFile = fileIn;
		index = 0;
		this.cardsPlayed = new ArrayList<>();
	}
	
	public void initialDeal() {
		Card c;
		handleCard(userFile[index]);
		c = Card.builder(userFile[index++]);
		handleCardPlayed(c);
		cardsPlayed.add(c);
		user.addCard(c);
		
		handleCard(userFile[index]);
		c = Card.builder(userFile[index++]);
		handleCardPlayed(c);
		cardsPlayed.add(c);
		user.addCard(c);
		updateScore(user);
		
		handleCard(userFile[index]);
		c = Card.builder(userFile[index++]);
		handleCardPlayed(c);
		cardsPlayed.add(c);
		dealer.addCard(c);
		
		handleCard(userFile[index]);
		c = Card.builder(userFile[index++]);
		handleCardPlayed(c);
		cardsPlayed.add(c);
		c.setVisible(false);
		dealer.addCard(c);
		updateScore(dealer);
	}
	
	public void dealCard(Player p) {
		Card c;
		handleCard(userFile[index]);
		c = Card.builder(userFile[index++]);
		handleCardPlayed(c);
		cardsPlayed.add(c);
		p.addCard(c);
		updateScore(p);
	}
	
	public String getHitOrStandOrSplit() {
		if (index >= userFile.length) handle();
		handleMissingInput(userFile[index]);
		return userFile[index++];
	}
	
	private void handleCard(String s) {
		String suit = s.substring(0, 1);
		if (!(suit.equalsIgnoreCase("D") || suit.equalsIgnoreCase("S") || suit.equalsIgnoreCase("H") || suit.equalsIgnoreCase("C"))) {
			handle();
		}
		String value = s.substring(1);
		if (!(value.equalsIgnoreCase("k") || value.equalsIgnoreCase("a") || value.equalsIgnoreCase("q") || value.equalsIgnoreCase("j"))) {
			try {
				int num = Integer.parseInt(value);
				if (num < 2 || num > 10) {
					handle();
				}
			}
			catch (NumberFormatException e) { 
				handle();
			}
		}

	}
	
	private void handleMissingInput(String s) {
		if (!(s.equalsIgnoreCase("h") || s.equalsIgnoreCase("s") || s.equalsIgnoreCase("d"))) {
			handle();
		}
	}
	
	private void handleCardPlayed(Card c1) {
		for (Card c2: cardsPlayed) {
			if (c1.getRepresentation().equals(c2.getRepresentation())) handle();
		}
	}
	
	private void handle() {
		view.printFileIsBad();
		System.exit(0);
	}


}
