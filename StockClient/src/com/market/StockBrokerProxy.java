package com.market;

public class StockBrokerProxy implements com.market.StockBroker {
  private String _endpoint = null;
  private com.market.StockBroker stockBroker = null;
  
  public StockBrokerProxy() {
    _initStockBrokerProxy();
  }
  
  public StockBrokerProxy(String endpoint) {
    _endpoint = endpoint;
    _initStockBrokerProxy();
  }
  
  private void _initStockBrokerProxy() {
    try {
      stockBroker = (new com.market.StockBrokerServiceLocator()).getStockBroker();
      if (stockBroker != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)stockBroker)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)stockBroker)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (stockBroker != null)
      ((javax.xml.rpc.Stub)stockBroker)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.market.StockBroker getStockBroker() {
    if (stockBroker == null)
      _initStockBrokerProxy();
    return stockBroker;
  }
  
  public int stockQuote(java.lang.String scriptname) throws java.rmi.RemoteException{
    if (stockBroker == null)
      _initStockBrokerProxy();
    return stockBroker.stockQuote(scriptname);
  }
  
  
}