package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.Movable;

import java.util.Random;
public class Drone extends Movable
{
	public Drone(int size, int color, Point point, int speed, int heading)
	{
		super(size, color, point, speed, heading);
	}

	public void setColor(int gameColor) // sets color to a different value
	{
		super.setColor(gameColor);
	}
	
	public String toString()
	{
		return "Drone: loc = " + this.getLocation().getX() + "," + this.getLocation().getY() + " color = [" + this.getColor() + "]" 
			    + " heading = " + this.getHeading() + " speed = " + this.getSpeed() + " size = " + this.getSize();
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
		
	}

	@Override
	public void handleCollision(GameObject otherObject) {
	
		
	}
	

}
