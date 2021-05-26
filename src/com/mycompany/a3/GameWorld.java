package com.mycompany.a3;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.TextField;
import com.mycompany.a3.GameObjects.Base;
import com.mycompany.a3.GameObjects.Cyborg;
import com.mycompany.a3.GameObjects.Drone;
import com.mycompany.a3.GameObjects.EnergyStation;
import com.mycompany.a3.GameObjects.NonPlayerCyborg;
import com.mycompany.a3.GameObjects.PlayerCyborg;
import com.mycompany.a3.Iterator.GameObjectCollection;
import com.mycompany.a3.Iterator.IIterator;
import com.mycompany.a3.Strategy.BaseMoveStrategy;
import com.mycompany.a3.Strategy.IStrategy;
import com.mycompany.a3.Strategy.PlayerMoveStrategy;

public class GameWorld extends Observable
{
	private GameObjectCollection gameObjectCollection; 
	private Random rand = new Random(); // rng for all gameObject values
	
	private int clockCycle = 0;
	private int lives = 3;
	private int finalBase = 4;
	private boolean exitCondition = false; // false initially to prevent player from accidently closing
	private boolean soundState = true; // sound initially set to true

	private Sound cyborgCollision, esCollision, explosion;
	
	private int baseColor = ColorUtil.rgb(0, 0, 255); // colors for all gameObject types
	private int cyborgColor = ColorUtil.rgb(255, 0, 0);
	private int droneColor = ColorUtil.rgb(128, 128, 128);
	private int esColor = ColorUtil.rgb(0, 255, 0);

	
	public void init()
	{
		gameObjectCollection = new GameObjectCollection();
		Point startingPoint = randomPoint(); // retrieve a new random point to start game
		
		PlayerCyborg c = PlayerCyborg.getCyborg(cyborgColor, 0, 10, startingPoint); // starting player Cyborg
		Base b = new Base(1, baseColor, startingPoint); // 1st base
		
		gameObjectCollection.add(c);
		gameObjectCollection.add(b);
		
		for(int i = 2; i < 5; i++) // creates 3 bases
		{
			Base rb = new Base(i, baseColor, randomPoint());
			gameObjectCollection.add(rb);
		}
		
		for(int i = 0; i < 2; i++) // creates 3 npcs
		{
			int offsetX = rand.nextInt(60);
			int offsetY = rand.nextInt(60);
			offsetX = offsetX - - 120;
			offsetY = offsetY - - 120;
			Point p = new Point(startingPoint.getX() + offsetX, startingPoint.getY() + offsetY);
			NonPlayerCyborg npc = new NonPlayerCyborg(cyborgColor, 0, 5, p);
			
			if(i % 2 == 0)
			{
				npc.setStrategy(new BaseMoveStrategy(npc, findBase(npc.getNextBase())));
			}
			else
			{
				npc.setStrategy(new PlayerMoveStrategy(npc, findFirstCyborg()));
			}
			gameObjectCollection.add(npc);
		}
		
		for(int i = 1; i < 3; i++) // adds 2 drones
		{
			int droneSettings[] = droneRandomSettings();
			int size = droneSettings[0];
			int speed = droneSettings[1];
			int heading = droneSettings[2];
			
			Drone d = new Drone(size, droneColor, randomPoint(), speed, heading);
			gameObjectCollection.add(d);
		}
		
		for(int i = 1; i < 3; i++) // adds 2 energy stations
		{
			int esSettings[] = esRandomSettings();
			int size = esSettings[0];
			int capacity = esSettings[1];
			EnergyStation es = new EnergyStation(size, esColor, randomPoint(), capacity);
			gameObjectCollection.add(es);
		}
						
	}

