package shared.network;

import shared.materials.Material;
import shared.materials.Place;
import shared.materials.strategy.SearchStrategy;
import shared.person.MaterialCreator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

//Kutaiba-Lilian
public interface MaterialServer extends Remote
{
  int createMaterial(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String genre, String url, String keywords) throws RemoteException;

  void setSelectedMaterial(Material material) throws RemoteException;

  Material getSelectedMaterial() throws RemoteException;

  void registerBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, Place place, MaterialCreator author,
      String genre, String url, String keywords) throws RemoteException;

  void createBookCopy(int materialID) throws RemoteException;

  void deleteBookCopy(int materialID, int copyNo) throws RemoteException;

  boolean bookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, String isbn,
      int pageCount, MaterialCreator author, String genre)
      throws RemoteException;

  void registerDVD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String subtitlesLanguage, int playDuration, Place placeID, String genre,
      String url) throws RemoteException;

  void createDVDCopy(int materialID) throws RemoteException;

  void deleteDVDCopy(int materialID, int copyNo) throws RemoteException;

  boolean dvdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      String playDuration, String genre) throws RemoteException;

  void registerCD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, Place place, String genre, String url)
      throws RemoteException;

  void createCDCopy(int materialID) throws RemoteException;

  void deleteCDCopy(int materialID, int copyNo) throws RemoteException;

  boolean cdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      int playDuration, String genre) throws RemoteException;

  void registerEBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int pageCount, int licenseNr, MaterialCreator author, String genre,
      String url) throws RemoteException;

  void createEBookCopy(int materialID) throws RemoteException;

  void deleteEBookCopy(int materialID, int copyNo) throws RemoteException;

  boolean eBookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, int pageCount,
      int licenseNr, String genre, MaterialCreator author)
      throws RemoteException;

  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, String genre, MaterialCreator author, String url)
      throws RemoteException;

  void createAudioBookCopy(int materialID) throws RemoteException;

  void deleteAudioBookCopy(int materialID, int copyNo) throws RemoteException;

  boolean audioBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, MaterialCreator author, String genre)
      throws RemoteException;

  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience, SearchStrategy searchStrategy)
      throws RemoteException;
  int numberOfAvailableCopies() throws RemoteException;

  int totalNumberOfCopies(int materialID) throws RemoteException;

  void deleteMaterial(int materialID) throws RemoteException;

}
