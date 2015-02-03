import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ContinueFileParser {
	File f;
	private int difficulty;
	private int time;
	private int[][] boardArray;
	private int[][] editableArray;
	private int hints;
	
	ContinueFileParser (File f) {
		this.f = f;
		boardArray = new int[9][9];
		editableArray = new int[9][9];
	}
	
	/**
	 * Parses the save file
	 * @throws FileNotFoundException
	 */
	void Parse () throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader("save.txt"));
		time = in.nextInt();
		difficulty = in.nextInt();
		hints = in.nextInt();
		for (int row = 8; row >=0; row--) {
			for (int col = 0; col < 9; col++) {
				boardArray[col][row] = in.nextInt();
			}
		}
		for (int row = 8; row >=0; row--) {
			for (int col = 0; col < 9; col++) {
				editableArray[col][row] = in.nextInt();
			}
		}
		in.close();
	}
	
	/**
	 * getter for difficulty parsed from file
	 * @return difficulty of save
	 */
	int getDifficulty () {
		return difficulty;
	}
	
	/**
	 * getter for time spent in save
	 * @return time spent in save
	 */
	int getTime () {
		return time;
	}
	
	/**
	 * Getter for the saved board
	 * @return board
	 */
	int[][] getBoardArray () {
		return boardArray;
	}
	/**
	 * Getter for an array which determines which cells are editable
	 * @return an array of 1s and 0s
	 */
	int[][] getEditableArray() {
		return editableArray;
	}
	/**
	 * Getter for how many hints the user has left
	 * @return
	 */
	int getHints() {
		return hints;
	}
}
