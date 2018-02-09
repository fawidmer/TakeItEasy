package takeiteasy.game.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Helper methods concerning {@link PlayingCard} sets.
 * 
 * @author Fabio Widmer
 *
 */
public enum CardSet {
	;

	private static Random randomGenerator = new Random();

	/**
	 * @return A new full "set" of {@link PlayingCard} containing every card
	 *         exactly once.
	 */
	public static List<PlayingCard> getNew() {

		List<PlayingCard> list = new ArrayList<PlayingCard>();

		for (int leftValue : PlayingCard.LEFT_VALUES)
			for (int midValue : PlayingCard.MIDDLE_VALUES)
				for (int rightValue : PlayingCard.RIGHT_VALUES)
					list.add(new PlayingCard(leftValue, midValue, rightValue));

		return list;

	}

	/**
	 * @param cards
	 *            The list to draw from.
	 * @return One random {@link PlayingCard} drawn from a given List of Cards.
	 */
	public static PlayingCard drawRandom(List<PlayingCard> cards) {
		return cards.get(randomGenerator.nextInt(cards.size()));
	}
}
