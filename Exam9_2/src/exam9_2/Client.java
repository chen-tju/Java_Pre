package exam9_2;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

//import javax.swing.JButton;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//                                         import javax.swing.JTextField;
class Client extends JFrame implements ActionListener{
	
	Socket sock;
	JTextArea txtMsg = new JTextArea();
	JTextField txtSendMsg = new JTextField(20);
	JButton btnSend = new JButton("����");
	JButton btnConnect = new JButton("���ӷ�����");
	JButton btnDisConnect = new JButton("�Ͽ�����");
	DataOutputStream out;
	DataInputStream in;
	boolean canWaiter = true;
	CWaiter waiter;
	public Client(){
		Container con = this.getContentPane();
		txtMsg.setEditable(false);
		btnConnect.setEnabled(true);
		btnDisConnect.setEnabled(false);
		btnSend.setEnabled(false);
		txtSendMsg.setEditable(false);
		JPanel p1 = new JPanel();
		JScrollPane p2 = new JScrollPane(txtMsg);
		JPanel p3 = new JPanel();
		p1.add(btnConnect);
		p1.add(btnDisConnect);
		p3.add(txtSendMsg);
		p3.add(btnSend);
		con.add(p1, "North");
		con.add(p2, "Center");
		con.add(p3, "South");
		txtSendMsg.addActionListener(this);
		btnSend.addActionListener(this);
		btnConnect.addActionListener(this);
		btnDisConnect.addActionListener(this);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				try{
					disconnect();
				}catch(Exception ee){
					
				}
				dispose();
				System.exit(0);
			}
		});
		setTitle("�ͻ���");
		setSize(340,200);
		setVisible(true);
	}
	private void connect(){
		try{
			sock = new Socket("127.0.0.1",6000);
			OutputStream os = sock.getOutputStream();
			InputStream is = sock.getInputStream();
			out = new DataOutputStream(os);
			in = new DataInputStream(is);
			out.writeUTF("�ͻ�����");
			txtMsg.append("���ӳɹ�\n");
			btnConnect.setEnabled(false);
			btnDisConnect.setEnabled(true);
			btnSend.setEnabled(true);
			txtSendMsg.setEditable(true);
			waiter = new CWaiter();
			waiter.start();
		}catch(Exception ee){
			JOptionPane.showMessageDialog(null, "���ӷ�����ʧ��!");
		}
	}
	private void sendMsg(){
		if(!txtSendMsg.getText().equals("")){
			try{
				out.writeUTF(txtSendMsg.getText());
				txtMsg.append("�ͻ�˵:" + txtSendMsg.getText() + "\n");
			}catch(Exception ee){
				JOptionPane.showMessageDialog(null, "������Ϣʧ��!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "���ܷ��Ϳ���Ϣ!");
		}
	}
	private void disconnect(){
		btnConnect.setEnabled(true);
		btnDisConnect.setEnabled(false);
		btnSend.setEnabled(false);
		txtSendMsg.setEditable(false);
		try{
			out.writeUTF("disconnect");
		}catch(Exception ex){
			
		}finally{
			canWaiter = false;
			try{
				in.close();
				out.close();
			}catch(Exception ex){
				
			}finally{
				try{
					sock.close();
				}catch(Exception ex){
					
				}
			}
	}
		
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnSend || e.getSource() == txtSendMsg){
			sendMsg();
			txtSendMsg.setText("");
			txtSendMsg.requestFocus();
		}else if (e.getSource() == btnConnect){
			canWaiter = true;
			connect();
		}else if (e.getSource() == btnDisConnect){
			disconnect();
		}
	}
	private class CWaiter extends Thread{
		@Override
		public void run(){
			String msg;
			while(canWaiter){
				try{
					msg = in.readUTF();
					if(msg.equals("serverStop")){
						txtMsg.append("������ֹͣ\n");
						break;
					}
					txtMsg.append("������˵��" + msg + "\n");
				}catch(IOException ex){
					break;
				}
			}
			txtMsg.append("�ͻ��뿪\n");
			disconnect();
		}
	}
	public static void main(String args[]){
		new Client();
	}
}
//public class Client {

//}
