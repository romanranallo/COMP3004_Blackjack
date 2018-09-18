package mvc;

import java.util.ArrayList;

import cards.Ace;
import cards.Card;
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
		for (Card c: dealer.getHand()) {
			c.setVisible(true);
		}
		view.printPlayerHand(dealer, true);
		
		while (dealerShouldHit()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dealCard(dealer);
			updateScore(dealer);
			view.printPlayerHand(dealer, true);
			if (isBusted(dealer)) {
				view.printBusted(dealer);
				setWinner(user);
				return;
			}
		}
		endDealerTurn();
	}
	
	
	public void endPlayerTurn() {
		this.turn = dealer;
	}
	public void endDealerTurn() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			if (p.equals(dealer)) { 
				setWinner(user);
			}
			else {
				setWinner(dealer);
			}
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
		ArrayList<Ace> aces = new ArrayList<Ace>();
		for (Card c: dealer.getHand()) {
			if (c instanceof Ace && c.getValue() == 11) return true;
		}
		return false;
		
		
	}
	
	private void determineWinner() {
		if (dealer.getScore() < user.getScore()) setWinner(user);
		else setWinner(dealer);
	}
	
	public boolean canSplit(Player p) {
		if (p.getHand().size() != 2) return false;
		
		if (p.getHand().get(0).isEquivalent(p.getHand().get(1))) {
			return true;
		}
		return false;
	}
}
