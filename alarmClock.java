package alarm;

import static alarm.Cin.cin;
import static alarm.Cout.cout;
import static alarm.Cout.coutf;
import static alarm.Cout.coutln;

import java.awt.Toolkit;

import java.io.IOException;
import java.io.File;

import java.time.LocalTime;

import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class alarmClock implements Runnable{

	private final LocalTime alarmTime;
	private final Scanner scanner;
	private final String filePath;

	alarmClock(String filePath, LocalTime alarmTime, Scanner scanner){

	this.alarmTime = alarmTime;	
	this.filePath = filePath;
	this.scanner = scanner;
	}

	@Override
	public void run(){

		if(LocalTime.now().isAfter(alarmTime)){
		Toolkit.getDefaultToolkit().beep();
		return;
		}else{

		while(LocalTime.now().isBefore(alarmTime)){
		try{
		Thread.sleep(1000);
		LocalTime now = LocalTime.now();

		coutf("\r%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
		}
		catch(InterruptedException e){
		coutln("Thread was interrupted");
		}
		
		}
		}
		//Toolkit.getDefaultToolkit().beep();
		audioPlayer();
	}
	private void audioPlayer(){
	
	File audioFile = new File(filePath);
	try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)){
	Clip clip = AudioSystem.getClip();
	clip.open(audioStream);	
	clip.start();
	coutf("\nhit enter to close alarm: ");
	cin(scanner);

	}
	catch(IOException e){
	coutln("unsupported file, please convert to a .wav extension");
	}
	catch(UnsupportedAudioFileException e){
	coutln("found IOException, might be related to file missing, please check the whole library");
	}
	catch(LineUnavailableException e){
	coutln("Audio might be unavailable, check for exception");
	}
	}
}
