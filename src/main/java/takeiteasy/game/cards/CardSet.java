package takeiteasy.game.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum CardSet {
	;

	public static List<PlayingCard> getNew() {

		List<PlayingCard> list = new ArrayList<PlayingCard>();

		int[] leftValues = { 2, 6, 7 };
		int[] midValues = { 1, 5, 9 };
		int[] rightValues = { 3, 4, 8 };

		for (int leftValue : leftValues) {
			for (int midValue : midValues) {
				for (int rightValue : rightValues) {
					list.add(new PlayingCard(leftValue, midValue, rightValue));
				}
			}
		}

		return Collections.unmodifiableList(list);

	}
}
