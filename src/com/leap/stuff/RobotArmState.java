package com.leap.stuff;

import com.leap.exceptions.InvalidArmStateException;

public class RobotArmState
{

	private final float armOpenThreshold=35.00f;
	private final float armCloseThreshold=25.00f;
	
	private final int minFramesToCloseArm=3;
	private final int minFramesToOpenArm=3;
	
	private int closeThresholdFrameCount;
	private int openThresholdFrameCount;
	
	private boolean armClosingRequested;
	private boolean armOpeningRequested;
	private boolean allStopRequested;
	
	private boolean armClosed;
	private boolean armOpen;
	
	public void requestArmClose() 
	{
		if(armOpen==true)
		{
			if(closeThresholdFrameCount<armCloseThreshold)
			{
				closeThresholdFrameCount++;
			}
			else if (closeThresholdFrameCount==armCloseThreshold)
			{
				sendArmCloseRequest();// If there is a callback, it comes here
				openThresholdFrameCount=0;
			}
		}
		//does this need an else with an exception thrown?
	}
	
	public void requestArmOpen() 
	{
		if(armClosed==true)
		{
			if(openThresholdFrameCount<armOpenThreshold)
			{
				openThresholdFrameCount++;
			}
			else if (openThresholdFrameCount==armOpenThreshold)
			{
				sendArmOpenRequest();// If there is a callback, it comes here
				closeThresholdFrameCount=0;
			}
		}
		//does this need an else with an exception thrown?
	}
	
	public void sendArmCloseRequest()
	{
		//TODO: Add some real functionality here
		System.out.println("Sending Arm Closing Request");
		armClosingRequested=true;
		armOpeningRequested=false;
		//Should there be a callback associated with this?
	}
	
	public void setArmClosed() throws InvalidArmStateException
	{
		if (armClosingRequested==true)
		{
			if(armOpen==true)
			{
				armClosed=true;
				armOpen=false;
				openThresholdFrameCount=0;
			}
			else
			{
				throw new InvalidArmStateException();
			}
		}
		else
		{
			throw new InvalidArmStateException();
		}
		
	}
	
	public void sendArmOpenRequest()
	{
		//TODO: Add some real functionality here
		System.out.println("Sending Arm Open Request");
		armOpeningRequested=true;
		armClosingRequested=false;
		//Should there be a callback associated with this?
	}
	
	public void setArmOpen() throws InvalidArmStateException
	{
		if (armClosingRequested==true)
		{
			if(armOpen==true)
			{
				armClosed=true;
				armOpen=false;
				openThresholdFrameCount=0;
			}
			else
			{
				throw new InvalidArmStateException();
			}
		}
		else
		{
			throw new InvalidArmStateException();
		}
		
	}

}
