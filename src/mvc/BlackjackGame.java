package mvc;

import java.util.ArrayList;

import cards.Ace;
import cards.Card;
import cards.Hand;
import player.*;

public abstract class BlackjackGame {
	
	protected Player user;
	protected Player dealer;
	protected Player winner = null;
	protected Player turn = null;
	protected BlackjackView view;
	
	public Player getUser() { return user; }
	public Player getDealer() { return dealer; }
	public Player getWinner() { return this.winner; }
	public Player getTurn() { return this.turn; }
	public BlackjackView getView() { return this.view; }
	public void setWinner(Player p) { this.winner = p; }
	
	public BlackjackGame() {
		this.user = new User();
		this.dealer = new Dealer();
		this.view = new BlackjackView();
		this.turn = user;
	}
	
	public abstract void initialDeal();
	public abstract void dealCard(Player p);
	
	public void dealerTurn() {
		boolean split = false;
		int handIndex = 0;
		
		for (Card c: dealer.getHand()) {
			c.setVisible(true);
		}
		if (canSplit(dealer)) {
			view.printDealerCanSplit();
			split(dealer);
			split = true;
		}
		for (Hand h: dealer.getHands()) {
			handIndex = dealer.getHands().indexOf(h);
			dealer.setHand(h);
			if (split) {
				dealCard(dealer);
				view.printHandNumber(handIndex);
			}
			view.printPlayerHand(dealer, true);
			
			while (dealerShouldHit()) {
				if (split) view.printHandNumber(handIndex);
				view.printHits(dealer);
				dealCard(dealer);
				updateScore(dealer);
				if (split) view.printHandNumber(handIndex);
				view.printPlayerHand(dealer, true);
				if (isBusted(dealer)) {
					if (split) view.printHandNumber(handIndex);
					view.printBusted(dealer);
					dealer.setBusted(true);
					if (!split) {
						setWinner(user);
						return;
					}

				}
			}
			if (split && !dealer.isBusted()) view.printHandNumber(handIndex);
			if (!dealer.isBusted()) view.printStands(dealer);
		}
		boolean bothBusted = true;
		for (Hand h: dealer.getHands()) {
			if (!h.isBusted()) bothBusted = false;
		}
		
		if (bothBusted) {
			setWinner(user); 
			return;
		}
		endDealerTurn();
	}
	
	
	public void endPlayerTurn() {
		this.turn = dealer;
	}
	
	public void endDealerTurn() {		
		for (Card c: dealer.getHand()) c.setVisible(true);
		determineWinner();
	}
	
	public boolean isBlackjack() {
		if (dealer.getScore() == 21) {
			for (Card c: dealer.getHand()) {
				c.setVisible(true);
			}
			view.printPlayerHand(dealer, true);
			setWinner(dealer);
			return true;
		}
		else if (user.getScore() == 21) {
			setWinner(user);
			return true;
		}
		return false;
	}
	
	public void updateScore(Player p) {
		ArrayList<Ace> aces = new ArrayList<Ace>();
		int score = 0;
		for (Card c: p.getHand()) {
			if (!(c instanceof Ace)) {
				score += c.getValue();
			}
			else {
				aces.add((Ace)c);
			}
		}
		
		if (!aces.isEmpty()) {
			for (Ace a: aces) {
				if (score <= 10 -(aces.size() - 1)) {
					a.setValue(11);
				}
				else {
					a.setValue(1);
				}
				score += a.getValue();
			}
		}
		
		p.setScore(score);
	}
	
	public boolean isBusted(Player p) {
		if (p.getScore() > 21) {
			p.setBusted(true);
			return true;
		}
		return false;
	}
	
	public boolean dealerShouldHit() {
		if (dealer.getScore() <= 16 || dealerHasSoft17()) return true;
		return false;
	}
	
	public boolean dealerHasSoft17() {
		if (dealer.getScore() != 17) return false;
		for (Card c: dealer.getHand()) {
			if (c instanceof Ace && c.getValue() == 11) return true;
		}
		return false;
		
		
	}
	
	private void determineWinner() {
		if (dealer.getBestScore() < user.getBestScore()) setWinner(user);
		else setWinner(dealer);
	}
	
	public boolean canSplit(Player p) {
		if (p.getHand().size() != 2) return false;
		
		if (p.getHand().get(0).isEquivalent(p.getHand().get(1))) {
			if (p instanceof Dealer) return (p.getScore() < 17);
			return true;
		}
		return false;
	}
	
	public void split(Player p) {
		p.addHand(p.getHand().remove(1));
		updateScore(p);
		switchHand(p);
		updateScore(p);
		switchHand(p);
	}
	
	public void switchHand(Player p) {
		if (p.getHands().size() != 2) return;
		
		if ((p.getHand(0)).equals(p.getCurrentHand())) {
			p.setHand(p.getHand(1));;
		}
		else p.setHand(p.getHand(0));
	}
	
	
}
