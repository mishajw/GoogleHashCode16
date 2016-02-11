import java.util.List;
import java.util.Random;

/**
 * Created by misha on 11/02/16.
 */
public class Order extends Locatable {
	Product product;
	int customer, total;

	public Order(int customer, int x, int y, Product product, int total) {
		super(x, y);
		this.customer = customer;
		this.product = product;
		this.total = total;
	}

	@Override
	public String toString() {
		return "Order(" + x + ", " + y + ", " + product + ")";
	}
}
