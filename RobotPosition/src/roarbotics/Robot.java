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

	AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(angle), (int) (x * scaleX + 15),
			(int) (y * scaleY + 15));;

	/**
	 * The Robot object is a visual representation of the robot on the field. The
	 * Robot class displays a picture of a robot (RIP dozer) which moves and rotates
	 * according to the data received from the robot on the field.
	 */
	public Robot() {
		setSize(30, 30);
		t = Toolkit.getDefaultToolkit();
		dozer = t.getImage("src/img/dozer.png");
		dozer = dozer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		flip_dozer = t.getImage("src/img/flip_dozer.png");
		flip_dozer = flip_dozer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		setAngle(angle);
	}

	/**
	 * Set the position of the Robot on the field. This does not set the position of
	 * the robot on the screen, instead these coordinates are scaled place the robot
	 * in the correct location on the field.
	 * 
	 * @param x     New X coordinate of the Robot
	 * @param y     New Y coordinate of the Robot.
	 * @param angle Angle in degrees the Robot is facing.
	 */
	public void setPos(double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	/**
	 * Set the x position of the Robot on the field. This does not set the position
	 * the the robot on the screen, instead this coordinate is scaled to place the
	 * robot in the correct location on the field.
	 * 
	 * @param x New X coordinate of Robot.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Set the y position of the Robot on the field. This does not set the position
	 * the the robot on the screen, instead this coordinate is scaled to place the
	 * robot in the correct location on the field.
	 * 
	 * @param y New Y coordinate of Robot.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Set the angle in degrees that the robot is facing. This value can exceed 360.
	 * 
	 * @param angle Angle the Robot is facing.
	 */
	public void setAngle(double angle) {
		this.angle = angle;
		rotateImage();
	}

	/**
	 * Rotate the image according to the angle given. If the angle is more than 90
	 * or less than 270, the image is changed the same picture but upside down, so
	 * dozer is mirrored instead of being upside down. He is still dead though, and
	 * it is our duty to keep him alive in our hearts and in our minds for years to
	 * come.
	 */
	private void rotateImage() {
		double cappedAngle = angle % 360;
		if (cappedAngle > 90 && cappedAngle < 270) {
			print_dozer = flip_dozer;
		} else {
			print_dozer = dozer;
		}
		rotate = AffineTransform.getRotateInstance(Math.toRadians(angle), (int) (x * scaleX + 15),
				(int) (y * scaleY + 15));
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
