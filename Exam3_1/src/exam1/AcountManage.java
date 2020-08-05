package exam1;

public class AcountManage {
	public static void main(String[] args){
		Account a1 = new Account("10001","John",30);
		Account a2 = new Account("10002","Mary");
		a1.desposit(60);
		a1.withdraw(100);
		a2.desposit(100);
		a1.show();
		a2.show();
	}

}
