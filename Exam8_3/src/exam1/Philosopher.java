package exam1;

public class Philosopher extends Thread{
	ChopStick left,right;
	String name;
	public Philosopher (String name,ChopStick left,ChopStick right){
		this.name = name;
		this.left = left;
		this.right = right;
	}
	public void think(){
		left.putdown();
		right.putdown();
		System.out.println(name + "ÔÚË¼¿¼....");
	}
	public void eat(){
		left.takeup(name);
		right.takeup(name);
		System.out.println(name + "ÔÚ³Ô·¹....");
	}
	@Override
	public void run(){
		while(true){
			eat();
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				
			}
			think();
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e){
				
			}
		}
	}

}
