import java.util.Random;

public class Game {

	private int numColumns;
	private int numRows;
	private float mineDensity = 0.9f;

	private int field[][];

	private final int COVERED = 0;
	private final int UNCOVERED = 1;
	private final int MINE = 2;

	public Game(int numColumns, int numRows) {
		this.numColumns = numColumns;
		this.numRows = numRows;
		field = new int[numColumns][numRows];
	}

	public void initializeGameAndFillFieldWithMines() {
		Random rnd = new Random();
		for (int column = 0; column < numColumns; column++) {
			for (int row = 0; row < numRows; row++) {
				if (rnd.nextFloat() > mineDensity) {
					field[column][row] = MINE;
				} else {
					field[column][row] = COVERED;
				}
			}
		}
	}

	public boolean hasMine(int column, int row) {
		if (field[column][row] == MINE) {
			return true;
		}
		return false;
	}

	public boolean isCovered(int column, int row) {
		if (field[column][row] == COVERED) {
			return true;
		}
		return false;
	}

	public boolean isUncovered(int column, int row) {
		if (field[column][row] == UNCOVERED) {
			return true;
		}
		return false;
	}

	public boolean uncoverAndCheckForMine(int column, int row) {
		if (field[column][row] != MINE) {
			field[column][row] = UNCOVERED;
			if (numberOfSurroundingMines(column, row) == 0) {
				uncoverSurroundingFields(column, row);
				updateStatus();
			}
			return false;
		}
		return true;
	}

	private void uncoverSurroundingFields(int column, int row) {

		if (column > 0 && row > 0) {
			field[column - 1][row - 1] = UNCOVERED;
		}

		if (column > 0) {
			field[column - 1][row] = UNCOVERED;
		}

		if (column > 0 && row < numRows - 1) {
			field[column - 1][row + 1] = UNCOVERED;
		}

		if (row > 0) {
			field[column][row - 1] = UNCOVERED;
		}

		if (row < numRows - 1) {
			field[column][row + 1] = UNCOVERED;
		}

		if ((column < numColumns - 1) && (row > 0)) {
			field[column + 1][row - 1] = UNCOVERED;
		}

		if (column < numColumns - 1) {
			field[column + 1][row] = UNCOVERED;
		}

		if (column < numColumns - 1 && row < numRows - 1) {
			field[column + 1][row + 1] = UNCOVERED;
		}

	}

	public void updateStatus() {
		for (int i = 0; i < 200; i++) {

			for (int column = 0; column < numColumns; column++) {
				for (int row = 0; row < numRows; row++) {

					if (field[column][row] == UNCOVERED
							&& numberOfSurroundingMines(column, row) == 0) {
						uncoverSurroundingFields(column, row);
					}
				}
			}
		}
	}

	public Integer numberOfSurroundingMines(int column, int row) {
		if (hasMine(column, row))
			return -1;
		return countSurroundingMines(column, row);
	}

	public Integer countSurroundingMines(int column, int row) {

		Integer count = 0;

		if (column > 0 && row > 0)
			if (hasMine(column - 1, row - 1))
				count++;

		if (column > 0)
			if (hasMine(column - 1, row))
				count++;

		if (column > 0 && row < numRows - 1)
			if (hasMine(column - 1, row + 1))
				count++;

		if (row > 0)
			if (hasMine(column, row - 1))
				count++;

		if (row < numRows - 1)
			if (hasMine(column, row + 1))
				count++;

		if ((column < numColumns - 1) && (row > 0))
			if (hasMine(column + 1, row - 1))
				count++;

		if (column < numColumns - 1)
			if (hasMine(column + 1, row))
				count++;

		if ((column < numColumns - 1) && (row < numRows - 1))
			if (hasMine(column + 1, row + 1))
				count++;

		return count;
	}
}
