import java.io.File;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class CalEvent {
	public static void main(String[] args) {
		File file = null;
		//boolean bool = false;
		String start = "BEGIN:VCALENDAR\n" +
					"CALSCALE:GREGORIAN\n" +
					"VERSION:2.0\n";
		String append = "Appends to the file!";

		// This is where the file and text are created
		try {
			// Create a new file
			file = new File("ics314.ics");

			// Writing to the ics file
			// FileWriter will append to the file
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(start);
			output.write(append);
			output.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
