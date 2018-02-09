package takeiteasy.game.players;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import takeiteasy.game.BoardCalculations;
import takeiteasy.game.Playboard;
import takeiteasy.game.cards.PlayingCard;

public class ComputerPlayer implements Player {

	private final int depth;

	public ComputerPlayer(int depth) {
		this.depth = depth;
	}

	@Override
	public Pair<Integer, Integer> decideMove(PlayingCard currentCard, Playboard playboard) {

		List<Pair<Integer, Integer>> movePossibilities = playboard.getAllFreeCoordinates();

		int bestIdx = getBest(movePossibilities, playboard, currentCard, depth).getLeft();

		return movePossibilities.get(bestIdx);
	}

	private Pair<Integer, Double> getBest(List<Pair<Integer, Integer>> movePossibilities, Playboard playboard,
			PlayingCard currentCard, int localDepth) {
		double bestValue = Double.NEGATIVE_INFINITY;
		int bestIdx = -1;

		for (int coordinateIdx = 0; coordinateIdx < movePossibilities.size(); coordinateIdx++) {
			Pair<Integer, Integer> coordinates = movePossibilities.get(coordinateIdx);
			Playboard currentBoard = playboard.copy();
			currentBoard.set(currentCard, coordinates.getLeft(), coordinates.getRight());

			double currentValue = getValue(localDepth - 1, currentBoard);

			if (currentValue > bestValue) {
				bestValue = currentValue;
				bestIdx = coordinateIdx;
			}
		}

		return Pair.of(bestIdx, bestValue);
	}

	private double getValue(int localDepth, Playboard currentBoard) {
		if (currentBoard.isComplete())
			return BoardCalculations.getScore(currentBoard);

		if (localDepth < 0)
			return BoardCalculations.getNumberOfFreeRows(currentBoard)
					- 2 * BoardCalculations.getNumberOfDestroyedRows(currentBoard);

		List<PlayingCard> cards = currentBoard.getAvailableCards();
		List<Pair<Integer, Integer>> movePossibilities = currentBoard.getAllFreeCoordinates();
		List<Double> values = new ArrayList<>();
		for (PlayingCard card : cards)
			values.add(getBest(movePossibilities, currentBoard, card, localDepth).getRight());

		return values.stream().collect(Collectors.averagingDouble(Double::doubleValue));
	}

	@Override
	public String getName() {
		return "computerPlayer";
	}

}
