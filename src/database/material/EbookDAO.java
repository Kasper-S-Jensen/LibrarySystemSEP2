package database.material;

import shared.materials.Material;
import shared.materials.reading.EBook;
import shared.person.MaterialCreator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface EbookDAO
{
    void create(int material_id, int page_no, MaterialCreator author, int license_no) throws SQLException;
    EBook createEBookCopy(int materialID, int copyNo) throws SQLException;

    ResultSet getEBookDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
    List<Material> findMaterial(String title, String language,
        String keywords, String genre, String targetAudience);
}
