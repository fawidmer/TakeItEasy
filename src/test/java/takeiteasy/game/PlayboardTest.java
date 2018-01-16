package takeiteasy.game;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import junit.framework.TestCase;
import takeiteasy.game.cards.PlayingCard;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class PlayboardTest extends TestCase {

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

		String expectedString = "        (6,1,8)\n";
		expectedString += "    (6,9,8) (7,9,8)\n";
		expectedString += "(6,5,3) (7,1,8) (2,5,8)\n";
		expectedString += "    (7,9,3) (2,9,8)\n";
		expectedString += "(7,5,4) (2,1,8) (6,1,3)\n";
		expectedString += "    (2,9,4) (6,9,3)\n";
		expectedString += "(2,5,4) (6,1,4) (7,5,3)\n";
		expectedString += "    (6,9,4) (7,9,4)\n";
		expectedString += "        (7,1,4)\n\n\n";
		expectedString += "[(2,5,3), (2,9,3), (2,1,4), (6,5,4), (2,1,3), (7,5,8), (6,5,8), (7,1,3)]";
		assertEquals(expectedString, board.toString());

		assertEquals(new PlayingCard(7, 9, 4), board.get(3, 3));

		assertEquals(36, board.getValueVerticalRow(1));
		assertEquals(21, board.getValueAscendingRow(4));
		assertEquals(0, board.getValueDescendingRow(2));
		assertEquals(245, board.getScore());

		assertTrue(board.getAvailableCards().contains(new PlayingCard(6, 5, 4)));
		assertFalse(board.getAvailableCards().contains(new PlayingCard(6, 1, 3)));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testPlacingOnSameCoordinate() {
		Playboard board = new Playboard();

		board.set(new PlayingCard(6, 5, 3), 0, 0);
		board.set(new PlayingCard(6, 5, 4), 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPlacingSameCard() {
		Playboard board = new Playboard();

		board.set(new PlayingCard(6, 5, 3), 0, 0);
		board.set(new PlayingCard(6, 5, 3), 0, 1);
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

		assertEquals(new PlayingCard(7, 1, 8), board.get(1, 1));
		assertEquals(null, board.get(2, 2));

		assertEquals(0, board.getValueVerticalRow(0));
		assertEquals(0, board.getValueAscendingRow(4));
		assertEquals(4, board.getValueVerticalRow(1));
		assertEquals(4, board.getScore());

	}

}
