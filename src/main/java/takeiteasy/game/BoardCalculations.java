package takeiteasy.game;

/**
 * Enum containing methods for calculations concerning {@link Playboard}.
 * 
 * @author Fabio Widmer
 */
public enum BoardCalculations {
	;
	/**
	 * @param board
	 *            The {@link Playboard} to operate on.
	 * @return The number of free rows (in all directions).
	 */
	public static int getNumberOfFreeRows(Playboard board) {
		int numberOfFreeRows = 0;

		for (int i = 0; i < 5; i++) {
			if (RowCalculations.isFree(board.getVerticalRow(i)))
				numberOfFreeRows++;
			if (RowCalculations.isFree(board.getAscendingRow(i)))
				numberOfFreeRows++;
			if (RowCalculations.isFree(board.getDescendingRow(i)))
				numberOfFreeRows++;
		}

		return numberOfFreeRows;

	}

	/**
	 * @param board
	 *            The {@link Playboard} to operate on.
	 * @return The score of the board. If the game is not finished yet, returns
	 *         the current score (i.e. does not count unfinished rows).
	 */
	public static int getScore(Playboard board) {

		int totalScore = 0;

		for (int i = 0; i < 5; i++) {
			totalScore += RowCalculations.getScore(board.getVerticalRow(i));
			totalScore += RowCalculations.getScore(board.getAscendingRow(i));
			totalScore += RowCalculations.getScore(board.getDescendingRow(i));
		}

		return totalScore;
	}

	/**
	 * @param board
	 *            The {@link Playboard} to operate on.
	 * @return The number of destroyed rows (in all directions).
	 */
	public static int getNumberOfDestroyedRows(Playboard board) {
		int numberOfDestroyedRows = 0;

		for (int i = 0; i < 5; i++) {
			if (RowCalculations.isDestroyed(board.getVerticalRow(i)))
				numberOfDestroyedRows++;
			if (RowCalculations.isDestroyed(board.getAscendingRow(i)))
				numberOfDestroyedRows++;
			if (RowCalculations.isDestroyed(board.getDescendingRow(i)))
				numberOfDestroyedRows++;
		}

		return numberOfDestroyedRows;
	}

}
