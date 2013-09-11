package co.networkery.uvbeenzaned.SnowBrawl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Clock {
	
	private static ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			if(!Round.isGameActive()) {
				if(countdown == 0) {
					Round.startRandomMap();
					stopTimer();
				} else {
					Chat.sendAllTeamsMsg(String.valueOf(countdown));
					countdown--;
				}
			} else {
				stopTimer();
			}
		}
	};
	
	private static Timer timer;
	private static int countdown = Settings.getRoundstartdelay();
	
	public static void setTimer(Timer t)
	{
		timer = t;
	}
	
	public static Timer getTimer()
	{
		return timer;
	}
	
	public static void startTimer()
	{
		if(!Round.isGameActive()) {
			setTimer(new Timer(1000, taskPerformer));
			setCountDownSeconds(Settings.getRoundstartdelay());
			timer.setRepeats(true);
			timer.start();
		}
	}
	
	public static void stopTimer()
	{
		timer.stop();
		timer.setRepeats(false);
		setCountDownSeconds(Settings.getRoundstartdelay());
	}
	
	public static boolean isRunning()
	{
		return timer.isRunning();
	}
	
	public static void setCountDownSeconds(int s)
	{
		countdown = s;
	}
	
	public static int getCountDownSeconds()
	{
		return countdown;
	}
}
