
public class Helper {
	public static double eulDist(Locatable l1, Locatable l2){
		return Math.sqrt(Math.pow(l1.x - l2.x, 2) + Math.pow(l1.y - l2.y, 2));
	}
}
