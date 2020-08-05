package exam1;
import java.util.Collection;
public class TestCart {
	public static void main(String[] args){
		Cart cart = new Cart();
		Book b1 = new Book("111","java",40);
		Book b2 = new Book("222","jsp",36);
		Book b3 = new Book("333","java",40);
		
		cart.addItem(b1);
		cart.addItem(b1);
		cart.addItem(b1);
		cart.addItem(b2);
		cart.editItem("222", 2);
		cart.addItem(b3);
		Collection<CartItem>items = cart.findItems();
		for(CartItem item : items){
			System.out.println(item);
		}
		System.out.println("×Ü¼Æ£º"+cart.findTotal());
	}

}
