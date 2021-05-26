package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AboutCommand extends Command
{
	private GameWorld gw;
	public AboutCommand(GameWorld gw)
	{
		super("About");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Command cOk = new Command("Ok");
		TextField myTF = new TextField("Ryan Blocker, CSC 134, Version 2");
		myTF.setEditable(false);
		Dialog.show("About:", myTF, cOk);
	}

}
