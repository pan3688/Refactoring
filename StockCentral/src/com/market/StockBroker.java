package com.market;

import java.util.HashMap;
import java.util.Map;

/*
 * In this sample application, the StockBroker.java is an application
 * that will find out the prices of stocks from a database or some other
 * source. This java object is exposed to clients in 2 protocols
 * 	1. HTTP WebService
 * 		The webservice is exposed by extracting a WSDL and providing it
 * 		to the clients. CLients will generate proxies and other metadata
 * 		of this object (such as URL) to connect to it and communicate.
 * 
 * 	2. WebSocket
 * 
 * 	In the Broker side, the application will need to have additional
 * 	code to accept incoming Socket connection requests and then methods
 * 	that will respond to the received socket messages
 * 	eg. StockBrokerSocket.java
 */

public class StockBroker {
	
	private Map<String, Integer> scriptMap;
	
	public StockBroker(){
        scriptMap = new HashMap<>();
		
		scriptMap.put("AAPL", 4);	// Apple's stock
		scriptMap.put("MSFT", 5);	// Microsoft's stock
		scriptMap.put("SNE", 80); 	// Sony's stock
		scriptMap.put("AMZN", 0);	// Amazon's stock
		scriptMap.put("GOOGL", 400);	// Google's stock
	}
	
	public int stockQuote(String scriptname){
		return scriptMap.get(scriptname); 
	}
}
