package com.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

import com.market.StockBroker;
import com.market.StockBrokerProxy;

public class StockClient {

	private static StockClient client;
	private StockBroker broker;
	
	private StockClient(){
		broker = new StockBrokerProxy();
	}
	
	public static StockClient getInstance(){
		if (client == null){
			client = new StockClient();
		}
		return client;
	}
	
	public int getQuote(String scriptName){
		int marketvalue = -1;
		try {
			marketvalue = broker.stockQuote(scriptName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return marketvalue;
	}
	
	public static void main(String[] args) {
		
		Map<Integer, String> scriptMap = new HashMap<>();
		
		scriptMap.put(1, "AAPL");	// Apple's stock
		scriptMap.put(2, "MSFT");	// Microsoft's stock
		scriptMap.put(3, "SNE"); 	// Sony's stock
		scriptMap.put(4, "AMZN");	// Amazon's stock
		scriptMap.put(5, "GOOGL");	// Google's stock
		
		Random rand = new Random(2L);
		
		boolean tryWS = false;
		StockClient client = StockClient.getInstance();
		SimpleSocket sock = null;
		
		// Lets try to connect using socket first, if it fails then use the webservice		
		try {
			String destURI = "ws://localhost:8080/StockCentral/stockCentral--";
			sock = new SimpleSocket();
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(sock, new URI(destURI));
			//sock.getLatch().await();
		} catch (DeploymentException | IOException | URISyntaxException e) {
			e.printStackTrace();
			System.out.println("WebSocket connection refused/failed...");
			tryWS = true;
		}
		
		for (int i = 0; i < 100; i++){
			int j = rand.nextInt(5) + 1;
			
			String cur = scriptMap.get(j);
			
			if (!tryWS){
				System.out.println("Connected via WebSocket, price of " + cur + " is :\t" + sock.getQuote(cur));
			}else{
				System.out.println("Connected via HTTP, price of " + cur + "is :\t" + client.getQuote(cur));
			}
			
			try {
				Thread.sleep(100l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
