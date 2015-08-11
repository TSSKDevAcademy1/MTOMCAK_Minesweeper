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
		newGame();
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
	
	public void newGame() {
		settings = settings.load();
        Field field = new Field(settings.getRowCount(), settings.getColumnCount(), settings.getMineCount());
        startMillis = System.currentTimeMillis();
        userInterface.newGameStarted(field);
        setSettings(settings);
    }

	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}

	public BestTimes getBestTimes() {
		return BestTimes;
	}
	
	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		settings.save();
		this.settings = settings;
	}

	public static Minesweeper getInstance() {
		if ( instance == null )
		{
			new Minesweeper();
		}
		return instance;
	}
}
