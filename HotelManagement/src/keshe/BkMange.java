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
		//�����û������õ������  
	    JLabel userName1 = null;
	    JLabel userName2 = null;//Ҫ��ѯ�ķ����  
	    JTextField textField1 = null;
	    JTextField textField2 = null;//�û�������ı���  
	    JButton select1 = null; 
	    JButton select2 = null; //��ѯ��ť  
	    JButton insert = null;    //�Ǽǰ�ť  
	    JButton return1 = null;    //�޸İ�ť  
	    JButton delete  = null;  //���˰�ť  
	    JTable table = null;      //������ʾ���ݿ���������ݵı�  
	    Panel panel1 = null;       
	    Panel panel2 = null;  
	    Panel panel3 = null;   
	    JScrollPane jscrollPane = null;  
	      
	    BkModel bkModel = null;  //bk���ģ��  
	    private Object PreparedStatement;  
	    public BkMange()  
	    {  
	        //������ʾ���ϲ������  
	        userName1 = new JLabel("������Ϣ��");
	        textField1 = new JTextField(10); 
	        select1 = new JButton("��ѯ"); 
	        userName2 = new JLabel("�ͷ���Ϣ��");  
	        textField2 = new JTextField(10); //�û������ı����޶�Ϊ�������10���ַ�  
	        select2 = new JButton("��ѯ"); //��ѯ��ť  
	       
	        //��ӵ���һ�������   
	        panel1 = new Panel();  
	        panel1.add(userName1);  
	        panel1.add(textField1);  
	        panel1.add(select1);   
	        panel1.add(userName2);  
	        panel1.add(textField2);  
	        panel1.add(select2); 
	       
	        //�����²������  
	        insert = new JButton("�Ǽ�");  
	        delete = new JButton("����");  
	        return1 = new JButton("����");  
	        //��ӵ��������������  
	        panel3 = new Panel();  
	        panel3.add(insert);  
	        panel3.add(delete);  
	        panel3.add(return1); 
	          
	        //�����в������  
	          bkModel = new BkModel();  
	          bkModel.insertDatabase("select * from �ͷ���Ϣ");  
	         table = new JTable(bkModel);  
	         jscrollPane = new JScrollPane(table);  
	           
	         //ע���¼�����  

	         select1.addActionListener(this);
	         select2.addActionListener(this);
	         insert.addActionListener(this);  
	         return1.addActionListener(this);  
	         delete.addActionListener(this);  

	           
	          
	      //�����������ӵ�������  
	        this.add(panel1, BorderLayout.NORTH);  
	        this.add(jscrollPane, BorderLayout.CENTER);  
	        this.add(panel3, BorderLayout.SOUTH);  
	          
	        //���ô�������  
	        this.setSize(400, 300);  //���ô��ڴ�С  
	        this.setLocation(200, 200); //���ô�����ʾ��λ��  
	        this.setTitle("�Ƶ�ͷ�������������");  //���ô��ڱ���  
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //���ô��ڹر�֮�����̨����ͬʱ�ر�  
	        //���ô���Ϊ�ɼ�  
	        this.setVisible(true);  
	          
	    }  
	      
	    public void actionPerformed(ActionEvent e)  
	    {  
	        //�û�����˲�ѯ��ť  
	        if(e.getSource() == select1)  
	        {  
	            String paras1 = textField1.getText().trim();  
	            bkModel = new BkModel();  
	            bkModel.insertDatabase("select * from �ͷ���Ϣ where ����='"+paras1+"'");   
	            table.setModel(bkModel);  
	        }     
	      else if(e.getSource() == select2)  
	       {  
	            String paras2 = textField2.getText().trim();  
	           bkModel = new BkModel();  
	            bkModel.insertDatabase("select * from �ͷ���Ϣ where ��������='"+paras2+"' and ���� is NULL"); 
	            table.setModel(bkModel);  
	      }     
	        //�û�����˵Ǽǰ�ť  
	        else if(e.getSource() == insert)  
	        {  
	            InsertBk insertBook = new InsertBk(this, "��ӿͷ���Ϣ", true);  
	              
	            //ˢ�´��ڱ������  
	            bkModel = new BkModel();  
	            bkModel.insertDatabase("select * from �ͷ���Ϣ");  
	            table.setModel(bkModel);  
	        }  
	        //�û�����˷��ذ�ť  
	        else if(e.getSource() == return1)  
	        {  
	        	this.dispose();
	    		BkMange sm = new BkMange();
	        }     
	        //�û�����˽��˰�ť  
	        else if(e.getSource() == delete)  
	        {  
	            //���ݿ�  
	            Connection con = null;  
	            PreparedStatement ps = null;  
	            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //jdbc����  
	            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=�Ƶ�ͷ�������������";  //����Դ  
	            String user = "sa";  //���ݿ��û�����  
	            String passWord = "chen@198917"; //���ݿ��û�sa������  
	              
	            int row = this.table.getSelectedRow();  //�û�ѡ�����  
	            if(-1 == row)   //�û�û��ѡ����  
	            {  
	                JOptionPane.showMessageDialog(this, "��ѡ��һ��");  
	            }  
	            else  
	            {  
	                try  
	                {  
	                    //����jdbc����  
	                    Class.forName(driver);  
	                    //��������Դ  
	                    con = DriverManager.getConnection(url, user, passWord);  
	                    //ִ��sql  
	                    String sql = "delete from �ͷ���Ϣ  where �����=?";
	                    String id = (String)(bkModel.getValueAt(row, 0));    
	                    ps = con.prepareStatement(sql); 
	                    ps.setString(1, id); 
	                    ps.executeUpdate(); 
	                    
	                    String sql1 = "insert into �ͷ���Ϣ(�����,�۸�,��������)  values('1202','800','��׼��')";
	                    ps = con.prepareStatement(sql1); 
	                    ps.executeUpdate();  
	                      
	                    //ˢ�´���  
	                    bkModel = new BkModel();  
	                    bkModel.insertDatabase("select * from �ͷ���Ϣ");  
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
	                    //�ر���Դ  
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

