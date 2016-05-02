package com.client;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;


@WebSocket
public class SocketClient {

	private final CountDownLatch closeLatch;
	private Session session;
	
	public SocketClient() {
		closeLatch = new CountDownLatch(5);
	}
	
	public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException{
		return this.closeLatch.await(duration, unit);
	}
	
	@OnWebSocketClose
	public void onClose(int statusCode, String reason){
		System.out.println("Connection closed: " + statusCode + ":\t" + reason);
		this.session = null;
		this.closeLatch.countDown();
	}
	
	@OnWebSocketConnect
	public void OnWebSocketConnect(Session session){
		System.out.println("Got session :\t" + session);
		this.session = session;
		
		try {
			Future<Void> fut;
			fut = session.getRemote().sendStringByFuture("AAPL");
			fut.get(2, TimeUnit.SECONDS);
			session.close(StatusCode.NORMAL, "Client done!");
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	@OnWebSocketMessage
	public void onMessage(String message){
		System.out.println("The quote is : " + message);
	}
}
