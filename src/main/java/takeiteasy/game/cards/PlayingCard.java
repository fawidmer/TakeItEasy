package takeiteasy.game.cards;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PlayingCard {

	public final int left;
	public final int middle;
	public final int right;

	public PlayingCard(int left, int middle, int right) {
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
