import java.util.HashMap;
import java.util.concurrent.Callable;

import javax.swing.JFrame;

import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.LightControl;
import com.jme3.scene.shape.Box;

public class Fixture {
	
	public int universe;
	public int address;
	public static int footprint;
	public static String fixtureName = "Generic Fixture";
	public static String nickname;
	public static Attributes[] personality;
	public Geometry fixtureGeometry;
	public SpotLight spot;
	public HashMap<String, Object> inspector = new HashMap<>();
	
	
	public int getLocalValue(int relativeAddress) {
		if(relativeAddress < footprint) {
			int add = address+relativeAddress+1;
			return Main.universes.get(universe).dmxData[add] & 0xff;
		}else {
			return 0;
		}
	}
	
	public int getLocalPercent(int relativeAddress) {
		if(relativeAddress <= footprint) {
			int add = (address+(relativeAddress+1))-3;
			return (int)((((float)(Main.universes.get(universe).dmxData[add] & 0xff))/255f)*100f);
		}else {
			return 0;
		}
	}
	
	public Fixture() {
		address = 1;
		footprint = 10;
		(new Thread() {
	    	  public void run() {
	    		  while(true) {
	    		  	try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			  update();
	    		  }
	    	  }
	    	 }).start();
	}
	
	public void update() {
		if(fixtureGeometry != null) {
			Main.app.enqueue(new Callable<Integer>() {
		        public Integer call() throws Exception {
		        	spot.setColor(ColorRGBA.White.mult((((float)getLocalPercent(1)))/100f));  
		        	return 0;
		        }
		    });
		}
	}
	
	public Geometry fixtureGeometry() {
		/** create a red box straight above the blue one at (1,3,1) */
		if(fixtureGeometry == null) {
	        Box box = new Box(0.2f,1,0.2f);
	        fixtureGeometry = new Geometry(fixtureName, box);
	        fixtureGeometry.setLocalTranslation(new Vector3f(1,3,1));
	    	Material base = new Material(Main.app.getAssetManager(),  // Create new material and...
	        	    "Common/MatDefs/Light/Lighting.j3md"); // ... specify .j3md file to use (illuminated).
	    	base.setBoolean("UseMaterialColors",true);  // Set some parameters, e.g. blue.
	    	base.setColor("Ambient", ColorRGBA.DarkGray);   // ... color of this object
	    	base.setColor("Diffuse", ColorRGBA.DarkGray);
	        fixtureGeometry.setMaterial(base);
	        fixtureGeometry.rotate(120,0,0);
	        

	        
	        spot = new SpotLight();
	        spot.setSpotRange(100f);                           // distance
	        spot.setSpotInnerAngle(15f * FastMath.DEG_TO_RAD); // inner light cone (central beam)
	        spot.setSpotOuterAngle(35f * FastMath.DEG_TO_RAD); // outer light cone (edge of the light)
	        spot.setColor(ColorRGBA.White.mult(1.3f));         // light color
	        spot.setPosition(fixtureGeometry.getWorldTranslation());               // shine from camera loc
	        spot.setDirection(new Vector3f(180,-90,0));             // shine forward from camera loc
	        LightControl lightControl = new LightControl(spot);
	        fixtureGeometry.addControl(lightControl); // this spatial controls the position of this light.
	        Main.app.getRootNode().addLight(spot);
	        
		}
        return fixtureGeometry;
	}
	
	public JFrame fixtureEditor() {
		return new JFrame();
	}
	
}
