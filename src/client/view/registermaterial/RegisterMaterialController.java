package client.view.registermaterial;

import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.*;
import javafx.scene.paint.Paint;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;

public class RegisterMaterialController
{
  @FXML private ComboBox<String> materialTypeCompo;
  @FXML private ComboBox<String> audiance;
  @FXML private ComboBox<String> language;

  @FXML private TextField title;
  @FXML private TextField publisher;
  @FXML private TextArea description;
  @FXML private TextArea keywords;
  @FXML private TextField isbn;
  @FXML private DatePicker releaseDate;
  @FXML private TextField numberOfPages;
  @FXML private TextField licensNumber;
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField country;
  @FXML private DatePicker dateOfBirth;
  @FXML private TextField hallNumber;
  @FXML private TextField genre;
  @FXML private TextField creatorLastName;
  @FXML private TextField department;
  @FXML private TextField subtitleLanguage;
  @FXML private TextField length;
  @FXML private TextField url;

  @FXML private Label error;
  @FXML private Label hallNoWarning;
  @FXML private Label departmentWaning;
  @FXML private Label creatorLNameWarning;
  @FXML private Label genreWarning;
  @FXML private Label firstNameWarning;
  @FXML private Label lastNameWarning;
  @FXML private Label dobWarning;
  @FXML private Label countryWarning;
  @FXML private Label typeWarning;
  @FXML private Label titleWarning;
  @FXML private Label publisherWarning;
  @FXML private Label releseDateWarning;
  @FXML private Label descriptionWarning;
  @FXML private Label keywordsWarning;
  @FXML private Label audianceWarning;
  @FXML private Label pageNoWarning;
  @FXML private Label languageWarning;
  @FXML private Label isbnWarning;
  @FXML private Label licensNoWarning;
  @FXML private Label subtitileWarning;
  @FXML private Label lengthWarning;

  private ObservableList<TextField> listT = FXCollections.observableArrayList();

