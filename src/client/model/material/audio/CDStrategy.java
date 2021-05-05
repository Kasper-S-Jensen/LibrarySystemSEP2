package client.model.material.audio;

import client.model.material.MaterialFilterStrategyInterface;
import client.model.material.MaterialList;
import database.BaseDAO;
import database.material.MaterialDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CDStrategy extends BaseDAO implements
    MaterialFilterStrategyInterface
{

  private static CDStrategy instance;
  private static final Lock lock = new ReentrantLock();

  public  static CDStrategy getInstance()
  {

    //Double lock check for Thread safety
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new CDStrategy();
        }
      }
    }
    return instance;
  }


  @Override public MaterialList findMaterial(String title, String language,
      String keywords, String genre, String targetAudience)
  {
    MaterialList ml = new MaterialList();
    Connection connection = null;
    try
    {
      connection = getConnection();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    String sql = "SELECT * FROM material "
        + "join cd on material.material_id = cd.material_id "
        + "where material.title LIKE  ('%" + title + "%' ) ";
    if(language != ""){
      sql += " and material.language LIKE  ('%" + language + "%' ) ";
    }
    if(keywords != ""){
      sql += " and material.keywords LIKE  ('%" + keywords + "%' ) ";
    }
    if(genre != ""){
      sql += " and material.genre LIKE  ('%" + genre + "%' ) ";
    }
    if(targetAudience != ""){
      sql += " and material.targetAudience LIKE  ('%" + targetAudience + "%' ) ";
    }
    PreparedStatement stm = null;
    try
    {
      stm = connection.prepareStatement(sql);
      ResultSet resultSet = stm.executeQuery();
      while(resultSet.next()){
        CD cd = new CD(resultSet.getInt("material_id"), MaterialDAOImpl.getInstance()
            .getCopyNumberForMaterial(resultSet.getInt("material_id")),
            resultSet.getString("title"),
            resultSet.getString("publisher"),
            String.valueOf(resultSet.getDate("release_date")),
            resultSet.getString("description_of_the_content"),
            resultSet.getString("keywords"),
            resultSet.getString("audience"),
            resultSet.getString("language_"),
            resultSet.getString("length_"),
            resultSet.getInt("place_id"));
     ml.addMaterial(cd);
      }
    }
    catch (SQLException throwables){
      throwables.printStackTrace();
    }
    return ml;
  }
}
