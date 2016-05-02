/**
 * StockBrokerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.market;

public class StockBrokerServiceLocator extends org.apache.axis.client.Service implements com.market.StockBrokerService {

    public StockBrokerServiceLocator() {
    }


    public StockBrokerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public StockBrokerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for StockBroker
    private java.lang.String StockBroker_address = "http://localhost:8080/StockCentral/services/StockBroker";

    public java.lang.String getStockBrokerAddress() {
        return StockBroker_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String StockBrokerWSDDServiceName = "StockBroker";

    public java.lang.String getStockBrokerWSDDServiceName() {
        return StockBrokerWSDDServiceName;
    }

    public void setStockBrokerWSDDServiceName(java.lang.String name) {
        StockBrokerWSDDServiceName = name;
    }

    public com.market.StockBroker getStockBroker() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(StockBroker_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getStockBroker(endpoint);
    }

    public com.market.StockBroker getStockBroker(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.market.StockBrokerSoapBindingStub _stub = new com.market.StockBrokerSoapBindingStub(portAddress, this);
            _stub.setPortName(getStockBrokerWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setStockBrokerEndpointAddress(java.lang.String address) {
        StockBroker_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.market.StockBroker.class.isAssignableFrom(serviceEndpointInterface)) {
                com.market.StockBrokerSoapBindingStub _stub = new com.market.StockBrokerSoapBindingStub(new java.net.URL(StockBroker_address), this);
                _stub.setPortName(getStockBrokerWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("StockBroker".equals(inputPortName)) {
            return getStockBroker();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://market.com", "StockBrokerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://market.com", "StockBroker"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("StockBroker".equals(portName)) {
            setStockBrokerEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
