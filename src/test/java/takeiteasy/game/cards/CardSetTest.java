package takeiteasy.game.cards;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import junit.framework.TestCase;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class CardSetTest extends TestCase {

	@Test
	public void testSize() {
		List<PlayingCard> cardSet = CardSet.getNew();
		assertEquals(27, cardSet.size());
	}

	@Test
	public void testSample() {
		List<PlayingCard> cardSet = CardSet.getNew();
		PlayingCard testCard1 = new PlayingCard(2, 1, 3);
		PlayingCard testCard2 = new PlayingCard(7, 9, 8);

		assertTrue(cardSet.contains(testCard1));
		assertTrue(cardSet.contains(testCard2));
	}

	@Test
	public void testDraw() {
		List<PlayingCard> cards = new ArrayList<>();
		PlayingCard onlyCard = new PlayingCard(2, 1, 3);
		cards.add(onlyCard);

		PlayingCard drawnCard = CardSet.drawRandom(cards);

		assertEquals(onlyCard, drawnCard);
	}

}
