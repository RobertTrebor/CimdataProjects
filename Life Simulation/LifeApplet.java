import java.applet.*;
import java.awt.*;
import java.awt.event.*;


public class LifeApplet extends Applet implements MouseListener {
	// Creates a 15 x 15 grid and then lets the user
	// place living cells in the squares.
	// Author: Robert Lengsfeld, February 12, 1999

	
	private Button nextGen;
	private Button next10Gen;
	public static final int hor = 80;
	public static final int ver = 50;
	public static final int size = 15;
	public Life field;
	public Life nextField;

	public void init() {
		field = new Life(ver, hor);
		nextField = new Life(ver, hor);
		addMouseListener(this);
		nextGen = new Button("Next Generation");
		nextGen.addMouseListener(this);
		next10Gen = new Button("Next 10");
		next10Gen.addMouseListener(this);
		setLayout(new BorderLayout());
		add("South", nextGen);
		add("North", next10Gen);
	}

	public void paint(Graphics g) {
		// Draws the grid and draws the living cells.

		setLocation(0, 0);
		for (int i = 0; i < ver + 1; i++) {
			g.drawLine(0, i * size, size * hor, i * size);
		}
		for (int i = 0; i < hor + 1; i++) {
			g.drawLine(i * size, 0, i * size, size * ver);
		}

		int centerDot = (int) (size * .1);
		for (int row = 0; row < ver; row++) {
			for (int col = 0; col < hor; col++) {
				if (field.hasLife(row, col)) {
					g.setColor(Color.red);
					g.fillOval(col * size + centerDot,
							(row) * size + centerDot, (int) (size * .8),
							(int) (size * .8));
					g.setColor(Color.black);
				}
			}
		}
	}

	public void update(Graphics g) {
		setLocation(0, 0);
		for (int i = 0; i < ver + 1; i++) {
			g.drawLine(0, i * size, size * hor, i * size);
		}
		for (int i = 0; i < hor + 1; i++) {
			g.drawLine(i * size, 0, i * size, size * ver);
		}
		int centerDot = (int) (size * .1);
		for (int row = 0; row < ver; row++) {
			for (int col = 0; col < hor; col++) {
				if (field.hasLife(row, col)) {
					g.setColor(Color.red);
					g.fillOval(col * size + centerDot,
							(row) * size + centerDot, (int) (size * .8),
							(int) (size * .8));					
				}
				else{
					g.setColor(Color.white);
					g.fillOval(col * size + centerDot,
							(row) * size + centerDot, (int) (size * .8),
							(int) (size * .8));
				}
			}
		}
	}

	// Mouse Events

	public void mouseClicked(MouseEvent e) {
		int X, Y;

		if (e.getSource() == nextGen) {
			Life.calcNextGen(ver, hor);
		}

		else if (e.getSource() == next10Gen) {
			for (int i = 0; i < 10; i++) {
				Life.calcNextGen(ver, hor);
			}
		}

		else {
			X = (e.getX() / size);
			Y = (e.getY() / size);
			field.setLife(X, Y);
		}
		repaint();

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
