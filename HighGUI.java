import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * GUI showing high scores.
 */
public class HighGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public HighGUI() {
    	
        drawList dl = new drawList();
        add(dl,BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
		
		setTitle("High Scores");
		pack();
		setSize(400, 500);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width - 500), (d.height / 2 - 210));
		setResizable(true);
		setVisible(true);
    }
}
