package bo.custom.impl;

import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.*;

import db.DbConnection;
import dto.*;
import entity.Customer;
import entity.Item;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        Connection con=null;
        try {
            con= DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm =con.
                    prepareStatement("INSERT INTO `Order` VALUES(?,?,?,?,?)");


            stm.setObject(1, dto.getOrderId());
            stm.setObject(2, dto.getCustomerId());
            stm.setObject(3, dto.getOrderDate());
            stm.setObject(4, dto.getOrderTime());
            stm.setObject(5, dto.getCost());

            if (stm.executeUpdate() > 0){
                if (saveOrderDetail(dto.getOrderId(), dto.getItems())){
                    con.commit();
                    return true;
                }else{
                    con.rollback();
                    return false;
                }
            }else{
                con.rollback();
                return false;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {

                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean saveOrderDetail(String orderId, ArrayList<ItemDetailsDTO> items) throws SQLException, ClassNotFoundException {
        for (ItemDetailsDTO temp : items
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("INSERT INTO `Order Detail` VALUES(?,?,?,?)");
            stm.setObject(1, temp.getItemCode());
            stm.setObject(2, orderId);
            stm.setObject(3, temp.getQtyForSell());
            stm.setObject(4, temp.getTotalDiscount());
            if (stm.executeUpdate() > 0) {

                if (updateQty(temp.getItemCode(), temp.getQtyForSell())){

                }else{
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE ITEM SET qtyOnHand=(qtyOnHand-" + qty
                                + ") WHERE itemCode='" + itemCode + "'");
        return stm.executeUpdate()>0;
    }


    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateOrderId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getId(),c.getTitle(), c.getName(), c.getAddress(),c.getCity(),c.getProvince()));
        }
        return allCustomers;

    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item : all) {
            allItems.add(new ItemDTO(item.getCode(), item.getDescription(), item.getPackSize(), item.getQtyOnHand(), item.getDiscount(), item.getUnitPrice()));
        }
        return allItems;

    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(code);
        return new ItemDTO(item.getCode(), item.getDescription(), item.getPackSize(), item.getQtyOnHand(), item.getDiscount(), item.getUnitPrice());
    }



    @Override
    public CustomerDTO searchCustomer(String s) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(s);
        return new CustomerDTO(c.getId(),c.getTitle(), c.getName(), c.getAddress(),c.getCity(),c.getProvince());
    }
}
