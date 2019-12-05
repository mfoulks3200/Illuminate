import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ArtNetExplorer extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public ArtNetTableModel model;


	/**
	 * Create the frame.
	 */
	public ArtNetExplorer() {
		setBounds(new Rectangle(0, 0, 600, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Input Data");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		ArtNetTableModel model = new ArtNetTableModel();
		table = new JTable(model);
		scrollPane.setViewportView(table);
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
		this.repaint();
	}

}
