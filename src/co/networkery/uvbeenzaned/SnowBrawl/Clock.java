package co.networkery.uvbeenzaned.SnowBrawl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Clock {
	
	private static ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Round.startRandomMap();
		}
	};
	
	private static Timer timer;
	
	public static Timer getTimer()
	{
		return timer;
	}
	
	public static void startTimer()
	{
		if(!Round.isGameActive()) {
			timer = new Timer(Settings.getRoundstartdelay(), taskPerformer);
			timer.setRepeats(false);
			timer.start();
		}
	}
	
	public static void stopTimer()
	{
		timer.stop();
		timer.setRepeats(false);
	}
	
	public static boolean isRunning()
	{
		return timer.isRunning();
	}
}
