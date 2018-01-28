package takeiteasy.game;

import java.util.List;

public enum RowCalculations {
	;

	/**
	 * @param row
	 * @return The score of the row. Returns zero, if the row is destroyed or
	 *         not completed.
	 */
	public static int getScore(List<Integer> row) {
		/* Check if any card is missing on the row */
		if (row.stream().anyMatch(value -> value == null))
			return 0;

		/* Check if not destroyed */
		if (row.stream().allMatch(card -> card == row.get(0)))
			return row.get(0) * row.size();
		else
			return 0;
	}

	/**
	 * @param row
	 * @return The assigned number (value) of the row. Returns zero, if the row
	 *         is empty. Return -1, if the row is destroyed.
	 */
	public static int getValue(List<Integer> row) {

		int value = 0;

		for (Integer currentValue : row) {
			if (currentValue == null)
				continue;

			if (value == 0)
				value = currentValue;
			else if (value != currentValue)
				return -1;

		}

		return value;
	}

	public static boolean isFree(List<Integer> row) {
		if (getValue(row) == 0)
			return true;
		else
			return false;

	}

	public static boolean isDestroyed(List<Integer> row) {
		if (getValue(row) == -1)
			return true;
		else
			return false;
	}

}
