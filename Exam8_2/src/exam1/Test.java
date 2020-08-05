package exam1;

public class Test {
	public static void main(String[] args){
		Account account = new Account("����","1234",100);
		MyThread t1 = new MyThread("����",account,20);
		MyThread t2 = new MyThread("�����",account,-60);
		MyThread t3 = new MyThread("������",account,-80);
		MyThread t4 = new MyThread("С����",account,-30);
		MyThread t5 = new MyThread("�Ķ���",account,-10);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();		
	}

}
class MyThread extends Thread{
	private Account account;
	private int amount;
	MyThread(String name,Account account,int amount){
		super(name);
		this.account = account;
		this.amount = amount;
	}
	@Override
	public void run(){
		try{
			Thread.sleep(10);
		}catch(InterruptedException ex){
			
		}
		if(amount>=0){
			account.desposit(amount);
		}else{
			account.withdraw(-amount);
		}
	}
}
