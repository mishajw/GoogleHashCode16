import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 11/02/16.
 */
public class Drone {
	double x, y;
	int maxPayload;

	List<Product> carrying;

	public Drone(double x, double y, int maxPayload) {
		this.x = x;
		this.y = y;
		this.maxPayload = maxPayload;

		carrying = new ArrayList<>();
	}


}
