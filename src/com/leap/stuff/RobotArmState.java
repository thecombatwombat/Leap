package com.leap.stuff;

public class RobotArmState
{

	public final float armOpenThreshold=55.00f;
	public final float armCloseThreshold=45.00f;
	
	public final int minFramesToCloseArm=10;
	public final int minFramesToOpenArm=10;
	
	private int closeThresholdFrameCount;
	private int openThresholdFrameCount;
	
	
	public int getCloseThresholdFrameCount()
	{
		return closeThresholdFrameCount;
	}

	public int getOpenThresholdFrameCount()
	{
		return openThresholdFrameCount;
	}

	public RobotArmState()
	{
		closeThresholdFrameCount=0;
		openThresholdFrameCount=0;
	}
	
	public void addCloseFrame()
	{
		closeThresholdFrameCount++;
	}
	
	public void addOpenFrame()
	{
		openThresholdFrameCount++;
	}
	
	public void resetOpenFrames()
	{
		openThresholdFrameCount=0;
	}
	
	public void resetClosedFrames()
	{
		closeThresholdFrameCount=0;
	}
}
