package exam1;

public class Book {
	private String bookNo;
	private String bookName;
	private float bookPrice;
	public Book(){
		
	}
	public Book(String bookNo,String bookName,float bookPrice){
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
	}
	public String getBookNo(){
		return bookNo;
	}
	public String getBookName(){
		return bookName;
	}
	public float getBookPrice(){
		return bookPrice;
	}

}
