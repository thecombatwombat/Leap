package com.leap.stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture.Type;
import com.leapmotion.leap.Listener;

public class LeapListener extends Listener
{

	public LeapListener()
	{
		super();
	}

	public LeapListener(long arg0, boolean arg1)
	{
		super(arg0, arg1);
	}

	@Override
	public void onInit(Controller controller)
	{
		super.onInit(controller);
		System.out.println("onInit");
	}

	@Override
	public void onConnect(Controller controller)
	{
		super.onConnect(controller);
		System.out.println("onConnect");
	}

	@Override
	public void onDisconnect(Controller controller)
	{
		super.onDisconnect(controller);
		System.out.println("onDisconnect");
	}

	@Override
	public void onExit(Controller controller)
	{
		super.onExit(controller);
		System.out.println("onExit");
	}

	@Override
	public void onFrame(Controller controller)
	{
		super.onFrame(controller);
		Frame frame = controller.frame();
		FingerList fingerList=frame.fingers();
		System.out.println(frame.gestures().count());
		if(fingerList.isEmpty()){
			System.out.println("empty finger list");
			return;
		}
		List<Finger> fingers= new ArrayList<Finger>();
		Iterator<Finger> iterator=fingerList.iterator();

		while(iterator.hasNext())
		{
			fingers.add(iterator.next());
		}

		Comparator<Finger> fingerComparator= new Comparator<Finger>(){

			@Override
			public int compare(Finger finger1, Finger finger2)
			{
				return (int) (Math.round(finger1.tipPosition().getZ() - finger2.tipPosition().getZ()));
			}
		};

		Collections.sort(fingers, fingerComparator);
		if(fingers.size()==2)
		{
			System.out.println("Finger: "+fingers.get(fingers.size()-1).id()+ " position: "+fingers.get(0).tipPosition() );
			System.out.println("Finger: "+fingers.get(fingers.size()-2).id()+ " position: "+fingers.get(1).tipPosition() );
			System.out.println("Difference in x axis: "+ Math.abs((fingers.get(fingers.size()-1).tipPosition().getX()-fingers.get(fingers.size()-2).tipPosition().getX())));
		}
		else if( fingers.size()==1 )
		{
			System.out.println("Single finger");
		}
		else
		{
			//System.out.println(fingers.size());
		}
	}

}
