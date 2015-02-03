import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * A GUI class to display the settings of the game
 * Displays the chosen difficulty of the sudoku
 * Default difficulty is 1
 *
 */
public class SettingGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    protected int difficulty;
    //Used to store the radiobutton selected to change
    //the difficulty of the board
    private JRadioButton selected;
    
    public SettingGUI() {

    	this.difficulty = 1;
    	
    	JPanel menu = createSettings();
    	menu.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    	setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("ui/b2.jpg")));	
		setLayout(new FlowLayout(FlowLayout.CENTER));
    	add(menu, BorderLayout.CENTER);
    	
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
		
		setTitle("Settings");
		pack();
		setSize(300, 125);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width / 2 - 500), (d.height / 2 - 210));
		setResizable(true);
		setVisible(true);
    }

    /**
     * 
     * @return a panel that holds all the settings
     * Can alter the difficulty of the board, from 1-4
     */
    public JPanel createSettings() {
    	JPanel settings = new JPanel();
    	settings.setLayout(new GridBagLayout());
    	settings.setBackground(new Color(255, 231, 186));
    	GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
    	
    	JLabel set = new JLabel("Settings");
    	set.setFont(new Font("Arial", Font.BOLD, 25));
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.gridwidth = 2;
    	settings.add(set, gbc);
    	
    	JLabel diff = new JLabel("Difficulties");
    	diff.setFont(new Font("Arial", Font.PLAIN, 15));
    	gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
    	settings.add(diff, gbc);

    	JRadioButton one = new JRadioButton("1");
    	one.setMnemonic(KeyEvent.VK_C);
    	one.setBackground(new Color(255, 231, 186));
    	selected = one;
    	selected.setSelected(true);
    	one.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                difficulty = 1;
                if(selected != null) {
                	selected.setSelected(false);
                }
                selected = (JRadioButton) (event.getSource());
                selected.setSelected(true);
            }
        });
    	gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
    	settings.add(one, gbc);

    	JRadioButton two = new JRadioButton("2");
    	two.setMnemonic(KeyEvent.VK_C);
    	two.setBackground(new Color(255, 231, 186));
    	two.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                difficulty = 2;
                if(selected != null) {
                	selected.setSelected(false);
                }
                selected = (JRadioButton) (event.getSource());
                selected.setSelected(true);
            }
        });
    	gbc.weightx = 0.5;
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
    	settings.add(two, gbc);

    	JRadioButton three = new JRadioButton("3");
    	three.setMnemonic(KeyEvent.VK_C);
    	three.setBackground(new Color(255, 231, 186));
    	three.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                difficulty = 3;
                if(selected != null) {
                	selected.setSelected(false);
                }
                selected = (JRadioButton) (event.getSource());
                selected.setSelected(true);
            }
        });
    	gbc.weightx = 0.5;
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
    	settings.add(three, gbc);

    	JRadioButton four = new JRadioButton("4");
    	four.setMnemonic(KeyEvent.VK_C);
    	four.setBackground(new Color(255, 231, 186));
    	four.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                difficulty = 4;
                if(selected != null) {
                	selected.setSelected(false);
                }
                selected = (JRadioButton) (event.getSource());
                selected.setSelected(true);
            }
        });
    	gbc.weightx = 0.5;
        gbc.gridx = 8;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
    	settings.add(four, gbc);
    	
    	return settings;
    	
    }
}
