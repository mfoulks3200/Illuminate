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
import javax.swing.JEditorPane;
import javax.swing.JCheckBox;

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
		setBounds(100, 100, 300, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[:25px:25px][25px][25px][25px][25px][25px][25px][:25px:25px][:25px:25px]", "[][][][][][][][][][][][][][][][][grow]"));
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblPosition, "cell 0 0 5 1");
		
		JLabel lblX = new JLabel("X");
		contentPane.add(lblX, "cell 0 1,alignx right");
		
		JSpinner posX = new JSpinner();
		contentPane.add(posX, "cell 1 1 2 1,growx");
		
		JLabel lblY = new JLabel("Y");
		contentPane.add(lblY, "cell 3 1,alignx right");
		
		JSpinner posY = new JSpinner();
		contentPane.add(posY, "cell 4 1 2 1,growx");
		
		JLabel lblZ = new JLabel("Z");
		contentPane.add(lblZ, "cell 6 1,alignx right");
		
		JSpinner posZ = new JSpinner();
		contentPane.add(posZ, "cell 7 1 2 1,growx");
		
		JSeparator separator = new JSeparator();
		contentPane.add(separator, "cell 0 2 9 1");
		
		JLabel lblRotation = new JLabel("Rotation");
		lblRotation.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblRotation, "cell 0 3 5 1");
		
		JLabel lblX_1 = new JLabel("X");
		contentPane.add(lblX_1, "cell 0 4,alignx right");
		
		JSpinner rotX = new JSpinner();
		contentPane.add(rotX, "cell 1 4 2 1,growx");
		
		JLabel lblY_1 = new JLabel("Y");
		contentPane.add(lblY_1, "cell 3 4,alignx right");
		
		JSpinner rotY = new JSpinner();
		contentPane.add(rotY, "cell 4 4 2 1,growx");
		
		JLabel lblZ_1 = new JLabel("Z");
		contentPane.add(lblZ_1, "cell 6 4,alignx right");
		
		JSpinner rotZ = new JSpinner();
		contentPane.add(rotZ, "cell 7 4 2 1,growx");
		
		JLabel lblArtnet = new JLabel("Art-Net");
		lblArtnet.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblArtnet, "cell 0 5 5 1");
		
		JLabel lblUniverse = new JLabel("Universe");
		contentPane.add(lblUniverse, "cell 0 6 4 1");
		
		JSpinner universe = new JSpinner();
		contentPane.add(universe, "cell 4 6 2 1,growx");
		
		JLabel lblAddress = new JLabel("Address");
		contentPane.add(lblAddress, "cell 0 7 4 1");
		
		JSpinner address = new JSpinner();
		contentPane.add(address, "cell 4 7 2 1,growx");
		
		JLabel lblFocus = new JLabel("Focus");
		lblFocus.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblFocus, "cell 0 8 4 1");
		
		JLabel lblDegree = new JLabel("Degree");
		contentPane.add(lblDegree, "cell 0 9 4 1");
		
		JSpinner deg = new JSpinner();
		contentPane.add(deg, "cell 4 9 2 1,alignx right");
		
		JLabel lblSharpness = new JLabel("Sharpness");
		contentPane.add(lblSharpness, "cell 0 10 4 1");
		
		JSlider sharp = new JSlider();
		sharp.setMaximum(1);
		contentPane.add(sharp, "cell 4 10 5 1");
		
		JLabel lblGel = new JLabel("Gel");
		contentPane.add(lblGel, "cell 0 11 4 1");
		
		JComboBox gelSelect = new JComboBox(Gel.getAllGels());
		contentPane.add(gelSelect, "cell 4 11 5 1,growx");
		
		JLabel lblEmitter = new JLabel("Emitter");
		contentPane.add(lblEmitter, "cell 0 12");
		
		JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, "cell 4 12 5 1,growx");
		
		JLabel lblOverrideArtnet = new JLabel("Override Art-Net");
		contentPane.add(lblOverrideArtnet, "cell 0 13");
		
		JCheckBox checkBox = new JCheckBox("");
		contentPane.add(checkBox, "cell 4 13");
		
		JLabel lblFixtureValue = new JLabel("Override Value");
		contentPane.add(lblFixtureValue, "cell 0 14");
		
		JSlider slider = new JSlider();
		contentPane.add(slider, "cell 4 14 5 1");
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblNotes, "cell 0 15");
		
		JEditorPane editorPane = new JEditorPane();
		contentPane.add(editorPane, "cell 0 16 9 1,grow");
	}

}
