package mvc;

import cards.Card;
import player.Player;

public class FileBlackjackGame extends BlackjackGame {
	
	private String[] userFile;
	private int index;
	
	public FileBlackjackGame(String[] fileIn) {
		super();
		this.userFile = fileIn;
		index = 0;
	}
	
	public void initialDeal() {
		user.addCard(Card.builder(userFile[index++]));
		user.addCard(Card.builder(userFile[index++]));
		updateScore(user);
		
		dealer.addCard(Card.builder(userFile[index++]));
		Card lastDealerCard = Card.builder(userFile[index++]);
		lastDealerCard.setVisible(false);
		dealer.addCard(lastDealerCard);
		updateScore(dealer);
	}
	
	public void dealCard(Player p) {
		p.addCard(Card.builder(userFile[index++]));
		updateScore(p);
	}
	
	public String getHitOrStandOrSplit() {	
		return userFile[index++];
	}
	
	

}
