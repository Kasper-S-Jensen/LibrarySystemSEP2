package client.view.search;

import client.core.ViewModelFactory;
import shared.materials.Material;
import client.view.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class SearchController
{

  @FXML private TextField title;
  @FXML private  TextField genre;
  @FXML private  TextField keywords;
  @FXML private ComboBox chooseType;
  @FXML private  ComboBox chooseLanguage;
  @FXML private  ComboBox targetAudience;
  @FXML private Label errorLabel;
  @FXML private TableView< Material> searchTableView;
  @FXML private TableColumn<String, Material> titleColumn;
  @FXML private TableColumn<String, Material> publisherColumn;
  @FXML private TableColumn<String, Material> releaseDateColumn;
  @FXML private TableColumn<String, Material> statusColumn;

  private ViewHandler viewHandler;
  private SearchVM viewModel;


  public void init()
  {
   titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
   publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
   releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    statusColumn.setCellValueFactory(new PropertyValueFactory<>("materialStatus"));
   title.textProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM()
       .titleProperty());
   genre.textProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM()
       .genreProperty());
   keywords.textProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM()
       .keywordProperty());
   chooseType.valueProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM().chooseTypeProperty());
   chooseLanguage.valueProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM().languageProperty());
   targetAudience.valueProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM()
       .targetAudienceProperty());
  }

  @FXML public void onButtonSearch(ActionEvent actionEvent)
  {
    ObservableList<Material> materials = ViewModelFactory.getInstance().getSearchVM().searchMaterial();
    searchTableView.setItems(materials);
    if (materials.size() > 0 ){
    }
    searchTableView.refresh();
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  @FXML public void onButtonContinue(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("BorrowReserve");
    ViewModelFactory.getInstance().getSearchVM().setSelectMaterial(searchTableView.getSelectionModel().getSelectedItem());
  }


}
