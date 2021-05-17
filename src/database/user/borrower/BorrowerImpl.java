package database.user.borrower;

import shared.person.Address;
import shared.person.borrower.Borrower;
import database.BaseDAO;
import database.address.AddressImpl;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BorrowerImpl extends BaseDAO implements BorrowerDAO
{

  private static BorrowerDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static BorrowerDAO getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new BorrowerImpl();
        }
      }
    }
    return instance;
  }

  @Override public Borrower create(String cpr, String firstName,
      String lastName, String email, String tlfNumber, Address address,
      String password) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if (AddressImpl.getInstence()
          .getAddressId(address.getCity(), address.getStreetName(),
              address.getZipCode(), address.getStreetNr()) == -1)
      {
        Address ad = AddressImpl.getInstence()
            .create(address.getCity(), address.getStreetName(),
                address.getZipCode(), address.getStreetNr());
        PreparedStatement stm = connection.prepareStatement(
            //the table structure needs to change to the values from the query so we can test it
            "INSERT INTO Borrower(cpr_no,f_name,l_name, email, tel_no, address_id, password) values (?,?,?,?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, cpr);
        stm.setString(2, firstName);
        stm.setString(3, lastName);
        stm.setString(4, email);
        stm.setString(5, tlfNumber);
        stm.setInt(6, ad.getAddressId());
        stm.setString(7, password);
        stm.executeUpdate();
        ResultSet keys = stm.getGeneratedKeys();
        keys.next();
        connection.commit();
        return new Borrower(cpr, firstName, lastName, email, tlfNumber, address,
            password);
      }
      else
      {
        int adId = AddressImpl.getInstence()
            .getAddressId(address.getCity(), address.getStreetName(),
                address.getZipCode(), address.getStreetNr());
        PreparedStatement stm = connection.prepareStatement(
            //the table structure needs to change to the values from the query so we can test it
            "INSERT INTO Borrower(cpr_no,f_name,l_name, email, tel_no, address_id, password) values (?,?,?,?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, cpr);
        stm.setString(2, firstName);
        stm.setString(3, lastName);
        stm.setString(4, email);
        stm.setString(5, tlfNumber);
        stm.setInt(6, adId);
        stm.setString(7, password);
        stm.executeUpdate();
        ResultSet keys = stm.getGeneratedKeys();
        keys.next();
        connection.commit();
        return new Borrower(cpr, firstName, lastName, email, tlfNumber, address,
            password);
      }
    }
  }

  @Override public boolean loginBorrower(String cprNo, String password)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      // writing the sql query
      PreparedStatement stm = connection.prepareStatement(
          "SELECT cpr_no, password FROM borrower WHERE cpr_no = ? AND password = ?;");
      stm.setString(1, cprNo);
      stm.setString(2, password);
      ResultSet result = stm.executeQuery();
      if (result.next())
      {
        return true;
      }
    }
    return false;
  }
}
