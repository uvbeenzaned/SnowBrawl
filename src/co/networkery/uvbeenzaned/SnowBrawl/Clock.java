package co.networkery.uvbeenzaned.SnowBrawl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Clock {
	
	private static ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			if(countdown == 0) {
				Round.startRandomMap();
				stopClock();
			} else {
				Chat.sendAllTeamsMsg(String.valueOf(countdown));
				countdown--;
			}
		}
	};
	
	private static Timer clock;
	private static int countdown = Settings.getRoundstartdelay();
	
	public static void setClock(Timer t)
	{
		clock = t;
	}
	
	public static Timer getClock()
	{
		return clock;
	}
	
	public static void startClock()
	{
		if(!Round.isGameActive()) {
			setClock(new Timer(1000, taskPerformer));
			setCountDownSeconds(Settings.getRoundstartdelay());
			clock.setRepeats(true);
			clock.start();
		}
	}
	
	public static void stopClock()
	{
		clock.stop();
		Round.setGameActive(false);
		setCountDownSeconds(Settings.getRoundstartdelay());
	}
	
	public static boolean isRunning()
	{
		return clock.isRunning();
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
