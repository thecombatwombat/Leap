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
		System.out.println("onFrame");
		// Get the most recent frame and report some basic information
		Frame frame = controller.frame();
		FingerList fingerList=frame.fingers();
		if(fingerList.isEmpty()){
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
		if(fingers.size()>=2)
		{
			System.out.println("Finger: "+fingers.get(fingers.size()-1).id()+ " position: "+fingers.get(0).tipPosition() );
			System.out.println("Finger: "+fingers.get(fingers.size()-2).id()+ " position: "+fingers.get(1).tipPosition() );
		}
	}

}
