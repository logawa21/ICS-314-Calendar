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
		//String priority = "PRIORITY:";
		//String class = "CLASS:";	// public, private, confidential
		//int intPriority;			// 1-4 high, 5 meduim, 6-9 low
		//sTime;	// Date time start; 20150508T180000
		//eTime;	// Date time end; end comes before the start in the file
		//timeZone;		// Time Zone

		ver = ver.concat(verNum + "\n");
		//ver = ver.concat(newLine);
		// Get user input
		Scanner scan = new Scanner(System.in);

		// Get the name of the event
		System.out.print("Name of the event: ");
		input = scan.nextLine();
		summary = summary.concat(input + "\n");

		// Get the location of the event
		System.out.print("Location of the event: ");
		input = scan.nextLine();
		location = location.concat(input + "\n");

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
			output.write(summary);
			output.write(location);
			output.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
