package server.model.user;

import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.borrower.BorrowerList;
import shared.person.librarian.Librarian;
import shared.person.librarian.LibrarianList;
import database.user.borrower.BorrowerImpl;
import database.user.librarian.LibrarianImpl;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class UserModelManagerServer implements UserModelServer
{
  private PropertyChangeSupport support;
  private BorrowerList borrowerList;
  private LibrarianList librarianList;
  private String borrowerCpr;
  private int librarianEMPNr;

  public UserModelManagerServer()
  {
    support = new PropertyChangeSupport(this);
    borrowerList = new BorrowerList();
    librarianList = new LibrarianList();
  }

  @Override public Borrower create(String cpr, String firstName,
      String lastName, String email, String tlfNumber, Address address,
      String password)
  {
    Borrower borrower = null;
    try
    {
      borrower = BorrowerImpl.getInstance()
          .create(cpr, firstName, lastName, email, tlfNumber, address,
              password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    borrowerList.addBorrower(borrower);
    support.firePropertyChange(EventTypes.BORROWERREGISTERED, null, borrower);
    return borrower;
  }

  @Override public boolean logInBorrower(String cprNo, String password)
  {
    boolean login = false;
    try
    {
      login = BorrowerImpl.getInstance().loginBorrower(cprNo, password);

      if (login)
      {
        borrowerCpr = cprNo;
      }

    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    support.firePropertyChange(EventTypes.LOGINREQUESTED, null, login);
    return login;
  }

  @Override public Borrower getLoginBorrower()
  {
    Borrower borrower = null;
    try
    {
      borrower = BorrowerImpl.getInstance().getBorrower(borrowerCpr);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return borrower;
  }

  @Override public Librarian registerLibrarian(int employee_no,
      String firstName, String lastName, String cpr, String tlfNumber,
      String email, Address address, String password)
  {
    Librarian librarian = null;
    try
    {
      librarian = LibrarianImpl.getInstance()
          .create(employee_no, firstName, lastName, cpr, tlfNumber, email,
              address, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    librarianList.addLibrarian(librarian);
    support.firePropertyChange(EventTypes.LIBRARIANREGISTERD, null, librarian);
    return librarian;
  }

  @Override public boolean librarianLogin(int employee_no, String password)
  {
    boolean login = false;
    try
    {
      login = LibrarianImpl.getInstance().librarianLogin(employee_no, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    support.firePropertyChange(EventTypes.LOGINREQUESTED, null, login);
    return login;
  }

  public void setBorrowerCpr(String borrowerCpr)
  {
    this.borrowerCpr = borrowerCpr;
  }

  @Override public Librarian getLoginLibrarian()
  {
    return null;
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
