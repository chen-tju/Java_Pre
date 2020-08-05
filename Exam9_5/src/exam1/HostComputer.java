package exam1;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import javax.swing.*;
public class HostComputer extends JFrame implements ActionListener{
	JTextArea txtMsg = new JTextArea();
	JTextField txtSendMsg = new JTextField(20);
	JTextField txtToAddr = new JTextField(15);
	JTextField txtToPort = new JTextField(5);
	JTextField txtLocalPort = new JTextField(5);
	JButton btnSend = new JButton("����");
	JButton btnStart = new JButton("��ʼ");
	byte[] buf = new byte[1024];
	InetAddress toAddress;
	int toPort;
	int localPort;
	DatagramSocket socket;
	boolean canWaiter;
	public HostComputer(){
		txtSendMsg.setEditable(false);
		txtMsg.setEditable(false);
		txtMsg.setEditable(false);
		btnSend.setEnabled(false);
		Container con = this.getContentPane();
		JPanel p1 = new JPanel(new GridLayout(2,1));
		JPanel p11 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p12 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JScrollPane p2 = new JScrollPane(txtMsg);
		JPanel p3 = new JPanel();
		p11.add(new JLabel("�Է���ַ"));
		p11.add(txtToAddr);
		p11.add(new JLabel("�Է��˿�"));
		p11.add(txtToPort);
		p12.add(new JLabel("���ض˿�"));
		p12.add(txtLocalPort);
		p12.add(btnStart);
		p1.add(p11);
		p1.add(p12);
		p3.add(txtSendMsg);
		p3.add(btnSend);
		txtSendMsg.addActionListener(this);
		btnSend.addActionListener(this);
		btnStart.addActionListener(this);
		con.add(p1, "North");
		con.add(p2, "Center");
		con.add(p3, "South");
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				try{
					stop();
				}catch(Exception ee){
					
				}
				dispose();
				System.exit(0);
			}
		});
		setTitle("�������ݱ�ͨ��");
		setSize(500,300);
		setVisible(true);
	}
	private void start(){
		try{
			toAddress = InetAddress.getByName(txtToAddr.getText());
			toPort = Integer.parseInt(txtToPort.getText());
			localPort = Integer.parseInt(txtLocalPort.getText());
			socket = new DatagramSocket(localPort);
			canWaiter = true;
			(new Waiter()).start();
			txtToAddr.setEditable(false);
			txtToPort.setEditable(false);
			txtLocalPort.setEditable(false);
			txtSendMsg.setEditable(true);
			btnSend.setEnabled(true);
			btnStart.setText("ֹͣ");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
		}
	}
	private void stop(){
		try{
			txtToAddr.setEditable(true);
			txtToPort.setEditable(true);
			txtLocalPort.setEditable(true);
			txtSendMsg.setEditable(false);
			btnSend.setEnabled(false);
			canWaiter = false;
			toAddress = null;
			socket.close();
			btnStart.setText("��ʼ");
		}catch(Exception ex){
			
		}
	}
	private void send(){
		try{
			if(!txtSendMsg.getText().equals("")){
				byte[] b = txtSendMsg.getText().getBytes();
				DatagramPacket packet = new DatagramPacket(b,b.length,toAddress,toPort);
				socket.send(packet);
				txtMsg.append("���͵����ݣ�" + txtSendMsg.getText() + "\n");
				txtMsg.append("���ݷ��͵���" + toAddress + "\n");
				txtMsg.append("���ݳ���Ϊ��" + packet.getLength() + "\n");
				txtSendMsg.setText("");
				txtSendMsg.requestFocus();
			}else{
				JOptionPane.showMessageDialog(null, "���ܷ��Ϳ���Ϣ");
			}
		}catch(Exception ee){
			ee.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == txtSendMsg || e.getSource() == btnSend){
			send();
		}else if (e.getActionCommand().equals("��ʼ")){
			start();
			txtSendMsg.requestFocus();
		}else if (e.getActionCommand().equals("ֹͣ")){
			stop();
		}
	}
	class Waiter extends Thread{
		@Override
		public void run(){
			while(canWaiter){
				try{
					DatagramPacket parket = new DatagramPacket(buf,buf.length);
					socket.receive(parket);
					String received = new String (parket.getData(),0,parket.getLength());
					txtMsg.append("�յ�������Ϊ��" + received + "\n");
					txtMsg.append("���������ڣ�" + parket.getAddress() + "\n");
					txtMsg.append("���ݳ���Ϊ��" + parket.getLength() + "\n");
				}catch(IOException ex){
					
				}
			}
		}
	}
	public static void main(String[] args){
		new HostComputer();
	}

}
