
public class Sudoku_Solver {

	static final int GRID_WIDTH = 9;
	static final int SUB_GRID_WIDTH = 3;

	public static void main(String[] args) {
		int[][] grid = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, { 6, 0, 0, 1, 9, 5, 0, 0, 0 }, { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
				{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 }, { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
				{ 0, 6, 0, 0, 0, 0, 2, 8, 0 }, { 0, 0, 0, 4, 1, 9, 0, 0, 5 }, { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };

		printGrid(grid);
		System.out.print("\nFilled:");
		occupyGrid(grid);
		printGrid(grid);
	}

	private static boolean occupyGrid(int[][] grid) {
		int[] emptySpace = findEmptySpace(grid);
		if (emptySpace[0] == -1)
			return true;
		int row = emptySpace[0];
		int column = emptySpace[1];
		for (int i = 1; i <= 9; i++) {
			if (shouldWriteNumber(grid, i, row, column)) {
				grid[row][column] = i;
				if (occupyGrid(grid) == false)
					grid[row][column] = 0;
				else
					return true;
			}
		}
		return false;
	}

	private static int[] findEmptySpace(int[][] grid) {
		int[] emptySpace = { -1, -1 };
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_WIDTH; j++) {
				if (grid[i][j] == 0) {
					emptySpace[0] = i;
					emptySpace[1] = j;
					return emptySpace;
				}
			}
		}
		return emptySpace;
	}

	private static boolean shouldWriteNumber(int[][] grid, int number, int row, int column) {
		if (grid[row][column] != 0)
			return false;
		else if (checkRow(grid, number, row))
			return false;
		else if (checkColumn(grid, number, column))
			return false;
		else if (checkSubGrid(grid, number, row, column))
			return false;
		else
			return true;
	}

	private static boolean checkRow(int[][] grid, int number, int row) {
		for (int i = 0; i < GRID_WIDTH; i++) {
			if (grid[row][i] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkColumn(int[][] grid, int number, int column) {
		for (int i = 0; i < GRID_WIDTH; i++) {
			if (grid[i][column] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkSubGrid(int[][] grid, int number, int row, int column) {
		int rowStart = getSubGridStartIndex(row);
		int columnStart = getSubGridStartIndex(column);
		for (int i = 0; i < SUB_GRID_WIDTH; i++) {
			for (int j = 0; j < SUB_GRID_WIDTH; j++) {
				if (grid[rowStart + i][columnStart + j] == number)
					return true;
			}
		}
		return false;
	}

	private static int getSubGridStartIndex(int position) {
		if (position >= SUB_GRID_WIDTH * 2)
			return SUB_GRID_WIDTH * 2;
		else if (position >= SUB_GRID_WIDTH)
			return SUB_GRID_WIDTH;
		else
			return 0;
	}

	private static void printGrid(int[][] grid) {
		String gridString = "\n";
		for (int i = 0; i < GRID_WIDTH; i++) {
			gridString += addGridHorizontal(i);
			for (int j = 0; j < GRID_WIDTH; j++) {
				gridString += addGridVertical(j);
				gridString += addGridNumber(grid[i][j]);
			}
			gridString += "|\n";
		}
		gridString += addGridHorizontal();
		System.out.println(gridString);
	}
	
	private static String addGridNumber(int number) {
		if (number == 0)
			return "  ";
		else
			return number + " ";
	}
	
	private static String addGridVertical(int index) {
		if (index == 0 || index == 3 || index == 6)
			return "| ";
		else
			return "";
	}

	private static String addGridHorizontal(int index) {
		if (index == 0 || index == 3 || index == 6)
			return "-------------------------\n";
		else
			return "";
	}
	
	private static String addGridHorizontal() {
			return "-------------------------\n";
	}
}
