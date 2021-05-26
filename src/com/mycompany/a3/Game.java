package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Commands.*;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;

public class Game extends Form implements Runnable
{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	private Point bounding; // bottom right of mapView
	private BGSound bgSound;
	
	public Game()
	{
		gw = new GameWorld();
		gw.init();
		
		mv = new MapView(gw);
		sv = new ScoreView(gw); 
		
		timer = new UITimer(this);
		timer.schedule(30, true, this);
		
		gw.addObserver(mv); 
		gw.addObserver(sv);
		
		this.setLayout(new BorderLayout());
		add(BorderLayout.CENTER, mv);
		add(BorderLayout.NORTH, sv);
				
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		Button accelerate = createButton("Accelerate");
		AccelerateCommand myAccelerateCommand = new AccelerateCommand(gw);
		accelerate.setCommand(myAccelerateCommand);
		
		Button stop = createButton("Brake");
		BrakeCommand myBrakeCommand = new BrakeCommand(gw);
		stop.setCommand(myBrakeCommand);
		
		Button left = createButton("Left");
		LeftCommand myLeftCommand = new LeftCommand(gw);
		left.setCommand(myLeftCommand);
		
		Button right = createButton("Right");
		RightCommand myRightCommand = new RightCommand(gw);
		right.setCommand(myRightCommand);
			
		Button changeStrat = createButton("Change Strategies");
		ChangeStrategyCommand myChangeStrategyCommand = new ChangeStrategyCommand(gw);
		changeStrat.setCommand(myChangeStrategyCommand);

		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		Label myLabel = createLabel("Silli-Challenge Game");
		myToolbar.setTitleComponent(myLabel);
		
		myToolbar.addCommandToSideMenu(myAccelerateCommand);
		ExitCommand myExitCommand = new ExitCommand(gw);
		myToolbar.addCommandToSideMenu(myExitCommand);
		
		

		CheckBox soundCB = new CheckBox();
		String status;
		if(gw.getSound())
		{
			status = "ON";
		}
		else 
			status = "OFF";
		
		AboutCommand myAboutCommand = new AboutCommand(gw);
		myToolbar.addCommandToSideMenu(myAboutCommand);
		
		HelpCommand myHelpCommand = new HelpCommand();
		myToolbar.addCommandToRightBar(myHelpCommand);
		
		this.addKeyListener('a', myAccelerateCommand);
		this.addKeyListener('b', myBrakeCommand);
		this.addKeyListener('l', myLeftCommand);
		this.addKeyListener('r', myRightCommand);		
		
		westContainer.add(accelerate);
		westContainer.add(stop);
		westContainer.add(left);
		westContainer.add(right);
		
		eastContainer.add(changeStrat);
		
		add(BorderLayout.WEST, westContainer);
		add(BorderLayout.SOUTH, southContainer);
		add(BorderLayout.EAST, eastContainer);

		this.show();
		SoundCommand mySoundCommand = new SoundCommand(gw, soundCB, status);
		soundCB.setCommand(mySoundCommand);
		myToolbar.addComponentToSideMenu(soundCB);
		
		bounding = new Point(mv.getWidth(), mv.getHeight());
		
		gw.createSounds();
		bgSound = new BGSound("music.wav");
		bgSound.play();
	
		
	}

	private Button createButton(String name)
	{
		Button b = new Button(name);
		b.getUnselectedStyle().setBgTransparency(255);
		b.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		b.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		b.getAllStyles().setPadding(Component.TOP, 5);
		b.getAllStyles().setPadding(Component.BOTTOM, 5);
		b.getAllStyles().setPadding(Component.RIGHT, 5);
		b.getAllStyles().setPadding(Component.LEFT, 5);
		b.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		return b;
	}
	private Label createLabel(String name)
	{
		Label l = new Label(name);
		l.getUnselectedStyle().setFgColor(ColorUtil.BLACK);		
		return l;
	}

	@Override
	public void run() 
	{
		gw.clockTick(30, bounding);	
	}

}
