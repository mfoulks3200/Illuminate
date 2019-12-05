import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class SceneGraphTreeModel implements TreeModel{

	@Override
	public void addTreeModelListener(TreeModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getChild(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return ((Node)arg0).getChild(arg1);
	}

	@Override
	public int getChildCount(Object arg0) {
		// TODO Auto-generated method stub
		return ((Node)arg0).getChildren().size();
	}

	@Override
	public int getIndexOfChild(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return ((Node)arg0).getChildIndex((Spatial) arg1);
	}

	@Override
	public Object getRoot() {
		// TODO Auto-generated method stub
		return Main.app.getRootNode();
	}

	@Override
	public boolean isLeaf(Object arg0) {
		// TODO Auto-generated method stub
		return arg0 instanceof Spatial;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