	public PlayerCyborg findFirstCyborg() // finds player cyborg within gameObjects list
	{
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext())
		{
			GameObject gameObject = (GameObject) elements.getNext() ;
			if (gameObject instanceof PlayerCyborg)
			{
				return (PlayerCyborg)gameObject;
			}
			else
			{
			}
			
		}
		return null;
	}
	
	public NonPlayerCyborg findRandomCyborg() // finds player cyborg within gameObjects list
	{
		ArrayList<NonPlayerCyborg> al = new ArrayList<NonPlayerCyborg>();
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext())
		{
			GameObject gameObject = (GameObject) elements.getNext() ;
			if (gameObject instanceof NonPlayerCyborg)
			{
				al.add((NonPlayerCyborg) gameObject);
			}
			else
			{
			}
			
		}
		int randCyb = rand.nextInt(al.size());
		return al.get(randCyb);	
	}
	
	public Base findBase(int baseNumber) // finds base depending on sequence number
	{
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext())
		{
			GameObject gameObject = (GameObject) elements.getNext();
			if (gameObject instanceof Base)
			{
				if(((Base) gameObject).getSN() == baseNumber)
				{
					return (Base) gameObject;
				}
			
			}
			else
			{

			}
			
		}
		
		return null;		
	}
	
	public int[] droneRandomSettings () // creates random settings for drones, which include their Size, speed, and heading
		{
			int droneSize = rand.nextInt(10);
			droneSize = droneSize + 35;
			
			int droneSpeed = rand.nextInt(5);
			droneSpeed = droneSpeed + 5;
			
			int droneHeading = rand.nextInt(359);
			droneHeading = droneHeading + 1;
			
			int droneSettings[] = {droneSize, droneSpeed, droneHeading};
			return droneSettings;
						
		}
	public int[] esRandomSettings () // energy station random settings, including size and capacity
	{
		int esSize = rand.nextInt(150);
		esSize = esSize + 50;
		
		int esCapacity = esSize/4 + 50;
		
		int esSettings[] = {esSize, esCapacity};
		return esSettings;
					
	}
	
	public Point randomPoint() 	    // creates a random point for gameOjbects to be placed into
	{                          		// gameObjects are bounded by 50 units on either side 
		int x = rand.nextInt(900);
		x = x + 50;
		int y = rand.nextInt(900);
		y = y + 50;
		Point p = new Point(x, y);
		return p;
	}
	
	
	public void accelerate() // increases Cyborg's speed varying on damageLevel
	{
		Cyborg c = findFirstCyborg();
		int eL = c.getEnergyLevel();
		
		int speedIncrease = 5;
		int damageLevel = c.getDamageLevel()/40;
		speedIncrease = speedIncrease-damageLevel;
		
		c.setSpeed(c.getSpeed() + speedIncrease);
		if(c.getSpeed() >= c.getMaxSpeed())
		{
			c.setSpeed(c.getMaxSpeed());
		}
		setChanged();
		notifyObservers();
	}
	
	public void brake() // decreases Cyborg's speed by 5
	{
		Cyborg c = findFirstCyborg();
		c.setSpeed(c.getSpeed() - 5);
		if(c.getSpeed() < 0)
		{
			c.setSpeed(0);
		}
		setChanged();
		notifyObservers();
	}
	
	public void left() // changes steering degrees 2 to the left
	{
		Cyborg c = findFirstCyborg();
		c.setSteeringDirection(c.getSteeringDirection() - 2);
		if(c.getSteeringDirection() < -40)
		{
			c.setSteeringDirection(-40);
		}
		setChanged();
		notifyObservers();
	}
	
	public void right() // changes steering degrees 2 to the right
	{
		Cyborg c = findFirstCyborg();
		c.setSteeringDirection(c.getSteeringDirection() + 2);
		if(c.getSteeringDirection() > 40)
		{
			c.setSteeringDirection(40);
		}
		setChanged();
		notifyObservers();
	}
	

	public void addEnergyStation()
	{
		int esSettings[] = esRandomSettings();
		int size = esSettings[0];
		int capacity = esSettings[1];
		EnergyStation newEs = new EnergyStation(size, esColor, randomPoint(), capacity);
		gameObjectCollection.add(newEs);
		setChanged();
		notifyObservers();
	}

	public void createSounds()
	{
		cyborgCollision = new Sound("crash.wav");
		esCollision = new Sound("charge.wav");
		explosion = new Sound("explosion.wav");
	}

	public void clockTick(int elapsedTime, Point bounding) // increments clock cycle by 1, and moves all moveable objects, while also draining energy from cyborg
	{
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext())
		{
			GameObject gameObject = (GameObject) elements.getNext();
			if (gameObject instanceof Movable)
			{
				if (gameObject instanceof PlayerCyborg)
				{
					if(((Cyborg) gameObject).getLastBaseReached() == finalBase)
					{
						this.exit();
						Command cOk = new Command("Ok");
						Command co = Dialog.show("You win!", "", cOk);
						if (co == cOk)
						{
							this.exitAccept();
						}
					}
					if(((PlayerCyborg) gameObject).unmoveable() == false) // cyborg moves and loses energy
					{
						PlayerCyborg c = (PlayerCyborg) gameObject;
						c.setHeading(c.getHeading() + c.getSteeringDirection());
						c.setEnergyLevel(c.getEnergyLevel() - c.getECR());
						
						Point boundingSize = new Point();
						boundingSize.setX(bounding.getX()-c.getSize());
						boundingSize.setY(bounding.getY()-c.getSize()-10);
						
						c.move(elapsedTime, boundingSize);
						if(c.getEnergyLevel() <= 0 || c.getDamageLevel() >= c.getMaximumDamage())
						{
							if(this.getSound())
							{
								explosion.play();
							}
							lives = lives - 1;
							c.setEnergyLevel(999);
							Point newStartingPoint = randomPoint();
							c.setLocation(newStartingPoint);
							c.setDamageLevel(0);
							c.speedUpdate();
							c.setColor(cyborgColor);
							this.init();
						}
					}
					
					else if(((PlayerCyborg) gameObject).unmoveable() == true) // cyborg only loses energy
					{
						PlayerCyborg c = (PlayerCyborg) gameObject;
						c.setEnergyLevel(c.getEnergyLevel() - c.getECR());
						if(c.getEnergyLevel() <= 0 || c.getDamageLevel() >= c.getMaximumDamage())
						{
							if(this.getSound())
							{
								explosion.play();
							}
							lives = lives - 1;
							c.setEnergyLevel(999);
							Point newStartingPoint = randomPoint();
							c.setLocation(newStartingPoint);
							c.setDamageLevel(0);
							c.speedUpdate();
							c.setColor(cyborgColor);
							this.init();
						}
					}
					
				}
				else if (gameObject instanceof NonPlayerCyborg)
				{
					if(((Cyborg) gameObject).getLastBaseReached() == finalBase)
					{
						if(this.getSound())
						{
							explosion.play();
						}
						PlayerCyborg c = findFirstCyborg();
						lives = lives - 1;
						c.setEnergyLevel(999);
						Point newStartingPoint = randomPoint();
						c.setLocation(newStartingPoint);
						c.setDamageLevel(0);
						c.speedUpdate();
						c.setColor(cyborgColor);
						this.init();
					}
					NonPlayerCyborg npc = (NonPlayerCyborg)gameObject;
					npc.invokeStrategy();
					npc.setEnergyLevel(100);
					npc.setHeading(npc.getHeading() + npc.getSteeringDirection());
					
					Point boundingSize = new Point();
					boundingSize.setX(bounding.getX()-npc.getSize());
					boundingSize.setY(bounding.getY()-npc.getSize());
					npc.move(elapsedTime, boundingSize);
				}
				else if(gameObject instanceof Drone)
				{
					Drone d; 
					d = (Drone) gameObject;
					int dchange = rand.nextInt(10);
					dchange = dchange - 5;
					d.setHeading(d.getHeading() + dchange);
					
					Point boundingSize = new Point();
					boundingSize.setX(bounding.getX()-d.getSize());
					boundingSize.setY(bounding.getY()-d.getSize());
					d.move(elapsedTime, boundingSize);
				}
				else
				{
					Point boundingSize = new Point();
					boundingSize.setX(bounding.getX()-gameObject.getSize());
					boundingSize.setY(bounding.getY()-gameObject.getSize());
					((Movable) gameObject).move(elapsedTime, boundingSize);
				}
			}
			IIterator collisions = gameObjectCollection.getIterator();
			
			while(collisions.hasNext())
			{
				GameObject collisionObject = (GameObject) collisions.getNext();
				if(gameObject != collisionObject)
				{
					if(gameObject.collidesWith(collisionObject))
					{
						if(collisionObject instanceof EnergyStation && gameObject instanceof Cyborg)
						{
							
							if(((EnergyStation) collisionObject).getCapacity() != 0)
							{
								if(this.getSound())
								{
									esCollision.play();
								}
								addEnergyStation();
								
							}
						}
						gameObject.handleCollision(collisionObject);
						if(collisionObject instanceof Base && gameObject instanceof NonPlayerCyborg)
						{
							updateStrategy((NonPlayerCyborg) gameObject);
						}
						if(collisionObject instanceof NonPlayerCyborg && gameObject instanceof PlayerCyborg)
						{
							if(((Cyborg) gameObject).getDamageLevel() == 100)
							{
								if(this.getSound())
								{
									cyborgCollision.play();
								}
							}
						}
						
						
						
					}
				}
			}
			
		}
		clockCycle = clockCycle + 1;
		setChanged();
		notifyObservers();
		if(lives <= 0)
		{
			this.exit();
			Command cOk = new Command("Ok");
			Command co = Dialog.show("You lose!", "", cOk);
			if (co == cOk)
			{
				this.exitAccept();
			}
		}
	}

	public void map()
	{

		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext())
		{
			GameObject gameObject = (GameObject) elements.getNext();
			System.out.println(gameObject.toString());
		}
		System.out.println("");
	}
	
	public String mapInfo()
	{
		String s = "";
		
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext())
		{
			GameObject gameObject = (GameObject) elements.getNext();
			s = s + gameObject.toString() + "\n";
		}
		return s;
			
	}
	public void display()
	{
		System.out.println("Display");
		Cyborg c = findFirstCyborg();
		System.out.println("Lives = " + lives + " Clock Cycle = " + clockCycle + " Highest Base Reached = " + c.getLastBaseReached() +
		" Current Energy Level = " + c.getEnergyLevel() + " Current Damage Level = " + c.getDamageLevel());
		System.out.println("");
				
	}
	
	public String displayInfo()
	{
		Cyborg c = findFirstCyborg();
		String s;
		if(soundState)
		{
			s = "ON";
		}
		else
		{
			s = "OFF";
		}
		return ("Time:   " + clockCycle + "   Lives:   " + lives + "   Highest Base Reached:   " + c.getLastBaseReached() +
		"   Player Energy Level:   " + c.getEnergyLevel() + "   Player Damage Level:   " + c.getDamageLevel() + "   Sound:   " + s);
				
	}
	public HashMap displayValues() // returns a HashMap of values according to the label for easy creation in SoundStrategyCommand
	{
		HashMap hm = new HashMap();
		Cyborg c = findFirstCyborg();
		String s;
		if(soundState)
		{
			s = "ON";
		}
		else
		{
			s = "OFF";
		}
		
		hm.put("Time:", clockCycle);
		hm.put("Lives Left:", lives);
		hm.put("Player Last Base Reached:", c.getLastBaseReached());
		hm.put("Player Energy Level:", c.getEnergyLevel());
		hm.put("Player Damage Level:", c.getDamageLevel());
		hm.put("Sound:", s);
		return hm;
		
	}

	public boolean getSound()
	{
		return soundState;
	}
	
	public void setSound(boolean b)
	{
		soundState = b;
		setChanged();
		notifyObservers();
	}
	public void exit()
	{
		exitCondition = true;
	}
	
	public void exitAccept()
	{ 
		if(exitCondition == true)
		{
			System.exit(0);
		}
	}
	public void exitDecline()
	{
		exitCondition = false;
	}

	public void updateStrategy(NonPlayerCyborg npc) 
	{
		if(npc.getStrategy() == "BaseMoveStrategy" && findBase(npc.getNextBase()) != null)
		{
			npc.setStrategy(new BaseMoveStrategy(npc, findBase(npc.getNextBase())));
		}
	}
	
	public void changeStrategy() 
	{
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext())
		{
			GameObject gameObject = (GameObject) elements.getNext();
			if (gameObject instanceof NonPlayerCyborg)
			{
				NonPlayerCyborg npc = (NonPlayerCyborg) gameObject;
				if(npc.getStrategy().equals("PlayerMoveStrategy"))
				{
					npc.setStrategy(new BaseMoveStrategy(npc, findBase(npc.getNextBase())));
				}
				else if(npc.getStrategy().equals("BaseMoveStrategy"))
				{
					npc.setStrategy(new PlayerMoveStrategy(npc, findFirstCyborg()));
				}
			}
			
		}
		setChanged();
		notifyObservers();
	}
	
	public void drawObjects(Graphics g, Point pCmpRelPrnt)
	{
		IIterator elements = gameObjectCollection.getIterator();
		while(elements.hasNext())
		{
			GameObject gameObject = (GameObject) elements.getNext();
			gameObject.draw(g, pCmpRelPrnt);
		}
		setChanged();
		notifyObservers();
	}
	
	
}
