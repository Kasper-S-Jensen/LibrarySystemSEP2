package client.view.borrowercontactinfo;

import client.model.user.UserModelClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.person.Address;
import shared.person.borrower.Borrower;

import java.util.NoSuchElementException;

/**
 * View model for borrower contact info
 *
 * @author Michael
 * @version 1.0
 */
public class BorrowerContactInfoVM
{
  private UserModelClient userModel;
  private StringProperty firstNameProperty, lastNameProperty, phoneNumberProperty, cprProperty, addressProperty, emailProperty, warningProperty;

  public BorrowerContactInfoVM(UserModelClient userModel)
  {
    this.userModel = userModel;
    firstNameProperty = new SimpleStringProperty();
    lastNameProperty = new SimpleStringProperty();
    phoneNumberProperty = new SimpleStringProperty();
    cprProperty = new SimpleStringProperty("");
    addressProperty = new SimpleStringProperty();
    emailProperty = new SimpleStringProperty();
    warningProperty = new SimpleStringProperty();
  }

  public void getBorrowerInfo()
  {
    if (cprProperty != null && (!cprProperty.get().contains("-")
        || cprProperty.get().length() != 11 || !cprContainsNoChars())
        && cprProperty.get().length() > 0)
    {
      warningProperty.set("Ugyldigt CPR");
      clearAllProperties();
    }
    else if (cprProperty == null || cprProperty.get().isEmpty())
    {
      warningProperty.set("Indtast et CPR nummer.");
      clearAllProperties();
    }
    else
    {
      try
      {
        Borrower borrower = userModel.getBorrowerByCPR(cprProperty.get());
        Address address = borrower.getAddress();
        String addressString =
            address.getStreetName() + " " + address.getStreetNr() + " "
                + address.getZipCode() + " " + address.getCity();
        firstNameProperty.set(borrower.getFirstName());
        lastNameProperty.set(borrower.getLastName());
        emailProperty.set(borrower.getEmail());
        addressProperty.set(addressString);
        phoneNumberProperty.set(borrower.getTlfNumber());
        warningProperty.set("");
      }
      catch (NoSuchElementException e)
      {
        System.out.println(cprContainsNoChars());
        warningProperty.set(e.getMessage());
        clearAllProperties();
      }
    }
  }

  public void clearAllProperties()
  {
    firstNameProperty.set("");
    lastNameProperty.set("");
    phoneNumberProperty.set("");
    cprProperty.set("");
    addressProperty.set("");
    emailProperty.set("");

  }

  public StringProperty firstNameProperty()
  {
    return firstNameProperty;
  }

  public StringProperty lastNameProperty()
  {
    return lastNameProperty;
  }

  public StringProperty phoneNumberProperty()
  {
    return phoneNumberProperty;
  }

  public StringProperty cprProperty()
  {
    return cprProperty;
  }

  public StringProperty addressProperty()
  {
    return addressProperty;
  }

  public StringProperty emailProperty()
  {
    return emailProperty;
  }

  public StringProperty warningProperty()
  {
    return warningProperty;
  }

  private boolean cprContainsNoChars()
  {
    String[] cprSplit = cprProperty.get().split("-");
    try
    {
      Integer.parseInt(cprSplit[0]);
      Integer.parseInt(cprSplit[1]);
      return true;
    }
    catch (NumberFormatException e)
    {
      return false;
    }
  }
}
