package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ExitCommand extends Command
{
	private GameWorld gw;
	public ExitCommand(GameWorld gw)
	{
		super("Exit");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.exit();
		Command cOk = new Command("Ok");
		Command cCancel = new Command("Cancel");
		Command[] cmds = new Command[]{cOk, cCancel};
		TextField myTF = new TextField();
		Command c = Dialog.show("Are you sure you want to exit?", "", cmds);
		if (c == cOk)
		{
			gw.exitAccept();
		}
		else
			gw.exitDecline();
	}

}
