
import com.jme3.app.DebugKeysAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioListenerState;
import com.jme3.input.MouseInput;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

/** Sample 1 - how to get started with the most simple JME 3 application.
 * Display a blue 3D cube and view from all sides by
 * moving the mouse and pressing the WASD keys. */
public class Viz extends SimpleApplication {
	
	public static boolean ready = false;
	
	public Viz() {
		//super(new StatsAppState(), new FlyCamAppState(), new DebugKeysAppState());
		super(new StatsAppState(), new DebugKeysAppState());
	}
	
    @Override
    public void simpleInitApp() {
        ready = true;
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-.5f,-.5f,-.5f).normalizeLocal());
        rootNode.addLight(sun);
        
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.1f));
        rootNode.addLight(al);
        
        Box box = new Box(10f,10f,1f);
        Geometry fixtureGeometry = new Geometry("Stage", box);
    	Material base = new Material(Main.app.getAssetManager(),  // Create new material and...
        	    "Common/MatDefs/Light/Lighting.j3md"); // ... specify .j3md file to use (illuminated).
    	base.setBoolean("UseMaterialColors",true);  // Set some parameters, e.g. blue.
    	base.setColor("Ambient", ColorRGBA.DarkGray);   // ... color of this object
    	base.setColor("Diffuse", ColorRGBA.DarkGray);
        fixtureGeometry.setMaterial(base);
        fixtureGeometry.rotate(-20,0,0);
        rootNode.attachChild(fixtureGeometry);
        
    
    }
}
