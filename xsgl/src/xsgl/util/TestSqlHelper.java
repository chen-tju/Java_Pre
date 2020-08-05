package xsgl.util;
import java.sql.*;
import javax.swing.*;
public class TestSqlHelper {
	public static void main(String args[]){
		Connection con = SqlHelper.connect();
		if(con!=null){
			JOptionPane.showMessageDialog(null, "数据库连接成功！");
			SqlHelper.closeConnection(con);
		}else{
			JOptionPane.showMessageDialog(null, "数据库连接失败！");
		}
	}

}
