import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to represent the sudokuboard
 * back-end only stores the number in a 2dim array
 * if there is nothing in the cell it is 0
 */
public class SudokuBoard {

	int[][] boardArray;

	public SudokuBoard() {
		boardArray = new int[9][9];
	}

	public SudokuBoard(int[][] boardArray) {
		this.boardArray = boardArray;
	}

	/**
	 * Set the specified col and row with the specified number on the board
	 * @param col
	 * @param row
	 * @param number
	 */
	void setNumber (int col, int row, int number) {
		boardArray[col][row] = number;
	}

	/**
	 * Get the specified number in the cell
	 * @param col
	 * @param row
	 * @return
	 */
	int getNumber (int col, int row) {
		return boardArray[col][row];
	}

	/**
	 * Check is the number entered is legal in the row
	 * @param row
	 * @param col
	 * @param num
	 * @return
	 */
	boolean isLegalRow (int row, int col, int num) {
		for (int c = 0; c<9; c++){
			if (c != col) {
				if (boardArray[c][row]==num){
					return false;
				}
			}

		}
		return true;		
	}

	/**
	 * Checks if the number entered is legal in the column
	 * @param col
	 * @param row
	 * @param num
	 * @return
	 */
	boolean isLegalCol (int col, int row, int num) {
		for (int r = 0; r<9; r++){
			if(r != row) {
				if (boardArray[col][r]==num){
					return false;
				}
			}
		}
		return true;

	}

	/**
	 * Checks if the number entered is correct in the square
	 * @param row
	 * @param col
	 * @param num
	 * @return
	 */
	boolean isLegalBox (int row, int col, int num) {
		int x = (row / 3) * 3 ;
		int y = (col / 3) * 3 ;
		for( int r = 0; r < 3; r++ ) {
			for( int c = 0; c < 3; c++ ) {
				if(r != row && c != col) {
					if( boardArray[y+c][x+r] == num ) {
						return false;
					}
				}

			}
		}
		return true;
	}

	/**
	 * Checks if the number entered is correct in the row, column and square
	 * used the methods above
	 * @param row
	 * @param col
	 * @param num
	 * @return
	 */
	boolean numberIsLegal (int row, int col, int num) {
		if (isLegalRow(row, col, num)&&isLegalCol(col, row, num)&&isLegalBox(row,col,num)) {
			return true;
		} else if (num == 0) {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * Prints the board onto the console
	 * Used for testing
	 */
	public void printBoard() {
		for (int row=8; row>=0;row--){
			for (int col=0; col<9;col++){
				System.out.print(boardArray[col][row]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}

	/**
	 * Saves the state of the board into a file called save.txt
	 * @param difficulty
	 * @param time
	 * @param hints
	 * @param editableArray
	 * @throws IOException
	 */
	public void saveState(int difficulty, int time, int hints,
			boolean[][] editableArray) throws IOException {
		File save = new File("save.txt");
		save.createNewFile();
		FileWriter writer = new FileWriter(save);
		Integer timeInteger = (Integer)time;
		writer.write(timeInteger.toString());
		writer.write("\n");
		Integer difficultyInteger = (Integer)difficulty;
		writer.write(difficultyInteger.toString());
		writer.write("\n");
		Integer hintsInteger = (Integer)hints;
		writer.write(hintsInteger.toString());
		writer.write("\n");
		for (int row = 8; row >= 0; row--) {
			for (int col = 0; col < 9; col++) {
				Integer currentNum = new Integer(boardArray[col][row]);
				String output = currentNum.toString();
				writer.write(output);
				writer.write(" ");
			}
			writer.write("\n");
		}
		for (int row = 8; row >= 0; row--) {
			for (int col = 0; col < 9; col++) {
				String output;
				boolean editable = editableArray[col][row];
				if (editable) {
					output = "1";
				} else {
					output = "0";
				}		
				writer.write(output);
				writer.write(" ");
			}
			writer.write("\n");
		}
		writer.close();
	}
}
