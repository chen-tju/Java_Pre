package tcpfileupload;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.FileOutputStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.DataOutputStream;
import java.io.DataInputStream;

public class Server extends JFrame { 
	private JTextArea ta_info;	
    public void GetServer() {
    	try{
    		 ServerSocket server = new ServerSocket(12345);
    		 ta_info.setFont(new java.awt.Font("����", Font.PLAIN, 20));
    		 ta_info.append("��������������12345�˿ں�\n"); // �����Ϣ
             //System.out.println("��������������12345�˿ں�");
             boolean listenning = true;
     		while (listenning)
     		{
     			//�ȴ��ͻ�����
     			Socket client = server.accept();
     			ta_info.append("�����ӽ���...�˿�"+client.getPort()+"...\n");
     			//Ϊ��socket����һ���߳�
     			new ClientThread(client).start();
     		}
     		server.close();
    		
    	}catch (Exception e) {
            e.printStackTrace(); // ����쳣��Ϣ
        }   
        
    }
    class ClientThread extends Thread
    {
    	private Socket client;
    	public ClientThread(Socket socket)
    	{
    		client=socket;
    	}
    	public void run()
    	{//Socket socket = null;
    		try{
    			//Socket s = server.accept();//����ʽ�����ͻ���ľ�������һֱ�ȴ�
    			//while(true){				
    			    DataInputStream bis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
    			    
    			   
    			    //IO������4�д���
    			    byte[] b = new byte[1024];
    			    long passedlen = 0;
    			    long len = 0;
    			    DataOutputStream bos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("C:/Users/132/Desktop/TCP_File/Server/�����ļ�---�˿ںţ�"+client.getPort()+"---"+bis.readUTF())));
    			    //while((len=bis.read(b))!=-1){
    			      //  bos.write(b, 0, len);
    			    //}
    			    len = bis.readLong();
    			    ta_info.append("�ļ��ĳ���Ϊ��    "+ len +"�ֽ�\n");
    			    ta_info.append("��ʼ�����ļ���" + "\n");
                   while(true){
                	   int read = 0;
                	   if(bis != null){
                		   read = bis.read(b);
                	   }
                	   passedlen += read;
                	   if(read == -1){
                		   break;
                	   }
                	   bos.write(b, 0, read);
                	   bos.flush();
                	   double a = Double.valueOf(String.valueOf(passedlen))/Double.valueOf(String.valueOf(len))*100;
                	   ta_info.append("�ļ�������ɶȣ� "+a+"%\n");
                	   while(a == 100) {
                		   ta_info.append("�ļ����ճɹ���");
                	   }
                   }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
    			    //5.ͨ���ͻ������Ӷ����ȡ�ֽ����������װ���ֽڻ��������
    			    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
    			    bw.write("�ļ��ϴ��ɹ�");
    			    ta_info.append("�ļ����ճɹ���\n");
    			    ta_info.append("�ͻ��˳ɹ����ջ�ִ��\n");
    			    bw.newLine();
    			    bw.flush();

    			    //6.�ر���Դ
    			    bos.close();
    			    client.close();
    			}
    		catch(Exception e){
    			ta_info.append("������Ϣ����"+"\n");
    			e.printStackTrace();
    		}
    		}
    			
    }
    public static void main(String[] args){
    	Server frame = new Server(); // �����������
    	frame.setVisible(true);// ��ʾ����
        frame.GetServer(); // ���÷���
       
    }
    public Server(){
    	setTitle("��������");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JLabel label = new JLabel();
        label.setText("��Ϣ1504������");
        panel.add(label);
        
    	    	
    	final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
    	final JLabel label_1 = new JLabel();
        label_1.setForeground(new Color(0, 0, 255));
        label_1.setFont(new Font("", Font.BOLD, 22));
        label_1.setText("�ļ��ϴ�ϵͳ��������");
        panel_1.add(label_1);
    	
    }
}


