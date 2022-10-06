package dao.custom;

import dao.CrudDAO;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO  extends CrudDAO<Item, String> {
    List<String> generateNewId() throws SQLException, ClassNotFoundException;
}


