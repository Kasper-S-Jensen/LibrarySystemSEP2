package server.model.material;

import shared.materials.Material;
import shared.materials.MaterialList;
import shared.materials.Place;
import shared.person.MaterialCreator;
import shared.materials.strategy.SearchStrategy;
import database.material.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;

/**
 * Material model implementation for server
 *
 * @author Kutaiba
 * @author Lilian
 * @version 1.0
 */
public class MaterialModelManagerServer implements MaterialModelServer
{
  private PropertyChangeSupport support;
  private MaterialList materialList;
  private SearchStrategy searchStrategy;
  private Material selectedMaterial;

  private AudioBookDAO audioBookDAO;
  private BookDAO bookDAO;
  private EbookDAO ebookDAO;
  private CdDAO cddao;
  private DvdDAO dvddao;
  private MaterialDAO materialDAO;
  private MaterialCopyDAO materialCopyDAO;

  public MaterialModelManagerServer(AudioBookDAO audioBookDAO, BookDAO bookDAO,
      EbookDAO ebookDAO, CdDAO cddao, DvdDAO dvddao, MaterialDAO materialDAO,
      MaterialCopyDAO materialCopyDAO)
  {
    this.audioBookDAO = audioBookDAO;
    this.bookDAO = bookDAO;
    this.ebookDAO = ebookDAO;
    this.cddao = cddao;
    this.dvddao = dvddao;
    this.materialDAO = materialDAO;
    this.materialCopyDAO = materialCopyDAO;

    selectedMaterial = null;
    support = new PropertyChangeSupport(this);
    materialList = new MaterialList();
  }

  @Override public int registerMaterial(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String genre, String url, String keywords)
  {
    int id = 0;
    try
    {
      id = materialDAO
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, keywords);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return id;
  }

  @Override public int registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place place, MaterialCreator author, String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = materialDAO
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, tags);
      bookDAO.create(materialID, isbn, pageCount, author, place);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return materialID;
  }

  @Override public void createBookCopy(int materialID)
  {
    try
    {
      bookDAO.createBookCopy(materialID,
          materialDAO.getLatestCopyNo(materialID) + 1);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
  }

  @Override public void deleteBookCopy(int materialID, int copyNo)
  {
    try
    {
      bookDAO.deleteBookCopy(materialID, copyNo);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
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
      bookIn = bookDAO
          .bookAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, isbn, pageCount, author, genre);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return bookIn;
  }

  @Override public int registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      int playDuration, Place place, String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = materialDAO
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, tags);
      dvddao.create(materialID, subtitlesLanguage, playDuration, place);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return materialID;
  }

  @Override public void createDVDCopy(int materialID)
  {
    try
    {
      dvddao.createDVDCopy(materialID,
          materialDAO.getLatestCopyNo(materialID) + 1);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
  }

  @Override public void deleteDVDCopy(int materialID, int copyNo)
  {
    try
    {
      dvddao.deleteDVDCopy(materialID, copyNo);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
  }

  @Override public boolean dvdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String playDuration, String genre)
  {
    boolean dvdIn = false;
    try
    {
      dvdIn = dvddao
          .dvdAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, playDuration, genre);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return dvdIn;
  }

  @Override public int registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, Place place,
      String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = materialDAO
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, tags);
      cddao.create(materialID, playDuration, place);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return materialID;
  }

  @Override public void createCDCopy(int materialID)
  {
    try
    {
      cddao.createCDCopy(materialID,
          materialDAO.getLatestCopyNo(materialID) + 1);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
  }

  @Override public void deleteCDCopy(int materialID, int copyNo)
  {
    try
    {
      cddao.deleteCDCopy(materialID, copyNo);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
  }

  @Override public boolean cdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, String genre)
  {
    boolean cdIn = false;
    try
    {
      cdIn = cddao.cdAlreadyExists(title, publisher, releaseDate, description,
          targetAudience, language, playDuration, genre);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return cdIn;
  }

  @Override public int registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount, int licenseNr,
      MaterialCreator author, String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = materialDAO
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, tags);
      ebookDAO.create(materialID, pageCount, author, licenseNr);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return materialID;
  }

  @Override public void createEBookCopy(int materialID)
  {
    try
    {
      ebookDAO.createEBookCopy(materialID,
          materialDAO.getLatestCopyNo(materialID) + 1);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
  }

  @Override public void deleteEBookCopy(int materialID, int copyNo)
  {
    try
    {
      ebookDAO.deleteEBookCopy(materialID, copyNo);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
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
      eBookIn = ebookDAO
          .eBookAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, pageCount, licenseNr, genre, author);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return eBookIn;
  }

  @Override public int registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String genre,
      MaterialCreator author, String url)
  {
    int materialID = 0;
    try
    {
      materialID = materialDAO
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url, tags);
      audioBookDAO.create(materialID, playDuration, author);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return materialID;
  }

  @Override public void createAudioBookCopy(int materialID)
  {
    try
    {
      audioBookDAO.createAudioBookCopy(materialID,
          materialDAO.getLatestCopyNo(materialID) + 1);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
  }

  @Override public void deleteAudioBookCopy(int materialID, int copyNo)
  {
    try
    {
      audioBookDAO.deleteAudioBookCopy(materialID, copyNo);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
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
      audioBookIn = audioBookDAO
          .audioBookAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, playDuration, author, genre);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
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
    return materialDAO
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

  @Override public int totalNumberOfCopies(int materialID)
  {
    return materialDAO.totalNumberOfCopies(materialID);
  }

  @Override public void deleteMaterial(int materialID)
  {
    materialDAO.deleteMaterial(materialID);
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
