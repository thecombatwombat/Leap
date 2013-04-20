/**
 * 
 */
package com.leap.stuff;

/**
 * @author architjoshi
 *
 */
public class Leaper 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("Na na na na na na na na na na na... Batmaaaaan!");
		LeapListener listener=new LeapListener();
		LeapController controller=new LeapController();
		
		controller.addListener(listener);
		
		System.out.println("Press enter to exit");
		
		try
		{
			System.in.read();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}	