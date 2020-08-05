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
	//存款
	public void desposit(double amount){
		balance += amount;
		String recordStr = String.format("%1$s %2$tF %2$tT 存款: %3$.2f,账户余额: %4$ .2f",accountNo,new Date(),amount,balance);
		System.out.println(recordStr);
	}
	//取款
	public void withdraw(double amount){
		if(amount <= balance){
			balance -= amount;
			String recordStr = String.format("%1$s %2$tF %2$tT 取款: %3$.2f,账户余额: %4$.2f",accountNo,new Date(),amount,balance);
			System.out.println(recordStr);
		}else{
			//System.out.println("余额不足，不能支取！");
			String recordStr = String.format("%1$s 余额不足，不能支取！",accountNo);
			System.out.println(recordStr);
		}
	}
	public void show(){
		System.out.println("姓名：" +ownerName);
		System.out.println("账号：" +accountNo);
		System.out.println("当前余额：" + new java.text.DecimalFormat(".00").format(balance));
		System.out.println("账号创建时间：" + new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(datetime));
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
