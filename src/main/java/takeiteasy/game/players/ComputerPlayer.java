package takeiteasy.game.players;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import takeiteasy.game.BoardCalculations;
import takeiteasy.game.Playboard;
import takeiteasy.game.cards.PlayingCard;

public class ComputerPlayer implements Player {

	@Override
	public Pair<Integer, Integer> decideMove(PlayingCard currentCard, Playboard playboard) {

		List<Pair<Integer, Integer>> movePossibilities = playboard.getAllFreeCoordinates();

		int best = Integer.MIN_VALUE;
		int bestIdx = -1;

		for (int coordinateIdx = 0; coordinateIdx < movePossibilities.size(); coordinateIdx++) {
			Pair<Integer, Integer> coordinates = movePossibilities.get(coordinateIdx);
			Playboard currentBoard = playboard.copy();
			currentBoard.set(currentCard, coordinates.getLeft(), coordinates.getRight());

			int currentValue = getValue(currentBoard);

			if (currentValue > best) {
				best = currentValue;
				bestIdx = coordinateIdx;
			}
		}

		return movePossibilities.get(bestIdx);
	}

	private int getValue(Playboard currentBoard) {
		return BoardCalculations.getNumberOfFreeRows(currentBoard)
				- 2 * BoardCalculations.getNumberOfDestroyedRows(currentBoard);
	}

	@Override
	public String getName() {
		return "computerPlayer";
	}

}
