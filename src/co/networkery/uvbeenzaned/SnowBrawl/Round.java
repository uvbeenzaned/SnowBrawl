package co.networkery.uvbeenzaned.SnowBrawl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.Timer;

public class Round {
	
	private Map<String, Integer> hitsperplayer = new HashMap<String, Integer>();

	private static ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			
		}
	};

	public static Timer roundtimer;

	public static void startIndependentTimerRound()
	{
		roundtimer = new Timer(Configurations.getMainConfig().getInt("round-start-delay"), taskPerformer);
		roundtimer.setRepeats(false);
		roundtimer.start();
	}

	private static Random r = new Random();
	private static int l = 0;

	public static void randomMap()
	{
		r.setSeed(System.currentTimeMillis());
		int mapnum = r.nextInt();
		while(mapnum == l)
		{
			mapnum = r.nextInt();
		}
		int i = 0;
		for(String arena : //iterator here)
		{
			if(mapnum == i)
			{
				l = mapnum;

			}
			i++;
		}
	}
	
}
