package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import db.DbConnection;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public List<String> generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    @Override
    public boolean add(Item dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item (itemCode, description, packSize, qtyOnHand, unitPrice, discount) VALUES (?,?,?,?,?,?)",
                dto.getCode(), dto.getDescription(), dto.getPackSize(), dto.getQtyOnHand(), dto.getUnitPrice(), dto.getDiscount());
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET description=?, packSize=?, qtyOnHand=?, unitPrice=?, discount=? WHERE  itemCode=?",
                dto.getDescription(), dto.getPackSize(), dto.getQtyOnHand(), dto.getUnitPrice(), dto.getDiscount(), dto.getCode());
    }

    @Override
    public boolean delete(String itemCode) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE itemCode=?", itemCode);
    }

    @Override
    public Item search(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE itemCode=?", itemCode);
        rst.next();
        return new Item(itemCode, rst.getString("description"), rst.getString("packSize"), rst.getInt("qtyOnHand"), rst.getInt("discount"), rst.getDouble("unitPrice"));

    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()) {
            allItems.add(new Item(rst.getString("itemCode"), rst.getString("description"), rst.getString("packSize"), rst.getInt("qtyOnHand"), rst.getInt("discount"), rst.getDouble("unitPrice")));
        }
        return allItems;
    }

}