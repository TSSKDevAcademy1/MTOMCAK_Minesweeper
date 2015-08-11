package minesweeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import minesweeper.core.Mine;

public class Settings implements Serializable {
	private final int rowCount;
	private final int columnCount;
	private final int mineCount;

	public static final Settings BEGINNER = new Settings(9, 9, 10);
	public static final Settings INTERMEDIATE = new Settings(16, 16, 40);
	public static final Settings EXPERT = new Settings(16, 30, 99);

	private static final String SETTING_FILE = System.getProperty("user.home") + System.getProperty("file.separator")
			+ "minesweeper.settings";

	public Settings(int rowCount, int columnCount, int mineCount) {
		super();
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	/**
	 * Save setting of game - difficult.
	 */
	public void save() {
		File file = new File(SETTING_FILE);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// write object to file
			oos.writeObject(this);
			oos.close();
			System.out.println("Settings saved !");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Settings load(){
		System.out.println(SETTING_FILE);
		try {
			FileInputStream fis = new FileInputStream(SETTING_FILE);
			ObjectInputStream oos = new ObjectInputStream(fis);
			
			// read file to object
			Settings s = (Settings) oos.readObject();
			oos.close();
			System.out.println("Settings saved !");
			return s;

		} catch (Exception e) {
			return Settings.BEGINNER;
					
		}
	}
	

	@Override
	public boolean equals(Object o) {
		if (o.equals(this))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return rowCount * columnCount * mineCount;
	}

}
