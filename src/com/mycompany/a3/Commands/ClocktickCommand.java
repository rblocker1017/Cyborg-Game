package com.mycompany.a3.Commands;

import com.codename1.charts.models.Point;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ClocktickCommand extends Command{

	private GameWorld gw;
	private Point bounding;
	public ClocktickCommand(GameWorld gw, Point bounding)
	{
		super("clockTick");
		this.gw = gw;
		this.bounding = bounding;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.clockTick(30, bounding);
	}
}
