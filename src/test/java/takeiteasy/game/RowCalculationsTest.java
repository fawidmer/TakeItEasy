package takeiteasy.game;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class RowCalculationsTest extends TestCase {

	@Test
	public void testScore() {
		List<Integer> row = Arrays.asList(2, 2, 2, 2);
		assertEquals(8, RowCalculations.getScore(row));

		row.set(0, 7);
		assertEquals(0, RowCalculations.getScore(row));

		row.set(0, null);
		assertEquals(0, RowCalculations.getScore(row));

	}

	@Test
	public void testValue() {
		List<Integer> row = Arrays.asList(null, null, null, null, null);
		assertEquals(0, RowCalculations.getValue(row));

		row.set(2, 5);
		assertEquals(5, RowCalculations.getValue(row));

		row.set(3, 9);
		assertEquals(-1, RowCalculations.getValue(row));

		row.set(2, 9);
		assertEquals(9, RowCalculations.getValue(row));

	}

	@Test
	public void testIsFree() {
		List<Integer> row = Arrays.asList(null, null, null, null, null);
		assertTrue(RowCalculations.isFree(row));

		row.set(2, 5);
		assertFalse(RowCalculations.isFree(row));

		row.set(3, 9);
		assertFalse(RowCalculations.isFree(row));

	}

	@Test
	public void testIsDestroyed() {
		List<Integer> row = Arrays.asList(null, null, null, null, null);
		assertFalse(RowCalculations.isDestroyed(row));

		row.set(2, 5);
		assertFalse(RowCalculations.isDestroyed(row));

		row.set(3, 9);
		assertTrue(RowCalculations.isDestroyed(row));

		row.set(2, 9);
		assertFalse(RowCalculations.isDestroyed(row));

	}

}
