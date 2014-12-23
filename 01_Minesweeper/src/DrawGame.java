import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class DrawGame extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private int numColumns;
	private int numRows;
	private final int size = 20;
	private int centerDot = (int) (size * .1);
	private boolean mineWasHit = false;

	private Game game;

	public DrawGame(int numColumns, int numRows) {
		this.numColumns = numColumns;
		this.numRows = numRows;
		game = new Game(numColumns, numRows);
		game.initializeGameAndFillFieldWithMines();
		addMouseListener(this);
	}

	private void doDrawing(Graphics g) {

		super.paintComponents(g);

		g.setColor(Color.BLACK);
		for (int i = 0; i < numRows + 1; i++) {
			g.drawLine(0, i * size, size * numColumns, i * size);
		}
		for (int i = 0; i < numColumns + 1; i++) {
			g.drawLine(i * size, 0, i * size, size * numRows);
		}

		for (int column = 0; column < numColumns; column++) {
			for (int row = 0; row < numRows; row++) {
				if (game.isCovered(column, row) || game.hasMine(column, row)) {
					drawCoveredTiles(g, column, row);
				}

				if (game.hasMine(column, row) && mineWasHit) {
					drawDots(g, column, row);
				}

				if (game.isUncovered(column, row)) {
					if(game.numberOfSurroundingMines(column, row) == 0) {
						g.setColor(Color.LIGHT_GRAY);
						g.fillRect(column * size + centerDot, row * size
								+ centerDot, (int) (size * .8), (int) (size * .8));
					} else{
						g.setColor(Color.GREEN);
						g.fillRect(column * size + centerDot, row * size
								+ centerDot, (int) (size * .8), (int) (size * .8));
					}
					
					g.setColor(Color.WHITE);
					g.setFont(new Font(null, Font.PLAIN, 18));
					String str = game.numberOfSurroundingMines(column, row).toString();
					g.drawString(str, column * size + centerDot * 3, row * size
							+ centerDot * 8);
				}
			}
		}
		this.repaint();
	}

	public void drawCoveredTiles(Graphics g, int column, int row) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(column * size + centerDot, row * size + centerDot,
				(int) (size * .8), (int) (size * .8));
	}

	public void drawDots(Graphics g, int column, int row) {
		g.setColor(Color.RED);
		g.fillOval(column * size + centerDot, (row) * size + centerDot,
				(int) (size * .8), (int) (size * .8));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("X: " + e.getX() + " Y: " + e.getY());
		int column = e.getX() / size;
		int row = e.getY() / size;
		System.out.println("Column: " + column + " Row: " + row);
		System.out.println("--------------------------------------");
		if (game.uncoverAndCheckForMine(column, row)) {
			System.out.println("Mine was hit!");
		}
		mineWasHit = game.uncoverAndCheckForMine(column, row);
		// game.updateStatus();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
