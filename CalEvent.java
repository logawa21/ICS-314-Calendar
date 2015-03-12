import java.io.File;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.Scanner;

public class CalEvent {
	public static void main(String[] args) {
		File file = null;
		
		String start = "BEGIN:VCALENDAR\n";
		String ver = "VERSION:";
		double verNum = 2.0;
		String newLine = "\n";
		String input;
		String summary = "SUMMARY:";
		String location = "LOCATION:";
		String date = "";
		//String sTime = "DTSTART;";	// Date time start; 20150508T180000
		//String eTime = "DTEND;";	// Date time end; end comes before the start in the file
		//String priority = "PRIORITY:";
		//String class = "CLASS:";	// public, private, confidential
		//int intPriority;			// 1-4 high, 5 meduim, 6-9 low
		//String timeZone = "";		// Time Zone
		//String end = "END:VEVENT";

		ver = ver.concat(verNum + "\n");
		//ver = ver.concat(newLine);
		// Get user input
		Scanner scan = new Scanner(System.in);

		// Get the name of the event
		System.out.print("Name of the event: ");
		input = scan.nextLine();
		summary = summary.concat(input + "\n");

		// Get the location of the event
		System.out.print("Location: ");
		input = scan.nextLine();
		location = location.concat(input + "\n");

		// Get the date of the event
		System.out.print("From Date: ");
		input = scan.nextLine();
		date = date.concat(input + "\n");

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
			output.write(summary);
			output.write(location);
			output.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
