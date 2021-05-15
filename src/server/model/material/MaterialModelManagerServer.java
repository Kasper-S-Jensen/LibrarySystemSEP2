package server.model.material;

import client.model.material.Material;
import client.model.material.Place;
import client.model.material.strategy.MaterialCreator;
import client.model.material.strategy.SearchStrategy;

import java.sql.SQLException;
import java.util.List;

public class MaterialModelManagerServer implements MaterialModelServer
{
  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place place, MaterialCreator author, String genre, String url)
  {

  }

  @Override public void createBookCopy(int materialID)
  {

  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      String playDuration, int placeID, String genre, String url)
  {

  }

  @Override public void createDVDCopy(int materialID)
  {

  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID,
      String genre, String url)
  {

  }

  @Override public void createCDCopy(int materialID)
  {

  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      String licenseNr, int authorId, String genre, String url)
  {

  }

  @Override public void createEBookCopy(int materialID)
  {

  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, String genre,
      int authorId, String url)
  {

  }

  @Override public void createAudioBookCopy(int materialID)
  {

  }


  @Override public void findMaterial(String arg, SearchStrategy searchStrategy)
  {

  }

  @Override public List<Material> searchAll() throws SQLException
  {
    return null;
  }

  @Override public List<Material> searchTitle(String title) throws SQLException
  {
    return null;
  }

  @Override public List<Material> searchGenre(String genre) throws SQLException
  {
    return null;
  }

  @Override public List<Material> searchTargetAudience(String targetAudience)
      throws SQLException
  {
    return null;
  }

  @Override public List<Material> searchLanguage(String language)
      throws SQLException
  {
    return null;
  }
}
