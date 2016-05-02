package com.client;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class SimpleSocket {
	CountDownLatch latch = new CountDownLatch(2);
	private Session session;
	private static volatile String response = null;
	
	@OnOpen
	public void onOpen(Session session){
		System.out.println("Client socket started...");
		this.session = session;
		latch.countDown();
	}
	
	@OnMessage
	public void onText(String message, Session session){
		System.out.println("Server sent this : " + message);
		response = message;
	}
	
	public void onClose(CloseReason reason, Session session){
		System.out.println("Client socket closing because :\t" + reason.getReasonPhrase());
	}
	
	public CountDownLatch getLatch(){
		return latch;
	}
	
	public int getQuote(String scriptName){
		
		try {
			session.getBasicRemote().sendText(scriptName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (response == null){
			//System.out.println("infinity");
		}
		assert(response != null);
		
		int i = Integer.parseInt(response);
		response = null;
		
		return i;
	}
}
