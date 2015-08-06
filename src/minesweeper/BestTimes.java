package minesweeper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import java.util.*;

/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
	/** List of best player times. */
	private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

	/**
	 * Returns an iterator over a set of best times.
	 * 
	 * @return an iterator
	 */
	public Iterator<PlayerTime> iterator() {
		return playerTimes.iterator();
	}
	
	/**
	 * Remove each player from best player
	 */
	public void reset(){
		playerTimes.clear();
	}

	/**
	 * Adds player time to best times.
	 * 
	 * @param name
	 *            name of the player
	 * @param time
	 *            player time in seconds
	 */
	public void addPlayerTime(String name, int time) {
		playerTimes.add(new PlayerTime(name, time));
		Collections.sort(playerTimes);
	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	public String toString() {
		Formatter f = new Formatter();
		f.format("\tPlayers table\n________________________\n");
		for (int i = 0; i < playerTimes.size(); i++) {
			f.format("%d. Name: %s Time: %d\n", i, playerTimes.get(i).name, playerTimes.get(i).time);
		}
		return f.toString();
	}

	/**
	 * Player time.
	 */
	public static class PlayerTime implements Comparable<PlayerTime> {
		/** Player name. */
		private final String name;
		/** Playing time in seconds. */
		private final int time;

		public String getName() {
			return name;
		}

		public int getTime() {
			return time;
		}

		public int compareTo(PlayerTime o) {
			if (this.time < o.time) {
				return 1;
			} else if (this.time > o.time) {
				return -1;
			} else
				return 0;
		}

		/**
		 * Constructor.
		 * 
		 * @param name
		 *            player name
		 * @param time
		 *            playing game time in seconds
		 */
		public PlayerTime(String name, int time) {
			this.name = name;
			this.time = time;
		}
	}
}