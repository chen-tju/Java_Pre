package keshe;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Vector;
	import javax.swing.table.AbstractTableModel;

	public class BkModel extends AbstractTableModel{
		Vector<String> columNames = null;  //����ֶ�����  
	    Vector<Vector> rowDate = null;    //�������   
	      
	   //���ݿ�  
	   private Connection con = null;  
	   private PreparedStatement ps = null;  
	   private ResultSet rs = null;  
	   private final static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";    //���ص�jdbc����  
	   private final static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=�Ƶ�ͷ�������������";  //����Դ  
	   private final static String user = "sa";  //���ݿ���û���  
	   private final static String passWord = "chen@198917";   //���ݿ���û���������  
	 
	   //�����ݿ���в���  
	   public void insertDatabase(String sql)  
	   {  
	       columNames = new Vector<String>();  //����ֶ�����  
	       rowDate = new Vector<Vector>();    //�������   
	         
	       columNames.add("�����");  
	       columNames.add("�۸�"); 
	       columNames.add("��������"); 
	       columNames.add("���֤����");
	       columNames.add("����"); 
	       columNames.add("�Ա�"); 
	       columNames.add("����"); 
	       columNames.add("��סʱ��"); 
	       columNames.add("���ʱ��");
	         
	       try  
	       {  
	           //����jdbc����  
	           Class.forName(driver);  
	           //��������Դ  
	           con = DriverManager.getConnection(url,user,passWord);  
	           //ִ��sql  
	           ps = con.prepareStatement(sql);  
	           rs = ps.executeQuery();  
	           while(rs.next())  
	           {     
	               Vector<String> row = new Vector<String>();  
	               row.add(rs.getString(1));  
	               row.add(rs.getString(2));  
	               row.add(rs.getString(3));  
	               row.add(rs.getString(4));  
	               row.add(rs.getString(5));  
	               row.add(rs.getString(6));
	               row.add(rs.getString(7));
	               row.add(rs.getString(8));
	               row.add(rs.getString(9)); 
	               
	                 
	               rowDate.add(row);  //���ͷ���һ����¼��row����ӵ�rowDate��  
	           }         
	       }  
	       catch(ClassNotFoundException e)  
	       {  
	           e.printStackTrace();  
	       }  
	       catch(SQLException e)  
	       {  
	           e.printStackTrace();  
	       }  
	       finally  
	       {  
	           //�ر����ݿ���Դ  
	           try  
	           {  
	               if(null != rs)  
	               {  
	                   rs.close();  
	               }  
	               if(null != ps)  
	               {  
	                   ps.close();  
	               }  
	               if(null != con)  
	               {  
	                   con.close();  
	               }  
	           }  
	           catch(SQLException e)  
	           {  
	               e.printStackTrace();  
	           }  
	       }  
	 
	   }  
	     
	     
	   @Override  
	   //��������  
	   public int getColumnCount() {  
	       return this.columNames.size();  
	   }  
	 
	   @Override  
	    //���ر������  
	   public int getRowCount() {  
	       return this.rowDate.size();  
	   }  
	 
	   @Override  
	   //�õ�ĳ��ĳ�е�����  
	   public Object getValueAt(int rowIndex, int columnIndex) {  
	       return ((Vector)this.rowDate.get(rowIndex)).get(columnIndex);  
	   }  
	   //���ñ���ֶ���  
	   public String getColumnName(int column)  
	   {  
	    return (String)this.columNames.get(column);  
	   }  

	}
