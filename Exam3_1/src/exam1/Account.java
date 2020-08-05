package exam1;

import java.util.Date;

public class Account {
	private String ownerName;
	private String accountNo;
	private double balance;
	private Date datetime;
	public Account(String accountNo,String ownerName){
		this(accountNo,ownerName,0);
	}
	public Account(String accountNo,String ownerName,double balance){
		this.accountNo = accountNo;
		this.ownerName = ownerName;
		this.balance = balance;
		this.datetime = new Date();
	}
	//存款
	public void desposit(double amount){
		balance += amount;
		String recordStr = String.format("%1$s %2$tF %2$tT存款: %3$.2f,账户余额: %4$ .2f",accountNo,new Date(),amount,balance);
		System.out.println(recordStr);
	}
	//取款
	public void withdraw(double amount){
		if(amount <= balance){
			balance -= amount;
			String recordStr = String.format("%1$s %2$tF %2$tT取款: %3$.2f,账户余额: %4$.2f",accountNo,new Date(),amount,balance);
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
	public String getOwnerName(){
		return ownerName;
	}
	public void setOwnerName(String owerName){
		this.ownerName = ownerName;
	}
	public String getAccountNo(String accountNo){
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
