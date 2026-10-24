package alarm;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;

import java.util.Scanner;

import static alarm.Cout.cout;
import static alarm.Cout.coutln;

public class Main{
	public static void main(String[] args){
	
	String filePath = "twinsatcarousel.wav";

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	LocalTime alarmTime = null;
	Scanner scanner = new Scanner(System.in);

	while(alarmTime == null){	
	     try{
		

		cout("Enter the time were the alarm will trigger (HH:MM:SS): ");
		String inputTime = scanner.nextLine();

		alarmTime = LocalTime.parse(inputTime, formatter);

 		

		coutln("alarm set for " + alarmTime);	

		}
	      catch(DateTimeParseException e){
		coutln("invalid time format:(");
		}
	}
	alarmClock Alarm = new alarmClock(filePath, alarmTime, scanner);
	Thread alarmThread = new Thread(Alarm);
	alarmThread.start();

	}
}
