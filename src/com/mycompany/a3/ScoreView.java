package com.mycompany.a3;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class ScoreView extends Container implements Observer 
{	
	private Label time = createLabel("Time:");
	private Label lives = createLabel("Lives Left:");
	private Label lastBase = createLabel("Player Last Base Reached:");
	private Label energyLevel = createLabel("Player Energy Level:");
	private Label damageLevel = createLabel("Player Damage Level:");
	private Label sound = createLabel("Sound:");
	
	private Label timeValue = createValueLabel("");
	private Label livesValue = createValueLabel("");
	private Label lastBaseValue = createValueLabel("");
	private Label energyLevelValue = createValueLabel("");
	private Label damageLevelValue = createValueLabel("");
	private Label soundValue = createValueLabel("");
	
	
	@Override
	public void update (Observable o, Object arg) 
	{
		HashMap hm = ((GameWorld) o).displayValues();
		updateLabels(time, timeValue, hm);
		updateLabels(lives, livesValue, hm);
		updateLabels(lastBase, lastBaseValue, hm);
		updateLabels(energyLevel, energyLevelValue, hm);
		updateLabels(damageLevel, damageLevelValue, hm);
		updateLabels(sound, soundValue, hm);
	}
	
	public ScoreView(GameWorld g)
	{
		
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		HashMap hm = g.displayValues();
		addLabels(time, timeValue, hm);
		addLabels(lives, livesValue, hm);
		addLabels(lastBase, lastBaseValue, hm);
		addLabels(energyLevel, energyLevelValue, hm);
		addLabels(damageLevel, damageLevelValue, hm);
		addLabels(sound, soundValue, hm);
		timeValue.getAllStyles().setPadding(Component.RIGHT, 5);
		energyLevelValue.getAllStyles().setPadding(Component.RIGHT, 5);
		damageLevelValue.getAllStyles().setPadding(Component.RIGHT, 5);

	}
	
	private Label createLabel(String name)
	{
		Label l = new Label(name);
		l.getUnselectedStyle().setFgColor(ColorUtil.BLACK);		
		return l;
	}
	private Label createValueLabel(String name)
	{
		Label l = new Label(name);
		l.getUnselectedStyle().setFgColor(ColorUtil.BLACK);		
		return l;
	}
	
	private void addLabels(Label label, Label value, HashMap hm)
	{
		add(label);
		add(value);
		updateLabels(label, value, hm);
	}
	
	public void updateLabels(Label label, Label value, HashMap hm)
	{
		if(label == damageLevel)
		{
			value.setText(hm.get(label.getText()) + "        ");
		}
		else
			value.setText(hm.get(label.getText()) + "   ");
	}

}
