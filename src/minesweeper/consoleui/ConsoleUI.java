package minesweeper.consoleui;

import java.io.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import minesweeper.UserInterface;
import minesweeper.core.Field;
import minesweeper.core.GameState;

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
		do {
			update();
			processInput();
		} while (field.getState() == GameState.PLAYING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
	@Override
	public void update() {

		/** Write column index */
		for (int i = 0; i < field.getRowCount(); i++) {
			System.out.format("\t%d ", i);
		}

		System.out.println();

		/** Print field using format */
		for (int r = 0; r < field.getRowCount(); r++) {
			char p = (char) (r + 65);
			System.out.printf("%s ", p);
			for (int c = 0; c < field.getColumnCount(); c++) {
				System.out.printf("\t%s ", field.getTile(r, c).toString());
			}
			System.out.println();
		}

		System.out.println();
		System.out.print("Please enter your selection (X) EXIT, (MA1) MARK, (OB4) OPEN :");

	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		String selection = null;
		Pattern p = Pattern.compile("O([A-I])([0-8])");
		
		selection = readLine(); // read selection
		
		
		if (p.matcher(input))
		{
			
		}

		if (selection.equals("X")) {
			this.field.setState(GameState.FAILED);
		}

	}
}
