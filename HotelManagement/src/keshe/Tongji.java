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
	String Names[] = {"��������", "����"};
	Object[][] playerInfo = {
			{"1�����൥�˼�",""},
			{"2���Ѷ����˼�",""},
			{"3�������׼��",""},
			{"4���Ѷ���׼��",""},
			{"5������˫�˼�",""},
			{"6���Ѷ�˫�˷�",""},
	};
	private String ss[][];
	
	public Tongji() {
		setTitle("�Ƶ�ͷ�ͳ�ƽ��");
		setSize(800, 500);
		setLocation(350, 150);
		scrollPane = new JScrollPane();
		scrollPane.setSize(300, 200);
		
		//����һ��ֻ�б�ͷ�ı��ģ��
		defaultTableModel  = new DefaultTableModel( null,Names);
		
		//��playerInfo�зǿ�Ԫ�ز������
		for(Object[] x:playerInfo){
			if(x[0]!=null){
				defaultTableModel.addRow(x);
			}
		}
		
		table = new JTable(defaultTableModel);
		
		//���õ�Ԫ���е����־��� �Ǳ�ͷ��Ԫ��
		DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r);
		
		scrollPane.setViewportView(table);	
		add(scrollPane);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		/* //��ȡ����е���Ϣ 
		 * 
		 * Vector data = defaultTableModel.getDataVector();
		 * VectorToString(data);
		 * 
		 */
	}
	//����άʸ������ת���ɶ�ά�ַ������� �洢������ss��
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

