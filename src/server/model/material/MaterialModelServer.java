package server.model.material;

import client.model.material.Material;
import client.model.material.MaterialList;
import client.model.material.strategy.SearchStrategy;

import java.sql.SQLException;
import java.util.List;

public interface MaterialModelServer
{
  void registerBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, int placeID, int authorId, String genre,
      String url);

  void createBookCopy(int materialID);

  void registerDVD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage, String playDuration, int placeID, String genre,
      String url);

  void createDVDCopy(int materialID);

  void registerCD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID, String genre,
      String url);

  void createCDCopy(int materialID);

  void registerEBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, String licenseNr, int authorId, String genre,
      String url);

  void createEBookCopy(int materialID);


  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      double playDuration, String genre, int authorId, String url);

  void createAudioBookCopy(int materialID);


  void findMaterial(String arg, SearchStrategy searchStrategy);

  List<Material> searchAll() throws SQLException;
  List<Material> searchTitle(String title) throws SQLException;
  List<Material> searchGenre(String genre) throws SQLException;
  List<Material> searchTargetAudience(String targetAudience) throws SQLException;
  List<Material> searchLanguage(String language) throws SQLException;
}
