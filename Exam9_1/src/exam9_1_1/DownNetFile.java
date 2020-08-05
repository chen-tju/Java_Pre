package exam9_1_1;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
class DownNetFile extends JFrame implements ActionListener{
	JTextField t1 = new JTextField(30);
	JTextArea t2 = new JTextArea();
	JButton b1 = new JButton("打开");
	JButton b2 = new JButton("下载");
	JPanel p = new JPanel();
	public DownNetFile(){
		p.add(t1);
		p.add(b1);
		p.add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);
		getContentPane().add(p, "North");
		getContentPane().add(new JScrollPane(t2), "Center");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		setTitle("读取网页内容");
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == b1){
			t2.setText(getDocumentAt(t1.getText()));
		}else{
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showSaveDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File file = chooser.getSelectedFile();
				String filename = file.getAbsolutePath();
				downDocumentAt(t1.getText(),filename);
			}
		}
	}
	public String getDocumentAt(String urlString){
		StringBuilder document = new StringBuilder();
		try{
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
			String line;
			while ((line = reader.readLine())!= null){
				document.append(line).append("\r\n");
			}
			reader.close();
		}catch (MalformedURLException e){
			JOptionPane.showMessageDialog(null,"网络连接失败！");
		}catch (IOException e){
			JOptionPane.showMessageDialog(null,"读取数据出现异常！");
		}
		return document.toString();
	}
	public void downDocumentAt(String urlString,String filename){
		try{
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			File file = new File(filename);
			FileOutputStream out = new FileOutputStream(file);
			InputStream in = conn.getInputStream();
			byte buf[] = new byte[1024];
			int n;
			while((n = in.read(buf,0,1024))>0){
				out.write(buf, 0, n);
			}
			in.close();
			out.close();
			JOptionPane.showMessageDialog(null, "下载完毕！");
		}catch(MalformedURLException e){
			JOptionPane.showMessageDialog(null, "网络连接失败！");
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "下载文件时出现异常！");
		}
	}
	public static void main(String args[]){
		new DownNetFile();
	}
}
//public class DownNetFile {

//}
