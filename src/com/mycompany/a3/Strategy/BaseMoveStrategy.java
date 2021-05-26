package com.mycompany.a3.Strategy;
import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;
import com.mycompany.a3.GameObjects.Base;
import com.mycompany.a3.GameObjects.NonPlayerCyborg;

public class BaseMoveStrategy implements IStrategy 
{
	private NonPlayerCyborg npc;
	private Base b;
	public BaseMoveStrategy(NonPlayerCyborg npc, Base b)
	{
		this.npc = npc;
		this.b = b;
	}

	@Override
	public void apply() 
	{
		double x0 = npc.getLocation().getX();
		double y0 = npc.getLocation().getY();
		double x1 = b.getLocation().getX();
		double y1 = b.getLocation().getY();
		
		double x2 = x1 - x0;
		double y2 = y1 - y0;
		
		double result = y2/x2;
		result = MathUtil.atan(result);
		result = Math.toDegrees(result);
		int steer;
		if(x2 < 0)
		{
			steer = (180-(int)result);
			npc.setHeading(steer);
			
		}
		else
		{
			steer = (90-(int)result);
			npc.setHeading(steer);
		}
		
	}
	
	public String getStrategy()
	{
		return "BaseMoveStrategy";
	}

}
