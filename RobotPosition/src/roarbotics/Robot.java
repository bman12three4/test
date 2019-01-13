package roarbotics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

public class Robot extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8495836591046026367L;
	Image dozer;
	Toolkit t;

	private double x = 175;
	private double y = 605;
	private double angle = 0;

	private double scaleX = 1;
	private double scaleY = 1;

	public Robot() {
		setSize(30, 30);
		t = Toolkit.getDefaultToolkit();
		dozer = t.getImage("img/dozer.png");
		dozer = dozer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	}

	public void setPos(double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getXPos() {
		return x;
	}

	public double getYPos() {
		return y;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLUE);
		if (RobotPosition.getAlliance().getBoolean(false))
			g2.setColor(Color.RED);
		g2.fillOval((int) (x * scaleX - 5), (int) (y * scaleY - 5), 40, 40);
		AffineTransform back = g2.getTransform();
		g2.setTransform(
				AffineTransform.getRotateInstance(Math.toRadians(angle), (int) (x * scaleX + 15), (int) (y * scaleY + 15)));
		g2.drawImage(dozer, (int) (x * scaleX), (int) (y * scaleY), this);
		g2.setTransform(back);
	}

}
