package exam2;

import java.util.Date;

public class Account {
	private String ownerName;
	private String accountNo;
	private double balance;
	private Date datetime;
	public static long numOfAccount;
	public static double totalBalance;
	
	public Account(String ownerName){
		this(ownerName,0);
	}
	public Account(String ownerName,double balance){
		numOfAccount++;		
		this.accountNo = newAccountNo();
		this.ownerName = ownerName;
		this.balance = balance;  
		this.datetime = new Date();
		//totalBalance += Account.balance;
	}
	//���
	public void desposit(double amount){
		balance += amount;
		String recordStr = String.format("%1$s %2$tF %2$tT ���: %3$.2f,�˻����: %4$ .2f",accountNo,new Date(),amount,balance);
		System.out.println(recordStr);
	}
	//ȡ��
	public void withdraw(double amount){
		if(amount <= balance){
			balance -= amount;
			String recordStr = String.format("%1$s %2$tF %2$tT ȡ��: %3$.2f,�˻����: %4$.2f",accountNo,new Date(),amount,balance);
			System.out.println(recordStr);
		}else{
			//System.out.println("���㣬����֧ȡ��");
			String recordStr = String.format("%1$s ���㣬����֧ȡ��",accountNo);
			System.out.println(recordStr);
		}
	}
	public void show(){
		System.out.println("������" +ownerName);
		System.out.println("�˺ţ�" +accountNo);
		System.out.println("��ǰ��" + new java.text.DecimalFormat(".00").format(balance));
		System.out.println("�˺Ŵ���ʱ�䣺" + new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(datetime));
	}
	public static long getNumOfAccount(){
		return numOfAccount;
	}
	public static void setTotalBalance(double totalBalance){
		Account.totalBalance  = totalBalance;
		//totalBalance += new java.text.DecimalFormat(".00").format(balance);
	}
	public static double getTotalBalance(){	
		
		return totalBalance;
	}
	private String newAccountNo(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<12;i++){
			sb.append((int)(Math.random()*10));
		}
		sb.append(String.format("%04d", numOfAccount));
		return sb.toString();
	}
	public String getOwnerName(){
		return ownerName;
	}
	public void setOwnerName(String owerName){
		this.ownerName = ownerName;
	}
	public String getAccountNo(){
		return accountNo;
	}
	public void setAccuntNo(String accountNo){
		this.accountNo = accountNo;
	}
	public void setBalance(double balance){
		this.balance = balance;
	}
	public double getBalance(){
		return balance;
	}
	public Date getDatetime(){
		return datetime;
	}
	

}
