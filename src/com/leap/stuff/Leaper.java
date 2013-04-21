/**
 * 
 */
package com.leap.stuff;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import java.net.MalformedURLException;

import org.json.JSONObject;

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
		final SocketIO socket;
		
		try
		{
			socket= new SocketIO("http://rosslazer-leapsocet.nodejitsu.com");
		} catch (MalformedURLException e1)
		{
			// TODO Auto-generated catch block
			System.out.println("exit!");
			return;
		}
		
		socket.connect(new IOCallback()
		{
			
			@Override
			public void onMessage(JSONObject arg0, IOAcknowledge arg1)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onMessage(String arg0, IOAcknowledge arg1)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onError(SocketIOException arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDisconnect()
			{
				System.out.println("Disconnect");
				
			}
			
			@Override
			public void onConnect()
			{
				System.out.println("Connection Established....");
				LeapController controller=new LeapController();
				LeapListener listener=new LeapListener(socket);
				
				controller.addListener(listener);
			}
			
			@Override
			public void on(String arg0, IOAcknowledge arg1, Object... arg2)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
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