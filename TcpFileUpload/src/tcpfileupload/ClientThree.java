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
	private static JTextArea ta_info; // 创建JtextArea对象
    private JTextField tf_send; // 创建JtextField对象
  

	private void connect() {
		try{
			    File fi = getFile();   	
		        Socket s = new Socket("127.0.0.1",12345);        
		        DataOutputStream bos  = new DataOutputStream(s.getOutputStream());               
		        //BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:/Users/132/Desktop/TCP_File/Client/a.txt"));
		        DataInputStream bis = new DataInputStream(new FileInputStream(fi));
		        bos.writeUTF(fi.getName());
		        //IO流经典4行代码
		        byte[] b = new byte[1024];
		        int len;
		        while((len=bis.read(b))!=-1){
		            bos.write(b, 0, len);
		        }       
		        bos.flush();
		        s.shutdownOutput();

		        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		        //读取
		        String string = br.readLine();
		        ta_info.append(string);
		        //关闭资源
		        bis.close();//自己创建的字节缓冲输出流
		        s.close();//关闭Socket，其自己封装好的输入输出流也将关闭
		}catch (Exception e) {
            e.printStackTrace(); // 输出异常信息
        }		   	   
	}
	private static File getFile(){
		Scanner wj = new Scanner(System.in);
		//ta_info.append("请输入一个文件路径:\n");
		ta_info.setFont(new java.awt.Font("黑体", Font.PLAIN, 20));
		System.out.println("请输入一个文件路径:\n");
		while(true){
			String line = wj.nextLine();
			File file = new File(line);
			if(!file.exists()){
				System.out.println("文件路径不正确，请输入正确的文件路径！\n");
				ta_info.append("文件路径不正确，请输入正确的文件路径！\n");
			}else if(file.isDirectory()){
				System.out.println("输入的是一个文件夹路径，请输入一个文件路径！\n");
				ta_info.append("输入的是一个文件夹路径，请输入一个文件路径！\n");
			}else{
				return file;
			}
		}
		
    }
	public static void main(String[] args) { 
	    	ClientThree clien = new ClientThree(); // 创建本例对象
	        clien.setVisible(true); // 将窗体显示
	        clien.connect(); // 调用连接方法
	        
	    } 
	public ClientThree(){
    	setTitle("客户端");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        
        final JPanel p1 = new JPanel();
        //getContentPane().add(panel, BorderLayout.NORTH);
        final JLabel label = new JLabel();
        label.setForeground(new Color(0, 0, 255));
        label.setFont(new Font("", Font.BOLD, 22));
        label.setText("文件上传系统客户端");
        p1.add(label);
        
        //final JPanel p2 = new JPanel();
       //getContentPane().add(p2, BorderLayout.SOUTH);        
       // final JLabel label_1 = new JLabel();
       // label_1.setText("上传文件的路径：");
       // p2.add(label_1);        
       // tf_send = new JTextField();
       // tf_send.setPreferredSize(new Dimension(400, 25));
       // p2.add(tf_send);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                File inputFile = new File(tf_send.getText());
            	//writer.println(tf_send.getText()); // 将文本框中信息写入流
                ta_info.append("要上传文件的文件路径是：" + tf_send.getText()
                        + "\n"); // 将文本框中信息显示在文本域中
                tf_send.setText(""); // 将文本框清空
            }
        });
        //button.setText("确定");
        //p2.add(button);
        //JPanel top = new JPanel(new GridLayout(2,1));
        //top.add(p1);
       // top.add(p2);
        getContentPane().add(p1, BorderLayout.NORTH);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JLabel la = new JLabel();
        la.setText("信息1504王朝臣");
        panel.add(la);

        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        ta_info = new JTextArea();
        scrollPane.setViewportView(ta_info);
    	
    }
}
