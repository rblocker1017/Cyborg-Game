package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.Fixed;
import com.mycompany.a3.GameObject;

public class Base extends Fixed 
{
	
	private int sequenceNumber;

	public Base(int sN, int color, Point point)
	{
		super(100, color, point);
		sequenceNumber = sN;
	}
	
	
	@Override
	public void setColor(int c)
	{
		// Bases are not allowed to change color
	}
	
	public int getSN()
	{
		return sequenceNumber;
	}
	
	public String toString()
	{
		return "Base: loc = " + this.getLocation().getX() + "," + this.getLocation().getY() + " color = [" + this.getColor() + "]" + " size = " +this.getSize() + " seqNum=" + sequenceNumber;

	}


	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		float x = pCmpRelPrnt.getX() + this.getLocation().getX();
		float y = pCmpRelPrnt.getY() + this.getLocation().getY();
		
		int[] xs = {(int) x, (int) (x + this.getSize()/2), (int) (x + this.getSize())};
		int[] ys = {(int) y, (int) (y + this.getSize()), (int) (y)};
		g.setColor(this.getIntColor());
		
		g.drawPolygon(xs, ys, 3);
		g.fillPolygon(xs, ys, 3);
		
		g.setColor(ColorUtil.WHITE);
		g.drawString(this.getSN() + "", (int) (x + this.getSize()/2.5), (int)(y + this.getSize()/10));
	}



	@Override
	public void handleCollision(GameObject otherObject) 
	{
		
	}

}
