package roarbotics;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.Timer;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class RobotPosition {

	private JFrame frame;

	NetworkTableEntry xMXPEntry;
	NetworkTableEntry yMXPEntry;
	NetworkTableEntry angleMXPEntry;

	NetworkTableEntry xGyroEntry;
	NetworkTableEntry yGyroEntry;
	NetworkTableEntry angleGyroEntry;

	NetworkTableEntry xRioEntry;
	NetworkTableEntry yRioEntry;

	NetworkTableEntry leftEncoderEntry;
	NetworkTableEntry rightEncoderEntry;

	FieldPanel panel;

	Timer t;

	JComboBox<Position> startingPos;

	Robot robot;

	class Position {

		String name;
		double x;

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public double getAngle() {
			return angle;
		}

		double y;
		double angle;

		public Position(String name, double x, double y, double angle) {
			this.name = name;
			this.x = x;
			this.y = y;
			this.angle = angle;
		}

		public String toString() {
			return name;
		}
	}

	double startX = 0;
	double startY = 0;
	double startAngle = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RobotPosition window = new RobotPosition();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RobotPosition() {
		initialize();
		frame.setVisible(true);
		t.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/**
		 * Create the main window with an absolute layout.
		 */
		frame = new JFrame();
		frame.setSize(400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		panel = new FieldPanel();
		panel.setBounds(0, 0, 400, 600);
		panel.setBackground(Color.BLUE);
		frame.getContentPane().add(panel);
		frame.repaint();

		startingPos = new JComboBox<>();
		startingPos.addItem(new Position("Left", 100, 500, 0));
		startingPos.addItem(new Position("Center", 200, 500, 0));
		startingPos.addItem(new Position("Right", 300, 500, 0));
		panel.add(startingPos);

		robot = new Robot();
		panel.add(robot);

		System.out.println("added panel");

		t = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					System.out.println("interrupted");
					return;
				}

				double xMXP = xMXPEntry.getDouble(startX);
				double yMXP = yMXPEntry.getDouble(startY);
				double angleMXP = angleMXPEntry.getDouble(startAngle);

				double xGyro = xGyroEntry.getDouble(0);
				double yGyro = yGyroEntry.getDouble(0);
				double angleGyro = angleGyroEntry.getDouble(0);

				double xRio = xRioEntry.getDouble(0);
				double yRio = yRioEntry.getDouble(0);

				double leftEncoder = leftEncoderEntry.getDouble(0);
				double rightEncoder = rightEncoderEntry.getDouble(0);

				robot.setAx((xGyro + xRio) / 2);
				robot.setAy((yGyro + yRio) / 2);

				robot.setAngle((angleMXP + angleGyro) / 2);

				robot.setX(xMXP);
				robot.setY(yMXP);

				robot.setLeftEnc(leftEncoder);
				robot.setRightEnc(rightEncoder);

			}
		});

		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		NetworkTable table = inst.getTable("datatable");

		yMXPEntry = table.getEntry("xMXP");
		yMXPEntry = table.getEntry("yMXP");
		angleMXPEntry = table.getEntry("angleMXP");
		xGyroEntry = table.getEntry("xGyro");
		yGyroEntry = table.getEntry("yGyro");
		xRioEntry = table.getEntry("xRio");
		yRioEntry = table.getEntry("yRio");
		angleGyroEntry = table.getEntry("angleGyro");
		leftEncoderEntry = table.getEntry("leftEnc");
		rightEncoderEntry = table.getEntry("rightEnc");

		inst.startClientTeam(5482);
		inst.startDSClient();

	}

}
