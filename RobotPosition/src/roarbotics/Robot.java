package roarbotics;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Robot extends JComponent {

	double x = 0;
	double y = 0;
	double angle = 0;
	double ax = 0;
	double ay = 0;

	public Robot() {

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

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.drawOval((int) x, (int) y, 10, 10);
	}

}
