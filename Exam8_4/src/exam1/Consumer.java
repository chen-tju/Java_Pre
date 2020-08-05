package exam1;

public class Consumer extends Thread{
	Dish d;
	int tim;
	public Consumer(String name,Dish d,int tim){
		super(name);
		this.d = d;
		this.tim = tim;
	}
	@Override
	public void run(){
		while(true){
			d.get();
			try{
				Thread.sleep(tim);
			}catch(InterruptedException e){
				
			}
		}
	}

}
