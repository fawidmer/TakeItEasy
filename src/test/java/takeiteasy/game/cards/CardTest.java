package takeiteasy.game.cards;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import junit.framework.TestCase;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class CardTest extends TestCase {
	@Test
	public void testNormalCard() {
		PlayingCard card1 = new PlayingCard(7, 5, 4);
		PlayingCard card2 = new PlayingCard(7, 5, 4);
		PlayingCard card3 = new PlayingCard(7, 5, 8);

		assertEquals(card1.left, 7);
		assertEquals(card1.middle, 5);
		assertEquals(card1.right, 4);

		assertEquals(card1.toString(), "(7,5,4)");

		assertTrue(card1.equals(card2));
		assertFalse(card1.equals(card3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInexistentCard1() {
		PlayingCard card = new PlayingCard(7, 5, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInexistentCard2() {
		PlayingCard card = new PlayingCard(7, 6, 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInexistentCard3() {
		PlayingCard card = new PlayingCard(3, 5, 4);
	}
}
