import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by misha on 11/02/16.
 */
public class Main {

	private static final String fileName = "res/busy_day.in";

	public static void main(String[] args) {
		try {
			Stream<String> stream = Files.lines(Paths.get(fileName));

			stream.forEach(l -> {
				System.out.println(l);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
