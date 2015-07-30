package minesweeper.core;

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
		return this.value + "";
	}
}
