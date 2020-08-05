package keshe;

import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Tongji extends JFrame{	
	private JTable table;
	private DefaultTableModel defaultTableModel = null;
	private JScrollPane scrollPane;	
	String Names[] = {"房间类型", "数量"};
	Object[][] playerInfo = {
			{"1、空余单人间",""},
			{"2、已订单人间",""},
			{"3、空余标准间",""},
			{"4、已订标准间",""},
			{"5、空余双人间",""},
			{"6、已订双人房",""},
	};
	private String ss[][];
	
	public Tongji() {
		setTitle("酒店客房统计结果");
		setSize(800, 500);
		setLocation(350, 150);
		scrollPane = new JScrollPane();
		scrollPane.setSize(300, 200);
		
		//创建一个只有表头的表格模型
		defaultTableModel  = new DefaultTableModel( null,Names);
		
		//将playerInfo中非空元素插入表中
		for(Object[] x:playerInfo){
			if(x[0]!=null){
				defaultTableModel.addRow(x);
			}
		}
		
		table = new JTable(defaultTableModel);
		
		//设置单元格中的文字居中 非表头单元格
		DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r);
		
		scrollPane.setViewportView(table);	
		add(scrollPane);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		/* //获取表格中的信息 
		 * 
		 * Vector data = defaultTableModel.getDataVector();
		 * VectorToString(data);
		 * 
		 */
	}
	//将二维矢量数组转化成二维字符串数组 存储在数组ss中
	public void VectorToString(Vector data){
		Vector rowData;
		ss = new String[10][3];
		String field="";
		for(int i = 0; i<data.size();i++){
			rowData =(Vector) data.get(i);
			for(int j=0;j<rowData.size();j++){
				field = (String) rowData.get(j);
				ss[i][j] = field;
			}			
		}	
	}
	
	public static void main(String[] args) {
		new Tongji();
	}}

