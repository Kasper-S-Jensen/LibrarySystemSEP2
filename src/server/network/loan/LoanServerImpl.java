package server.network.loan;

import client.model.loan.Loan;
import client.model.material.Material;
import client.model.user.borrower.Borrower;
import server.core.ModelFactoryServer;
import server.model.loan.LoanModelServer;
import shared.ClientCallback;
import shared.LoanServer;
import shared.util.EventTypes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class LoanServerImpl implements LoanServer
{


  public LoanServerImpl()
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

  @Override public void registerLoan(Material material, Borrower borrower) throws IllegalStateException
  {
    ModelFactoryServer.getInstance().getLoanModel().registerLoan(material, borrower);
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    return ModelFactoryServer.getInstance().getLoanModel().getAllLoansByCPR(cpr);
  }

  @Override
  public void registerReservation(Material material, Borrower borrower) {
    ModelFactoryServer.getInstance().getLoanModel().registerReservation(material, borrower);
  }

  public void registerClientCallBack(ClientCallback client)
  {
    PropertyChangeListener listener = new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        try
        {
          //TODO: let client/rmiclient implement ClientCallBack and implement the loanRegistered Method. The method should forward/fire the same event.
          client.loanRegistered((Loan) evt.getNewValue());
        }
        catch (RemoteException e)
        {
          //Removes listener if connection failed
          e.printStackTrace();
          ModelFactoryServer.getInstance().getLoanModel().removePropertyChangeListener(EventTypes.LOANREGISTERED, this);
        }
      }
    };
    ModelFactoryServer.getInstance().getLoanModel().addPropertyChangeListener(EventTypes.LOANREGISTERED, listener);
  }
}
