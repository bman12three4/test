package roarbotics;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class FieldPanel extends JPanel {
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		System.out.println("am getting run");
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawOval(10, 10, 10, 10);
	}

}
