package takeiteasy.game;

import java.util.Scanner;

import takeiteasy.game.cards.PlayingCard;

public class HumanPlayer implements Player {

	Playboard board = new Playboard();
	private static Scanner reader = new Scanner(System.in);

	@Override
	public void decideAndPerform(PlayingCard currentCard) {
		System.out.println("\n\n" + board);
		System.out.println("Please decide where to put: " + currentCard);
		board.set(currentCard, reader.nextInt(), reader.nextInt());
	}

	@Override
	protected void finalize() throws Throwable {
		reader.close();
		super.finalize();
	}

	@Override
	public void showBoard() {
		System.out.println(board);
	}
}
