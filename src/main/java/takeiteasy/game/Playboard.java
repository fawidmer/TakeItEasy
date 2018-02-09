package takeiteasy.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import takeiteasy.game.cards.CardSet;
import takeiteasy.game.cards.PlayingCard;;

/**
 * Class representing a Take it easy playboard.
 * 
 * @author Fabio Widmer
 */
public class Playboard {

	private static final String LONG_SPACER = "    ";
	private static final String SPACER = " ";
	private static final String NEW_LINE = "\n";

	private final List<PlayingCard> placedCards;
	private final Map<PlayingCard, Boolean> isPlacedMap;

	/**
	 * Constructor constructing an empty play board.
	 */
	public Playboard() {
		placedCards = new ArrayList<PlayingCard>();
		isPlacedMap = new HashMap<>();

		for (int i = 0; i < 19; i++)
			placedCards.add(null);

		List<PlayingCard> allCards = CardSet.getNew();
		for (PlayingCard playingCard : allCards) {
			isPlacedMap.put(playingCard, false);
		}
	}

	private Playboard(Map<PlayingCard, Boolean> isPlacedMap, List<PlayingCard> placedCards) {
		this.isPlacedMap = isPlacedMap;
		this.placedCards = placedCards;
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

	/**
	 * @return A list of all cards which have not been placed yet.
	 */
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

	/**
	 * @param idx
	 *            The row number
	 * @return A {@link List} of {@link Integer} containing the values of the
	 *         cards in the specified row.
	 */
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
	 * 
	 * @param i
	 *            The row number
	 * @return A {@link List} of {@link Integer} containing the values of the
	 *         cards in the specified row.
	 */
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

	/**
	 * 
	 * @param i
	 *            The row number
	 * @return A {@link List} of {@link Integer} containing the values of the
	 *         cards in the specified row.
	 */
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

	/**
	 * @param columnNr
	 * @param rowNr
	 * @return <code>null</code>, if no card is placed at the given coordinates.
	 */
	public PlayingCard get(int columnNr, int rowNr) {
		return placedCards.get(getLinearIndex(columnNr, rowNr));
	}

	/**
	 * Sets a playing card at the specified position on the board.
	 * 
	 * @param playingCard
	 *            The card to set.
	 * @param columnNr
	 *            The column number (x-coordinate)
	 * @param rowNr
	 *            The row number (y-coordinate)
	 */
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

	private Pair<Integer, Integer> getSubscript(int linearIdx) {
		if (linearIdx < 3)
			return Pair.of(0, linearIdx);
		else if (linearIdx < 7)
			return Pair.of(1, linearIdx - 3);
		else if (linearIdx < 12)
			return Pair.of(2, linearIdx - 7);
		else if (linearIdx < 16)
			return Pair.of(3, linearIdx - 12);
		else if (linearIdx < 19)
			return Pair.of(4, linearIdx - 16);
		else
			throw new InternalError(linearIdx + "is not an allowed linear index.");
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

	/**
	 * @return The current turn number (starting at 0, if no cards are placed,
	 *         ending at 19, when the game is finished).
	 */
	public int getTurnNumber() {
		return (int) placedCards.stream().filter(card -> card != null).count();
	}

	/**
	 * @return A deep copy of the {@link Playboard}.
	 */
	public Playboard copy() {
		return new Playboard(new HashMap<>(isPlacedMap), new ArrayList<>(placedCards));
	}

	/**
	 * @return A list of all coordinates which are empty.
	 */
	public List<Pair<Integer, Integer>> getAllFreeCoordinates() {

		List<Integer> freeCoordinates = new ArrayList<>();

		for (int linearIdx = 0; linearIdx < placedCards.size(); linearIdx++) {
			if (placedCards.get(linearIdx) == null)
				freeCoordinates.add(linearIdx);
		}

		return freeCoordinates.stream().map(linearIdx -> getSubscript(linearIdx)).collect(Collectors.toList());
	}

	/**
	 * @return <code>true</code>, if the board is full.
	 */
	public boolean isFull() {
		return getTurnNumber() == 19;
	}

}
