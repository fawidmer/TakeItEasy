package takeiteasy.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import junit.framework.TestCase;
import takeiteasy.game.cards.PlayingCard;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class BoardCalculationsTest extends TestCase {

	@Test
	public void testFullBoard() {
		Playboard board = new Playboard();

		board.set(new PlayingCard(6, 5, 3), 0, 0);
		board.set(new PlayingCard(7, 5, 4), 0, 1);
		board.set(new PlayingCard(2, 5, 4), 0, 2);
		board.set(new PlayingCard(6, 9, 8), 1, 0);
		board.set(new PlayingCard(7, 9, 3), 1, 1);
		board.set(new PlayingCard(2, 9, 4), 1, 2);
		board.set(new PlayingCard(6, 9, 4), 1, 3);
		board.set(new PlayingCard(6, 1, 8), 2, 0);
		board.set(new PlayingCard(7, 1, 8), 2, 1);
		board.set(new PlayingCard(2, 1, 8), 2, 2);
		board.set(new PlayingCard(6, 1, 4), 2, 3);
		board.set(new PlayingCard(7, 1, 4), 2, 4);
		board.set(new PlayingCard(7, 9, 8), 3, 0);
		board.set(new PlayingCard(2, 9, 8), 3, 1);
		board.set(new PlayingCard(6, 9, 3), 3, 2);
		board.set(new PlayingCard(7, 9, 4), 3, 3);
		board.set(new PlayingCard(2, 5, 8), 4, 0);
		board.set(new PlayingCard(6, 1, 3), 4, 1);
		board.set(new PlayingCard(7, 5, 3), 4, 2);

		assertEquals(245, BoardCalculations.getScore(board));
		assertEquals(0, BoardCalculations.getNumberOfFreeRows(board));
		assertEquals(3, BoardCalculations.getNumberOfDestroyedRows(board));

	}

	@Test
	public void testPartlyFullBoard() {
		Playboard board = new Playboard();

		board.set(new PlayingCard(2, 9, 4), 0, 0);
		board.set(new PlayingCard(6, 9, 3), 0, 1);
		board.set(new PlayingCard(2, 1, 8), 1, 0);
		board.set(new PlayingCard(7, 1, 8), 1, 1);
		board.set(new PlayingCard(7, 1, 3), 1, 2);
		board.set(new PlayingCard(6, 1, 4), 1, 3);

		assertEquals(4, BoardCalculations.getScore(board));
		assertEquals(5, BoardCalculations.getNumberOfFreeRows(board));
		assertEquals(2, BoardCalculations.getNumberOfDestroyedRows(board));

	}

}
