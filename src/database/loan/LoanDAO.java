package database.loan;

import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.util.List;
import java.util.NoSuchElementException;

public interface LoanDAO
{
  /**
   * Registers a new Loan in the system for the given Material and Borrower.
   * The Loan is bound to a specific copy of the Material unlike Reservations.
   *
   * @param material material is the Material with specific Copy Number the Borrower wants to loan.
   * @param borrower borrower is the owner of the Loan which the Material is bound to.
   * @throws IllegalStateException  if the material is is not available for loan because the number of available copies
   *                                or the material has reservations and the borrower is not the next person to loan the copy.
   * @throws NoSuchElementException if the material is not registered in the system.
   */
  Loan create(Material material, Borrower borrower, String deadline,
      String loanDate) throws IllegalStateException, NoSuchElementException;
  List<Loan> getAllLoansByCPR(String cpr) throws NoSuchElementException;
  void endLoan(Loan loan);
  Loan extendLoan(Loan loan);

}
