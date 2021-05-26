package com.mycompany.a3.Strategy;

import com.codename1.util.MathUtil;
import com.mycompany.a3.GameObjects.Base;
import com.mycompany.a3.GameObjects.Cyborg;
import com.mycompany.a3.GameObjects.NonPlayerCyborg;
import com.mycompany.a3.GameObjects.PlayerCyborg;

public class PlayerMoveStrategy implements IStrategy 
{
	private NonPlayerCyborg npc;
	private PlayerCyborg c;
	
	public PlayerMoveStrategy(NonPlayerCyborg npc, PlayerCyborg c)
	{
		this.npc = npc;
		this.c = c;
	}
	@Override
	public void apply() 
	{
		double x0 = npc.getLocation().getX();
		double y0 = npc.getLocation().getY();
		double x1 = c.getLocation().getX();
		double y1 = c.getLocation().getY();
		
		double x2 = x1 - x0;
		double y2 = y1 - y0;
		
		double result = y2/x2;
		result = MathUtil.atan(result);
		result = Math.toDegrees(result);
		
		if(x2 < 0)
		{
			npc.setHeading(180-(int)result);
		}
		else
		{
			npc.setHeading(90-(int)result);
		}
		
	}
	
	public String getStrategy()
	{
		return "PlayerMoveStrategy";
	}


}
