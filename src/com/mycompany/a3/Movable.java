package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject 
{
	private int heading;
	private int speed;
	
	public void move(int elapsedTime, Point bounding) // moves object based on current heading and speed
	{
		Point p = new Point();
		
		double deltaX = (Math.cos(Math.toRadians(90 - heading))) * speed;
		double deltaY = (Math.sin(Math.toRadians(90 - heading))) * speed;
		
		int trueTime = elapsedTime / 10;
		deltaX = deltaX / trueTime;
		deltaY = deltaY / trueTime;
		
		deltaX = Math.round(deltaX);
		deltaY = Math.round(deltaY);
		
		if(this.getLocation().getX() + (float)deltaX < 0)
		{
			p.setX(0);
		}
		else if(this.getLocation().getX() + (float)deltaX > bounding.getX())
		{
			p.setX(bounding.getX());
		}
		else
		{
			p.setX(this.getLocation().getX() + (float)deltaX);
		}
		
		if(this.getLocation().getY() + (float)deltaY < 0)
		{
			p.setY(0);
		}
		else if(this.getLocation().getY() + (float)deltaY > bounding.getY())
		{
			p.setY(bounding.getY());
		}
		else
		{
			p.setY(this.getLocation().getY() + (float)deltaY);
		}
		
		this.setLocation(p);
		
		
	}
	public void setColor(int gameColor) // sets color to a different value
	{
		super.setColor(gameColor);
	}
	public int getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(int s)
	{
		speed = s;
	}
	
	public int getHeading()
	{
		return heading;
	}
	
	public void setHeading(int h)
	{
		heading = h;
	}
	
	public Movable(int size, int color, Point point, int gameSpeed, int gameHeading)
	{
		super(size, color, point);
		speed = gameSpeed;
		heading = gameHeading;
	}


}
