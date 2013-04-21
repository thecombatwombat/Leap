package com.leap.stuff;

public class RobotArmState
{

	public final float armOpenThreshold= 55.00f;
	public final float armCloseThreshold= 45.00f;
	public final float armForwardThreshold= -80.00f;
	public final float armBackwardThreshold= 80.00f;
	public final float armLeftThreshold= -50.00f;
	public final float armRightThreshold= 50.00f;
	
	public final int minFramesToCloseArm=1;
	public final int minFramesToOpenArm=1;
	public final int minFramesToMoveArmForward=20;
	public final int minFramesToMoveArmBackwards=20;
	public final int minFramesToMoveArmLeft=20;
	public final int minFramesToMoveArmRight=20;
	
	private int closeThresholdFrameCount;
	private int openThresholdFrameCount;
	private int forwardThresholdFrameCount;
	private int backwardsThresholdFrameCount;
	private int leftThresholdFrameCount;
	private int rightThresholdFrameCount;
	
	
	public int getCloseThresholdFrameCount()
	{
		return closeThresholdFrameCount;
	}

	public int getOpenThresholdFrameCount()
	{
		return openThresholdFrameCount;
	}
	

	public int getForwardThresholdFrameCount()
	{
		return forwardThresholdFrameCount;
	}

	public int getBackwardsThresholdFrameCount()
	{
		return backwardsThresholdFrameCount;
	}

	public int getLeftThresholdFrameCount()
	{
		return leftThresholdFrameCount;
	}

	public int getRightThresholdFrameCount()
	{
		return rightThresholdFrameCount;
	}

	public RobotArmState()
	{
		closeThresholdFrameCount=0;
		openThresholdFrameCount=0;
		forwardThresholdFrameCount=0;
		backwardsThresholdFrameCount=0;
		leftThresholdFrameCount=0;
		rightThresholdFrameCount=0;
	}
	
	public void addCloseFrame()
	{
		closeThresholdFrameCount++;
	}
	
	public void resetOpenFrames()
	{
		openThresholdFrameCount=0;
	}
	
	public void addOpenFrame()
	{
		openThresholdFrameCount++;
	}
	
	public void resetClosedFrames()
	{
		closeThresholdFrameCount=0;
	}
	
	public void addForwardFrame()
	{
		forwardThresholdFrameCount++;
	}
	
	public void resetForwardFrameCount()
	{
		forwardThresholdFrameCount=0;
	}
	
	public void addBackwardsFrame()
	{
		backwardsThresholdFrameCount++;
	}
	
	public void resetBackwardsFrameCount()
	{
		backwardsThresholdFrameCount=0;
	}
	
	public void addLeftFrame()
	{
		leftThresholdFrameCount++;
	}
	
	public void resetLeftFrameCount()
	{
		leftThresholdFrameCount=0;
	}
	
	public void addRightFrame()
	{
		rightThresholdFrameCount++;
	}
	
	public void resetRightFrameCount()
	{
		rightThresholdFrameCount=0;
	}
}
