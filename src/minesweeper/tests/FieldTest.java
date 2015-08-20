package minesweeper.tests;

import static org.junit.Assert.*;

import java.io.ObjectInputStream.GetField;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Mine;
import minesweeper.core.Tile;
import minesweeper.core.Tile.State;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FieldTest {

	static final int ROWS = 9;
	static final int COLUMNS = 9;
	static final int MINES = 10;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// run command before start all test
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// run before each test method
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void isSolved() {
		Field field = new Field(ROWS, COLUMNS, MINES);

		assertEquals(GameState.PLAYING, field.getState());

		int open = 0;
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Clue) {
					field.openTile(row, column);
					open++;
				}
				if (field.getRowCount() * field.getColumnCount() - open == field.getMineCount()) {
					assertEquals(GameState.SOLVED, field.getState());
				} else {
					assertNotSame(GameState.FAILED, field.getState());
				}
			}
		}

		assertEquals(GameState.SOLVED, field.getState());
	}

	@Test
	public void generate() {
		Field field = new Field(ROWS, COLUMNS, MINES);
		int clueCount = 0, numberOfTile = 0;
		int allTile = ROWS * COLUMNS;

		assertEquals(ROWS, field.getRowCount());
		assertEquals(COLUMNS, field.getColumnCount());
		assertEquals(MINES, field.getMineCount());

		for (int r = 0; r < field.getRowCount(); r++) {
			for (int c = 0; c < field.getColumnCount(); c++) {
				assertNotNull(field.getTile(r, c));
				numberOfTile++;
				if (field.getTile(r, c) instanceof Clue) {
					clueCount++;
				}
			}
		}
		assertEquals(MINES, field.getMineCount());
		assertEquals(ROWS * COLUMNS - MINES, clueCount);
	}

	@Test
	public void openTile() {
		Field field = new Field(ROWS, COLUMNS, MINES);
		Tile tile = field.getTile(1,1);
		
		// OPEN if CLOSE
		tile.setState(State.CLOSED);
		field.openTile(1, 1);
		assertEquals(tile.getState(), State.OPEN);
		
		// OPEN if OPEN
		tile.setState(State.OPEN);
		field.openTile(1, 1);
		assertEquals(tile.getState(), State.OPEN);
		
		// OPEN if MARK
		tile.setState(State.OPEN);
		field.markTile(1, 1);
		assertEquals(tile.getState(), State.OPEN);
		
		// MARK if CLOSE
		tile.setState(State.CLOSED);
		field.markTile(1, 1);
		assertEquals(tile.getState(), State.MARKED);
		
		// MARK if OPEN
		tile.setState(State.MARKED);
		field.openTile(1, 1);
		assertEquals(tile.getState(), State.MARKED);
		}
	}
	/*
	 * APPEN NEXT TEST METHOD
	@Test
	public void markTile(int row, int column) {
		Tile tile = tiles[row][column];

		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.MARKED);
		}

		else if (tile.getState() == Tile.State.MARKED) {
			tile.setState(Tile.State.CLOSED);
		}
	}
	*/
