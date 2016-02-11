import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by misha on 11/02/16.
 */
public class Drone extends Locatable {
	int maxPayload;
	int id;
	Main env;
	double currentTimeLeft = 0;

	List<String> commands = new ArrayList<>();
	List<String> tempReport = new ArrayList<>();

	Order currOrder = null;

	List<Product> carrying;
	Warehouse warehouse;

	public Drone(Warehouse warehouse, int maxPayload, int id, Main env) {
		super(warehouse.x, warehouse.y);
		this.warehouse = warehouse;
		this.id = id;
		this.env = env;

		this.maxPayload = maxPayload;

		carrying = new ArrayList<>();
	}

	public void getNext(List<Order> orders, List<Warehouse> warehouses) {
//		System.out.println("Drone.getNext");
//		if (currentWeight() == 0) {
//			returnToWarehouse(warehouses);
//			return;
//		}

		Order bestOrder = bestOrderToDo(orders);

		if (bestOrder != null) {
			this.currOrder = bestOrder;
			this.env.orders.remove(bestOrder);
			this.warehouse.productAmounts.set(bestOrder.product.index, this.warehouse.productAmounts.get(bestOrder.product.index) - 1);
			bestOrder.total --;
			this.carrying.add(bestOrder.product);
			this.currentTimeLeft = Helper.eulDist(this, bestOrder);
			tempReport.add(id + " D " + bestOrder.customer + " " + bestOrder.product.index + " " + 1);
		} else {
			returnToWarehouse(warehouses);
		}
	}

	public void step(List<Order> orders, List<Warehouse> warehouses) {
//		System.out.println("Drone.step");
//		if(this.id==10){
//			System.out.println(currentTimeLeft);
//			System.out.println(orders.size());
//		}
		if (currentTimeLeft == 0) {
			getNext(orders, warehouses);
		} else {
			currentTimeLeft -= 1;
		}

		currentTimeLeft = Math.max(0, currentTimeLeft);
	}

	private void returnToWarehouse(List<Warehouse> warehouses) {
		processString();

		Locatable curPos = this.currOrder == null ? this : this.currOrder;

		this.warehouse = warehouses.stream()
				.sorted((o1, o2) -> (int) (Helper.eulDist(curPos, o1) - Helper.eulDist(curPos, o2)))
				.collect(Collectors.toList())
				.get(0);

		this.currentTimeLeft = Helper.eulDist(this, warehouse);

		this.carrying.clear();
	}

	public Order bestOrderToDo(List<Order> orders) {
		List<Order> toCheck = orders
				.stream()
				.filter(o -> warehouse.contains(o.product))
				.filter(o -> o.product.weight < weightRemaining())
				.sorted((o1, o2) -> getHeuristic(o1) - getHeuristic(o2))
				.collect(Collectors.toList());

//		for (Order o : toCheck) {
//			System.out.println(Helper.eulDist(this, o));
//		}
//
//		System.out.println("\n\n\n");


		if (toCheck.isEmpty()) {
			return null;
		} else {
			this.x = toCheck.get(0).x;
			this.y = toCheck.get(0).y;
			return toCheck.get(0);
		}
	}

	private int getHeuristic(Order o) {
		double dist = Helper.eulDist(this, o);

		double total = Math.pow(1 / ((double) o.total), 8);

		return (int) (dist * total);
	}

	private void processString() {
//		System.out.println("Drone.processString");

		carrying.stream()
				.forEach(c ->
					commands.add(id + " L " + warehouse.id + " " + c.index + " " + 1)
				);

		commands.addAll(tempReport);
		tempReport.clear();
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
