package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.GameObjects.NonPlayerCyborg;
import com.mycompany.a3.Iterator.IIterator;

public class ChangeStrategyCommand extends Command{

	private GameWorld gw;
	public ChangeStrategyCommand(GameWorld gw)
	{
		super("Change Strategy");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.changeStrategy();
	}
}
