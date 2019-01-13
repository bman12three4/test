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

	private double x = 175;
	private double y = 605;
	private double angle = 0;
	private double ax = 0;
	private double ay = 0;

	private double scaleX = 1;
	private double scaleY = 1;

	private double leftEnc;
	private double rightEnc;

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
		g2.drawImage(dozer, (int) (x * scaleX), (int) (y * scaleY), this);
	}

}
