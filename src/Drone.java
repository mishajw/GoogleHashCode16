import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 11/02/16.
 */
public class Drone extends Locatable {
	int maxPayload;

	List<Product> carrying;

	public Drone(double x, double y, int maxPayload) {
		super(x, y);

		this.maxPayload = maxPayload;

		carrying = new ArrayList<>();
	}

	public void getNext(List<Order> orders, List<Warehouse> warehouses) {
		if (currentWeight() == 0) {
			// goto warehouse
			return;
		}

		// pick best location
		for (Order o : orders) {

		}
	}

	public int weightRemaining() {
		return maxPayload - currentWeight();
	}

	public int currentWeight() {
		int total = 0;

		for (Product p : carrying) {
			total += p.weight;
		}

		return total;
	}

	@Override
	public String toString() {
		return "Drone(" + x + ", " + y + " - " + carrying.size() + ")";
	}
}
