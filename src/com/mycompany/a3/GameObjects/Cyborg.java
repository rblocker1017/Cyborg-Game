package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a3.ISteerable;
import com.mycompany.a3.Movable;

public abstract class Cyborg extends Movable implements ISteerable
{
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int lastBaseReached;
	private int maximumDamage;
	
	@Override
	public void setSteeringDirection(int s) 
	{
		steeringDirection = s;
	}
	
	public int getSteeringDirection()
	{
		return steeringDirection;
	}
	public int getMaxSpeed()
	{
		return maximumSpeed;
	}
	
	public void setMaximumSpeed(int ms)
	{
		maximumSpeed = ms;
	}
	
	public void setDamageLevel(int dl)
	{
		damageLevel = dl;
	}
	
	public int getDamageLevel()
	{
		return damageLevel;
	}
	
	public int getLastBaseReached()
	{
		return lastBaseReached;
	}
	
	public void setLastBaseReached(int lb)
	{
		lastBaseReached = lb;
	}
	
	public int getEnergyLevel()
	{
		return energyLevel;
	}
	
	public void setEnergyLevel(int el)
	{
		energyLevel = el;
	}
	public int getECR()
	{
		return energyConsumptionRate;
	}
	public void setECR(int ecr)
	{
		energyConsumptionRate = ecr;
	}
	public void setMaximumDamage(int md)
	{
		maximumDamage = md;
	}
	public int getMaximumDamage()
	{
		return maximumDamage;
	}
	
	public Cyborg(int color, int speed, int heading, Point point)
	{
		super(50, color, point, heading, speed);
		this.setColor(color);
		steeringDirection = 0;
		maximumSpeed = 20;
		energyLevel = 999;
		energyConsumptionRate = 1;
		damageLevel = 0;
		maximumDamage = 200;
		lastBaseReached = 1;
	}
	
	public void speedUpdate() // updates maximum speed and current speed depending on damage level
	{
		int damage = this.getDamageLevel();
		damage = damage/10;
		this.setMaximumSpeed(20 - damage);
		this.setSpeed(this.getSpeed()-5);
		if(this.getSpeed() <= 0)
		{
			this.setSpeed(5);
		}
	}
	
	public boolean unmoveable() // determines if cyborg can be moved or not
	{
		if(this.getSpeed() == 0)
		{
			return true;	
		}
		else
			return false;
	}
	public String toString()
	{
		return "Cyborg: loc = " + this.getLocation().getX() + "," + this.getLocation().getY() + " color = [" + this.getColor() + "]" 
	    + " heading = " + this.getHeading() + " speed = " + this.getSpeed() + " size = " + this.getSize() +  " maxSpeed = " + maximumSpeed + " steeringDirection = " 
	    + steeringDirection + " energyLevel = " + energyLevel + " damageLevel = " + damageLevel + " lastBaseReached = " + lastBaseReached;
	}
}
