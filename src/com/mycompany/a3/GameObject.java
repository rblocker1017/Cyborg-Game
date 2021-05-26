package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
public abstract class GameObject implements IDrawable, ICollider
{
	private int size;
	private Point location;
	private int color;
	
	public int getSize()
	{
		return size;
	}
	
	public Point getLocation()
	{
		return location;
	}	
	
	public void setLocation(Point p)
	{
		location = p;
	}
	
	public String getColor() // returns color as a String in RGB values such as (255, 0, 0)
	{
		return ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color);
		
	}
	
	public int getIntColor() // returns color as an int
	{
		return color;
	}
	
	public void setRGBColor(int red, int green, int blue) // sets individual rgb values 
	{
		int realColor = ColorUtil.rgb(red, green, blue);
		color = realColor;		
	}
	
	public void setColor(int gameColor) // sets color to a different value
	{
		color = gameColor;
	}
	
	public GameObject(int s)
	{
		size = s;
	}
	public GameObject(int gameSize, int gameColor, Point point)
	{
		size = gameSize;
		color = gameColor;
		location = point;
	}
	
	public String toString() {
		return "";}
	public GameObject() {}
	
	@Override
	public boolean collidesWith(GameObject otherObject) 
	{
		boolean result = false;
		int thisCenterX = (int) (this.getLocation().getX() + (this.getSize()/2));
		int thisCenterY = (int) (this.getLocation().getY() + (this.getSize()/2));
		
		int otherCenterX = (int) (otherObject.getLocation().getX() + (otherObject.getSize()/2));
		int otherCenterY = (int) (otherObject.getLocation().getY() + (otherObject.getSize()/2));
		
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		
		int thisRadius = this.getSize()/2;
		int otherRadius = otherObject.getSize()/2;
		
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius+ otherRadius*otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) 
		{ 
			result = true ; 
		}
		return result ;
	}
}
