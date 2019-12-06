import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.jme3.scene.Geometry;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class SceneGraphGUI extends JFrame {

	private JPanel contentPane;
	private JTree tree;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public SceneGraphGUI() {
		setTitle("Scene Graph");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNew = new JMenu("New");
		menuBar.add(mnNew);
		
		JMenuItem mntmBox = new JMenuItem("Box");
		mnNew.add(mntmBox);
		
		JMenu mnFixture = new JMenu("Fixture");
		mnNew.add(mnFixture);
		
		JMenuItem mntmLeko = new JMenuItem("Leko");
		mnFixture.add(mntmLeko);
		
		mntmLeko.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.addFixture(new Leko());
			}
			
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tree = new JTree(new JMETreeModel(Main.app.getRootNode()));
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				if(!tree.isSelectionEmpty()) {
					if(Main.fixtureEditor != null){
						Main.fixtureEditor.dispose();
					}
					if(((Geometry)tree.getSelectionPath().getLastPathComponent()).getUserData("object_type") != null && ((Geometry)tree.getSelectionPath().getLastPathComponent()).getUserData("object_type").equals("fixture")) {
						Fixture f = Fixture.fixtures.get(((Geometry)tree.getSelectionPath().getLastPathComponent()));
						Main.fixtureEditor = f.fixtureEditor();
						Main.fixtureEditor.setVisible(true);
					}
				}
			}
			
		});
		scrollPane.setViewportView(tree);(new Thread() {
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
		tree.updateUI();
		this.repaint();
	}


}
