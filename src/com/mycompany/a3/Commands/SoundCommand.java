package com.mycompany.a3.Commands;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.BGSound;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command
{
	private CheckBox cb;
	private GameWorld gw;
	private BGSound bg;
	
	public SoundCommand(GameWorld gw, CheckBox cb, String status)
	{
		super("Sound: " + status);
		cb.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.cb = cb;
		this.gw = gw;
		this.bg = bg;
		if(gw.getSound()) // if soundState is true, set it to ON, else sound is OFF
		{
			cb.setSelected(true);
			cb.setText("Sound: ON");
		}
		else
		{
			cb.setSelected(false);
			cb.setText("Sound: OFF");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{

		if(cb.isSelected())
		{
			gw.setSound(true);
			cb.setText("Sound: ON");
		}
		else if(!cb.isSelected())
		{
			gw.setSound(false);
			cb.setText("Sound: OFF");
		}
	}

}