  public void init()
  {
    materialTypeCompo.setItems(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .getMaterialType());
    audiance.setItems(ViewModelFactory.getInstance().getRegisterMaterialVM()
        .getTargetAudiance());
    language.setItems(
        ViewModelFactory.getInstance().getRegisterMaterialVM().getLang());

    listT.addAll(title, publisher, isbn, firstName, lastName, country,
        hallNumber, creatorLastName, department, subtitleLanguage, length,
        numberOfPages, genre, url);

    title.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM().titleProperty());
    publisher.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .publisherProperty());
    language.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .languageProperty());
    isbn.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM().isbnProperty());
    firstName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .firstNameProperty());
    lastName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .lastNameProperty());
    country.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .countryProperty());
    hallNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .hallNumberProperty());
    creatorLastName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .creatorLastNameProperty());
    department.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .departmentProperty());
    subtitleLanguage.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .subtitleLanguageProperty());
    length.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .lengthProperty());
    numberOfPages.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .numberOfPagesProperty());
    genre.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM().genreProperty());
    dateOfBirth.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .dateOfBirthPropertyProperty());
    releaseDate.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .releaseDateProperty());
    description.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .descriptionProperty());
    keywords.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .keywordsProperty());
    audiance.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .audiancePropertyProperty());
    materialTypeCompo.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .typePropertyProperty());
    url.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM().urlProperty());
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  @FXML public void onButtonBack(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Administration");
  }

  @FXML public void onButtonConfirmWindow(ActionEvent actionEvent)
      throws IOException
  {
    String type = materialTypeCompo.getValue();

    if (type.equals("Book"))
    {
      if (ViewModelFactory.getInstance().getRegisterMaterialVM()
          .bookAlreadyExists())
      {
        error.setText("Book already exists");
        error.setTextFill(Paint.valueOf("red"));
        error.setVisible(true);
      }
      else
      {
        error.setVisible(false);
        ViewModelFactory.getInstance().getRegisterMaterialVM().addBook();
        setTestForLabelGreen("Bogen");
        clearFields();
      }
    }
    else if (type.equals("EBook"))
    {
      if (ViewModelFactory.getInstance().getRegisterMaterialVM()
          .eBookAlreadyExists())
      {
        error.setText("EBook already exists");
        error.setTextFill(Paint.valueOf("red"));
        error.setVisible(true);
      }
      else
      {
        ViewModelFactory.getInstance().getRegisterMaterialVM().addEBook();
        setTestForLabelGreen("E-bogen");
        clearFields();
      }
    }
    else if (type.equals("AudioBook"))
    {
      if (ViewModelFactory.getInstance().getRegisterMaterialVM()
          .audioBookAlreadyExists())
      {
        error.setText("AudioBook already exists");
        error.setTextFill(Paint.valueOf("red"));
        error.setVisible(true);
      }
      else
      {
        ViewModelFactory.getInstance().getRegisterMaterialVM().addAudioBook();
        setTestForLabelGreen("Lydbogen");
        clearFields();
      }
    }
    else if (type.equals("CD"))
    {
      if (ViewModelFactory.getInstance().getRegisterMaterialVM()
          .cdAlreadyExists())
      {
        error.setText("CD already exists");
        error.setTextFill(Paint.valueOf("red"));
        error.setVisible(true);
      }
      else
      {
        cdSelectedFields();
        ViewModelFactory.getInstance().getRegisterMaterialVM().addCD();
        setTestForLabelGreen("CD'en");
        clearFields();
      }
    }
    else if (type.equals("DVD"))
    {
      if (ViewModelFactory.getInstance().getRegisterMaterialVM()
          .dvdAlreadyExists())
      {
        error.setText("DVD already exists");
        error.setTextFill(Paint.valueOf("red"));
        error.setVisible(true);
      }
      else
      {
        ViewModelFactory.getInstance().getRegisterMaterialVM().addDVD();
        setTestForLabelGreen("DVD'en");
        clearFields();
      }
    }
    else if (type.equals(null))
    {
      error.setVisible(true);
    }
  }

  private void clearFields()
  {
    for (int i = 0; i < listT.size(); i++)
    {
      listT.get(i).clear();
    }
    dateOfBirth.getEditor().clear();
    releaseDate.getEditor().clear();
    description.clear();
    keywords.clear();
  }

  private void setTestForLabelGreen(String type)
  {
    error.setText(type + " er tilføjet");
    error.setTextFill(Paint.valueOf("green"));
    clearFields();
  }

  private void setErrorMaterialInSystem()
  {
    error.setText("Materialet findes allerede i systemet!!!");
    error.setTextFill(Paint.valueOf("red"));
  }

  private void bookSelectedfields()
  {
    numberOfPages.setDisable(false);
    isbn.setDisable(false);
    licensNumber.setDisable(true);
    firstName.setDisable(false);
    lastName.setDisable(false);
    dateOfBirth.setDisable(false);
    country.setDisable(false);
    hallNumber.setDisable(false);
    department.setDisable(false);
    creatorLastName.setDisable(false);
    subtitleLanguage.setDisable(true);
    length.setDisable(true);
  }

  private void cdSelectedFields()
  {
    numberOfPages.setDisable(true);
    isbn.setDisable(true);
    licensNumber.setDisable(true);
    firstName.setDisable(true);
    lastName.setDisable(true);
    dateOfBirth.setDisable(true);
    country.setDisable(true);
    hallNumber.setDisable(false);
    department.setDisable(false);
    creatorLastName.setDisable(false);
    subtitleLanguage.setDisable(true);
    length.setDisable(false);
  }

  private void eBookSelectedFields()
  {
    numberOfPages.setDisable(false);
    isbn.setDisable(true);
    licensNumber.setDisable(false);
    firstName.setDisable(false);
    lastName.setDisable(false);
    dateOfBirth.setDisable(false);
    country.setDisable(false);
    hallNumber.setDisable(true);
    department.setDisable(true);
    creatorLastName.setDisable(true);
    subtitleLanguage.setDisable(true);
    length.setDisable(true);
  }

  private void audioBookSelectedFields()
  {
    numberOfPages.setDisable(true);
    isbn.setDisable(true);
    licensNumber.setDisable(true);
    firstName.setDisable(false);
    lastName.setDisable(false);
    dateOfBirth.setDisable(false);
    country.setDisable(false);
    hallNumber.setDisable(true);
    department.setDisable(true);
    creatorLastName.setDisable(true);
    subtitleLanguage.setDisable(true);
    length.setDisable(false);
  }

  public void dvdSelectedFields()
  {
    numberOfPages.setDisable(true);
    isbn.setDisable(true);
    licensNumber.setDisable(true);
    firstName.setDisable(true);
    lastName.setDisable(true);
    dateOfBirth.setDisable(true);
    country.setDisable(true);
    hallNumber.setDisable(false);
    department.setDisable(false);
    creatorLastName.setDisable(false);
    subtitleLanguage.setDisable(false);
    length.setDisable(false);
  }

  public void disableAll()
  {
    for (int i = 0; i < listT.size(); i++)
    {
      listT.get(i).setDisable(true);
    }
    dateOfBirth.getEditor().setDisable(true);
    releaseDate.getEditor().setDisable(true);
    description.setDisable(true);
    keywords.setDisable(true);
  }

  private void releventFields()
  {
    String type = materialTypeCompo.getValue();
    if (type.equals("Book"))
    {
      bookSelectedfields();
    }
    else if (type.equals("EBook"))
    {
      eBookSelectedFields();
    }
    else if (type.equals("AudioBook"))
    {
      audioBookSelectedFields();
    }
    else if (type.equals("CD"))
    {
      cdSelectedFields();
    }
    else if (type.equals("DVD"))
    {
      dvdSelectedFields();
    }
    else
    {
      disableAll();
    }
  }

  @FXML public void onButtonConfirm(ActionEvent actionEvent)
  {

  }

  @FXML public void onTypedTitle(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .titleProperty().get();
    if (arg.isEmpty())
    {
      titleWarning.setVisible(true);
    }
    else
    {
      titleWarning.setVisible(false);
    }
  }

  @FXML public void onTypedPublisher(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .publisherProperty().get();
    if (arg.isEmpty())
    {
      publisherWarning.setVisible(true);
    }
    else
    {
      publisherWarning.setVisible(false);
    }
  }

  @FXML public void onTypedDescribtion(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .descriptionProperty().get();
    if (arg.isEmpty())
    {
      descriptionWarning.setVisible(true);
    }
    else
    {
      descriptionWarning.setVisible(false);
    }
  }

  @FXML public void onTypedKeywords(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .keywordsProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      keywordsWarning.setVisible(true);
    }
    else
    {
      keywordsWarning.setVisible(false);
    }
  }

  @FXML public void onTypedIsbn(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .isbnProperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*"))
    {
      isbnWarning.setVisible(true);
    }
    else
    {
      isbnWarning.setVisible(false);
    }
  }

  @FXML public void onTypedPageNumber(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .numberOfPagesProperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*"))
    {
      pageNoWarning.setVisible(true);
    }
    else
    {
      pageNoWarning.setVisible(false);
    }
  }

  @FXML public void onTypeLicensNumber(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .licensNumberProperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*"))
    {
      licensNoWarning.setVisible(true);
    }
    else
    {
      licensNoWarning.setVisible(false);
    }
  }

  @FXML public void onTypedHallNumber(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .hallNumberProperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*"))
    {
      hallNoWarning.setVisible(true);
    }
    else
    {
      hallNoWarning.setVisible(false);
    }
  }

  @FXML public void onTypeGenre(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .genreProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      genreWarning.setVisible(true);
    }
    else
    {
      genreWarning.setVisible(false);
    }
  }

  @FXML public void onTypeCreatorLastName(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .firstNameProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      firstNameWarning.setVisible(true);
    }
    else
    {
      firstNameWarning.setVisible(false);
    }
  }

  @FXML public void onTypeDepartment(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .descriptionProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*") || arg.length() != 1)
    {
      departmentWaning.setVisible(true);
    }
    else
    {
      departmentWaning.setVisible(false);
    }
  }

  @FXML public void onTypeSubtitleLanguage(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .subtitleLanguageProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      subtitileWarning.setVisible(true);
    }
    else
    {
      subtitileWarning.setVisible(false);
    }
  }

  @FXML public void onTypedLength(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .lengthProperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*"))
    {
      lengthWarning.setVisible(true);
    }
    else
    {
      lengthWarning.setVisible(false);
    }
  }

  @FXML public void onTypedLastName(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .lastNameProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      lastNameWarning.setVisible(true);
    }
    else
    {
      lastNameWarning.setVisible(false);
    }
  }

  @FXML public void onTypeCountry(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .countryProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      countryWarning.setVisible(true);
    }
    else
    {
      countryWarning.setVisible(false);
    }
  }

  @FXML public void onTypeFirstName(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .firstNameProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      firstNameWarning.setVisible(true);
    }
    else
    {
      firstNameWarning.setVisible(false);
    }
  }

  @FXML public void onMouseExitedAudiance(MouseEvent mouseEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .audiancePropertyProperty().get();
    try
    {
      if (arg.equals("Målgruppe:"))
      {
        audianceWarning.setVisible(true);
      }
      else
      {
        audianceWarning.setVisible(false);
      }
    }
    catch (NullPointerException e)
    {
      System.out.println("");
    }
  }

  @FXML public void onMouseExitedReleseDate(MouseDragEvent mouseDragEvent)
  {
    LocalDate arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .releaseDateProperty().get();
    try
    {
      if (arg.equals(null))
      {
        releseDateWarning.setVisible(true);
      }
      else
      {
        releseDateWarning.setVisible(false);
      }
    }
    catch (NullPointerException e)
    {
      System.out.println("");
    }
  }

  @FXML public void onMouseExitedType(MouseEvent mouseEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .typePropertyProperty().get();
    try
    {
      if (arg.equals("Typen"))
      {
        typeWarning.setVisible(true);
      }
      else
      {
        typeWarning.setVisible(false);
        releventFields();
      }
    }
    catch (NullPointerException e)
    {
      System.out.println("");
      ;
    }
  }

  @FXML public void birthDate(MouseEvent mouseEvent)
  {
    LocalDate arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .dateOfBirthPropertyProperty().get();
    try
    {
      if (arg.equals(null))
      {
        dobWarning.setVisible(true);
      }
      else
      {
        dobWarning.setVisible(false);
      }
    }
    catch (NullPointerException e)
    {
      System.out.println("");
    }
  }

  @FXML public void onMouseExitedLanguage(MouseEvent mouseEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .languageProperty().get();
    try
    {
      if (arg.equals("Sprog"))
      {
        languageWarning.setVisible(true);
      }
      else
      {
        languageWarning.setVisible(false);
      }
    }
    catch (NullPointerException e)
    {
      System.out.println("");
    }
  }
}
