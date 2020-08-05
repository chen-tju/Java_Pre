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
    JButton update,canel;  //�ֱ�Ϊ�޸ĺ�ȡ����ť  
    Panel panel1 ,panel2, panel3;  
      
    public UpdateDatabase(Frame own, String title, boolean model, BkModel stModel, int row)  
    {  
        super(own, title, model); //ʵ���븸�ര�ڶԻ�  
          
        //�������������  
        id = new JLabel("�����:");  
        price = new JLabel("�۸�:"); 
        name = new JLabel("��������:"); 
        number = new JLabel("����֤����:");  
        name1 = new JLabel("����:"); 
        sex = new JLabel("�Ա�:"); 
        age= new JLabel("����:"); 
        time = new JLabel("��סʱ��:"); 
        time1 = new JLabel("���ʱ��:"); 
      
       
        //���ӵ�panel1�����  
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
        
        //���ӵ�����Ķ��������  
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
     
      
        //���ӵ��ڶ��������  
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
         
        //�����ϲ������  
        update = new JButton("ȷ��");  
        canel = new JButton("ȡ��");  
        panel3 = new Panel();  
        panel3.add(update);  
        panel3.add(canel);  
          
        //ע���¼�����  
        update.addActionListener(this);  
        canel.addActionListener(this);  
          
        //���������������  
        this.add(panel1, BorderLayout.WEST);  
        this.add(panel2, BorderLayout.CENTER);  
        this.add(panel3, BorderLayout.SOUTH);  
  
        //���ô�������  
        this.setSize(500, 400);  
        //���ô���Ϊ�ɼ�  
        this.setVisible(true);  
    }  
      
    //��Ӧ�û�����  
    public void actionPerformed(ActionEvent e)  
    {  
        //���ݿ�  
        Connection con = null;  
        PreparedStatement ps = null;  
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  //jdbc����  
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=�Ƶ�ͷ���������������"; //��������Դ  
        String user = "sa";  //���ݿ���û�  
        String passWord = "chen@198917"; //���ݿ���û�����  
        boolean result = true;   //��¼�޸��Ƿ�ɹ�  
          
        if(e.getSource() == update)  
        {  
            try  
            {  
                //����jdbc����  
                Class.forName(driver);  
                //��������Դ  
                con = DriverManager.getConnection(url,user,passWord);  
                //ִ��sql  
                String sql = "update �ͷ���Ϣ  set �۸�=?,��������=?,����֤����=?,����=?,�Ա�=?,����=?,��סʱ��=?,���ʱ��=? where �����=? ";  
                ps = con.prepareStatement(sql);  
                //�޸ķ�����Ϣ����  
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
                //�رնԻ���  
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
                if(false == result)  
                {  
                    JOptionPane.showMessageDialog(this, "ʧ����Ϣ������Ų����ظ�");  
                }  
                  
            }  
        }  
        else if(e.getSource() == canel)  
        {  
            this.dispose();//�رնԻ���  
        }  
    }  
  

}