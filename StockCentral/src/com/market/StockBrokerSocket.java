package com.market;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/stockCentral")
public class StockBrokerSocket {

	private StockBroker broker;
	
	public StockBrokerSocket() {
		broker = new StockBroker();
	}
	
	@OnOpen
	public void onOpen(Session session){
		if (session.isOpen()){
			System.out.println("Server side socket started...");
		}
	}
	
	@OnMessage
	public void getQuote(Session session, String name){
		System.out.println("Server received request for " + name);
		
		if(session.isOpen()){
			try {
				session.getBasicRemote().sendText(broker.stockQuote(name) + "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@OnClose
	public void close(){
		System.out.println("Closing server socket...");
	}
}