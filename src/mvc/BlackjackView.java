package mvc;

import java.util.Scanner;

import cards.Card;
import player.Player;

public class BlackjackView {

	public BlackjackView() {}
	
	public void printErrorMessage() {
		System.out.println("Invalid input. Please try again.");
	}
	
	public String getInputType() {
		System.out.print("Enter 'c' for console input or 'f' for file input: ");
		String input = new Scanner(System.in).nextLine();
		return input;
	}
	
	public String getPlayerDecision() {
		System.out.print("Enter 'h' to hit or 's' to stand: ");
		String input = new Scanner(System.in).nextLine();
		return input;
	}
	
	public void printBlackjack(Player p) {
		System.out.println("BLACKJACK! " + p + " wins!");
	}
	
	public void printWinner(Player p) {
		System.out.println(p + " wins!");
	}
	
	public void printPlayerHand(Player p, boolean showScore) {
		System.out.print(p + "'s hand: ");
		for (Card c: p.getHand()) {
			if (c.isVisible()) {
				System.out.print(c + ", ");
			}
			else {
				System.out.print("CARD HIDDEN");
			}
		}
		if (showScore) {
			System.out.print("total score: " + p.getScore() + ".\n");
		}
		else {
			System.out.print(".\n");
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printHits(Player p) {
		System.out.println(p + " hits. ");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printStands(Player p) {
		System.out.println(p + " stands. ");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printStands(Player p, int i) {
		System.out.println(p + " stands with Hand #" + (i+1) + ". ");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printBusted(Player p) {
		System.out.println(p + " busts. ");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printBusted(Player p, int i) {
		System.out.print(p + " hand #" + (i+1) + " busts. ");
	}
	
	public void printHandNumber(int i) {
		System.out.print("Hand #" + (i+1) + ": ");
	}
	
	public String promptUserForFile() {
		String filename;
		
		System.out.println("Please enter the full path of the file: ");
		filename = new Scanner(System.in).nextLine();
		
		return filename;
	}

	public String getPlayerDecisionWithSplit() {
		System.out.print("You can split! Enter 'd' to split. If you don't want to, enter 'h' to hit or 's' to stand: ");
		String input = new Scanner(System.in).nextLine();
		return input;
	}

	public void printDealerCanSplit() {
		System.out.println("Dealer splits.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void printPlayerTurn() {
		System.out.println("User turn.");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printDealerTurn() {
		System.out.println("Dealer turn.");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
