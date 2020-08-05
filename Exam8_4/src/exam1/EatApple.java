package exam1;

public class EatApple {
	public static void main(String[] args){
		Thread th1,th2,th3,th4,th5;
		Dish d = new Dish(20);
		th1 = new Productor("妈妈",d);
		th2 = new Productor("爸爸",d);
		th3 = new Consumer ("老大",d,1000);
		th4 = new Consumer ("老二",d,1200);
		th5 = new Consumer ("老三",d,1500);
		th3.setPriority(Thread.NORM_PRIORITY - 2);
		th4.setPriority(Thread.NORM_PRIORITY - 1);
		th5.setPriority(Thread.NORM_PRIORITY);
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
	}

}
