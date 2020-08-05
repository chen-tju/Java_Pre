package keshe;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateDatabase extends JDialog implements ActionListener{
	private JLabel id, price, name, number, name1,sex,age,time,time1;  
    private JTextField jtf1, jtf2, jtf3, jtf4, jtf5,jtf6,jtf7,jtf8,jtf9; 
    JButton update,canel;  //分别为修改和取消按钮  
    Panel panel1 ,panel2, panel3;  
      
    public UpdateDatabase(Frame own, String title, boolean model, BkModel stModel, int row)  
    {  
        super(own, title, model); //实现与父类窗口对话  
          
        //窗体西部的组件  
        id = new JLabel("房间号:");  
        price = new JLabel("价格:"); 
        name = new JLabel("房间类型:"); 
        number = new JLabel("身份证号码:");  
        name1 = new JLabel("姓名:"); 
        sex = new JLabel("性别:"); 
        age= new JLabel("年龄:"); 
        time = new JLabel("入住时间:"); 
        time1 = new JLabel("离店时间:"); 
      
       
        //添加到panel1面板中  
        panel1 = new Panel(new GridLayout(9, 1));  
        panel1.add(id);  
        panel1.add(price);  
        panel1.add(name); 
        panel1.add(time);    
        panel1.add(time1);  
        panel1.add(name1);
        panel1.add(sex);
        panel1.add(age);
        panel1.add(number);
        
        //添加到窗体的东部的组件  
        jtf1 = new JTextField(10);  
        jtf1.setText((String)(stModel.getValueAt(row, 0)));  
        jtf1.setEditable(false);
        jtf2 = new JTextField(10);  
        jtf2.setText((String)(stModel.getValueAt(row, 1)));  
        jtf3 = new JTextField(10);  
        jtf3.setText((String)(stModel.getValueAt(row, 2)));  
        jtf4 = new JTextField(10);  
        jtf4.setText((String)(stModel.getValueAt(row, 3)));  
        jtf5 = new JTextField(10);  
        jtf5.setText((String)(stModel.getValueAt(row, 4)));  
        jtf6 = new JTextField(10);  
        jtf6.setText((String)(stModel.getValueAt(row, 5)));  
        jtf7 = new JTextField(10);  
        jtf7.setText((String)(stModel.getValueAt(row, 6)));  
        jtf8 = new JTextField(10);  
        jtf8.setText((String)(stModel.getValueAt(row, 7)));  
        jtf9 = new JTextField(10);  
        jtf9.setText((String)(stModel.getValueAt(row, 8)));  
     
      
        //添加到第二个面板中  
        panel2 = new Panel(new GridLayout(9, 1));  
        panel2.add(jtf1);  
        panel2.add(jtf2);  
        panel2.add(jtf3);  
        panel2.add(jtf4);  
        panel2.add(jtf5);  
        panel2.add(jtf6);  
        panel2.add(jtf7);  
        panel2.add(jtf8);  
        panel2.add(jtf9);  
         
        //窗体南部的组件  
        update = new JButton("确认");  
        canel = new JButton("取消");  
        panel3 = new Panel();  
        panel3.add(update);  
        panel3.add(canel);  
          
        //注册事件监听  
        update.addActionListener(this);  
        canel.addActionListener(this);  
          
        //添加组件到窗体中  
        this.add(panel1, BorderLayout.WEST);  
        this.add(panel2, BorderLayout.CENTER);  
        this.add(panel3, BorderLayout.SOUTH);  
  
        //设置窗体属性  
        this.setSize(500, 400);  
        //设置窗体为可见  
        this.setVisible(true);  
    }  
      
    //响应用户操作  
    public void actionPerformed(ActionEvent e)  
    {  
        //数据库  
        Connection con = null;  
        PreparedStatement ps = null;  
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  //jdbc驱动  
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=酒店客房管理——王朝臣"; //连接数据源  
        String user = "sa";  //数据库的用户  
        String passWord = "chen@198917"; //数据库的用户密码  
        boolean result = true;   //记录修改是否成功  
          
        if(e.getSource() == update)  
        {  
            try  
            {  
                //加载jdbc驱动  
                Class.forName(driver);  
                //连接数据源  
                con = DriverManager.getConnection(url,user,passWord);  
                //执行sql  
                String sql = "update 客房信息  set 价格=?,房间类型=?,身份证号码=?,姓名=?,性别=?,年龄=?,入住时间=?,离店时间=? where 房间号=? ";  
                ps = con.prepareStatement(sql);  
                //修改房间信息数据  
                ps.setString(1, jtf2.getText());  
                ps.setString(2, jtf3.getText());  
                ps.setString(3, jtf4.getText());  
                ps.setString(4, jtf5.getText());    
                ps.setString(5, jtf6.getText());  
                ps.setString(6, jtf7.getText());  
                ps.setString(7, jtf8.getText());  
                ps.setString(8, jtf9.getText());    
                ps.setString(9, jtf1.getText());  
                
                  
                ps.executeUpdate();  
                //关闭对话框  
                this.dispose();  
            }  
            catch(ClassNotFoundException e1)  
            {  
                result = false;  
                e1.printStackTrace();  
            }  
            catch(SQLException e1)  
            {  
                result = false;  
                e1.printStackTrace();  
            }  
            finally  
            {  
                //关闭资源  
                try  
                {  
                    if(null != ps)  
                    {  
                        ps.close();  
                    }  
                    if(null != con)  
                    {  
                        con.close();  
                    }  
                }  
                catch(SQLException e1)  
                {  
                    e1.printStackTrace();  
                }  
                if(false == result)  
                {  
                    JOptionPane.showMessageDialog(this, "失败信息：房间号不能重复");  
                }  
                  
            }  
        }  
        else if(e.getSource() == canel)  
        {  
            this.dispose();//关闭对话框  
        }  
    }  
  

}
