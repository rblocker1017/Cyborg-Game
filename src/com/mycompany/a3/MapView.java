package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer
{
	private GameWorld gw;
	

	@Override
	public void update (Observable o, Object arg) 
	{
		repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Point p = new Point(getX(), getY());
		gw.drawObjects(g, p);
		
	}
	
	public MapView(GameWorld g)
	{
		this.getAllStyles().setBorder(Border.createLineBorder(10, ColorUtil.rgb(255, 0, 0)));
		gw = g;
	}
	

}
