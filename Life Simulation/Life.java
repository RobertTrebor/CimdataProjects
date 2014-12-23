import java.util.Random;

class Life {
	// Does the calculations necessary for artificial
	// life simulation.
	// Author: Robert Lengsfeld, February 12, 1999

	public int ver;
	public int hor;
	private static boolean field[][];
	private static boolean nextField[][];
	private static boolean code[][];

	Life(int ver, int hor) {
		Random rnd = new Random();
		field = new boolean[ver][hor];
		for (int i = 0; i < ver; i++) {
			for (int j = 0; j < hor; j++) {
				if (rnd.nextFloat() > 0.9) {
					field[i][j] = true;
				} else {
					field[i][j] = false;
				}
			}
		}
	}

	public static void calcNextGen(int ver, int hor) {

		int count = 0;
		nextField = new boolean[ver][hor];
		code = new boolean[3][3];

		for (int k = 0; k < 3; k++) {
			for (int l = 0; l < 3; l++) {
				code[k][l] = false;
			}
		}
		for (int i = 0; i < ver; i++) {
			for (int j = 0; j < hor; j++) {
				count = 0;

				if (i > 0 && j > 0)
					if (field[i - 1][j - 1]) {
						count++;
						code[0][0] = true;
					}

				if (i > 0)
					if (field[i - 1][j]) {
						count++;
						code[0][1] = true;
					}

				if ((i > 0) && (j < hor - 1))
					if (field[i - 1][j + 1]) {
						count++;
						code[0][2] = true;
					}

				if (j > 0)
					if (field[i][j - 1]) {
						count++;
						code[1][0] = true;
					}

				if (j < hor - 1)
					if (field[i][j + 1]) {
						count++;
						code[1][2] = true;
					}

				if ((i < ver - 1) && (j > 0))
					if (field[i + 1][j - 1]) {
						count++;
						code[2][0] = true;
					}

				if (i < ver - 1)
					if (field[i + 1][j]) {
						count++;
						code[2][1] = true;
					}

				if ((i < ver - 1) && (j < hor - 1))
					if (field[i + 1][j + 1]) {
						count++;
						code[2][2] = true;
					}

				if (field[i][j]) {
					if ((count == 2) || (count == 3)) {
						nextField[i][j] = true;
					} else
						nextField[i][j] = false;
				} else if (count == 3) {
					nextField[i][j] = true;
				}
			}
		}

		field = nextField;

	}

	public boolean hasLife(int row, int col) {
		return field[row][col];
	}

	public void setLife(int col, int row) {
		field[row][col] = true;
	}
}
