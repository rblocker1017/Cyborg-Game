package com.mycompany.a3.Commands;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class HelpCommand extends Command{

	public HelpCommand()
	{
		super("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Command cOk = new Command("Ok");
		TextArea myTextArea = new TextArea("a: Accelerate \nb: Brake \nl: Turn left \nr: Turn right \ne: Collide with Energy Station \n"
				+ "g: Collide with drone \nt: Clock Tick");
		myTextArea.setEditable(false);
		Dialog.show("Key Commands:", myTextArea, cOk);
	}
}
