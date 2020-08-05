package exam1;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow  extends JDialog implements ActionListener{
	JTextField txtUsername = new JTextField(10);
	JPasswordField txtPassword = new JPasswordField(10);
	JButton btnOK = new JButton("确定");
	JButton btnCancel = new JButton("取消");
	public LoginWindow(){
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(3,1,5,5));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.add(new JLabel("用户名："));p1.add(txtUsername);
		p2.add(new JLabel("密 码："));p2.add(txtPassword);
		p3.add(btnOK);p3.add(btnCancel);
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);
		txtUsername.addActionListener(this);
		txtPassword.addActionListener(this);
		setSize(250,150);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((size.width - 300)/2,(size.height - 220)/2);
		setTitle("登录窗口");
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		setModal(true);
		setResizable(false);
		setVisible(true);

	}
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == btnOK || e.getSource() == txtPassword){
			if(txtUsername.getText().trim().equals("yang")&&new String(txtPassword.getPassword()).equals("1234")){
				dispose();
			}else{
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
				txtUsername.requestFocus();
			}
		}else if (e.getSource() ==btnCancel){
			dispose();
			System.exit(0);
		}else if(e.getSource() == txtUsername){
			txtPassword.requestFocus();
		}
	}
	public static void main(String args[]){
		new LoginWindow();
	}
	

}
