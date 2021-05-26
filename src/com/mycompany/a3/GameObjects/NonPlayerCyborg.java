package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.Strategy.IStrategy;

public class NonPlayerCyborg extends Cyborg{

	private IStrategy curStrategy;
	private int nextBase = 2;
	public NonPlayerCyborg(int color, int speed, int heading, Point point) {
		super(color, speed, heading, point);
		this.setMaximumDamage(600);
	}

	public void invokeStrategy() 
	{
		curStrategy.apply();
	}
	
	public void setStrategy(IStrategy s)
	{
		curStrategy = s;
	}
	
	public String getStrategy()
	{
		return curStrategy.getStrategy();
	}
	public void setNextBase(int i)
	{
		nextBase = i;
	}
	public int getNextBase()
	{
		return nextBase;
	}
	public String toString()
	{
		return "NonPlayer" + super.toString() + " Strategy = " + curStrategy.getStrategy();
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		float x = pCmpRelPrnt.getX() + this.getLocation().getX();
		float y = pCmpRelPrnt.getY() + this.getLocation().getY();
		
		g.setColor(this.getIntColor());
		g.drawRect((int)x, (int)y, this.getSize(), this.getSize());
		
	}

	@Override
	public void handleCollision(GameObject otherObject) 
	{
		if(otherObject instanceof Base)
		{
			int SN = ((Base) otherObject).getSN();
			int thisBase = this.getNextBase();
			
			if(SN == thisBase)
			{
				this.setLastBaseReached(SN);
				this.setNextBase(SN+1);
			}
		}
		else if(otherObject instanceof EnergyStation)
		{
			if(((EnergyStation) otherObject).getCapacity() != 0)
			{
				int energyLevel = this.getEnergyLevel();
				energyLevel = energyLevel + ((EnergyStation) otherObject).getCapacity();
				this.setEnergyLevel(energyLevel);
			
				((EnergyStation) otherObject).setCapacity(0);
				int color = otherObject.getIntColor();
				otherObject.setRGBColor(ColorUtil.red(color), ColorUtil.green(color)-126, ColorUtil.blue(color));
			}
		}
		else if(otherObject instanceof Drone)
		{
			int color = this.getIntColor();
			this.setRGBColor(ColorUtil.red(color)-12, ColorUtil.green(color), ColorUtil.blue(color));
			this.setDamageLevel(this.getDamageLevel() + 10);
			this.speedUpdate();
		}
		else if(otherObject instanceof PlayerCyborg)
		{
			int color = this.getIntColor();
			this.setRGBColor(ColorUtil.red(color)-12, ColorUtil.green(color), ColorUtil.blue(color));
			this.setDamageLevel(this.getDamageLevel() + 10);
			this.speedUpdate();
		}
		else if(otherObject instanceof NonPlayerCyborg)
		{
			int color = this.getIntColor();
			this.setRGBColor(ColorUtil.red(color)-12, ColorUtil.green(color), ColorUtil.blue(color));
			this.setDamageLevel(this.getDamageLevel() + 10);
			this.speedUpdate();
		}
	}
}
