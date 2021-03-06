package shared.materials.strategy;

import database.material.AudioBookDAOImpl;
import shared.materials.Material;

import java.util.List;

/**
 * Concrete audio book search strategy
 *
 * @author Lilian
 * @version 1.0
 */
public class AudioBookStrategy implements SearchStrategy
{
  private String materialType = "audiobook";
  private static final long serialVersionUID = -8799770373490598593L;

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience)
  {
    return AudioBookDAOImpl.getInstance()
        .findMaterial(title, language, keywords, genre, targetAudience);
  }
}
