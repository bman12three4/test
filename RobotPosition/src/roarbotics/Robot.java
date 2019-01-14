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
	Image flip_dozer;
	Image print_dozer = dozer;
	Toolkit t;

	private double x = 175;
	private double y = 605;
	private double angle = 0;

	private double scaleX = 1;
	private double scaleY = 1;

	AffineTransform rotate  = AffineTransform.getRotateInstance(Math.toRadians(angle), (int) (x * scaleX + 15), (int) ( y*scaleY + 15));;

	public Robot() {
		setSize(30, 30);
		t = Toolkit.getDefaultToolkit();
		dozer = t.getImage("img/dozer.png");
		dozer = dozer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		flip_dozer = t.getImage("img/flip_dozer.png");
		flip_dozer = flip_dozer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		setAngle(angle);
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
		rotateImage();
	}

	public double getXPos() {
		return x;
	}

	public double getYPos() {
		return y;
	}

	private void rotateImage() {
		System.out.println("Running rotateImage");
		double cappedAngle = angle % 360;
		if (cappedAngle > 90 && cappedAngle < 270) {
			print_dozer = flip_dozer;
			System.out.println("using flip_dozer");
		} else {
			System.out.println("using regular dozer");
			print_dozer = dozer;
		}
		rotate = AffineTransform.getRotateInstance(Math.toRadians(angle), (int) (x * scaleX + 15), (int) ( y*scaleY + 15));
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLUE);
		if (RobotPosition.getAlliance().getBoolean(false))
			g2.setColor(Color.RED);
		g2.fillOval((int) (x * scaleX - 5), (int) (y * scaleY - 5), 40, 40);
		AffineTransform back = g2.getTransform();
		g2.setTransform(rotate);
		g2.drawImage(print_dozer, (int) (x * scaleX), (int) (y * scaleY), this);
		g2.setTransform(back);
	}

}
