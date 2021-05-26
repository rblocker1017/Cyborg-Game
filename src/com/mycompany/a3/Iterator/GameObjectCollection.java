package com.mycompany.a3.Iterator;

import java.util.ArrayList;

import com.mycompany.a3.GameObject;

public class GameObjectCollection implements ICollection 
{
	private ArrayList<GameObject> gameObjects;
	
	public GameObjectCollection()
	{
		gameObjects = new ArrayList<GameObject>();
	}
	
	@Override
	public void add(GameObject g) 
	{
		gameObjects.add(g);
	}

	@Override
	public GOCIterator getIterator() 
	{
		return new GOCIterator();
	}
	
	private class GOCIterator implements IIterator
	{
		private int index;

		public GOCIterator()
		{
			index = -1;
		}
		@Override
		public boolean hasNext() 
		{
			if (gameObjects.size() <= 0)
			{
				return false;
			}
			else if(gameObjects.size()-1 == index)
			{
				return false;
			}
			else
				return true;	
		}

		@Override
		public GameObject getNext() 
		{
			index++;
			return gameObjects.get(index);
		}
			
	}

	
}

