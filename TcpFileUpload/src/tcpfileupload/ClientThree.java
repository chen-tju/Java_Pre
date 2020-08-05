package tcpfileupload;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.FileInputStream;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.File;

public class ClientThree extends JFrame {
	private static JTextArea ta_info; // ����JtextArea����
    private JTextField tf_send; // ����JtextField����
  

	private void connect() {
		try{
			    File fi = getFile();   	
		        Socket s = new Socket("127.0.0.1",12345);        
		        DataOutputStream bos  = new DataOutputStream(s.getOutputStream());               
		        //BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:/Users/132/Desktop/TCP_File/Client/a.txt"));
		        DataInputStream bis = new DataInputStream(new FileInputStream(fi));
		        bos.writeUTF(fi.getName());
		        //IO������4�д���
		        byte[] b = new byte[1024];
		        int len;
		        while((len=bis.read(b))!=-1){
		            bos.write(b, 0, len);
		        }       
		        bos.flush();
		        s.shutdownOutput();

		        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		        //��ȡ
		        String string = br.readLine();
		        ta_info.append(string);
		        //�ر���Դ
		        bis.close();//�Լ��������ֽڻ��������
		        s.close();//�ر�Socket�����Լ���װ�õ����������Ҳ���ر�
		}catch (Exception e) {
            e.printStackTrace(); // ����쳣��Ϣ
        }		   	   
	}
	private static File getFile(){
		Scanner wj = new Scanner(System.in);
		//ta_info.append("������һ���ļ�·��:\n");
		ta_info.setFont(new java.awt.Font("����", Font.PLAIN, 20));
		System.out.println("������һ���ļ�·��:\n");
		while(true){
			String line = wj.nextLine();
			File file = new File(line);
			if(!file.exists()){
				System.out.println("�ļ�·������ȷ����������ȷ���ļ�·����\n");
				ta_info.append("�ļ�·������ȷ����������ȷ���ļ�·����\n");
			}else if(file.isDirectory()){
				System.out.println("�������һ���ļ���·����������һ���ļ�·����\n");
				ta_info.append("�������һ���ļ���·����������һ���ļ�·����\n");
			}else{
				return file;
			}
		}
		
    }
	public static void main(String[] args) { 
	    	ClientThree clien = new ClientThree(); // ������������
	        clien.setVisible(true); // ��������ʾ
	        clien.connect(); // �������ӷ���
	        
	    } 
	public ClientThree(){
    	setTitle("�ͻ���");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        
        final JPanel p1 = new JPanel();
        //getContentPane().add(panel, BorderLayout.NORTH);
        final JLabel label = new JLabel();
        label.setForeground(new Color(0, 0, 255));
        label.setFont(new Font("", Font.BOLD, 22));
        label.setText("�ļ��ϴ�ϵͳ�ͻ���");
        p1.add(label);
        
        //final JPanel p2 = new JPanel();
       //getContentPane().add(p2, BorderLayout.SOUTH);        
       // final JLabel label_1 = new JLabel();
       // label_1.setText("�ϴ��ļ���·����");
       // p2.add(label_1);        
       // tf_send = new JTextField();
       // tf_send.setPreferredSize(new Dimension(400, 25));
       // p2.add(tf_send);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                File inputFile = new File(tf_send.getText());
            	//writer.println(tf_send.getText()); // ���ı�������Ϣд����
                ta_info.append("Ҫ�ϴ��ļ����ļ�·���ǣ�" + tf_send.getText()
                        + "\n"); // ���ı�������Ϣ��ʾ���ı�����
                tf_send.setText(""); // ���ı������
            }
        });
        //button.setText("ȷ��");
        //p2.add(button);
        //JPanel top = new JPanel(new GridLayout(2,1));
        //top.add(p1);
       // top.add(p2);
        getContentPane().add(p1, BorderLayout.NORTH);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JLabel la = new JLabel();
        la.setText("��Ϣ1504������");
        panel.add(la);

        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
    	
    }
}
