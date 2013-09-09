package co.networkery.uvbeenzaned.SnowBrawl;

public class Utilities{

	public static String convertArenaArgsToString(String[] args, int startpoint)
	{
		String aname = "";
		for(int i=startpoint; i<args.length; i++){
			aname = aname + args[i] + " ";
         }
		return aname.trim();
	}
	
}
