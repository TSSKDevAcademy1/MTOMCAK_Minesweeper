package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;

import minesweeper.UserInterface;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.
	 * Field)
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		System.out.printf("\tWelcome player " + System.getProperty("user.name") + "!\n\n");
		do {
			update();
			processInput();

			// Player won !
			if (field.getState() == GameState.SOLVED) {
				update();
				System.out.println("You won !");
				System.exit(0);
			}

			// player loose !
			else if (field.getState() == GameState.FAILED) {
				update();
				System.out.println("GAME OVER !");
				System.exit(0);
			}
		} while (true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
	@Override
	public void update() {

		// Create formatter for StringBuilder
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.US);

		// Write column index
		for (int i = 0; i < field.getRowCount(); i++) {
			formatter.format("\t%d ", i);
		}
		formatter.format("\n\n");

		// Print field using format
		for (int r = 0; r < field.getRowCount(); r++) {
			char p = (char) (r + 65);
			formatter.format("%s ", p);
			for (int c = 0; c < field.getColumnCount(); c++) {
				formatter.format("\t%s ", field.getTile(r, c).toString());
			}
			formatter.format("\n\n");
		}
		formatter.format("Please enter your selection (X) EXIT, (MA1) MARK, (OB4) OPEN: ");
		formatter.close();
		System.out.println(sb);
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {

		String selection = null;
		int col = 0, row = 0;

		Pattern pattern = Pattern.compile("X|(O|M)([A-Za-z])([0-9])"); // add
																		// ([0-9]+)
																		// for
																		// number
																		// > >10
		selection = readLine().toUpperCase(); // read selection
		Matcher matcher = pattern.matcher(selection);

		// match input value and select group of characters
		if (matcher.find() && !matcher.group().equals("X")) {
			row = matcher.group(2).charAt(0) - 65; // convert char to int range
													// <0; n>
			col = Integer.parseInt(matcher.group(3));

			if ((row > 0 && row <= field.getRowCount()) && (col > 0 && col <= field.getColumnCount())) {			

				switch (matcher.group(1)) {
				case "O":
					field.openTile(row, col);
					break;
				case "M":
					field.markTile(row, col);
					break;
				default:
					System.out.println("Wrong format or Out of range !");
					break;
				}
			}
		} else if (matcher.group().equals("X")) {
			System.out.println("\nGOODBYE !");
			System.exit(0);
		}
	}
}
