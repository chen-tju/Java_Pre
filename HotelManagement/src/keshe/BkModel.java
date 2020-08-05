package keshe;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Vector;
	import javax.swing.table.AbstractTableModel;

	public class BkModel extends AbstractTableModel{
		Vector<String> columNames = null;  //表的字段名称  
	    Vector<Vector> rowDate = null;    //表的数据   
	      
	   //数据库  
	   private Connection con = null;  
	   private PreparedStatement ps = null;  
	   private ResultSet rs = null;  
	   private final static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";    //加载的jdbc驱动  
	   private final static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=酒店客房管理——王朝臣";  //数据源  
	   private final static String user = "sa";  //数据库的用户名  
	   private final static String passWord = "chen@198917";   //数据库的用户名的密码  
	 
	   //对数据库进行查找  
	   public void insertDatabase(String sql)  
	   {  
	       columNames = new Vector<String>();  //表的字段名称  
	       rowDate = new Vector<Vector>();    //表的数据   
	         
	       columNames.add("房间号");  
	       columNames.add("价格"); 
	       columNames.add("房间类型"); 
	       columNames.add("身份证号码");
	       columNames.add("姓名"); 
	       columNames.add("性别"); 
	       columNames.add("年龄"); 
	       columNames.add("入住时间"); 
	       columNames.add("离店时间");
	         
	       try  
	       {  
	           //加载jdbc驱动  
	           Class.forName(driver);  
	           //连接数据源  
	           con = DriverManager.getConnection(url,user,passWord);  
	           //执行sql  
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
	               
	                 
	               rowDate.add(row);  //将客房的一条记录（row）添加到rowDate中  
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
	           //关闭数据库资源  
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
	   //返回列数  
	   public int getColumnCount() {  
	       return this.columNames.size();  
	   }  
	 
	   @Override  
	    //返回表的行数  
	   public int getRowCount() {  
	       return this.rowDate.size();  
	   }  
	 
	   @Override  
	   //得到某行某列的数据  
	   public Object getValueAt(int rowIndex, int columnIndex) {  
	       return ((Vector)this.rowDate.get(rowIndex)).get(columnIndex);  
	   }  
	   //设置表的字段名  
	   public String getColumnName(int column)  
	   {  
	    return (String)this.columNames.get(column);  
	   }  

	}
