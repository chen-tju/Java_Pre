package exam1;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class CopyCharFile extends JFrame implements ActionListener{
	JTextField t1 = new JTextField(30);
	JTextField t2 = new JTextField(30);
	JButton btn = new JButton("����");
	public CopyCharFile(){
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Դ�ļ���"));
		p1.add(t1);
		JPanel p2 = new JPanel();
		p2.add(new JLabel("Ŀ���ļ���"));
		p2.add(t2);
		JPanel p3 = new JPanel();
		p3.add(btn);
		this.getContentPane().setLayout(new GridLayout(3,1));
		this.getContentPane().add(p1);
		this.getContentPane().add(p2);
		this.getContentPane().add(p3);
		btn.addActionListener(this);
		setTitle("���ַ��������ļ�");
		setSize(450,150);
		setVisible(true);

	}
	public void actionPerformed(ActionEvent e){
		try{
			File inputFile = new File(t1.getText());
			File outputFile = new File(t2.getText());
			FileReader in = new FileReader(inputFile);
			BufferedReader bin = new BufferedReader(in);
			FileWriter out = new FileWriter(outputFile);
			BufferedWriter bout = new BufferedWriter (out);
			while(bin.ready()){
				String str = bin.readLine();
				bout.write(str);
			}
			bin.close();
			bout.close();
			in.close();
			out.close();
			JOptionPane.showMessageDialog(null, "���Ƴɹ���");
		}catch(IOException ee){
			System.err.println(ee);
		}
	}
	public static void main(String[] args)throws IOException{
		JFrame.setDefaultLookAndFeelDecorated(true);
		new CopyCharFile();
	}


}
