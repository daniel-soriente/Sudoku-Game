import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * GUI class to display the solution of the board on
 * a different window
 * It will just display all 81 filled cells with the correct solution
 *
 */
public class SolutionGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private BoardGUI solutionBoard;
	private SudokuBoard solution;

	public SolutionGUI(SudokuBoard solution) {
		JPanel panel = new JPanel();
		this.solution = solution;
		solutionBoard = new BoardGUI(solution);
		panel.add(solutionBoard);
		add(panel);	
		
		//Set JFrame Properties
		setLayout(new FlowLayout(FlowLayout.CENTER));
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
		setTitle("Solution");
		pack();
		setSize(400, 425);
		setResizable(true);
		setVisible(true);
	}

	/**
	 * A method used to populate the board with the correct
	 * solution and display them on the window
	 */
	public void populateBoard () {
		for (int col = 0; col<9; col++) {
			for (int row = 0; row<9; row++){
				//want to out
				String number = Integer.toString(solution.getNumber(col, row));
				solutionBoard.board[col][row].setText(number);
			}
		}
	}
}
