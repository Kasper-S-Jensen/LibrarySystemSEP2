package shared;

import client.model.material.Place;
import client.model.material.strategy.MaterialCreator;
import client.model.material.strategy.SearchStrategy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MaterialServer extends Remote
{
  /**
   *
   *
   */


  void registerBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, Place place, MaterialCreator author, String genre,
      String url) throws RemoteException;

  void createBookCopy(int materialID) throws RemoteException;

  void registerDVD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage, double playDuration, int placeID, String genre,
      String url) throws RemoteException;

  void createDVDCopy(int materialID) throws RemoteException;

  void registerCD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID, String genre,
      String url) throws RemoteException;

  void createCDCopy(int materialID) throws RemoteException;

  void registerEBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, String licenseNr, int authorId, String genre,
      String url) throws RemoteException;

  void createEBookCopy(int materialID) throws RemoteException;


  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      double playDuration, String genre, int authorId, String url) throws RemoteException;

  void createAudioBookCopy(int materialID) throws RemoteException;

  void findMaterial(String arg, SearchStrategy searchStrategy) throws RemoteException;
}
