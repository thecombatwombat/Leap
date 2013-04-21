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
	private RobotArmState robotArmState;
	public LeapListener()
	{
		this.robotArmState=new RobotArmState();
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
			//System.out.println("Finger: "+fingers.get(fingers.size()-1).id()+ " position: "+fingers.get(0).tipPosition() );
			//System.out.println("Finger: "+fingers.get(fingers.size()-2).id()+ " position: "+fingers.get(1).tipPosition() );
			float xAxisFingerDistance=Math.abs((fingers.get(0).tipPosition().getX()-fingers.get(1).tipPosition().getX()));
			System.out.println("Difference in x axis: "+xAxisFingerDistance);
			
			if(xAxisFingerDistance<robotArmState.armCloseThreshold)
			{
				robotArmState.addCloseFrame();
				if(robotArmState.getCloseThresholdFrameCount()>robotArmState.minFramesToCloseArm)
				{
					System.out.println("Close Arm!");
					robotArmState.resetOpenFrames();
				}
			}
			else if(xAxisFingerDistance>robotArmState.armOpenThreshold)
			{
				robotArmState.addOpenFrame();
				if(robotArmState.getOpenThresholdFrameCount()>robotArmState.minFramesToOpenArm)
				{
					System.out.println("Open Arm!");
					robotArmState.resetClosedFrames();
				}
			}
		
		}
		else if( fingers.size()==1 )
		{
			float zAxisFingerPosition=fingers.get(0).tipPosition().getZ();
			float xAxisFingerPosition=fingers.get(0).tipPosition().getX();
			
			if(zAxisFingerPosition<robotArmState.armForwardThreshold) //As you move forward, the Z value decreases
			{
				robotArmState.addForwardFrame();
				if(robotArmState.getForwardThresholdFrameCount()>robotArmState.minFramesToMoveArmForward)
				{
					System.out.println("Move forward: "+zAxisFingerPosition);
					robotArmState.resetForwardFrameCount();
				}
			}
			if(zAxisFingerPosition>robotArmState.armBackwardThreshold)
			{
				robotArmState.addBackwardsFrame();
				if(robotArmState.getBackwardsThresholdFrameCount()>robotArmState.minFramesToMoveArmBackwards)
				{
					System.out.println("Move back: "+zAxisFingerPosition);
					robotArmState.resetBackwardsFrameCount();
				}
			}
			
			if(xAxisFingerPosition<robotArmState.armLeftThreshold)
			{
				robotArmState.addLeftFrame();
				if(robotArmState.getLeftThresholdFrameCount()>robotArmState.minFramesToMoveArmLeft)
				{
					System.out.println("Move left: "+xAxisFingerPosition);
					robotArmState.resetLeftFrameCount();
				}
			}
			if(xAxisFingerPosition>robotArmState.armRightThreshold)
			{
				robotArmState.addRightFrame();
				if(robotArmState.getRightThresholdFrameCount()>robotArmState.minFramesToMoveArmRight)
				{
					System.out.println("Move right: "+xAxisFingerPosition);
					robotArmState.resetRightFrameCount();
				}
			}
		}
		else if (fingers.size()==5)
		{
			System.out.println("Stop!");
			robotArmState.resetClosedFrames();
			robotArmState.resetOpenFrames();
		}
	}

}
