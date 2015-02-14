package latmod.core.cmd;

import latmod.core.*;
import latmod.core.net.*;
import latmod.core.util.*;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class CmdLatCore extends CommandBaseLC
{
	public CmdLatCore()
	{ super("latcore", CommandLevel.ALL); }
	
	public void printHelp(ICommandSender ics)
	{
		printHelpLine(ics, "<versions>");
		printHelpLine(ics, "<friends>");
	}
	
	public int getRequiredPermissionLevel()
	{ return 0; }
	
	public boolean canCommandSenderUseCommand(ICommandSender ics)
	{ return !LCConfig.General.disableLatCoreCommand; }
	
	public String[] getSubcommands(ICommandSender ics)
	{ return new String[] { "versions", "friends" }; }
	
	public String[] getTabStrings(ICommandSender ics, String args[], int i)
	{
		if(i == 0) return getSubcommands(ics);
		return super.getTabStrings(ics, args, i);
	}
	
	public String onCommand(ICommandSender ics, String[] args)
	{
		if(args == null || args.length == 0)
			return "Subcommands: " + LatCore.strip(getSubcommands(ics));
		
		if(args[0].equals("versions"))
		{
			ThreadCheckVersions.init(ics, true);
			return null;
		}
		else if(args[0].equals("friends"))
		{
			EntityPlayerMP ep = getCommandSenderAsPlayer(ics);
			MessageLM.NET.sendTo(new MessageCustomServerAction(LCEventHandler.ACTION_OPEN_FRIENDS_GUI, null), ep);
			return null;
		}
		
		return onCommand(ics, null);
	}
}