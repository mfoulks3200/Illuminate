import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
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
	public static Inspector inspect;
	
	public static void main(String[] args) {
		byte[] content = null;
		try {
			content = Files.readAllBytes(Paths.get("gelDB.csv"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Gel.loadGels(new String(content));
		try {
			content = Files.readAllBytes(Paths.get("Blackbody.CSV"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Gel.loadGels(new String(content));
        
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inspect = new Inspector();
					inspect.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		app = new Viz();
		app.setShowSettings(false);
		AppSettings set = new AppSettings(true);
		set.setResolution(1920, 1080);
		set.setTitle("Illuminate v0.01a");
		app.setPauseOnLostFocus(false);
		app.setSettings(set);
        app.start(); // start the game
        Leko l = new Leko();
        addFixture(l);
		JFrame e = l.fixtureEditor();
		e.setVisible(true);
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
}
