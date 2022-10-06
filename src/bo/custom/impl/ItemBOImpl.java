package bo.custom.impl;

import bo.BoFactory;
import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item i : all) {
            allItems.add(new ItemDTO(i.getCode(), i.getDescription(), i.getPackSize(),i.getQtyOnHand(),i.getDiscount(), i.getUnitPrice()));
        }
        return allItems;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean addItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(dto.getCode(), dto.getDescription(), dto.getPackSize(), dto.getQtyOnHand(), dto.getDiscount(), dto.getUnitPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(), dto.getDescription(), dto.getPackSize(), dto.getQtyOnHand(), dto.getDiscount(), dto.getUnitPrice()));
    }

    @Override
    public Item searchItem(String itemCode) throws SQLException, ClassNotFoundException {
        return itemDAO.search(itemCode);
    }

    @Override
    public List<String> generateNewId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }

}
