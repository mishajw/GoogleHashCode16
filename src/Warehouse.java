import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by misha on 11/02/16.
 */
public class Warehouse extends Locatable {
	int x, y;
	List<Integer> productAmounts;

	public Warehouse(int x, int y, List<Integer> productAmounts) {
		super(x, y);
		this.productAmounts = productAmounts;
	}

	public Warehouse(String location, String amounts) {
		super(Integer.parseInt(location.split(" ")[0]), Integer.parseInt(location.split(" ")[1]));

		String[] amountsSplit = amounts.split(" ");
		productAmounts = Arrays.asList(amountsSplit)
				.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}


	@Override
	public String toString(){
		String s =  "([" + x + "," + y + "] - Product amounts: ";

		for (Integer i: productAmounts ) {
			s += i+ ",";
		}
		s = s.substring(0,s.length()-1);
		s += ")";
		return s;
	}
}
