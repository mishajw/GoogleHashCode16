import java.util.List;

/**
 * Created by misha on 11/02/16.
 */
public class Order extends Locatable {
	List<Product> products;

	public Order(int x, int y, List<Product> products) {
		super(x, y);
		this.products = products;
	}

	@Override
	public String toString() {
		return "Order(" + x + ", " + y + ", " + products.size() + ")";
	}

	public String toString2() {
		String s = this.toString().substring(0, this.toString().length()-1) + " products = ";

		for (Product i: products ) {
			s += i+ ",";
		}
		s = s.substring(0,s.length()-1);
		s += ")";
		return s;
	}
}
