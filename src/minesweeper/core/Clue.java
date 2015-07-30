package minesweeper.core;

import minesweeper.core.Tile.State;

/**
 * Clue tile.
 */
public class Clue extends Tile {
	/** Value of the clue. */
	private final int value;

	public int getValue() {
		return value;
	}

	/**
	 * Constructor.
	 * 
	 * @param value
	 *            value of the clue
	 */
	public Clue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (getState() == State.OPEN) {
			return getValue() + "";
		} else {
			return super.toString();
		}
	}
}
