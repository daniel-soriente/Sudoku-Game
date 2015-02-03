import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Class used to represent a cell on the board
 * acts as a way to display the number
 * Stores where in the board it is
 * @author Daniel
 *
 */
public class Cell extends JLabel {
  
    private static final long serialVersionUID = 1L;
	private boolean editable;
	private final int col;
	private final int row;

	public Cell(int col, int row) {
		this.editable = true;
		this.col = col;
		this.row = row;
		setHorizontalAlignment(JTextField.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		setPreferredSize(new Dimension(40, 40));
		setFont(new Font("Arial", Font.BOLD, 18));
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setOpaque(true);
	}

	/**
	 * Set if the cell can be changed
	 * If it cant be changed, set the colour to gray
	 * @param status
	 */
	public void setEditable(boolean status) {
		editable = status;
		if(!status) {
			setForeground(Color.GRAY);
		}
	}

	/**
	 * 
	 * @return if editabl or not
	 */
	public boolean getEditable() {
		return editable;
	}

	/**
	 * Get the column number the cell is in
	 * @return
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Get the row number the cell is in
	 * @return
	 */
	public int getRow() {
		return row;
	}
}
