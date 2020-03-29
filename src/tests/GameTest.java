package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import juegovida.*;

class GameTest {
	
	private Game game = new Game();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGameClear() {
		game.clear();
		int width = game.getWidth();
		int height = game.getHeight();
		for (int i=0;i < width;i++)
			if (!Arrays.equals(game.getMatriz()[i], new boolean[height])) {
				fail("Row " + i + " is not correct");
			}
	}
	
	@Test
	void testsetWidthHeight() {
		game.setWidthHeight(10,20);
		assertEquals(game.getWidth(), 10);
		assertEquals(game.getHeight(), 20);
		assertEquals(game.getMatriz().length, 10);
		assertEquals(game.getMatriz()[0].length, 20);
	}
	
	@Test
	void testSetCellAlive() {
		game.setCellAlive(0, 0, true);
		assertTrue(game.getMatriz()[0][0]);

		game.setCellAlive(0, 0, false);
		assertFalse(game.getMatriz()[0][0]);
	}
	
	@Test
	void testNextIteration() {
		game.setWidthHeight(3, 3);
		game.setCellAlive(0, 0, true);
		game.setCellAlive(0, 1, true);
		game.setCellAlive(1, 0, true);
		game.nextIteration();
		assertTrue(Arrays.equals(game.getMatriz()[0], new boolean[] {true, true, false}));
		assertTrue(Arrays.equals(game.getMatriz()[1], new boolean[] {true, true, false}));
		assertTrue(Arrays.equals(game.getMatriz()[2], new boolean[] {false, false, false}));
	}

}
