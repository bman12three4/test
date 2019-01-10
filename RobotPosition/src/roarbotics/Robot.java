package roarbotics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

public class Robot extends JComponent {

	Image dozer;
	Toolkit t;

	private double x = 100;
	private double y = 500;
	private double angle = 0;
	private double ax = 0;
	private double ay = 0;

	private double leftEnc;
	private double rightEnc;

	public Robot() {
		setSize(10, 10);
		t = Toolkit.getDefaultToolkit();
		dozer = t.getImage("img/dozer.png");
		dozer = dozer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
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
		//g2.fillOval((int) x, (int) y, 20, 20);
		g2.drawImage(dozer, (int) x, (int) y, this);
		g2.drawLine((int) x + 10, (int) y + 10, (int) (x + ax + 10), (int) (y + ay + 10));
	}

}
