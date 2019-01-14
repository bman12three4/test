package roarbotics;

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

	static NetworkTableEntry gameSpecificMessage;
	static NetworkTableEntry eventName;
	static NetworkTableEntry matchNumber;
	static NetworkTableEntry replayNumber;
	static NetworkTableEntry matchType;
	static NetworkTableEntry alliance;
	static NetworkTableEntry station;
	static NetworkTableEntry controlWord;

	RobotPosition window;

	public static Field field;

	Timer t;

	JComboBox<Position> startingPos;

	Robot robot;

	public static NetworkTableEntry getGameSpecificMessage() {
		return gameSpecificMessage;
	}

	public static NetworkTableEntry getEventName() {
		return eventName;
	}

	public static NetworkTableEntry getMatchNumber() {
		return matchNumber;
	}

	public static NetworkTableEntry getReplayNumber() {
		return replayNumber;
	}

	public static NetworkTableEntry getMatchType() {
		return matchType;
	}

	public static NetworkTableEntry getAlliance() {
		return alliance;
	}

	public static NetworkTableEntry getStation() {
		return station;
	}

	public static NetworkTableEntry getControlWord() {
		return controlWord;
	}

	/**
	 * The Position class stores an x and y coordinate and an angle. Used for
	 * storing positions of the Robot.
	 * 
	 * @author byron.lathi
	 *
	 */
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

	double angle = 0;

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
		frame.repaint();
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
		frame.setSize(500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		/**
		 * Create the new field object and set the size to the same as the frame.
		 */
		field = new Field();
		field.setSize(frame.getWidth(), frame.getHeight());

		/**
		 * create a new robot object which is also the size of the frame for some reason
		 */
		robot = new Robot();
		robot.setSize(field.getWidth(), field.getHeight());

		/**
		 * Create the list of possible starting positions for the robot. Make sure not
		 * to change this while the match is ongoing because it will move the robot back
		 * to the starting line. Maybe something to fix in the future.
		 */
		startingPos = new JComboBox<>();
		startingPos.addItem(new Position("Forward Left", 175, 605, 0));
		startingPos.addItem(new Position("Forward Center", 235, 605, 0));
		startingPos.addItem(new Position("Forward Right", 295, 605, 0));
		startingPos.addItem(new Position("Rear Left", 175, 645, 0));
		startingPos.addItem(new Position("Rear Center", 235, 645, 0));
		startingPos.addItem(new Position("Rear Right", 295, 645, 0));
		startingPos.setBounds(200, 5, 100, 25);

		/**
		 * Action listener for the combobox, basically just send the position to the
		 * robot.
		 */
		startingPos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double x = ((Position) startingPos.getSelectedItem()).x;
				double y = ((Position) startingPos.getSelectedItem()).y;
				double angle = ((Position) startingPos.getSelectedItem()).angle;

				robot.setPos(x, y, angle);

				frame.repaint();
				robot.repaint();
			}
		});

		// Testing. This is used to set the default value for the alliance. If a
		// connection the the FMS is made then this does not matter.
		field.isRed = false;

		/**
		 * Add all objects to the frame.
		 */
		frame.add(robot);
		frame.add(startingPos);
		frame.add(field);
		frame.repaint();

		// robot = new Robot();
		// panel.add(robot);

		/**
		 * main loop. This runs at 20Hz, the same as NetworkTables. It is possible to go
		 * faster but it hasn't been done yet. Inside the main loop, all the values are
		 * gotten from the NetworkTables and sent to the robot object to tell it where
		 * to go.
		 */
		t = new Timer(5, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * double xMXP = xMXPEntry.getDouble(startX); double yMXP =
				 * yMXPEntry.getDouble(startY); double angleMXP =
				 * angleMXPEntry.getDouble(startAngle);
				 * 
				 * double xGyro = xGyroEntry.getDouble(0); double yGyro =
				 * yGyroEntry.getDouble(0); double angleGyro = angleGyroEntry.getDouble(0);
				 * 
				 * double xRio = xRioEntry.getDouble(0); double yRio = yRioEntry.getDouble(0);
				 * 
				 * double leftEncoder = leftEncoderEntry.getDouble(0); double rightEncoder =
				 * rightEncoderEntry.getDouble(0);
				 */

//				robot.setAngle((angleMXP + angleGyro) / 2);

//				robot.setX(xMXP);
//				robot.setY(yMXP);

//				robot.setLeftEnc(leftEncoder);
//				robot.setRightEnc(rightEncoder);

				// robot.setAngle(angle++);

				field.repaint();
				robot.repaint();

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

		NetworkTable fms = inst.getTable("FMSInfo");

		gameSpecificMessage = fms.getEntry("GameSpecificMessage");
		eventName = fms.getEntry("EvenName");
		matchNumber = fms.getEntry("MatchNumber");
		matchType = fms.getEntry("MatchType");
		alliance = fms.getEntry("IsRedAlliance");
		station = fms.getEntry("StationNumber");
		controlWord = fms.getEntry("FMSControlData");

		inst.startClientTeam(5482);
		inst.startDSClient();

	}

}
