import java.util.Random;


/**
 * This algorithm was not created by me - more info in link
 * zhangroup.aporc.org/images/files/Paper3485.pdf
 * However ALL code is written by me
 * @author Kalana
 *
 */
public class SudokuGenerator {
	int difficulty;
	Random numberGenerator;
	SudokuBoard board;
	SudokuSolver solver;
	SudokuBoard solution;
	boolean[][] hasBeenDug;
	int minRowCol;
	int minPuzzle;
	
	/**
	 * Constructor for a generator
	 * @param difficulty difficult of puzzles generated
	 */
	SudokuGenerator (int difficulty) {
		this.difficulty = difficulty;
		numberGenerator = new Random();
		hasBeenDug = new boolean[9][9];
		if (difficulty==1) {
			minRowCol = 4;
			minPuzzle = 50;
		} else if (difficulty==2) {
			minRowCol = 3;
			minPuzzle = 40;
		} else if (difficulty==3) {
			minRowCol = 3;
			minPuzzle = 35;
		} else {
			minRowCol = 2;
			minPuzzle = 28;
		}
 	}
	
	/**
	 * Creates a seed for the generator
	 */
	void GenerateSudokuSeed () {
		board = new SudokuBoard();		
		for (int cellsFilled = 0; cellsFilled < 11; cellsFilled++) {
			int row = numberGenerator.nextInt(9);
			int col = numberGenerator.nextInt(9);
			int num = numberGenerator.nextInt(9)+1;
			if (board.numberIsLegal(row, col, num)) {
				board.setNumber(col, row, num);
			}
		}
	}
	
	/**
	 * Generates a solved board
	 */
	void GenerateTerminalPattern (){
		GenerateSudokuSeed();
		solver = new SudokuSolver(board);
		if (!solver.runSolve()) {
			GenerateTerminalPattern ();
		}
		solution = solver.getSolution();
		board = solver.getSolution();
	}
	
	/**
	 * Generates a Sudoku with a unique and valid solution
	 */
	void GenerateSolvableSudoku () {
		GenerateTerminalPattern ();
		boolean isJumping = false;
		int boardNumber = 81;
		if (difficulty<3) {
			//Random
			while (canBeDug(isJumping)) {
				boolean currentCanBeDug = true;
				int row = 0;
				int col = 0;
				while (currentCanBeDug==true) {
					row = numberGenerator.nextInt(9);
					col = numberGenerator.nextInt(9);
					currentCanBeDug = hasBeenDug[col][row];
				}
				if(boardNumber>minPuzzle&&digLegal(col,row)) {
					if (isValidDig(col,row)) {
						boardNumber--;
						board.setNumber(col, row, 0);
					}
				}
				hasBeenDug[col][row] = true;
			}
		} else if (difficulty==3) {
			// Jump one Cell
			isJumping = true;
			if (canBeDug(isJumping)) {
				for (int row = 0; row < 9; row++) {
					int initialCol;
					if (row%2==0) {
						initialCol = 0;
						for (int col = initialCol; col < 9; col=col+2) {
							if (isValidDig(col,row)) {
								boardNumber--;
								board.setNumber(col, row, 0);
							}
						}	
					} else {
						initialCol = 7;
						for (int col = initialCol; col > 0; col=col-2) {
							if (isValidDig(col,row)) {
								boardNumber--;
								board.setNumber(col, row, 0);
							}
						}
					}	
				}
			}			 
		} else if (difficulty==4) {
			if (canBeDug(isJumping)) {
				for (int row = 0; row < 9; row++) {
					int initialCol;
					if (row%2==0) {
						initialCol = 0;
						for (int col = initialCol; col < 9; col++) {
							if (isValidDig(col,row)) {
								boardNumber--;
								board.setNumber(col, row, 0);
							}
						}	
					} else {
						initialCol = 8;
						for (int col = initialCol; col >= 0; col--) {
							if (isValidDig(col,row)) {
								boardNumber--;
								board.setNumber(col, row, 0);
							}
						}
					}	
				}
			}
		}
	}
	
	/**
	 * Checks if this board has more tiles that can be removed
	 * @param isJumping determines the pattern currently used for generation
	 * @return true if tiles can be removed, false otherwise
	 */
	boolean canBeDug (boolean isJumping) {
		if (isJumping==false) {
			for (int row = 0; row < 9; row ++) {
				for (int col = 0; col < 9; col ++) {
					if (hasBeenDug[col][row]==false) {
						return true;
					}
				}
			}
			return false;
		} else {
			for (int row = 0; row < 9; row++) {
				int initialCol;
				if (row%2==0) {
					initialCol = 0;
				} else {
					initialCol = 1;
				}
				for (int col = initialCol; col < 9; col=col+2) {
					if (hasBeenDug[col][row]==false) {
						return true;
					}
				}	
			}
			return true;
		}
	}
	
	/**
	 * Checks if a tile removal at this point is legal, according to constraints
	 * @param col column of tile to be removed
	 * @param row row of tile to be removed
	 * @return true if tile can be removed, false otherwise
	 */
	boolean digLegal (int col, int row) {
		int rowNumber = 0;
		int colNumber = 0;
		for (int i = 0; i<9; i++) {
			if (board.getNumber(i, row)!=0) {
				rowNumber++;
			}
		}
		for (int j = 0; j<9; j++) {
			if (board.getNumber(col, j)!=0) {
				colNumber++;
			}
		}
		if(rowNumber==minRowCol||colNumber==minRowCol) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if a tile being removed will affect validity of sudoku
	 * @param col column of tile to be removed
	 * @param row row of tile to be removed
	 * @return true if tile can be removed, false otherwise
	 */
	boolean isValidDig (int col, int row) {
		SudokuSolver solver;
		int currentTemp = board.getNumber(col, row);
		for (int i = 1; i<9; i++){
			if (currentTemp!=i) {
				board.setNumber(col, row, i);
				solver = new SudokuSolver(board);
				if(solver.runSolve()) {
					board.setNumber(col, row, currentTemp);
					return false;
				}
			}
		}
		board.setNumber(col, row, currentTemp);
		return true;
	}
	
	/**
	 * Getter for the board generated by the generator
	 * @return the board
	 */
	SudokuBoard getBoard() {
		int[][] array = new int[9][9];
		for (int col = 0; col<9; col++) {
			for (int row=0; row<9; row++) {
				array[col][row]=board.getNumber(col, row);
			}
		}
		return new SudokuBoard (array);
	}
}
