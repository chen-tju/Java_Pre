package exam1;

public class Dish {
	int f = 4;
	int num;
	int n = 0;
	public Dish(int num){
		this.num = num;
	}
	synchronized void put(){
		while(f==0){
			try{
				System.out.println(Thread.currentThread().getName() + "�ȴ���ƻ��");
				wait();
			}catch(InterruptedException e){
				
			}
		}
		int x = (int)(Math.random()*f) + 1;
		f = f-x;
		n = n+x;
		System.out.println(Thread.currentThread().getName() + "��" + x + "��ƻ��");
		notify();
	}
	synchronized void get(){
		while(f==4){
			try{
				System.out.println(Thread.currentThread().getName() + "�ȴ�ȡƻ��");
				wait();
			}catch(InterruptedException e){				
			}			
		}
		f = f+1;
		System.out.println(Thread.currentThread().getName() + "ȡƻ����....");
		notify();
	}

}
