import java.util.List;

/**
 * Created by misha on 11/02/16.
 */
public class Order extends Locatable {
	Product product;
	int customer;

	public Order(int customer, int x, int y, Product product) {
		super(x, y);
		this.customer = customer;
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order(" + x + ", " + y + ", " + product + ")";
	}
}
