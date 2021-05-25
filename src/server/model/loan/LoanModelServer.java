package server.model.loan;

import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.servers.PropertyChangeSubject;

import java.util.List;
import java.util.NoSuchElementException;

public interface LoanModelServer extends PropertyChangeSubject
{
  /**
   * Registers a new Loan in the system for the given Material and Borrower.
   * The Loan is bound to a specific copy of the Material unlike Reservations.
   *
   * @param material material is the Material with specific Copy Number the Borrower wants to loan.
   * @param borrower borrower is the owner of the Loan which the Material is bound to.
   * @throws IllegalStateException  if the material is is not available for loan.
   * @throws NoSuchElementException if the material is not registered in the system.
   */
  void registerLoan(Material material, Borrower borrower)
      throws IllegalStateException, NoSuchElementException;

  /**
   * Returns a list of all Loans that are registered to the Borrower that has the given cpr.
   *
   * @param cpr cpr is the cpr number of the Borrower for which the method must return all Loans for.
   * @return an List of all Loans registered to the cpr.
   * @throws NoSuchElementException if the borrower has no active Loans registered in the system.
   */
  List<Loan> getAllLoansByCPR(String cpr) throws NoSuchElementException;

  /**
   * Ends the Loan by setting the returnDate of the Loan to the current date in "yyyy-MM-dd" format.
   *
   * @param loan is the Loan which is to be ended.
   */
  void endLoan(Loan loan);

  /**
  * Extends the deadline of the loan by 1 month.
   * The Loan can only be extended 2 times total and cannot be extended if the Material of the Loan has any reservations.
   * Loans can at earliest be extended 7 days before deadline.
   *
   * @param loan is the Loan which is to be extended by one month.
   * @throws IllegalStateException if the Loan has already been extended 2 times, if the Material of the Loan has a reservation or the current date is more than 7 days before deadline.
  * */
  void extendLoan(Loan loan) throws IllegalStateException;
}
