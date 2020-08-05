package xsgl.util;
import java.sql.*;
public class SqlHelper {
	public static Connection connect(){
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  //jdbc����  
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Student_Management"; //��������Դ  
        String user = "sa";  //���ݿ���û�  
        String passWord = "chen@198917"; //���ݿ���û�����  
		try{
			//����jdbc����  
            Class.forName(driver);  
            //��������Դ  
            Connection con = DriverManager.getConnection(url,user,passWord);
            return con;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static void closeResultSet(ResultSet rs){
		try{
			rs.close();
		}catch(SQLException ex){
			
		}
	}
	public static void closePreparedStatement(PreparedStatement ps){
		try{
			ps.close();
		}catch(SQLException ex){
			
		}
	}
	public static void closeConnection(Connection con){
		try{
			con.close();
		}catch(SQLException ex){
			
		}
	}

}
