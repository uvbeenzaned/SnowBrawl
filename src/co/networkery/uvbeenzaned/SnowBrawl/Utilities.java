package co.networkery.uvbeenzaned.SnowBrawl;

public class Utilities{

	public static String convertArenaArgsToString(String[] args)
	{
		String aname = "";
		for(int i=2; i<args.length; i++){
			aname = aname + args[i] + " ";
         }
		return aname.trim();
	}
	
}
