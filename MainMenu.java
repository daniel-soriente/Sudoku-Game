import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI class for the main menu. Contains buttons to the other
 * GUI's
 * New Game: Creates a sudoku board
 * Continue: Continues from the last saved board
 * Settings: Settings of the game
 * HighScores: Display high scores
 * Link for background : http://www.facebook.com/l.php?u=http%3A%2F%2Fwhdbg.com%2Fbackground-wallpaper-photoshop.html&h=-AQHjmEIH
 */
public class MainMenu extends JFrame{  

	private static final long serialVersionUID = 1L;
	
	//Contains a main menu so when we go back to the main menu
	//the settings are still the same
	private MainMenu main;
	private SettingGUI settings;
	private HighGUI highscores;

    public MainMenu() {
    	
    	this.main = this;
		setContentPane(new JLabel(new ImageIcon("ui/b2.jpg")));	
		setLayout(new FlowLayout(FlowLayout.CENTER));
    	JPanel menu = Menu();
    	add(menu, BorderLayout.CENTER);

    	//Set JFrame Properties
    	setTitle("Interactive Sudoku");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
        setSize(460,400);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width / 2 - 230), (d.height / 2 - 230));
		setResizable(true);
		setVisible(true);

    }
    
    /**
     * 
     * @return a panel that contains all the main menu aspects
     * - Image
     * - Buttons
     * New Game: Creates a new Sudoku Game
     * Continue: Continues from last sudoku
     * Settings: Settings of game
     * High Scores: View past high scores
     */
    public JPanel Menu() {
    	JPanel buttons = new JPanel();
    	buttons.setLayout(new GridBagLayout());
    	buttons.setBackground(new Color(255, 231, 186));
    	GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        
        ImageIcon titleImg = new ImageIcon("ui/title.png");
        JLabel title = new JLabel(titleImg);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        buttons.add(title,gbc);

        JButton newGame = new JButton("New Game");
        newGame.setFont(new Font("Arial", Font.BOLD, 17));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        newGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				if(settings == null) {
					new SudokuGUI(1, main);
				} else {
					new SudokuGUI(settings.difficulty, main);
					settings.setVisible(false);
					if(highscores != null) {
						highscores.setVisible(false);
					}
				}
				setVisible(false);
		    }
		});
        buttons.add(newGame,gbc);
        
        JButton continueGame = new JButton("Continue Game");
        continueGame.setFont(new Font("Arial", Font.BOLD, 17));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        continueGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				File f = new File("save.txt");
				if(f.exists()) {
					System.out.println("Continue");
					ContinueFileParser c = new ContinueFileParser(f);
					try {
						c.Parse();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					new SudokuGUI(c.getBoardArray(), c.getDifficulty(), c.getEditableArray(), main, c.getTime());
					dispose();
				}
		    }
		});
        buttons.add(continueGame,gbc);

        JButton setting = new JButton("Settings");
        setting.setFont(new Font("Arial", Font.BOLD, 17));
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setting.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				if (settings!=null) {
					settings.setVisible(true);
				} else { 
					settings = new SettingGUI();
				}

		    }
		});
        buttons.add(setting,gbc);

        JButton hiscore = new JButton("High Score");
        hiscore.setFont(new Font("Arial", Font.BOLD, 17));
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        hiscore.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				if (highscores!=null) {
					highscores.setVisible(true);
				} else {
					highscores = new HighGUI();
				}

		    }
		});
        buttons.add(hiscore,gbc);
        
    	return buttons;
    }
}  
