package client.view.addlibrarian;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class AddLibrarianController
{
  @FXML private Button signUpButton;
  @FXML private TextField lastName;
  @FXML private TextField firstName;
  @FXML private TextField cprNumber;
  @FXML private TextField streetName;
  @FXML private TextField city;
  @FXML private TextField zipCode;
  @FXML private TextField streetNumber;
  @FXML private TextField phoneNumber;
  @FXML private TextField employeeNo;
  @FXML private PasswordField password;
  @FXML private TextField email;
  @FXML private Label employeeNoError;
  @FXML private Label cprError;
  @FXML private Label fNameError;
  @FXML private Label lNameError;
  @FXML private Label streetNameError;
  @FXML private Label streetNoError;
  @FXML private Label zipCodeError;
  @FXML private Label cityError;
  @FXML private Label phoneError;
  @FXML private Label emailError;
  @FXML private Label passwordError;

  /**
   * This pattern return true if String contains any thing other than 0-9 digit,
   * which can be used to know if an String is number or not using regular expression.
   * email.getText().matches(".*\\d.*)"
   */
  public void init()
  {
    lastName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().lastNameProperty());
    firstName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().firstNameProperty());
    cprNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().cprProperty());
    streetName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM()
            .streetnameProperty());
    city.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().cityProperty());
    zipCode.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().zipCodeproperty());
    streetNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().streetNoProperty());
    phoneNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().phoneProperty());
    employeeNo.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM()
            .employeeNoProperty());
    password.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().passwordProperty());
    employeeNo.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM()
            .employeeNoProperty());
  }

  @FXML public void onButtonSignUp(ActionEvent actionEvent) throws IOException
  {
    ViewModelFactory.getInstance().getAddLibrarianVM().addLibrarian();
    ViewHandler.getInstance().openView("Administration");
  }

  @FXML public void onButtonBack(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Main");
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  @FXML public void onTypedEmailCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().emailProperty().get();
    if (arg.isEmpty() || !arg.contains("@"))
    {
      emailError.setVisible(true);
    }
    else
    {
      emailError.setVisible(false);
    }
  }

  @FXML public void onTypedPasswordCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().passwordProperty().get();
    if (arg.isEmpty())
    {
      passwordError.setVisible(true);
    }
    else
    {
      passwordError.setVisible(false);
    }
  }

  @FXML public void onTypedEmployyeNOCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().employeeNoProperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*"))
    {
      employeeNoError.setVisible(true);
    }
    else
    {
      employeeNoError.setVisible(false);
    }
  }

  @FXML public void onTypedPhoneNo(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().phoneProperty().get();
    if (arg.isEmpty() || !arg.contains("+45"))
    {
      phoneError.setVisible(true);
    }
    else
    {
      phoneError.setVisible(false);
    }
  }

  @FXML public void onTypeStreetNoCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().streetNoProperty().get();
    if (arg.isEmpty())
    {
      streetNoError.setVisible(true);
    }
    else
    {
      streetNoError.setVisible(false);
    }
  }

  @FXML public void onTypedZipCode(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().zipCodeproperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*") || arg.length() != 4);
  }

  @FXML public void onTypedCity(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().cityProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      cityError.setVisible(true);
    }
    else
    {
      cityError.setVisible(false);
    }
  }

  @FXML public void onTypeStreetNameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().streetnameProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      streetNameError.setVisible(true);
    }
    else
    {
      streetNameError.setVisible(false);
    }
  }

  @FXML public void onTypedCprCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().cprProperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*") || !arg.contains("-") || arg.length() != 11)
    {
      cprError.setVisible(true);
    }
    else
    {
      cprError.setVisible(false);
    }
  }

  @FXML public void onTypedFirstNameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().firstNameProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      fNameError.setDisable(true);
    }
    else
    {
      fNameError.setVisible(false);
    }
  }

  @FXML public void onTypedLastNameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM().lastNameProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      lNameError.setVisible(true);
    }
    else
    {
      lNameError.setVisible(false);
    }
  }
}
