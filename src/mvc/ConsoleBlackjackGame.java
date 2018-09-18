package mvc;

import cards.Card;
import cards.Deck;
import player.Player;

public class ConsoleBlackjackGame extends BlackjackGame {
	
	private Deck deck;
	
	public ConsoleBlackjackGame() {
		super();
		this.deck = new Deck();
		deck.shuffle();
	}
	
	@Override
	public void initialDeal() {
		user.addCard(deck.getCard());
		user.addCard(deck.getCard());
		updateScore(user);
		
		dealer.addCard(deck.getCard());
		Card lastDealerCard = deck.getCard();
		lastDealerCard.setVisible(false);
		dealer.addCard(lastDealerCard);
		updateScore(dealer);
		
	}
	
	@Override
	public void dealCard(Player p) {
		p.addCard(deck.getCard());
		updateScore(p);
	}

}
