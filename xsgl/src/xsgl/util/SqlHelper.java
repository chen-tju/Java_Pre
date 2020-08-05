package xsgl.util;
import java.sql.*;
public class SqlHelper {
	public static Connection connect(){
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  //jdbc驱动  
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Student_Management"; //连接数据源  
        String user = "sa";  //数据库的用户  
        String passWord = "chen@198917"; //数据库的用户密码  
		try{
			//加载jdbc驱动  
            Class.forName(driver);  
            //连接数据源  
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
