package client.model.material.strategy;

import shared.materials.Material;
import database.BaseDAO;
import database.material.MaterialDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class CDStrategy extends BaseDAO implements SearchStrategy
{
  private String materialType = "cd";

  public CDStrategy()
  {

  }

  @Override public List<Material> searchAll() throws SQLException
  {
    return MaterialDAOImpl.getInstance().findMaterial("","","","","",materialType);
  }

  @Override public List<Material> searchTitle(String title) throws SQLException
  {

    return MaterialDAOImpl.getInstance().findMaterial(title,"","","","",materialType);
  }

  @Override public List<Material> searchGenre(String genre) throws SQLException
  {
    return MaterialDAOImpl.getInstance().findMaterial("","","",genre,"",materialType);

  }

  @Override public List<Material> searchTargetAudience(String targetAudience)
      throws SQLException
  {
    return MaterialDAOImpl.getInstance().findMaterial("","","","",targetAudience,materialType);

  }

  @Override public List<Material> searchLanguage(String language) throws SQLException
  {
    return MaterialDAOImpl.getInstance().findMaterial("",language,"","","",materialType);

  }
}
