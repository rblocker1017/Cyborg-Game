package com.mycompany.a3;
import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject 
{
	public Fixed(int s)
	{
		super(s);
	}
	
	public Fixed(int size, int color, Point point)
	{
		super(size, color, point);
	}
	
	
	@Override
	public void setLocation(Point p)
	{
		// fixed objects are not allowed to change location
	}
}
