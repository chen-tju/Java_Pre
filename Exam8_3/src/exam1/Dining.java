package exam1;

public class Dining {
	static ChopStick cp[] = new ChopStick[5];
	static Philosopher ph[] = new Philosopher[5];
	public static void main(String args[]){
		for(int n = 0;n<5;n++){
			cp[n] = new ChopStick(n);
		}
		for(int n = 0; n<5;n++){
			ph[n] = new Philosopher("ÕÜÑ§¼Ò" + n,cp[n],cp[(n+1)%5]);
		}
		for(int n = 0;n<5;n++){
			ph[n].start();
		}
	}

}
