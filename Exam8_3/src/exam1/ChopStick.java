package exam1;

public class ChopStick {
	boolean available;
	int n;
	public ChopStick(int n){
		available = true;
		this.n = n;
	}
	public synchronized void takeup(String name){
		while(!available){
			System.out.println(name + "在等待拿起第" + n +"个筷子");
			try{
				wait();
			}catch(InterruptedException e){
				
			}
		}
		available = false;
	}
	public synchronized void putdown(){
		available = true;
		notify();
	}

}
