package takeiteasy.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import takeiteasy.game.cards.CardSet;
import takeiteasy.game.cards.PlayingCard;;

public class Playboard {

	private static final String LONG_SPACER = "    ";
	private static final String SPACER = " ";
	private static final String NEW_LINE = "\n";

	private final List<PlayingCard> placedCards = new ArrayList<PlayingCard>();
	private final Map<PlayingCard, Boolean> isPlacedMap = new HashMap<>();

	public Playboard() {
		for (int i = 0; i < 19; i++)
			placedCards.add(null);

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

		string = string + NEW_LINE + NEW_LINE;
		string = string + getAvailableCards();

		return string;
	}

	public List<PlayingCard> getAvailableCards() {

		List<PlayingCard> list = new ArrayList<>();

		for (Entry<PlayingCard, Boolean> isPlacedSet : isPlacedMap.entrySet())
			if (!isPlacedSet.getValue().booleanValue())
				list.add(isPlacedSet.getKey());

		return list;
	}

	private String toStringHelperThreeCards(int idx) {

		return toStringHelperDisplayCard(placedCards.get(getLinearIndex(0, idx))) + SPACER
				+ toStringHelperDisplayCard(placedCards.get(getLinearIndex(2, idx + 1))) + SPACER
				+ toStringHelperDisplayCard(placedCards.get(getLinearIndex(4, idx))) + NEW_LINE;
	}

	private String toStringHelperTwoCards(int idx) {
		return LONG_SPACER + toStringHelperDisplayCard(placedCards.get(getLinearIndex(1, idx))) + SPACER
				+ toStringHelperDisplayCard(placedCards.get(getLinearIndex(3, idx))) + NEW_LINE;
	}

	private String toStringHelperSingleCard(int idx) {
		return LONG_SPACER + LONG_SPACER + toStringHelperDisplayCard(placedCards.get(getLinearIndex(2, idx * 4)))
				+ NEW_LINE;
	}

	private static String toStringHelperDisplayCard(PlayingCard card) {
		if (card == null)
			return "(  -  )";
		else
			return card.toString();
	}

	public List<Integer> getVerticalRow(int idx) {
		List<Integer> list = new ArrayList<>();
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
			if (get(idx, i) == null)
				list.add(null);
			else
				list.add(get(idx, i).middle);

		return list;
	}

	/**
	 * @param columnNr
	 * @param rowNr
	 * @return <code>null</code>, if no card is placed at the given coordinates.
	 */
	public PlayingCard get(int columnNr, int rowNr) {
		return placedCards.get(getLinearIndex(columnNr, rowNr));
	}

	public void set(PlayingCard playingCard, int columnNr, int rowNr) {

		if (isPlacedMap.get(playingCard))
			throw new IllegalArgumentException("Card " + playingCard + " was already placed.");

		int linearIdx = getLinearIndex(columnNr, rowNr);

		if (placedCards.get(linearIdx) != null)
			throw new IllegalArgumentException("There was already a card at the given position.");

		placedCards.set(linearIdx, playingCard);
		isPlacedMap.put(playingCard, true);
	}

	private int getLinearIndex(int columnNr, int rowNr) {
		final int[] offsets = { 0, 3, 7, 12, 16 };
		return offsets[columnNr] + rowNr;
	}

	public int getValueVerticalRow(int i) {
		return getValue(getVerticalRow(i));
	}

	public int getValueAscendingRow(int i) {
		return getValue(getAscendingRow(i));
	}

	public List<Integer> getAscendingRow(int i) {
		switch (i) {
		case 0:
			return getListOfCardsFromListOfInt(new int[] { 0, 3, 7 }, "left");

		case 1:
			return getListOfCardsFromListOfInt(new int[] { 1, 4, 8, 12 }, "left");

		case 2:
			return getListOfCardsFromListOfInt(new int[] { 2, 5, 9, 13, 16 }, "left");

		case 3:
			return getListOfCardsFromListOfInt(new int[] { 6, 10, 14, 17 }, "left");

		case 4:
			return getListOfCardsFromListOfInt(new int[] { 11, 15, 18 }, "left");

		default:
			throw new IllegalArgumentException("Row number must be between 0 and 4.");
		}

	}

	private List<Integer> getListOfCardsFromListOfInt(int[] indices, String fieldName) {
		List<Integer> returnList = new ArrayList<>();

		for (int idx : indices)
			try {
				returnList.add(PlayingCard.class.getField(fieldName).getInt(placedCards.get(idx)));
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				throw new IllegalArgumentException(e);
			} catch (NullPointerException e) {
				returnList.add(null);
			}

		return returnList;
	}

	public int getValueDescendingRow(int i) {
		return getValue(getDescendingRow(i));
	}

	private int getValue(List<Integer> row) {
		/* Check if any card is missing on the row */
		if (row.stream().anyMatch(value -> value == null))
			return 0;

		/* Check if not destroyed */
		if (row.stream().allMatch(card -> card == row.get(0)))
			return row.get(0) * row.size();
		else
			return 0;
	}

	public List<Integer> getDescendingRow(int i) {
		switch (i) {
		case 0:
			return getListOfCardsFromListOfInt(new int[] { 7, 12, 16 }, "right");

		case 1:
			return getListOfCardsFromListOfInt(new int[] { 3, 8, 13, 17 }, "right");

		case 2:
			return getListOfCardsFromListOfInt(new int[] { 0, 4, 9, 14, 18 }, "right");

		case 3:
			return getListOfCardsFromListOfInt(new int[] { 1, 5, 10, 15 }, "right");

		case 4:
			return getListOfCardsFromListOfInt(new int[] { 2, 6, 11 }, "right");

		default:
			throw new IllegalArgumentException("Row number must be between 0 and 4.");
		}
	}

	public int getScore() {
		int totalScore = 0;

		for (int i = 0; i < 5; i++) {
			totalScore += getValueVerticalRow(i);
			totalScore += getValueAscendingRow(i);
			totalScore += getValueDescendingRow(i);
		}

		return totalScore;
	}
}
