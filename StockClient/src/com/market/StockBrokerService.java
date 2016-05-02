/**
 * StockBrokerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.market;

public interface StockBrokerService extends javax.xml.rpc.Service {
    public java.lang.String getStockBrokerAddress();

    public com.market.StockBroker getStockBroker() throws javax.xml.rpc.ServiceException;

    public com.market.StockBroker getStockBroker(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
