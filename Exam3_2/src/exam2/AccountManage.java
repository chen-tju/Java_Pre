package exam2;

public class AccountManage {
	public static void main(String[] args){
		Account a1 = new Account("John",30);
		Account a2 = new Account("Mary");
		//Account a3 = new Account("������",100);
		a1.desposit(60);
		a1.withdraw(10);
		a2.desposit(100);
		//a3.desposit(100);
		a1.show();
		a2.show();
		//a3.show();
	
		System.out.println("�˺�������" + Account.getNumOfAccount());
		System.out.println("��������" + Account.getTotalBalance());
	}

}
