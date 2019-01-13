package roarbotics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

public class Field extends JComponent {

	Image field;
	Toolkit t;

	AffineTransform a;

	boolean isRed = false;
	
	public boolean isRed() {
		return isRed;
	}

	public Field() {
		setSize(500,700);
		t = Toolkit.getDefaultToolkit();
		field = t.getImage("img/2019-field.png");
		field = field.getScaledInstance(500, 700, Image.SCALE_SMOOTH);
		a = AffineTransform.getRotateInstance(Math.toRadians(180), 250, 350);
	}

	public void paint(Graphics g) {
		super.paint(g);
		System.out.println("drawing frame");
		
		Graphics2D g2 = (Graphics2D) g;

		AffineTransform back = g2.getTransform();
		if (isRed) {
			g2.setTransform(a);
		}
		g2.drawImage(field, 0, 0, this);
		g2.setTransform(back);
		
	}

}
