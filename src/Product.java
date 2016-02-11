/**
 * Created by misha on 11/02/16.
 */
public class Product {
	final int index, weight;

	public Product(int index, int weight) {
		this.index = index;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Product(" + weight + ")";
	}
}
