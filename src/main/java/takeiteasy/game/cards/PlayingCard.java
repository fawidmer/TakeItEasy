package takeiteasy.game.cards;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Class representing a playing card of a Take it easy game.
 * 
 * @author Fabio Widmer
 *
 */
public class PlayingCard {

	/** Left value */
	public final int left;
	/** Middle value */
	public final int middle;
	/** Right value */
	public final int right;

	static final List<Integer> LEFT_VALUES = Arrays.asList(2, 6, 7);
	static final List<Integer> MIDDLE_VALUES = Arrays.asList(1, 5, 9);
	static final List<Integer> RIGHT_VALUES = Arrays.asList(3, 4, 8);

	/**
	 * Constructor.
	 * 
	 * @param left
	 *            Left value
	 * @param middle
	 *            Middle value
	 * @param right
	 *            Right value
	 */
	public PlayingCard(int left, int middle, int right) {

		if (!(LEFT_VALUES.contains(left) && MIDDLE_VALUES.contains(middle) && RIGHT_VALUES.contains(right)))
			throw new IllegalArgumentException();

		this.left = left;
		this.middle = middle;
		this.right = right;
	}

	@Override
	public String toString() {
		return "(" + left + "," + middle + "," + right + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PlayingCard) {
			PlayingCard new_name = (PlayingCard) obj;
			return new_name.left == left && new_name.middle == middle && new_name.right == right;

		} else
			return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
				append(left).append(middle).append(right).toHashCode();
	}

}
