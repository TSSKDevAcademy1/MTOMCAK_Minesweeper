package swingui.minesweeper.swingui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import minesweeper.Minesweeper;

public class BestTimesDialog extends JDialog {

	public BestTimesDialog() {
		JTextArea textArea = new JTextArea(20, 10);
		textArea.setText(Minesweeper.getInstance().getBestTimes().toString());
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		setLocationRelativeTo(textArea);

		JButton buttonOK = new JButton("OK");

	}
}
