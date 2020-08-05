package keshe;
	import java.awt.BorderLayout;
	import java.awt.Panel;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.JTextField;

	public class BkMange extends JFrame implements ActionListener{
		//定义用户界面用到的组件  
	    JLabel userName1 = null;
	    JLabel userName2 = null;//要查询的房间号  
	    JTextField textField1 = null;
	    JTextField textField2 = null;//用户输入的文本框  
	    JButton select1 = null; 
	    JButton select2 = null; //查询按钮  
	    JButton insert = null;    //登记按钮  
	    JButton return1 = null;    //修改按钮  
	    JButton delete  = null;  //结账按钮  
	    JTable table = null;      //用于显示数据库调出的数据的表  
	    Panel panel1 = null;       
	    Panel panel2 = null;  
	    Panel panel3 = null;   
	    JScrollPane jscrollPane = null;  
	      
	    BkModel bkModel = null;  //bk表的模型  
	    private Object PreparedStatement;  
	    public BkMange()  
	    {  
	        //窗口显示的上部的组件  
	        userName1 = new JLabel("客人信息：");
	        textField1 = new JTextField(10); 
	        select1 = new JButton("查询"); 
	        userName2 = new JLabel("客房信息：");  
	        textField2 = new JTextField(10); //用户输入文本框限定为最多输入10个字符  
	        select2 = new JButton("查询"); //查询按钮  
	       
	        //添加到第一个面板中   
	        panel1 = new Panel();  
	        panel1.add(userName1);  
	        panel1.add(textField1);  
	        panel1.add(select1);   
	        panel1.add(userName2);  
	        panel1.add(textField2);  
	        panel1.add(select2); 
	       
	        //窗口下部的组件  
	        insert = new JButton("登记");  
	        delete = new JButton("结账");  
	        return1 = new JButton("返回");  
	        //添加到第三个个面板中  
	        panel3 = new Panel();  
	        panel3.add(insert);  
	        panel3.add(delete);  
	        panel3.add(return1); 
	          
	        //窗口中部的组件  
	          bkModel = new BkModel();  
	          bkModel.insertDatabase("select * from 客房信息");  
	         table = new JTable(bkModel);  
	         jscrollPane = new JScrollPane(table);  
	           
	         //注册事件监听  

	         select1.addActionListener(this);
	         select2.addActionListener(this);
	         insert.addActionListener(this);  
	         return1.addActionListener(this);  
	         delete.addActionListener(this);  

	           
	          
	      //将各个组件添加到窗体中  
	        this.add(panel1, BorderLayout.NORTH);  
	        this.add(jscrollPane, BorderLayout.CENTER);  
	        this.add(panel3, BorderLayout.SOUTH);  
	          
	        //设置窗体属性  
	        this.setSize(400, 300);  //设置窗口大小  
	        this.setLocation(200, 200); //设置窗口显示的位置  
	        this.setTitle("酒店客房管理——王朝臣");  //设置窗口标题  
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置窗口关闭之后其后台程序同时关闭  
	        //设置窗口为可见  
	        this.setVisible(true);  
	          
	    }  
	      
	    public void actionPerformed(ActionEvent e)  
	    {  
	        //用户点击了查询按钮  
	        if(e.getSource() == select1)  
	        {  
	            String paras1 = textField1.getText().trim();  
	            bkModel = new BkModel();  
	            bkModel.insertDatabase("select * from 客房信息 where 姓名='"+paras1+"'");   
	            table.setModel(bkModel);  
	        }     
	      else if(e.getSource() == select2)  
	       {  
	            String paras2 = textField2.getText().trim();  
	           bkModel = new BkModel();  
	            bkModel.insertDatabase("select * from 客房信息 where 房间类型='"+paras2+"' and 姓名 is NULL"); 
	            table.setModel(bkModel);  
	      }     
	        //用户点击了登记按钮  
	        else if(e.getSource() == insert)  
	        {  
	            InsertBk insertBook = new InsertBk(this, "添加客房信息", true);  
	              
	            //刷新窗口表的数据  
	            bkModel = new BkModel();  
	            bkModel.insertDatabase("select * from 客房信息");  
	            table.setModel(bkModel);  
	        }  
	        //用户点击了返回按钮  
	        else if(e.getSource() == return1)  
	        {  
	        	this.dispose();
	    		BkMange sm = new BkMange();
	        }     
	        //用户点击了结账按钮  
	        else if(e.getSource() == delete)  
	        {  
	            //数据库  
	            Connection con = null;  
	            PreparedStatement ps = null;  
	            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //jdbc驱动  
	            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=酒店客房管理——王朝臣";  //数据源  
	            String user = "sa";  //数据库用户密码  
	            String passWord = "chen@198917"; //数据库用户sa的密码  
	              
	            int row = this.table.getSelectedRow();  //用户选择的行  
	            if(-1 == row)   //用户没有选中行  
	            {  
	                JOptionPane.showMessageDialog(this, "请选择一行");  
	            }  
	            else  
	            {  
	                try  
	                {  
	                    //加载jdbc驱动  
	                    Class.forName(driver);  
	                    //连接数据源  
	                    con = DriverManager.getConnection(url, user, passWord);  
	                    //执行sql  
	                    String sql = "delete from 客房信息  where 房间号=?";
	                    String id = (String)(bkModel.getValueAt(row, 0));    
	                    ps = con.prepareStatement(sql); 
	                    ps.setString(1, id); 
	                    ps.executeUpdate(); 
	                    
	                    String sql1 = "insert into 客房信息(房间号,价格,房间类型)  values('1202','800','标准间')";
	                    ps = con.prepareStatement(sql1); 
	                    ps.executeUpdate();  
	                      
	                    //刷新窗口  
	                    bkModel = new BkModel();  
	                    bkModel.insertDatabase("select * from 客房信息");  
	                    table.setModel(bkModel);  
	                      
	                }  
	                catch(ClassNotFoundException e1)  
	                {  
	                    e1.printStackTrace();  
	                }  
	                catch(SQLException e1)  
	                {  
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
	                  
	            }  
	        }  
	    }  

	}

