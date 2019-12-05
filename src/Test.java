import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JComboBox;

public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setTitle("Leko");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][]", "[][][][][][][][][][][][][grow]"));
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblPosition, "cell 0 0 4 1");
		
		JLabel lblX = new JLabel("X");
		contentPane.add(lblX, "cell 0 1");
		
		JSpinner posX = new JSpinner();
		contentPane.add(posX, "cell 1 1");
		
		JLabel lblY = new JLabel("Y");
		contentPane.add(lblY, "cell 3 1");
		
		JSpinner posY = new JSpinner();
		contentPane.add(posY, "cell 4 1");
		
		JLabel lblZ = new JLabel("Z");
		contentPane.add(lblZ, "cell 6 1");
		
		JSpinner posZ = new JSpinner();
		contentPane.add(posZ, "cell 7 1");
		
		JSeparator separator = new JSeparator();
		contentPane.add(separator, "cell 0 2 8 1");
		
		JLabel lblRotation = new JLabel("Rotation");
		lblRotation.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblRotation, "cell 0 3 4 1");
		
		JLabel lblX_1 = new JLabel("X");
		contentPane.add(lblX_1, "cell 0 4");
		
		JSpinner rotX = new JSpinner();
		contentPane.add(rotX, "cell 1 4");
		
		JLabel lblY_1 = new JLabel("Y");
		contentPane.add(lblY_1, "cell 3 4");
		
		JSpinner rotY = new JSpinner();
		contentPane.add(rotY, "cell 4 4");
		
		JLabel lblZ_1 = new JLabel("Z");
		contentPane.add(lblZ_1, "cell 6 4");
		
		JSpinner rotZ = new JSpinner();
		contentPane.add(rotZ, "cell 7 4");
		
		JLabel lblArtnet = new JLabel("Art-Net");
		lblArtnet.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblArtnet, "cell 0 5 4 1");
		
		JLabel lblUniverse = new JLabel("Universe");
		contentPane.add(lblUniverse, "cell 0 6 4 1");
		
		JSpinner universe = new JSpinner();
		contentPane.add(universe, "cell 4 6");
		
		JLabel lblAddress = new JLabel("Address");
		contentPane.add(lblAddress, "cell 0 7 4 1");
		
		JSpinner address = new JSpinner();
		contentPane.add(address, "cell 4 7");
		
		JLabel lblFocus = new JLabel("Focus");
		lblFocus.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblFocus, "cell 0 8 4 1");
		
		JLabel lblDegree = new JLabel("Degree");
		contentPane.add(lblDegree, "cell 0 9 4 1");
		
		JSpinner deg = new JSpinner();
		contentPane.add(deg, "cell 4 9");
		
		JLabel lblSharpness = new JLabel("Sharpness");
		contentPane.add(lblSharpness, "cell 0 10 4 1");
		
		JSlider sharp = new JSlider();
		sharp.setMaximum(1);
		contentPane.add(sharp, "cell 4 10 4 1");
		
		JLabel lblGel = new JLabel("Gel");
		contentPane.add(lblGel, "cell 0 11 4 1");
		
		JComboBox gelSelect = new JComboBox(Gel.getAllGels());
		contentPane.add(gelSelect, "cell 4 11 4 1,growx");
	}

}
