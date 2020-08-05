package exam1;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class CopyPicture extends JFrame implements ActionListener{
	JTextField t1 = new JTextField(20);
	JTextField t2 = new JTextField(20);
	JTextArea tt =new JTextArea(10,10);
	JButton btn = new JButton("复制");
	public CopyPicture(){
		JPanel p1 = new JPanel();
		p1.add(new JLabel("源文件："));
		p1.add(t1);
		JPanel p2 = new JPanel();
		p2.add(new JLabel("目标文件："));
		p2.add(t2);
		JPanel top = new JPanel(new GridLayout(2,1));
		top.add(p1);
		top.add(p2);
		JScrollPane center = new JScrollPane(tt);
		JPanel bottom = new JPanel();
		bottom.add(btn);
		this.getContentPane().add(top, "North");
		this.getContentPane().add(center, "Center");
		this.getContentPane().add(bottom, "South");
		btn.addActionListener(this);
		setTitle("用字节流复制文件");
		setSize(800,600);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		final int bsize = 1024;
		byte[] buffer = new byte[bsize];
		try{
			File inputFile = new File(t1.getText());
			File outputFile = new File(t2.getText());
			if(!outputFile.exists()){
				outputFile.createNewFile();
			}
			FileInputStream fis = new FileInputStream(inputFile);
			FileOutputStream fos = new FileOutputStream(outputFile);
			tt.setText("");
			tt.append("Read from " + inputFile.getAbsolutePath() + "\n");
			tt.append("Write to" + outputFile.getAbsolutePath() + "\n");
			int bytes;
			while((bytes = fis.read(buffer, 0, bsize))!=-1){
				fos.write(buffer, 0, bytes);
				tt.append("Read" + bytes + "B" + " Write" + bytes + "B" + "\n");
			}
			tt.append("复制完成\n");
			fis.close();
			fos.close();
		}catch(Exception err){
			tt.setText("复制失败");
		}
	}
	public static void main(String[] args) throws IOException{
		JFrame.setDefaultLookAndFeelDecorated(true);
		new CopyPicture();
	}

}
