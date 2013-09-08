package co.networkery.uvbeenzaned.SnowBrawl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Clock {
	
	private static ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			if(currentsec >= Settings.getRoundstartdelay()) {
				Round.startRandomMap();
				Round.setGameActive(true);
				stopClock();
			}
			currentsec++;
		}
	};
	
	private static Timer clock = new Timer(1000, taskPerformer);
	private static int currentsec;
	
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
		clock.setRepeats(false);
		clock.start();
	}
	
	public static void stopClock()
	{
		clock.stop();
		setCurrentSeconds(0);
	}
	
	public static boolean isRunning()
	{
		return clock.isRunning();
	}
	
	public static void setCurrentSeconds(int s)
	{
		currentsec = s;
	}
	
	public static int getCurrentSeconds()
	{
		return currentsec;
	}
}
