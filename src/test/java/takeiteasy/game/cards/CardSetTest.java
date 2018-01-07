package takeiteasy.game.cards;

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

	@Test(expected = UnsupportedOperationException.class)
	public void testMutability() {
		List<PlayingCard> cardSet = CardSet.getNew();
		cardSet.remove(0);
	}

}
