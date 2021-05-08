package client.model.loan;

import client.model.loan.loanstates.LoanState;
import client.model.loan.loanstates.NewLoanState;
import client.model.material.Material;
import client.model.user.Borrower;

public class Loan
{

//  private int loanID, materialID, copyNumber, numberOfExtensions;
  private LoanState loanState;
  private Material material;
  private Borrower borrower;
  private String deadline, loanDate, returnDate;
  private int loanID;
//  public Loan(int loanID, int materialID, int copyNumber, String cpr, String loanDate, String deadline, String title, int numberOfExtensions)
//  {
//    this.loanID = loanID;
//    this.materialID = materialID;
//    this.copyNumber = copyNumber;
//    this.cpr = cpr;
//    this.title = title;
//    this.loanDate = loanDate;
//    this.deadline = deadline;
//    this.numberOfExtensions = numberOfExtensions;
//  }

  public Loan(Material material, Borrower borrower, String deadline, String loanDate, String returnDate, int loanID)
  {
    this.material = material;
    this.borrower = borrower;
    this.deadline = deadline;
    this.loanDate = loanDate;
    this.returnDate = returnDate;
    this.loanID = loanID;
    setLoanState(new NewLoanState());
  }

  public void setLoanState(LoanState loanState)
  {
    this.loanState = loanState;
  }

  public void extendLoan()
  {
    loanState.extendLoan(this);
  }

  public Material getMaterial()
  {
    return material;
  }


  public Borrower getBorrower()
  {
    return borrower;
  }

  public int getLoanID()
  {
    return loanID;
  }
  //
//  public void setReturnDate(String returnDate)
//  {
//    this.returnDate = returnDate;
//  }
//
//  public int getLoanID()
//  {
//    return loanID;
//  }
//
//  public int getMaterialID()
//  {
//    return materialID;
//  }
//
//  public int getCopyNumber()
//  {
//    return copyNumber;
//  }
//
//  public int getNumberOfExtensions()
//  {
//    return numberOfExtensions;
//  }
//
//  public String getCpr()
//  {
//    return cpr;
//  }
//
//  public String getLoanDate()
//  {
//    return loanDate;
//  }
//
//  public String getDeadline()
//  {
//    return deadline;
//  }
//
//  public String getReturnDate()
//  {
//    return returnDate;
//  }
//
//  public String getTitle()
//  {
//    return title;
//  }

}
