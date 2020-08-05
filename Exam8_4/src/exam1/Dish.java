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
				System.out.println(Thread.currentThread().getName() + "等待放苹果");
				wait();
			}catch(InterruptedException e){
				
			}
		}
		int x = (int)(Math.random()*f) + 1;
		f = f-x;
		n = n+x;
		System.out.println(Thread.currentThread().getName() + "放" + x + "个苹果");
		notify();
	}
	synchronized void get(){
		while(f==4){
			try{
				System.out.println(Thread.currentThread().getName() + "等待取苹果");
				wait();
			}catch(InterruptedException e){				
			}			
		}
		f = f+1;
		System.out.println(Thread.currentThread().getName() + "取苹果吃....");
		notify();
	}

}
