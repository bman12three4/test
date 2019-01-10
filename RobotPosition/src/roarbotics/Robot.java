package roarbotics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Robot extends JComponent {

	private double x = 100;
	private double y = 500;
	private double angle = 0;
	private double ax = 0;
	private double ay = 0;

	private double leftEnc;
	private double rightEnc;

	public Robot() {
		setSize(10, 10);
	}

	public void setPos(double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	public void setLeftEnc(double leftEnc) {
		this.leftEnc = leftEnc;
	}

	public void setRightEnc(double rightEnc) {
		this.rightEnc = rightEnc;
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

	public void setAx(double ax) {
		this.ax = ax;
	}

	public void setAy(double ay) {
		this.ay = ay;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		
		g2.setColor(Color.RED);
		g2.fillOval((int) x, (int) y, 20, 20);
		g2.drawLine((int) x, (int) y, (int) (x + ax), (int) (y + ay));
	}

}
