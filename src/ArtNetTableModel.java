import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ArtNetTableModel extends DefaultTableModel implements TableModel{

	public ArtNetTableModel() {
		
	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0) {
		case 0:
			return Integer.class;
		case 1:
			return Byte.class;
		case 2:
			return Integer.class;
		default:
			return null;
	}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0) {
			case 0:
				return "Address";
			case 1:
				return "DMX";
			case 2:
				return "Value";
			default:
				return null;
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 512;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		switch(arg1) {
			case 0:
				return arg0+1;
			case 1:
				return Main.universes.get(0).dmxData[arg0] & 0xff;
			case 2:
				return (int)((((float)(Main.universes.get(0).dmxData[arg0] & 0xff))/255f)*100f);
			default:
				return null;
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
