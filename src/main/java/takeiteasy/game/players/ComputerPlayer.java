package takeiteasy.game.players;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Stopwatch;

import takeiteasy.game.BoardCalculations;
import takeiteasy.game.Playboard;
import takeiteasy.game.Verbosity;
import takeiteasy.game.cards.PlayingCard;

/**
 * 
 * @author Fabio Widmer
 */
public class ComputerPlayer implements Player {

	private final Duration maximumTime;
	private final Verbosity verbosity;

	/**
	 * Constructor.
	 * 
	 * @param duration
	 *            The maximum time {@link Duration} allowed for the calculation.
	 * @param verbosity
	 *            The {@link Verbosity} of the player.
	 */
	public ComputerPlayer(Duration duration, Verbosity verbosity) {
		this.verbosity = verbosity;
		this.maximumTime = duration;
	}

	@Override
	public Pair<Integer, Integer> decideMove(PlayingCard currentCard, Playboard playboard) {

		Stopwatch stopWatch = Stopwatch.createStarted();

		final ExecutorService service = Executors.newSingleThreadExecutor();

		List<Pair<Integer, Integer>> movePossibilities = playboard.getAllFreeCoordinates();

		int bestIdx = 0;
		int depth = 0;
		while (true) {

			final int currentDepth = depth;
			Future<Integer> result = service
					.submit(() -> getBest(movePossibilities, playboard, currentCard, currentDepth).getLeft());

			try {
				bestIdx = result.get(maximumTime.minus(stopWatch.elapsed()).toMillis(), TimeUnit.MILLISECONDS);
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			} catch (TimeoutException e) {
				break;
			}

			if (movePossibilities.size() - 1 - depth <= 0)
				break;

			depth++;
		}

		if (verbosity == Verbosity.verbose)
			System.out.println("Maximum depth: " + depth);

		service.shutdownNow();
		return movePossibilities.get(bestIdx);
	}

	private static Pair<Integer, Double> getBest(List<Pair<Integer, Integer>> movePossibilities, Playboard playboard,
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

	private static double getValue(int localDepth, Playboard currentBoard) {
		if (currentBoard.isFull())
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
