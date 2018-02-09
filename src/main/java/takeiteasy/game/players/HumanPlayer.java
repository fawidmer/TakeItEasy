package takeiteasy.game.players;

import java.util.Scanner;

import org.apache.commons.lang3.tuple.Pair;

import takeiteasy.game.Playboard;
import takeiteasy.game.cards.PlayingCard;

/**
 * A human player (uses the console for user input).
 * 
 * @author Fabio Widmer
 */
public class HumanPlayer implements Player {

	private static Scanner reader = new Scanner(System.in);
	private final String name;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            The name of the human player.
	 */
	public HumanPlayer(String name) {
		this.name = name;
	}

	@Override
	protected void finalize() throws Throwable {
		reader.close();
		super.finalize();
	}

	@Override
	public Pair<Integer, Integer> decideMove(PlayingCard currentCard, Playboard board) {
		System.out.println("\n\n" + board);
		System.out.println("Please decide where to put: " + currentCard);

		return Pair.of(reader.nextInt(), reader.nextInt());
	}

	@Override
	public String getName() {
		return name;
	}

}
