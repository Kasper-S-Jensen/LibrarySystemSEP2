package client.view.mymaterial;

import client.core.ModelFactoryClient;
import client.model.loan.LoanModelClient;
import client.model.user.UserModelClient;
import shared.loan.Loan;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventTypes;

public class MyMaterialVM
{
  private ObservableList<Loan> activeLoans;
  private ObjectProperty<Loan> loanProperty;
  private StringProperty cprProperty;
  private LoanModelClient loanModel;
  private UserModelClient userModel;

  public MyMaterialVM(LoanModelClient loanModel, UserModelClient userModel)
  {
    activeLoans = FXCollections.observableArrayList();
    cprProperty = new SimpleStringProperty(userModel.getLoginUser().getCpr());

    if (ModelFactoryClient.getInstance().getLoanModelClient()
        .getAllLoansByCPR(cprProperty.get()) != null)
    {
      //Initialises with all active loans for the user.
      activeLoans.addAll(loanModel
          .getAllLoansByCPR(cprProperty.get()));
    }
    ModelFactoryClient.getInstance().getLoanModelClient()
        /*Listens to for the LOANREGISTERED and LOANENDED event that is specific to the borrowers cpr
        * To ensure that other users Loan events won't affect the specific users window. */
        .addPropertyChangeListener(EventTypes.LOANREGISTERED,
            evt -> {

              if (((Loan) evt.getNewValue()).getBorrower().getCpr().equals(cprProperty.get()))
              {
                activeLoans.add((Loan) evt.getNewValue());
                System.out.println("LOAN REGISTERED CAUGHT");
              }
            });
    ModelFactoryClient.getInstance().getLoanModelClient()
        .addPropertyChangeListener(EventTypes.LOANENDED,
            evt -> {

              if (((Loan) evt.getNewValue()).getBorrower().getCpr().equals(cprProperty.get()))
              {
                activeLoans.removeIf(
                    activeLoan -> activeLoan.getLoanID() == ((Loan) evt
                        .getNewValue()).getLoanID());
              }
              System.out.println("LOAN ENDED EVT CAUGHT");
                System.out.println(((Loan) evt.getNewValue()).getBorrower().getCpr());

            });
    loanProperty = new SimpleObjectProperty<>();
    this.loanModel = loanModel;
    this.userModel = userModel;
  }


  public void endLoan()
  {
    loanModel.endLoan(loanProperty.get());
    System.out.println(loanProperty.get().getLoanID());
    System.out.println(loanProperty.get().getMaterial().getMaterialID());
    System.out.println(loanProperty.get().getMaterial().getCopyNumber());
  };

  public ObservableList<Loan> getLoanList()
  {
    return activeLoans;
  }

public ObjectProperty<Loan> loanProperty()
{
  return loanProperty;
}

}
