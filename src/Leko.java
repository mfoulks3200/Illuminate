import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.LightControl;
import com.jme3.scene.shape.Box;

import net.miginfocom.swing.MigLayout;

public class Leko extends Fixture{

	public static int footprint;
	public static Attributes[] personality = {Attributes.Intensity};
	public Vector3f position;
	public Vector3f rotation;
	public float sharpness = 0.95f;
	public int degrees = 32;
	public Gel gel;
	private boolean overrideEnable = false;
	private float overrideValue = 0.0f;
	
	public Leko() {
		super();
		position = new Vector3f(0,2,2);
		rotation = new Vector3f(0,0,0);
		gel = Gel.getGel(Manufacturer.Blackbody, 3000);
	}
	
	@Override
	public void update() {
		if(fixtureGeometry != null) {
			Main.app.enqueue(new Callable<Integer>() {
		        public Integer call() throws Exception {
			        fixtureGeometry.setLocalTranslation(position);
			        ColorRGBA col = new ColorRGBA(((float)gel.red)/255f,((float)gel.green)/255f,((float)gel.blue)/255f,1f);
			    if(!overrideEnable) {
			    		spot.setColor(col.mult((((float)getLocalPercent(1)))/100f));  
		        }else {
		        		spot.setColor(col.mult((((float)overrideValue))/100f));  
		        }
		        	spot.setSpotInnerAngle(degrees * sharpness * FastMath.DEG_TO_RAD); // inner light cone (central beam)
			    spot.setSpotOuterAngle(degrees * FastMath.DEG_TO_RAD); // outer light cone (edge of the light)
			    Quaternion pan = new Quaternion(0,0,0,0);
			    pan.fromAngleAxis(FastMath.PI * rotation.x / 180f, Vector3f.UNIT_Y);
			    Quaternion tilt = new Quaternion(0,0,0,0);
			    tilt.fromAngleAxis(FastMath.PI * rotation.y / 180f, Vector3f.UNIT_X);
			    fixtureGeometry.setLocalRotation(pan.mult(tilt));
		        	return 0;
		        }
		    });
		}
	}
	
	@Override
	public Geometry fixtureGeometry() {
		/** create a red box straight above the blue one at (1,3,1) */
		if(fixtureGeometry == null) {
	        Box box = new Box(0.2f,1,0.2f);
	        fixtureGeometry = new Geometry(fixtureName, box);
	    	Material base = new Material(Main.app.getAssetManager(),  // Create new material and...
	        	    "Common/MatDefs/Light/Lighting.j3md"); // ... specify .j3md file to use (illuminated).
	    	base.setBoolean("UseMaterialColors",true);  // Set some parameters, e.g. blue.
	    	base.setColor("Ambient", ColorRGBA.DarkGray);   // ... color of this object
	    	base.setColor("Diffuse", ColorRGBA.DarkGray);
	        fixtureGeometry.setMaterial(base);
	        

	        
	        spot = new SpotLight();
	        spot.setSpotRange(100f);                           // distance
	        spot.setSpotInnerAngle(degrees * sharpness * FastMath.DEG_TO_RAD); // inner light cone (central beam)
	        spot.setSpotOuterAngle(degrees * FastMath.DEG_TO_RAD); // outer light cone (edge of the light)
	        spot.setColor(ColorRGBA.White.mult(1.3f));         // light color
	        spot.setPosition(fixtureGeometry.getWorldTranslation());               // shine from camera loc
	        
	        
	        LightControl lightControl = new LightControl(spot);
	        fixtureGeometry.addControl(lightControl); // this spatial controls the position of this light.
	        Main.app.getRootNode().addLight(spot);
	        
		}
        return fixtureGeometry;
	}
	
	@Override
	public JFrame fixtureEditor() {
		JFrame f = new JFrame();
		f.setResizable(false);
		f.setTitle("Leko");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 300, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		f.setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[:25px:25px][25px][25px][25px][25px][25px][25px][:25px:25px][:25px:25px]", "[][][][][][][][][][][][][][][][grow]"));
		
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
		
		JLabel lblX_1 = new JLabel("Pan");
		contentPane.add(lblX_1, "cell 0 4,alignx right");
		
		JSpinner rotX = new JSpinner();
		contentPane.add(rotX, "cell 1 4 2 1,growx");
		
		JLabel lblY_1 = new JLabel("Tilt");
		contentPane.add(lblY_1, "cell 3 4,alignx right");
		
		JSpinner rotY = new JSpinner();
		contentPane.add(rotY, "cell 4 4 2 1,growx");
		
		JLabel lblArtnet = new JLabel("Art-Net");
		lblArtnet.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblArtnet, "cell 0 5 5 1");
		
		JLabel lblUniverse = new JLabel("Universe");
		contentPane.add(lblUniverse, "cell 0 6 4 1");
		
		JSpinner uverse = new JSpinner();
		contentPane.add(uverse, "cell 4 6 2 1,growx");
		
		JLabel lblAddress = new JLabel("Address");
		contentPane.add(lblAddress, "cell 0 7 4 1");
		
		JSpinner addr = new JSpinner();
		contentPane.add(addr, "cell 4 7 2 1,growx");
		
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
		
		JLabel lblOverrideArtnet = new JLabel("Override Art-Net");
		contentPane.add(lblOverrideArtnet, "cell 0 12");
		
		JCheckBox checkBox = new JCheckBox("");
		contentPane.add(checkBox, "cell 4 12");
		
		JLabel lblFixtureValue = new JLabel("Override Value");
		contentPane.add(lblFixtureValue, "cell 0 13");
		
		JSlider override = new JSlider();
		contentPane.add(override, "cell 4 13 5 1");
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblNotes, "cell 0 14");
		
		JEditorPane editorPane = new JEditorPane();
		contentPane.add(editorPane, "cell 0 15 9 1,grow");
		
		
		posX.setValue(position.x);
		posX.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				position.x = (Integer) posX.getValue();
				
			}
			
		});
		
		posY.setValue(position.y);
		posY.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				position.y = (Integer)posY.getValue();
				
			}
			
		});
		posZ.setValue(position.z);
		posZ.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				position.z = (Integer) posZ.getValue();
				
			}
			
		});
		rotX.setValue(rotation.x);
		rotX.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				rotation.x = (Integer) rotX.getValue();
				
			}
			
		});
		rotY.setValue(rotation.y);
		rotY.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				rotation.y = (Integer) rotY.getValue();
				
			}
			
		});
		uverse.setValue(universe);
		uverse.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				universe = (int) uverse.getValue();
			}
			
		});
		addr.setValue(address);
		addr.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				address = (int) addr.getValue();
			}
			
		});
		deg.setValue(degrees);
		deg.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				degrees = (int) deg.getValue();
			}
			
		});
		sharp.setMaximum(1000);
		sharp.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sharpness = ((float)sharp.getValue())/1000f;
			}
			
		});
		gelSelect.setSelectedItem(gel);
		gelSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gel = (Gel) gelSelect.getSelectedItem();
			}
			
		});
		checkBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				overrideEnable = checkBox.isSelected();
			}
			
		});
		override.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				overrideValue = override.getValue();
			}
			
		});
		return f;
	}
	
}
