package client.core;

import client.view.addlibrarian.AddLibrarianVM;
import client.view.adduser.AddUserVM;
import client.view.loanReserve.LoanReserveVM;
import client.view.copies.CopiesVM;
import client.view.main.MainVM;
import client.view.myLoans.MyLoansVM;
import client.view.myreservations.MyReservationsVM;
import client.view.registermaterial.RegisterMaterialVM;
import client.view.search.SearchVM;
import client.view.stafflogin.StaffLogInVM;

public class ViewModelFactory
{
  private static ViewModelFactory viewModelFactory;

  public static ViewModelFactory getInstance()
  {
    if (viewModelFactory == null)
    {
      viewModelFactory = new ViewModelFactory();
    }
    return viewModelFactory;
  }

  private AddUserVM addUserVM;
  private AddLibrarianVM addLibrarianVM;
  private LoanReserveVM loanReserveVM;
  private CopiesVM copiesVM;
  private MainVM mainVM;
  private MyLoansVM myLoansVM;
  private RegisterMaterialVM registerMaterialVM;
  private SearchVM searchVM;
  private StaffLogInVM staffLogInVM;
  private MyReservationsVM myReservationsVM;

  public AddUserVM getAddUserVM()
  {
    if (addUserVM == null)
    {
      addUserVM = new AddUserVM();
    }
    return addUserVM;
  }

  public AddLibrarianVM getAddLibrarianVM()
  {
    if (addLibrarianVM == null)
    {
      addLibrarianVM = new AddLibrarianVM();
    }
    return addLibrarianVM;
  }

  public LoanReserveVM getBorrowReserveVM()
  {
    if (loanReserveVM == null)
    {
      loanReserveVM = new LoanReserveVM();
    }
    return loanReserveVM;
  }

  public CopiesVM getCopiesVM()
  {
    if (copiesVM == null)
    {
      copiesVM = new CopiesVM();
    }
    return copiesVM;
  }

  public MainVM getMainVM()
  {
    if (mainVM == null)
    {
      mainVM = new MainVM();
    }
    return mainVM;
  }

  public MyLoansVM getMyMaterialVM()
  {
    if (myLoansVM == null)
    {
      myLoansVM = new MyLoansVM(ModelFactoryClient.getInstance().getLoanModelClient(), ModelFactoryClient.getInstance().getUserModelClient());
    }
    return myLoansVM;
  }

  public RegisterMaterialVM getRegisterMaterialVM()
  {
    if (registerMaterialVM == null)
    {
      registerMaterialVM = new RegisterMaterialVM();
    }
    return registerMaterialVM;
  }

  public SearchVM getSearchVM()
  {
    if (searchVM == null)
    {
      searchVM = new SearchVM();
    }
    return searchVM;
  }

  public StaffLogInVM getStaffLogInVM()
  {
    if (staffLogInVM == null)
    {
      staffLogInVM = new StaffLogInVM();
    }
    return staffLogInVM;
  }

  public MyReservationsVM getMyReservationsVM()
  {
    if (myReservationsVM == null)
    {
      myReservationsVM = new MyReservationsVM(ModelFactoryClient.getInstance().getReservationModelClient(), ModelFactoryClient.getInstance()
          .getUserModelClient());
    }
    return myReservationsVM;
  }
}
