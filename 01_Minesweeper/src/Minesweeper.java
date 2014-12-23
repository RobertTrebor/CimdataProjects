import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Minesweeper extends JFrame {

	private static final long serialVersionUID = 1L;
	DrawGame game;

	public Minesweeper() {
		initUI();
	}

	public final void initUI() {

		final GameOptions gameOptions = new GameOptions();
		add(gameOptions, BorderLayout.NORTH);
		JButton startButton = new JButton("start");
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (game != null) remove(game);
				int columns = Integer.valueOf(gameOptions.getNumberOfColumns()
						.getText());
				int rows = Integer.valueOf(gameOptions.getNumberOfRows()
						.getText());
				game = new DrawGame(columns, rows);
				add(game, BorderLayout.CENTER);
				validate();
			}
		});

		add(startButton, BorderLayout.SOUTH);

		setSize(800, 600);
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Minesweeper mine = new Minesweeper();
				mine.setVisible(true);
			}
		});
	}
}
