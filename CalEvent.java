import java.io.File;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class CalEvent {
	public static void main(String[] args) {
		File file = null;
		

		String start = "BEGIN:VCALENDAR\n";
		String ver = "VERSION:";
		double verNum = 2.0;

		// Convert double to string
		String strVerNum = Double.toString(verNum);

		// This is where the file and text are created
		try {
			// Create a new file
			file = new File("ics314.ics");

			// Writing to the ics file
			// FileWriter will append to the file
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			
			// Writing to the file
			output.write(start);
			output.write(ver);
			output.write(strVerNum);
			output.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
