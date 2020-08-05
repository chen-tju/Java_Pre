package exam1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class PicturesSwitch extends JFrame implements ActionListener,ItemListener{
	String fname[] = new String[10];
	MyCard p1;
	MyThread th;
	JPanel p2 = new JPanel();
	JButton[] b = new JButton[6];
	String[] buttonTitle = {"¿ªÊ¼","Í£Ö¹","ÔÝÍ£","»Ö¸´","ÉÏÒ³","ÏÂÒ³"};
	JComboBox cc = new JComboBox();
	public PicturesSwitch(){
		for (int i = 0;i<10;i++){
			fname[i] = "y" + i + ".jpg";
			cc.addItem("pic"+i);
		}
		p1 = new MyCard(this);
		for(int i = 0;i<6;i++){
			b[i] = new JButton(buttonTitle[i]);
			p2.add(b[i]);
			b[i].addActionListener(this);
		}
		p2.add(cc);
		b[1].setEnabled(false);
		b[2].setEnabled(false);
		b[3].setEnabled(false);
		cc.addItemListener(this);
		this.getContentPane().add(p1, "Center");
		this.getContentPane().add(p2, "South");
		setSize(500,300);
		setTitle("Í¼Æ¬ÇÐ»»");
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		String s = e.getActionCommand();
		if(s.equals("¿ªÊ¼")){
			th = new MyThread(this);
			th.start();
			for(int i = 0;i<6;i++){
				b[i].setEnabled(false);
			}
			b[1].setEnabled(true);
			b[2].setEnabled(true);
			cc.setEnabled(false);
		}else if(s.equals("Í£Ö¹")){
			th.newstop();
			for (int i = 0;i<6;i++){
				b[i].setEnabled(true);
			}
			b[1].setEnabled(false);
			b[2].setEnabled(false);
			b[3].setEnabled(false);
			cc.setEnabled(true);
		}else if(s.equals("ÔÝÍ£")){
			th.newsuspend();
			for(int i = 0;i<6;i++){
				b[i].setEnabled(false);
			}
			b[3].setEnabled(true);
		}else if(s.equals("»Ö¸´")){
			th.newresume();
			for(int i = 0;i<6;i++){
				b[i].setEnabled(false);
			}
			b[1].setEnabled(true);
			b[2].setEnabled(true);
		}else if (s.equals("ÏÂÒ³")){
			p1.dd.next(p1);
		}else if(s.equals("ÉÏÒ³")){
			p1.dd.previous(p1);
		}
	}
	public void itemStateChanged(ItemEvent e){
		p1.dd.show(p1,(String)cc.getSelectedItem());
	}
	public static void main(String args[]){
		new PicturesSwitch();
	}

}
class MyThread extends Thread{
	PicturesSwitch fp;
	int n = 0,total = 0;
	private volatile Thread blinker;
	private volatile boolean threadSuspended;
	MyThread(PicturesSwitch fp){
		this.fp = fp;
		total = fp.fname.length;
		blinker = this;
	}
	@Override
	public void run(){
		Thread thisThread = Thread.currentThread();
		while (blinker == thisThread){
			try{
				Thread.sleep(1000);
				synchronized(this){
					while (threadSuspended&&blinker == thisThread){
						wait();
					}
				}
			}catch(InterruptedException e){
				
			}
			fp.cc.setSelectedIndex(n);
			n = (n+1)%total;
		}
	}
	public synchronized void newstop(){
		blinker = null;
		notify();
	}
	public synchronized void newsuspend(){
		threadSuspended = true;
	}
	public synchronized void newresume(){
		threadSuspended = false;
		notify();
	}
}
class MyCard extends JPanel{
	CardLayout dd = new CardLayout();
	MyCard(PicturesSwitch fp){
		setLayout(dd);
		for (int i = 0;i<fp.fname.length;i++){
			add("pic" + i,new JLabel(new ImageIcon(fp.fname[i])));
		}
	}
}
