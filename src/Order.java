import java.util.List;

/**
 * Created by misha on 11/02/16.
 */
public class Order {
	List<Product> products;
	int x, y;

	public Order(int x, int y, List<Product> products) {
		this.x = x;
		this.y = y;
		this.products = products;
	}

	@Override
	public String toString() {
		return "Order(" + x + ", " + y + ", " + products.size() + ")";
	}
}
