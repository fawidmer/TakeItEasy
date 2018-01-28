package takeiteasy.game;

public enum BoardCalculations {
	;

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
