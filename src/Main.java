import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;

import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;

import ch.bildspur.artnet.*;

public class Main {
	public static ArrayList<ArtNetLink> universes = new ArrayList();
	public static ArtNetExplorer frame;
	public static HashMap<Geometry, Fixture> fixtures = new HashMap<>();
	public static Viz app;
	public static Spatial selected;
	public static JFrame fixtureEditor;
	
	public static void main(String[] args) {
		Gel.loadGels(getURL("http://notelek.com/Blackbody.CSV"));
		Gel.loadGels(getURL("http://notelek.com/gelDB.csv"));
		
		/*stream = Main.class.getResourceAsStream("/resources/gelDB.csv");
		try {
		 input = new Scanner (stream);
		} catch (Exception e) {
			
		}
		file = "";
		while (input.hasNextLine()) {
			file += input.nextLine()+"\n";
		}
		Gel.loadGels(file);*/
        
		universes.add(new ArtNetLink(0));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ArtNetExplorer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceneGraphGUI frame = new SceneGraphGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		app = new Viz();
		app.setShowSettings(false);
		AppSettings set = new AppSettings(true);
		set.setResolution(800, 600);
		set.setTitle("Illuminate v0.02a");
		app.setPauseOnLostFocus(false);
		app.setSettings(set);
        app.start(); // start the game
	}
	
	public static void addFixture(Fixture f) {
		(new Thread() {
    	  public void run() {
    		  	while(!app.ready) {
    		  		try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    		  	}
    			Geometry s = f.fixtureGeometry();
    			fixtures.put(s,f);
    			app.getRootNode().attachChild(s);
    			setSelected(s);
    	  }
    	 }).start();
	}
	
	public static void setSelected(Spatial f) {
		selected = f;
	}
	
	public static String getURL(String uri) {
		try {
            
            URL url = new URL(uri);
             
            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String outp = "";
            String line;
            while ((line = in.readLine()) != null) {
                outp += line+"\n";
            }
            in.close();
             return outp;
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
		return "";
	}
}
