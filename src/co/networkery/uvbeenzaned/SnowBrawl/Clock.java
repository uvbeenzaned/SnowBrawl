package co.networkery.uvbeenzaned.SnowBrawl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Clock {
	
	private static int cntdwn = 0;
	
	private static ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			if(cntdwn > 0)
			{
				Chat.sendAllTeamsMsg(String.valueOf(cntdwn));
				cntdwn--;
			}
			else
			{
				if(cntdwn <= 0)
				{
					stopTimer();
					Round.startRandomMap();
				}
			}
		}
	};
	
	private static Timer timer = new Timer(1000, taskPerformer);
	
	public static Timer getTimer()
	{
		return timer;
	}
	
	public static void startTimer()
	{
		if(!Round.isGameActive() && !isRunning()) {
			cntdwn = Settings.getRoundstartdelay() / 1000;
			timer.setRepeats(true);
			timer.start();
			Chat.sendAllTeamsMsg("Round will start in...");
		}
	}
	
	public static void stopTimer()
	{
		timer.stop();
	}
	
	public static boolean isRunning()
	{
		return timer.isRunning();
	}
}
