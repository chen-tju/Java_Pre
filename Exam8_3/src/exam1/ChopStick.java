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
			System.out.println(name + "�ڵȴ������" + n +"������");
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
