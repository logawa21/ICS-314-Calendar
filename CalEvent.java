import java.io.File;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class kiwiCal {
	public static void main(String[] args) {
		File file = null;
		String start = "BEGIN:VCALENDAR\n", end = "END:VCALENDAR", ver = "VERSION:";
		String tz = "America/Hawaii\n", dtstart, dtend, location, summary;
		double verNum = 2.0;

		// Convert double to string
		String strVerNum = (Double.toString(verNum) + "\n");

		// This is where the file and text are created
		try {
			// Create a new file
			file = new File("ics314.ics");

			// Writing to the ics file
			// FileWriter will append to the file
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			
			// Writing the header portion of the file
			output.write(start);
			output.write(ver);
			output.write(strVerNum);
			output.write("TIMEZONE:" + tz);
			
			// *****INSERT a while loop to always prompt for more events *****
			
			dtstart = "20150311T119000Z\n";
			dtend = "20150312T120000Z\n";
			location = "Sinclair\n";
			summary = "work on project\n";
			
			output.write("BEGIN:VEVENT\n");
			output.write("DTSTART:" + dtstart);
			output.write("DTEND:" + dtend);
			output.write("LOCATION:" + location);
			output.write("SUMMARY:" + summary);
			output.write("END:VEVENT\n");
			
			// *****After loop is over *****		
			output.write(end);
			output.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
