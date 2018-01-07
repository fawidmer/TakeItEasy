package takeiteasy.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import takeiteasy.game.cards.CardSet;
import takeiteasy.game.cards.PlayingCard;;

public class Playboard {

	private final String longSpacer = "    ";
	private final String spacer = " ";
	private final String newLine = "\n";

	private final List<PlayingCard> placedCards = new ArrayList<PlayingCard>();
	private final Map<PlayingCard, Boolean> isPlacedMap;

	public Playboard() {
		for (int i = 0; i < 19; i++)
			placedCards.add(null);

		isPlacedMap = new HashMap<>();
		List<PlayingCard> allCards = CardSet.getNew();
		for (PlayingCard playingCard : allCards) {
			isPlacedMap.put(playingCard, false);
		}
	}

	@Override
	public String toString() {

		String string = "";

		string = string + toStringHelperSingleCard(0);
		string = string + toStringHelperTwoCards(0);
		string = string + toStringHelperThreeCards(0);
		string = string + toStringHelperTwoCards(1);
		string = string + toStringHelperThreeCards(1);
		string = string + toStringHelperTwoCards(2);
		string = string + toStringHelperThreeCards(2);
		string = string + toStringHelperTwoCards(3);
		string = string + toStringHelperSingleCard(1);

		string = string + newLine + newLine;
		string = string + getAvailableCards();

		return string;
	}

	private List<PlayingCard> getAvailableCards() {

		List<PlayingCard> list = new ArrayList<>();

		for (Entry<PlayingCard, Boolean> isPlacedSet : isPlacedMap.entrySet())
			if (!isPlacedSet.getValue().booleanValue())
				list.add(isPlacedSet.getKey());

		return list;
	}

	private String toStringHelperThreeCards(int idx) {
		return toStringHelperDisplayCard(getVerticalRow(0).get(idx)) + spacer
				+ toStringHelperDisplayCard(getVerticalRow(2).get(idx + 1)) + spacer
				+ toStringHelperDisplayCard(getVerticalRow(4).get(idx)) + newLine;
	}

	private String toStringHelperTwoCards(int idx) {
		return longSpacer + toStringHelperDisplayCard(getVerticalRow(1).get(idx)) + spacer
				+ toStringHelperDisplayCard(getVerticalRow(3).get(idx)) + newLine;
	}

	private String toStringHelperSingleCard(int idx) {
		return longSpacer + longSpacer + toStringHelperDisplayCard(getVerticalRow(2).get(idx * 4)) + newLine;
	}

	private static String toStringHelperDisplayCard(PlayingCard card) {
		if (card == null)
			return "(  -  )";
		else
			return card.toString();
	}

	private List<PlayingCard> getVerticalRow(int idx) {
		List<PlayingCard> list = new ArrayList<>();
		int numberOfCards;

		if (idx == 0 || idx == 4)
			numberOfCards = 3;
		else if (idx == 1 || idx == 3)
			numberOfCards = 4;
		else if (idx == 2)
			numberOfCards = 5;
		else
			throw new IndexOutOfBoundsException("Cannot get vertical row number" + idx);

		for (int i = 0; i < numberOfCards; i++)
			list.add(get(idx, i));

		return list;
	}

	public PlayingCard get(int columnNr, int rowNr) {
		return placedCards.get(getLinearIndex(columnNr, rowNr));
	}

	public void setAt(PlayingCard playingCard, int columnNr, int rowNr) {
		int linearIdx = getLinearIndex(columnNr, rowNr);
		placedCards.set(linearIdx, playingCard);
		isPlacedMap.put(playingCard, true);
	}

	private int getLinearIndex(int columnNr, int rowNr) {
		final int[] offsets = { 0, 3, 7, 12, 16 };
		return offsets[columnNr] + rowNr;
	}
}
