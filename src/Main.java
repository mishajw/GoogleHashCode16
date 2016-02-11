import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by misha on 11/02/16.
 */
public class Main {

	private static final String fileName = "res/busy_day.in";

	private int rows, columns, droneAmount, turns, maxPayload;
	private int warehouseAmount, productAmount;

	private List<Product> products;
	private List<Warehouse> warehouses;
	private List<Order> orders;
	private List<Drone> drones;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		this.products = new ArrayList<>();
		this.warehouses = new ArrayList<>();
		this.orders = new ArrayList<>();
		this.drones = new ArrayList<>();

		List<String> lines = getLines();

		int count = 0;

		parseInitial(lines.get(count++));
		parseProducts(lines.get(count++), lines.get(count++));

		warehouseAmount = Integer.parseInt(lines.get(count++));

		for (int i = 0; i < warehouseAmount; i++) {
			warehouses.add(new Warehouse(lines.get(count++), lines.get(count++)));
		}

		int orderAmount = Integer.parseInt(lines.get(count++));

		for (int i = 0; i < orderAmount; i++) {
			String[] locationSplit = lines.get(count++).split(" ");

			int orderProductsAmount = Integer.parseInt(lines.get(count++));

			List<Product> orderProducts = new ArrayList<>();

			String[] productSplit = lines.get(count++).split(" ");

			for (String s : productSplit) {
				int index = Integer.parseInt(s);
				orderProducts.add(products.get(index));
			}

			orders.add(new Order(
					Integer.parseInt(locationSplit[0]),
					Integer.parseInt(locationSplit[0]),
					orderProducts
			));
		}

		generateDrones();
	}

	private void generateDrones() {
		Warehouse start = warehouses.get(0);

		for (int i = 0; i < droneAmount; i++) {
			this.drones.add(new Drone(start.x, start.y, maxPayload));
		}
	}

	private void parseInitial(String s) {
		String[] split = s.split(" ");

		rows = Integer.parseInt(split[0]);
		columns = Integer.parseInt(split[1]);
		droneAmount = Integer.parseInt(split[2]);
		turns = Integer.parseInt(split[3]);
		maxPayload= Integer.parseInt(split[4]);
	}

	private void parseProducts(String amountString, String weightString) {
		int amount = Integer.parseInt(amountString);
		String[] split = weightString.split(" ");

		for (int i = 0; i < amount; i++) {
			products.add(new Product(i, Integer.parseInt(split[i])));
		}
	}

	public List<String> getLines() {
		try {
			Stream<String> stream = Files.lines(Paths.get(fileName));

			return stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void printDetails() {
		products.stream().forEach(x -> System.out.println(x));
		warehouses.stream().forEach(x -> System.out.println(x));
		orders.stream().forEach(x -> System.out.println(x));
	}
}
