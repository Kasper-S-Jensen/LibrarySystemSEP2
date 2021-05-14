package database.place;

import client.model.material.Place;

import java.sql.SQLException;

public interface PlaceDAO
{
  Place create(int hallNo, String department, String creatorLName, String genre)
      throws SQLException;

  int getPlaceID(int hallNo, String department, String creatorLName, String genre)
      throws SQLException;
}
