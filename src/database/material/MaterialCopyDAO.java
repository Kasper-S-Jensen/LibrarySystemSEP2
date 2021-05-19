package database.material;

import java.sql.SQLException;

public interface MaterialCopyDAO
{
  void create(int materialID, int copyNr) throws SQLException;
  int getFirstAvailableCopyNo(int materialID);
}
