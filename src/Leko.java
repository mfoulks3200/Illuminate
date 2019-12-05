import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JComboBox;
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
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
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
	
	public Leko() {
		super();
		position = new Vector3f(0,2,2);
		gel = Gel.getGel(Manufacturer.Blackbody, 3000);
	}
	
	@Override
	public void update() {
		if(fixtureGeometry != null) {
			Main.app.enqueue(new Callable<Integer>() {
		        public Integer call() throws Exception {
			        fixtureGeometry.setLocalTranslation(position);
			        ColorRGBA col = new ColorRGBA(((float)gel.red)/255f,((float)gel.green)/255f,((float)gel.blue)/255f,1f);
		        	spot.setColor(col.mult((((float)getLocalPercent(1)))/100f));  
		        	spot.setSpotInnerAngle(degrees * sharpness * FastMath.DEG_TO_RAD); // inner light cone (central beam)
			        spot.setSpotOuterAngle(degrees * FastMath.DEG_TO_RAD); // outer light cone (edge of the light)
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
	        fixtureGeometry.rotate(120,0,0);
	        

	        
	        spot = new SpotLight();
	        spot.setSpotRange(100f);                           // distance
	        spot.setSpotInnerAngle(degrees * sharpness * FastMath.DEG_TO_RAD); // inner light cone (central beam)
	        spot.setSpotOuterAngle(degrees * FastMath.DEG_TO_RAD); // outer light cone (edge of the light)
	        spot.setColor(ColorRGBA.White.mult(1.3f));         // light color
	        spot.setPosition(fixtureGeometry.getWorldTranslation());               // shine from camera loc
	        spot.setDirection(new Vector3f(180,-90,0));             // shine forward from camera loc
	        LightControl lightControl = new LightControl(spot);
	        fixtureGeometry.addControl(lightControl); // this spatial controls the position of this light.
	        Main.app.getRootNode().addLight(spot);
	        
		}
        return fixtureGeometry;
	}
	
	@Override
	public JFrame fixtureEditor() {
		JFrame f = new JFrame();
		f.setTitle("Leko");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 500, 516);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		f.setContentPane(contentPane);
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
		
		JSpinner uverse = new JSpinner();
		uverse.setValue(universe);
		contentPane.add(uverse, "cell 4 6");
		uverse.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				universe = (int) uverse.getValue();
			}
			
		});
		
		JLabel lblAddress = new JLabel("Address");
		contentPane.add(lblAddress, "cell 0 7 4 1");
		
		JSpinner addr = new JSpinner();
		addr.setValue(address);
		contentPane.add(addr, "cell 4 7");
		addr.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				address = (int) addr.getValue();
			}
			
		});
		
		JLabel lblFocus = new JLabel("Focus");
		lblFocus.setFont(new Font("Tahoma", Font.BOLD, 21));
		contentPane.add(lblFocus, "cell 0 8 4 1");
		
		JLabel lblDegree = new JLabel("Degree");
		contentPane.add(lblDegree, "cell 0 9 4 1");
		
		JSpinner deg = new JSpinner();
		deg.setValue(degrees);
		contentPane.add(deg, "cell 4 9");
		deg.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				degrees = (int) deg.getValue();
			}
			
		});
		
		JLabel lblSharpness = new JLabel("Sharpness");
		contentPane.add(lblSharpness, "cell 0 10 4 1");
		
		JSlider sharp = new JSlider();
		sharp.setMaximum(1000);
		contentPane.add(sharp, "cell 4 10 4 1");
		sharp.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sharpness = ((float)sharp.getValue())/1000f;
			}
			
		});
		
		JLabel lblGel = new JLabel("Gel");
		contentPane.add(lblGel, "cell 0 11 4 1");
		
		JComboBox gelSelect = new JComboBox(Gel.getAllGels());
		contentPane.add(gelSelect, "cell 4 11 4 1,growx");
		gelSelect.setSelectedItem(gel);
		gelSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gel = (Gel) gelSelect.getSelectedItem();
			}
			
		});
		return f;
	}
	
}
