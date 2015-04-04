import java.io.*;
import java.util.*;
import java.text.*;

public class CreateCalEvent {
	public static void main(String[] args) {
		final String userFormat = "MM/dd/yyyy";
		final String calFormat = "yyyy/MM/dd";

		File file = null;
		
		String ver = "VERSION:", pvt = "PRIVATE", pub = "PUBLIC", con = "CONFIDENTIAL";
		double verNum = 2.0;
		String tz = "", timez = "", dtstart = "", dtend = "", upper = "";
		String location = "", summary = "", input = "", cl = "", priority = "";
		String newStartDateStr  = "", newEndDateStr = "";
		String[] splitTime, splitDate;
		String sTime = "", eTime = "", sHTime = "", sMTime = "", eHTime = "", eMTime = "", sDate = "", eDate = "";
		int intETime, intSTime, intSHTime, intSMTime, intEHTime, intEMTime, intPriority;

		ver = ver.concat(verNum + "\n");
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
		System.out.print("Date (MM/DD/YYYY or MM/DD/YYYY to MM/DD/YYYY): ");
		input = scan.nextLine();

		// If the event is overnight or over several days
		if(input.contains(" to ")) {
			splitDate = input.split(" to ");
			sDate = splitDate[0];
			eDate = splitDate[1];

			SimpleDateFormat sdfStart = new SimpleDateFormat(userFormat);
			SimpleDateFormat sdfEnd = new SimpleDateFormat(userFormat);

			// Change the format of the date
			try {
				Date sd = new Date();
				Date ed = new Date();

				// Change the start date format
				sd = sdfStart.parse(sDate);
				
				// Changing the format
				sdfStart.applyPattern(calFormat);
				newStartDateStr = sdfStart.format(sd);

				// Fixed the format, but not showing up in iCal
				// Change the end date format
				ed = sdfEnd.parse(eDate);
				sdfEnd.applyPattern(calFormat);
				newEndDateStr = sdfEnd.format(ed);
			}
			catch(ParseException pe) {
				pe.printStackTrace();
			}
			// Delete the "/" in the start date
			sDate = newStartDateStr.replaceAll("/", "");
			dtstart = dtstart.concat(sDate);

			// Delete the "/" in the end date
			eDate = newEndDateStr.replaceAll("/", "");
			dtend = dtend.concat(eDate);
		}
		else {
			// If the event is only one day
			// Change the date format from MM/dd/yyyy to yyyy/MM/dd
			SimpleDateFormat sdf = new SimpleDateFormat(userFormat);
			try {
				Date d = new Date();
				d = sdf.parse(input);
				// Changing the format
				sdf.applyPattern(calFormat);
				newStartDateStr = sdf.format(d);
			}
			catch(ParseException pe) {
				pe.printStackTrace();
			}

			// Delete the "/"
			input = newStartDateStr.replaceAll("/", "");
			dtstart = dtstart.concat(input);

			// For now assuming we are only adding an event on one day
			dtend = dtstart;
		}

		// Get the time of the event
		System.out.print("Time (6am-8pm): ");
		input = scan.nextLine();

		// Split the times into start time and end time while getting rid of "-"
		if(input.contains("-")) {
			splitTime = input.split("-");
			sTime = splitTime[0];
			eTime = splitTime[1];
		}
		// Convert the start time
		if(sTime.contains("pm")) {
			// Get rid of pm
			sTime = sTime.replace("pm", "");

			// Split the hours and minutes
			if(sTime.contains(":")) {
				splitTime = sTime.split(":");
				sHTime = splitTime[0];
				sMTime = splitTime[1];

				// Change it into an int to convert it
				intSHTime = Integer.parseInt(sHTime);
				intSHTime = intSHTime + 12;
				// Add it to dtstart
				dtstart = dtstart.concat("T" + intSHTime + sMTime + "00\n");
			}
			else {
				// Change it into an int to convert it
				intSTime = Integer.parseInt(sTime);
				intSTime = intSTime + 12;
				// Add it to dtstart
				dtstart = dtstart.concat("T" + sTime + "00\n");
			}
		}
		else if (sTime.contains("am")) {
			// Get rid of pm
			sTime = sTime.replace("am", "");

			// Split the hours and minutes
			if(sTime.contains(":")) {
				splitTime = sTime.split(":");
				sHTime = splitTime[0];
				sMTime = splitTime[1];

				// Add it to dtstart
				dtstart = dtstart.concat("T" + sHTime + sMTime + "00\n");
			}
			else {
				intSTime = Integer.parseInt(sTime);
				intSTime = intSTime + 12;
				dtstart = dtstart.concat("T" + intSTime + "00\n");
			}
		}
		else if (sTime.contains(":")){
			splitTime = sTime.split(":");
			sHTime = splitTime[0];
			sMTime = splitTime[1];

			// Change it into an int to convert it
			intSHTime = Integer.parseInt(sHTime);
			intSHTime = intSHTime + 12;
			// Add it to dtstart
			dtstart = dtstart.concat("T" + intSHTime + sMTime + "00\n");
		}
		else {
			intSHTime = Integer.parseInt(sTime);
			if (intSHTime <= 9) {
				sTime = "0" + sTime;
				dtstart = dtstart.concat("T" + sTime + "0000\n");
			}
			else {
				dtstart = dtstart.concat("T" + sTime + "0000\n");
			}
		}

		// Convert the end time
		if(eTime.contains("pm")) {
			// Get rid of pm
			eTime = eTime.replace("pm", "");

			// Split the hours and minutes
			if(eTime.contains(":")) {
				splitTime = eTime.split(":");
				eHTime = splitTime[0];
				eMTime = splitTime[1];

				// Change it into an int to convert it
				intEHTime = Integer.parseInt(eHTime);
				intEHTime = intEHTime + 12;

				// Add it to dtend
				dtend = dtend.concat("T" + intEHTime + eMTime + "00\n");
			}
			else {
				// Change it into an int to convert it
				intETime = Integer.parseInt(eTime);
				intETime = intETime + 12;

				// Add it to dtend
				dtend = dtend.concat("T" + intETime + "0000\n");
			}
		}
		else if (eTime.contains("am")) {
			// Get rid of pm
			eTime = eTime.replace("am", "");

			// Split the hours and minutes
			if(eTime.contains(":")) {
				splitTime = eTime.split(":");
				eHTime = splitTime[0];
				eMTime = splitTime[1];

				// Change it into an int to convert it
				intEHTime = Integer.parseInt(eHTime);
				intEHTime = intEHTime + 12;
				// Add it to dtstart
				dtend = dtend.concat("T" + intEHTime + eMTime + "00\n");
			}
			else {
				intETime = Integer.parseInt(eTime);
				intETime = intETime + 12;
				dtend = dtend.concat("T" + eTime + "0000\n");
			}
		}
		else if (eTime.contains(":")){
			splitTime = eTime.split(":");
			eHTime = splitTime[0];
			eMTime = splitTime[1];

			// Change it into an int to convert it
			intEHTime = Integer.parseInt(eHTime);
			intEHTime = intEHTime + 12;
			// Add it to dtstart
			dtend = dtend.concat("T" + intEHTime + eMTime + "00\n");
		}
		else {
			intEHTime = Integer.parseInt(eTime);
			if (intEHTime <= 9) {
				eTime = "0" + eTime;
				dtend = dtend.concat("T" + eTime + "0000\n");
			}
			else {
				dtend = dtend.concat("T" + eTime + "0000\n");
			}
		}

		// Get the timezone of the event
		System.out.print("Timezone (HST): ");
		input = scan.nextLine();
		timez = input;
		if(input.contains("HST")) {
			tz = tz.concat("TZID=Pacific/Honolulu");
		}
		else {
			// Force it to be PST
			tz = tz.concat("TZID=Pacific/Honolulu");
		}

		// Get the classification of the event
		System.out.print("Classification (public, private, or confidential: ");
		input = scan.nextLine();

		upper = input.toUpperCase();
		// Write the classification depending on what they wrote
		if(upper == pvt) {
			cl = cl.concat(pvt + "\n");
		}
		else if(input == con) {
			cl = cl.concat(con + "\n");
		}
		else {
			cl = cl.concat(pub + "\n");
		}

		// Get the priority of the event
		System.out.print("Priority of the event (0-9): ");
		intPriority = scan.nextInt();
		if(intPriority == 0)
			priority = priority.concat("UNDEFINED" + "\n");
		if(intPriority >= 1 && intPriority <= 4)
			priority = priority.concat("HIGH" + "\n");
		if(intPriority == 5)
			priority = priority.concat("MEDIUM" + "\n");
		if(intPriority >= 6 && intPriority <= 9)
			priority = priority.concat("LOW" + "\n");

		// This is where the file and text are created
		try {
			// Create a new file
			file = new File("ics314.ics");

			// Writing to the ics file
			// FileWriter will append to the file
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			
			// Writing to the file
			output.write("BEGIN:VCALENDAR\n");
			output.write(ver);

			output.write("BEGIN:VEVENT\n");
			output.write("DTSTART;" + tz + ":" + dtstart);
			output.write("DTEND;" + tz + ":" + dtend);
			output.write("LOCATION:" + location);
			output.write("SUMMARY:" + summary);
			output.write("CLASS:" + cl);
			output.write("PRIORITY:" + priority);
			output.write("END:VEVENT\n");

			output.write("END:VCALENDAR");
			output.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
