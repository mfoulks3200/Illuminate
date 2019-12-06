import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import com.jme3.scene.Node;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;

/**
 * shows SceneElements and their Controllers
 */
public class JMETreeModel implements TreeModel{
    Node rootNode;
    ArrayList<TreeModelListener> listeners;
   
    public JMETreeModel(Node root){
        listeners = new ArrayList<TreeModelListener>();
        rootNode = root;
    }
   
    public boolean isLeaf(Object node) {
        return (getChildCount(node) < 1);
    }

    public int getChildCount(Object parent) {
        if(!(parent instanceof Node)){
            return 0;
        }else{
            int controllers = ((Spatial)parent).getNumControls();
            int childs = 0;
            if(((Node) parent).getChildren() != null){
                childs = ((Node) parent).getChildren().size();
            }
            return controllers + childs;
        }
    }

    public void valueForPathChanged(javax.swing.tree.TreePath path, Object newValue) {
        //not supported
    }

    public void removeTreeModelListener(javax.swing.event.TreeModelListener l) {
        listeners.remove(l);
    }

    public void addTreeModelListener(javax.swing.event.TreeModelListener l) {
        listeners.add(l);
    }

    public Object getChild(Object parent, int index) {
        Node jn = (Node) parent;
        if(index < jn.getChildren().size())
            return jn.getChild(index);
        else
            return jn.getControl(index-jn.getChildren().size());
    }

    public Object getRoot() {
        return rootNode;
    }

    public int getIndexOfChild(Object parent, Object child) {
        Node jn = (Node) parent;
        ArrayList<Spatial> al = (ArrayList<Spatial>) jn.getChildren();
        int childs = al.size();
        for(int i=0; i< childs; i++){
            if(al.get(i) == child)
                return i;
        }
        for(int i=0; i< al.size(); i++){
            if(((Spatial)parent).getControl(i) == child)
                return i+childs;
        }
        return -1;
    }
  
    public static void showScenegraph(Node rootNode){
        JFrame f = new JFrame();
        JTree tree = new JTree(new JMETreeModel(rootNode));
        f.getContentPane().add(new JScrollPane(tree));
        f.pack();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}