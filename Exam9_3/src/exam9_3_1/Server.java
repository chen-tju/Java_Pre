package exam9_3_1;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

class Server extends JFrame implements ActionListener{
	ServerSocket serverSock;
	Socket sock;
	JTextArea txtMsg = new JTextArea();
	JTextField txtSendMsg = new JTextField(20);
	JButton btnSend = new JButton("发送");
	JButton btnStart = new JButton("启动服务器");
	JButton btnStop = new JButton("停止服务器");
	DataOutputStream out;
	DataInputStream in;
	boolean canWaiter = true;
	boolean canAccepter = true;
	Accepter accepter;
	SWaiter waiter;
	public Server(){
		Container con = this.getContentPane();
		txtMsg.setEditable(false);
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		btnSend.setEnabled(false);
		txtSendMsg.setEditable(false);
		JPanel p1 = new JPanel();
		JScrollPane p2 = new JScrollPane(txtMsg);
		JPanel p3 = new JPanel();
		p1.add(btnStart);
		p1.add(btnStop);
		p3.add(txtSendMsg);
		p3.add(btnSend);
		con.add(p1, "North");
		con.add(p2, "Center");
		con.add(p3, "South");
		txtSendMsg.addActionListener(this);
		btnSend.addActionListener(this);
		btnStart.addActionListener(this);
		btnStop.addActionListener(this);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){				
				stopServer();
				dispose();
				System.exit(0);
			}
		});
		setTitle("服务器端");
		setSize(340,200);
		setVisible(true);
	}
	private void startServer(){
		try{
			serverSock = new ServerSocket(6000);
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
			accepter = new Accepter();
			accepter.start();
			waiter.start();
		}catch(IOException ee){
			JOptionPane.showMessageDialog(null, "启动服务器失败!");
		}
	}
	private void sendMsg(){
		if(!txtSendMsg.getText().equals("")){
			try{
				out.writeUTF(txtSendMsg.getText());
				txtMsg.append("服务器说:" + txtSendMsg.getText() + "\n");
			}catch(Exception ee){
				JOptionPane.showMessageDialog(null, "发送消息失败!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "不能发送空消息!");
		}
	}
	private void stopServer(){
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		
		canAccepter = false;
		try{
			out.writeUTF("serverStop");
		}catch(Exception ex){
			
		}finally{
			//canWaiter = false;
			try{
				//in.close();
				//out.close();
				serverSock.close();
			}catch(Exception ex){
				
			}finally{
				//try{
					//sock.close();
				//}catch(Exception ex){
					disconnect();
				//}
			}
	}
		
	}
	public void disconnect(){
		btnSend.setEnabled(false);
		txtSendMsg.setEditable(false);
		try{
			out.writeUTF("serverStop");
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
	private void acceptConnect(){
		try{
			OutputStream os = sock.getOutputStream();
			InputStream is = sock.getInputStream();
			out = new DataOutputStream(os);
			in = new DataInputStream(is);
			String msg = in.readUTF();
			txtMsg.append(msg + "\n");
			btnSend.setEnabled(true);
			txtSendMsg.setEditable(true);
			canWaiter = true;
			waiter = new SWaiter();
			waiter.start();
		}catch(Exception e){
			
		}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnSend || e.getSource() == txtSendMsg){
			sendMsg();
			txtSendMsg.setText("");
			txtSendMsg.requestFocus();
		}else if (e.getSource() == btnStart){
			canAccepter = true;
			canWaiter = true;
			startServer();
		}else if (e.getSource() == btnStop){
			stopServer();
		}
	}
	private class Accepter extends Thread{
		@Override
		public void run(){
			while(canAccepter){
				try{
					sock = serverSock.accept();
					acceptConnect();
				}catch(Exception ex){
					break;
				}
			}
			try{
				serverSock.close();
			}catch(Exception ex){
				
			}
		}
	}
	private class SWaiter extends Thread{
		@Override
		public void run(){
			String msg = null;
			while(canWaiter){
				try{
					msg = in.readUTF();
					if(msg.equals("disconnect")){
						txtMsg.append("客户离开\n");
						break;
					}
					txtMsg.append("客户说：" + msg + "\n");
				}catch(IOException e){
					break;
				}
			}
			txtMsg.append("断开连接\n");
			disconnect();
		}
	}
	public static void main(String args[]){
		new Server();
	}
}

