package com.mycompany.a3.Iterator;
import com.mycompany.a3.GameObject;

public interface ICollection 
{
	public void add(GameObject g);
	public IIterator getIterator();

}
