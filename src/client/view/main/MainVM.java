package client.view.main;

import client.core.ModelFactory;

import java.sql.SQLException;

public class MainVM
{

  public MainVM()
  {
  }

  public boolean login(String cprNo, String password)
  {
   return ModelFactory.getInstance().getUserModelClient()
        .borrowerLogin(cprNo, password);
  }
}
