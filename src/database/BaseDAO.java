package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO
{
  protected Connection getConnection() throws SQLException
  {
    Connection result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=librarySystem", "postgres", "hbb54egnn");
    result.setAutoCommit(false);
    return result;
  }
}
