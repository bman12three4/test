package roarbotics;
import java.awt.EventQueue;

import javax.swing.JFrame;

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

	NetworkTableEntry leftEncoderEntry;
	NetworkTableEntry rightEncoderEntry;

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
		while (true) {
			run();
		}
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
		frame.setVisible(true);

		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		NetworkTable table = inst.getTable("datatable");
		
		yMXPEntry = table.getEntry("xMXP");
		yMXPEntry = table.getEntry("yMXP");
		angleMXPEntry = table.getEntry("angleMXP");
		xGyroEntry = table.getEntry("xGyro");
		yGyroEntry = table.getEntry("yGyro");
		angleGyroEntry = table.getEntry("angleGyro");
		leftEncoderEntry = table.getEntry("leftEnc");
		rightEncoderEntry = table.getEntry("rightEnc");
		
		inst.startClientTeam(5482);
		inst.startDSClient();

	}

	private void run() {
		try {
	        Thread.sleep(1000);
	      } catch (InterruptedException ex) {
	        System.out.println("interrupted");
	        return;
	      }
	     
		
	}
	

}
