package shared.servers;

import shared.places.Place;
import shared.person.MaterialCreator;
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
      String targetAudience, String language, String subtitlesLanguage, int playDuration, Place placeID, String genre,
      String url) throws RemoteException;

  void createDVDCopy(int materialID) throws RemoteException;

  void registerCD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, Place place, String genre,
      String url) throws RemoteException;

  void createCDCopy(int materialID) throws RemoteException;

  void registerEBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, String licenseNr, MaterialCreator author, String genre,
      String url) throws RemoteException;

  void createEBookCopy(int materialID) throws RemoteException;


  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      double playDuration, String genre, MaterialCreator author, String url) throws RemoteException;

  void createAudioBookCopy(int materialID) throws RemoteException;

  void findMaterial(String arg, SearchStrategy searchStrategy) throws RemoteException;
}
