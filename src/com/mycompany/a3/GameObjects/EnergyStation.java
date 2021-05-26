package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.Fixed;
import com.mycompany.a3.GameObject;

public class EnergyStation extends Fixed 
{
	private int capacity;
	
	public EnergyStation(int size, int color, Point point, int energyCapacity)
	{
		super(size, color, point);
		capacity = energyCapacity;
	}

	public String toString()
	{
		return "EnergyStation:: loc = " + this.getLocation().getX() + "," + this.getLocation().getY() + " color = [" + this.getColor() + "]" 
			    + " size = " + this.getSize() + " capacity = " + this.capacity;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	public void setCapacity(int c)
	{
		capacity = c;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		float x = pCmpRelPrnt.getX() + this.getLocation().getX();
		float y = pCmpRelPrnt.getY() + this.getLocation().getY();
		
		g.setColor(this.getIntColor());
		g.drawArc((int)x, (int)y, this.getSize(), this.getSize(), 0, 360);
		g.fillArc((int)x, (int)y, this.getSize(), this.getSize(), 0, 360);
		
		g.setColor(ColorUtil.WHITE);
		g.drawString("" + this.getCapacity(), (int) (x + this.getSize()/3.2), (int)(y + this.getSize()/3.2));
		
		
		
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}
}
