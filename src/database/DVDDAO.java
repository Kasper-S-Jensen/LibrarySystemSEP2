package database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DVDDAO {

    int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato,String undertitelsprog, int spillelængde) throws SQLException;
    void create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato, int spillelængde,  Connection connection);
}


