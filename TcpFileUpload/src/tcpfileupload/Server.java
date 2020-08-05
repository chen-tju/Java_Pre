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
    		 ta_info.setFont(new java.awt.Font("宋体", Font.PLAIN, 20));
    		 ta_info.append("服务器启动，绑定12345端口号\n"); // 输出信息
             //System.out.println("服务器启动，绑定12345端口号");
             boolean listenning = true;
     		while (listenning)
     		{
     			//等待客户连接
     			Socket client = server.accept();
     			ta_info.append("有连接接入...端口"+client.getPort()+"...\n");
     			//为该socket创建一个线程
     			new ClientThread(client).start();
     		}
     		server.close();
    		
    	}catch (Exception e) {
            e.printStackTrace(); // 输出异常信息
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
    			//Socket s = server.accept();//阻塞式，即客户端木有请求就一直等待
    			//while(true){				
    			    DataInputStream bis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
    			    
    			   
    			    //IO流经典4行代码
    			    byte[] b = new byte[1024];
    			    long passedlen = 0;
    			    long len = 0;
    			    DataOutputStream bos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("C:/Users/132/Desktop/TCP_File/Server/接收文件---端口号："+client.getPort()+"---"+bis.readUTF())));
    			    //while((len=bis.read(b))!=-1){
    			      //  bos.write(b, 0, len);
    			    //}
    			    len = bis.readLong();
    			    ta_info.append("文件的长度为：    "+ len +"字节\n");
    			    ta_info.append("开始接收文件！" + "\n");
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
                	   ta_info.append("文件接收完成度： "+a+"%\n");
                	   while(a == 100) {
                		   ta_info.append("文件接收成功！");
                	   }
                   }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
    			    //5.通过客户端连接对象获取字节输出流并包装成字节缓冲输出流
    			    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
    			    bw.write("文件上传成功");
    			    ta_info.append("文件接收成功！\n");
    			    ta_info.append("客户端成功接收回执！\n");
    			    bw.newLine();
    			    bw.flush();

    			    //6.关闭资源
    			    bos.close();
    			    client.close();
    			}
    		catch(Exception e){
    			ta_info.append("接收消息错误"+"\n");
    			e.printStackTrace();
    		}
    		}
    			
    }
    public static void main(String[] args){
    	Server frame = new Server(); // 创建本类对象
    	frame.setVisible(true);// 显示窗体
        frame.GetServer(); // 调用方法
       
    }
    public Server(){
    	setTitle("服务器端");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JLabel label = new JLabel();
        label.setText("信息1504王朝臣");
        panel.add(label);
        
    	    	
    	final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
    	final JLabel label_1 = new JLabel();
        label_1.setForeground(new Color(0, 0, 255));
        label_1.setFont(new Font("", Font.BOLD, 22));
        label_1.setText("文件上传系统服务器端");
        panel_1.add(label_1);
    	
    }
}


