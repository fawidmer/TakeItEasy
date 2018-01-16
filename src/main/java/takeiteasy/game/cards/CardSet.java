package takeiteasy.game.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum CardSet {
	;

	public static List<PlayingCard> getNew() {

		List<PlayingCard> list = new ArrayList<PlayingCard>();

		for (int leftValue : PlayingCard.LEFT_VALUES) {
			for (int midValue : PlayingCard.MIDDLE_VALUES) {
				for (int rightValue : PlayingCard.RIGHT_VALUES) {
					list.add(new PlayingCard(leftValue, midValue, rightValue));
				}
			}
		}

		return Collections.unmodifiableList(list);

	}
}
