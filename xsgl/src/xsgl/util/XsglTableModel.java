package xsgl.util;
import javax.swing.table.AbstractTableModel;
public class XsglTableModel extends AbstractTableModel{
	private Object[][] data;
	private String[] head;
	
	public XsglTableModel(String[] head,Object[][] data){
		this.head = head;
		this.data = data;
		
	}
	@Override
	public int getColumnCount(){
		return head.length;
	}
	@Override
	public int getRowCount(){
		return data.length;
	}
	@Override
	public String getColumnName(int col){
		return head[col];
	}
	@Override
	public Object getValueAt(int row,int col){
		return data[row][col];
	}
	@Override
	public Class getColumnClass(int c){
		return getValueAt(0,c).getClass();
	}

}
