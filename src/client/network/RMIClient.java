package client.network;

import client.model.material.Material;
import shared.RMIServer;
import shared.util.constants;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient implements Client
{
  private PropertyChangeSupport support;
  private RMIServer server;

  public RMIClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    Registry registry = null;

    try
    {
      registry = LocateRegistry.getRegistry(1099);
      server = (RMIServer) registry.lookup(constants.RMI_SERVER);
    }
    catch (RemoteException | NotBoundException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void registerLoan(Material material, String loanerCPR,
      String deadline)
  {
    try
    {
      server.registerLoan(material,loanerCPR,deadline);
    }
    catch (RemoteException e)
    {
     throw new RuntimeException("Server connection failed");
    }
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
