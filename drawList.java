import java.awt.*;

import javax.swing.*;

public class drawList extends JPanel {

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Arial", Font.PLAIN, 18));
        String t = new HighList().getMsg();
        int x = 30;
        int y = 30;
        for( String line : t.split("\n")) {
            g2.drawString(line,x,y);
            y += 30;
        }
    }
    
}
