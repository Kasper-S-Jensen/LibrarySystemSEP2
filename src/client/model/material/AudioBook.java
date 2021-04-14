package client.model.material;

public class AudioBook extends AudioMaterial
{
  public AudioBook(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, playDuration);
  }

  @Override public String getMaterialType()
  {
    return "Audio Book";
  }
}
