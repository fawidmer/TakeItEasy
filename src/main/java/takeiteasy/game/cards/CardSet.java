package takeiteasy.game.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum CardSet {
	;

	private static Random randomGenerator = new Random();

	public static List<PlayingCard> getNew() {

		List<PlayingCard> list = new ArrayList<PlayingCard>();

		for (int leftValue : PlayingCard.LEFT_VALUES) {
			for (int midValue : PlayingCard.MIDDLE_VALUES) {
				for (int rightValue : PlayingCard.RIGHT_VALUES) {
					list.add(new PlayingCard(leftValue, midValue, rightValue));
				}
			}
		}

		return list;

	}

	public static PlayingCard drawRandom(List<PlayingCard> unplacedCards) {
		return unplacedCards.get(randomGenerator.nextInt(unplacedCards.size()));
	}
}
