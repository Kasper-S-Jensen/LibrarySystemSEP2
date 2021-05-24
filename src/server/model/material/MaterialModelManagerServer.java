package server.model.material;

import shared.materials.Material;
import shared.materials.MaterialList;
import shared.materials.Place;
import shared.person.MaterialCreator;
import client.model.material.strategy.SearchStrategy;
import database.material.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;

public class MaterialModelManagerServer implements MaterialModelServer
{
  private PropertyChangeSupport support;
  private MaterialList materialList;
  private SearchStrategy searchStrategy;
  private Material selectedMaterial;

  public MaterialModelManagerServer()
  {
    selectedMaterial = null;
    support = new PropertyChangeSupport(this);
    materialList = new MaterialList();
  }

  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place place, MaterialCreator author, String genre, String url,
      String keywards)
  {
    int materialID = 0;
    try
    {
      materialID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, keywards);
      BookDAOImpl.getInstance()
          .create(materialID, isbn, pageCount, author, place);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createBookCopy(int materialID)
  {
    try
    {
      BookDAOImpl.getInstance().createBookCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID) + 1);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void deletBookCopy(int materialID)
  {
    try
    {
      BookDAOImpl.getInstance().deletBookCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID));
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public boolean bookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String isbn, int pageCount, MaterialCreator author,
      String genre)
  {
    boolean bookIn = false;
    try
    {
      bookIn = BookDAOImpl.getInstance()
          .bookAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, isbn, pageCount, author, genre);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return bookIn;
  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      int playDuration, Place place, String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, tags);
      DVDDAOImpl.getInstance()
          .create(materialID, subtitlesLanguage, playDuration, place);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createDVDCopy(int materialID)
  {
    try
    {
      DVDDAOImpl.getInstance().createDVDCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID) + 1);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void deletDVDCopy(int materialID)
  {
    try
    {
      DVDDAOImpl.getInstance().deletDVDCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID));
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public boolean dvdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String playDuration, String genre)
  {
    boolean dvdIn = false;
    try
    {
      dvdIn = DVDDAOImpl.getInstance()
          .dvdAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, playDuration, genre);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return dvdIn;
  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, Place place,
      String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, tags);
      CDDAOImpl.getInstance().create(materialID, playDuration, place);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createCDCopy(int materialID)
  {
    try
    {
      CDDAOImpl.getInstance().createCDCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID) + 1);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void deletCDCopy(int materialID)
  {
    try
    {
      CDDAOImpl.getInstance().deletCDCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID));
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public boolean cdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, String genre)
  {
    boolean cdIn = false;
    try
    {
      cdIn = CDDAOImpl.getInstance()
          .cdAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, playDuration, genre);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return cdIn;
  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount, int licenseNr,
      MaterialCreator author, String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, tags);
      EbookDAOImpl.getInstance()
          .create(materialID, pageCount, author, licenseNr);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createEBookCopy(int materialID)
  {
    try
    {
      EbookDAOImpl.getInstance().createEBookCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID) + 1);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void deletEBookCopy(int materialID)
  {
    try
    {
      EbookDAOImpl.getInstance().deletEBookCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID));
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public boolean eBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int pageCount, int licenseNr, String genre,
      MaterialCreator author)
  {
    boolean eBookIn = false;
    try
    {
      eBookIn = EbookDAOImpl.getInstance()
          .eBookAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, pageCount, licenseNr, genre, author);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return eBookIn;
  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String genre,
      MaterialCreator author, String url)
  {
    int materialID = 0;
    try
    {
      materialID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, tags);
      AudioBookDAOImpl.getInstance().create(materialID, playDuration, author);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createAudioBookCopy(int materialID)
  {
    try
    {
      AudioBookDAOImpl.getInstance().createAudioBookCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID) + 1);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void deletAudiotBookCopy(int materialID)
  {
    try
    {
      AudioBookDAOImpl.getInstance().deletAudioBookCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID));
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public boolean audioBookAlreadyExists(String title,
      String publisher, String releaseDate, String description,
      String targetAudience, String language, int playDuration,
      MaterialCreator author, String genre)
  {
    boolean audioBookIn = false;
    try
    {
      audioBookIn = AudioBookDAOImpl.getInstance()
          .audioBookAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, playDuration, author, genre);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return audioBookIn;
  }

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience,
      SearchStrategy searchStrategy)
  {
    this.searchStrategy = searchStrategy;
    return searchStrategy
        .findMaterial(title, language, keywords, genre, targetAudience);

  }

  @Override public int numberOfAvailableCopies()
  {
    return MaterialDAOImpl.getInstance()
        .getNumberOfAvailableCopies(selectedMaterial.getMaterialID());
  }

  @Override public Material getSelectMaterial()
  {
    return selectedMaterial;
  }

  @Override public void setSelectedMaterial(Material material)
  {
    this.selectedMaterial = material;
  }

  @Override public int totalNumberOfCopies()
  {
    try
    {
      return MaterialDAOImpl.getInstance()
          .totalNumberOfCopies(selectedMaterial.getMaterialID());
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return 0;
  }

  @Override public void deletMaterial(int materialID)
  {
    try
    {
      MaterialDAOImpl.getInstance().deletMaterial(materialID);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
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
