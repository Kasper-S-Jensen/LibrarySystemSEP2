package server.network.reservation;

import client.core.ModelFactoryClient;
import server.core.ModelFactoryServer;
import shared.loan.Loan;
import shared.loan.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.servers.ClientCallback;
import shared.servers.ReservationServer;
import shared.util.EventTypes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ReservationServerImpl implements ReservationServer
{
  public ReservationServerImpl()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerClientCallBack(ClientCallback client)
      throws RemoteException
  {


    PropertyChangeListener listenerReservationRegistered = new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        //TODO: Implement reservationRegistered callback method on Client and call it here. Catch remoteexception and unsubscribe as listener
      }
    };
    ModelFactoryServer.getInstance().getLoanModel().addPropertyChangeListener(EventTypes.RESERVATIONREGISTERED, listenerReservationRegistered);
  }

  @Override public List<Reservation> getAllReservationsByCPR(String cpr)
  {
    return ModelFactoryServer.getInstance().getReservationModelServer().getAllReservationsByCPR(cpr);
  }



  @Override public void endReservation(Reservation reservation)
      throws RemoteException
  {
    ModelFactoryServer.getInstance().getReservationModelServer().endReservation(reservation);
  }

  @Override public Reservation registerReservation(Material material,
      Borrower borrower) throws RemoteException
  {
    return ModelFactoryServer.getInstance().getReservationModelServer().registerReservation(material,borrower);
  }
}
