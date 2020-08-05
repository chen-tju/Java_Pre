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

	public class InsertBk extends JDialog implements ActionListener{
		private JLabel id, price, name,number, name1,sex,age,time, time1;  
	    private JTextField jtf1, jtf2, jtf3, jtf4, jtf5,jtf6,jtf7,jtf8,jtf9;   
	    private JButton add, canel;  //分别为登记按钮和返回按钮  
	    private Panel panel1, panel2, panel3;  
	      
	    public InsertBk(Frame owner, String title, boolean model)  
	    {  
	        super(owner, title, model);  //实现与父类窗口对话  
	        System.out.println("执行完毕");  
	          
	        //窗体的西部的组件  
	        id = new JLabel("房间号:");  
	        price = new JLabel("价格:"); 
	        name = new JLabel("房间类型:");
	        number = new JLabel("身份证号码:"); 
	        name1 = new JLabel("姓名:"); 
	        sex = new JLabel("性别:"); 
	        age = new JLabel("年龄:"); 
	        time = new JLabel("入住时间:");  
	        time1 = new JLabel("离店时间:");  
	        
	        //添加到panel1面板中  
	        panel1 = new Panel(new GridLayout(9, 1));  
	        panel1.add(id);  
	        panel1.add(price);  
	        panel1.add(name); 
	        panel1.add(number);    
	        panel1.add(name1);
	        panel1.add(sex);
	        panel1.add(age);
	        panel1.add(time);
	        panel1.add(time1);
	      
	          
	        //窗体的东部组件  
	        jtf1 = new JTextField(10);  
	        jtf2 = new JTextField(10);  
	        jtf3 = new JTextField(10);  
	        jtf4 = new JTextField(10);  
	        jtf5 = new JTextField(10);  
	        jtf6 = new JTextField(10); 
	        jtf7 = new JTextField(10); 
	        jtf8 = new JTextField(10); 
	        jtf9 = new JTextField(10); 
	        
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
	        
	          
	        //窗体北部的控件   
	        add = new JButton("添加");  
	        canel = new JButton("取消");  
	          
	        //注册事件监听  
	        add.addActionListener(this);  
	        canel.addActionListener(this);  
	          
	        //添加到panel3面板中  
	        panel3 = new Panel();  
	        panel3.add(add);  
	        panel3.add(canel);  
	          
	        //将各个组件添加到窗体中  
	        this.add(panel1, BorderLayout.WEST);  
	        this.add(panel2, BorderLayout.CENTER);  
	        this.add(panel3, BorderLayout.SOUTH);  
	          
	        //设置对话框属性  
	        this.setSize(1000, 800);  
	        //设置对话框为可见  
	        this.setVisible(true);  
	    }  
	      
	      
	    public void actionPerformed(ActionEvent e)  
	    {  
	        //数据库  
	        Connection con = null;  
	        PreparedStatement ps = null;  
	        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  //jdbc驱动  
	        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=酒店客房管理——王朝臣"; //连接数据源  
	        String user = "sa";  //数据库的用户  
	        String passWord = "chen@198917"; //数据库的用户密码  
	        boolean result = true; //记录添加客房是否成功  
	          
	        if(e.getSource() == add)  
	        {  
	            try  
	            {  
	                //加载jdbc驱动  
	                Class.forName(driver);  
	                //连接数据源  
	                con = DriverManager.getConnection(url,user,passWord);  
	                //执行sql  
	             
	                String sql = "insert into 客房信息  values(?,?,?,?,?,?,?,?,?)";  
	                ps = con.prepareStatement(sql);  
	                  
	                ps.setString(1, jtf1.getText());  
	                ps.setString(2, jtf2.getText());  
	                ps.setString(3, jtf3.getText());  
	                ps.setString(4, jtf4.getText());  
	                ps.setString(5, jtf5.getText()); 
	                ps.setString(6, jtf6.getText()); 
	                ps.setString(7, jtf7.getText()); 
	                ps.setString(8, jtf8.getText()); 
	                ps.setString(9, jtf9.getText()); 
	               
	              
	                  
	                ps.executeUpdate();  
	                  
	                //关闭对话框  
	                this.dispose();  
	            }  
	            catch(ClassNotFoundException e1)  
	            {  
	                result = false; //添加客房记录失败  
	                e1.printStackTrace();  
	            }  
	            catch(SQLException e1)  
	            {  
	                result = false;   //添加客房记录失败  
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
	            }     
	              
	            if(false == result)  
	            {  
	                JOptionPane.showMessageDialog(this, "失败信息：房间号不能重复");  
	            }  
	        }  
	          
	        else if(e.getSource() == canel)  
	        {  
	            //关闭对话框  
	            this.dispose();  
	        }  
	    }  

	}
