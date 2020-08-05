package exam1;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class Cart {
	private Map<String,CartItem>cartItems = new HashMap<String,CartItem>();
	public void addItem(Book book){
		CartItem item;
		if(!cartItems.containsKey(book.getBookNo())){
			item = new CartItem(book,1);
			cartItems.put(book.getBookNo(), item);
		}else {
			item = cartItems.get(book.getBookNo());
			item.setCount(item.getCount()+1);
		}
	}
	public void editItem(String bookNo,int count){
		CartItem item;
		if(cartItems.containsKey(bookNo)){
			item = cartItems.get(bookNo);
			if(count>0){
				item.setCount(count);;
			}else if (count == 0){
				deleteItem(bookNo);
			}else{
				System.out.println("数量不能为负数！");
			}
		}
	}
	public void deleteItem(String bookNo){
		cartItems.remove(bookNo);
	}
	public Collection<CartItem>findItems(){
		return cartItems.values();
	}
	public void deleteAll(){
		cartItems.clear();
	}
	public float findTotal(){
		float total = 0;
		for(CartItem item : cartItems.values()){
			total += item.getBook().getBookPrice()*item.getCount();
		}
		return total;
	}

}
