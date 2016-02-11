import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by misha on 11/02/16.
 */
public class Warehouse {
	int x, y;
	List<Integer> productAmounts;

	public Warehouse(int x, int y, List<Integer> productAmounts) {
		this.x = x;
		this.y = y;
		this.productAmounts = productAmounts;
	}

	public Warehouse(String location, String amounts) {
		String[] locationSplit = location.split(" ");
		this.x = Integer.parseInt(locationSplit[0]);
		this.y = Integer.parseInt(locationSplit[1]);

		String[] amountsSplit = amounts.split(" ");
		productAmounts = Arrays.asList(amountsSplit)
				.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "Warehouse(" + x + ", " + y + ", " + productAmounts.size() + ")";
	}
}
