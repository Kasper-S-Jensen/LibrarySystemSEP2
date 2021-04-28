import server.model.LibraryModel;
import server.model.LibraryModelManager;
import server.network.RMIServerImpl;
import shared.RMIServer;

import java.rmi.RemoteException;

public class Test
{
  public static void main(String[] args) throws RemoteException
  {
    LibraryModel model = new LibraryModelManager();
    RMIServer rmiServer = new RMIServerImpl(model);
    rmiServer
        .registerBook("as", "ddee", "2020-01-01", "dreewqt", "ddbh", "Voksen", "Dansk", "12345", 500, 1, 1, "nothing", null);

      rmiServer.createBookCopy(1);
  //  rmiServer.registerLoan(model.searchMaterial("1"), "111111-1111", "1999-12-12");
  }
}
