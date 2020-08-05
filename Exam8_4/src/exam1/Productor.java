package exam1;

public class Productor extends Thread{
	Dish d;
	public Productor(String name,Dish d){
		super(name);
		this.d = d;
	}
	@Override
	public void run(){
		while (true){
			d.put();
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				
			}
		}
	}

}
