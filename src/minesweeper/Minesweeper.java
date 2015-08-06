package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */
	private UserInterface userInterface;
	private long startMillis;
	private BestTimes BestTimes;
	private static Minesweeper instance = null;
	
	private Settings settings;
	
	/**
	 * Constructor.
	 */
	private Minesweeper() {
		instance = this;
		userInterface = new ConsoleUI();
		Field field = new Field(9, 9, 10);
		startMillis = System.currentTimeMillis();
		userInterface.newGameStarted(field);
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		Minesweeper.getInstance();
	}

	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}

	public BestTimes getBestTimes() {
		return BestTimes;
	}

	public static Minesweeper getInstance() {
		if ( instance == null )
		{
			new Minesweeper();
		}
		return instance;
	}
	
	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	
}
