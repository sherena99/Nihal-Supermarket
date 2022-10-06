package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import db.DbConnection;
import entity.ItemDetails;
import entity.Order;
import entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE ITEM SET qtyOnHand=(qtyOnHand-" + qty
                                + ") WHERE itemCode='" + itemCode + "'");
        return stm.executeUpdate()>0;
    }


    @Override
    public String generateOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }

        }else{
            return "O-001";
        }
    }


    @Override
    public boolean add(Order dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order` (orderId, cId, orderDate, time, cost) VALUES (?,?,?,?,?)",
                dto.getOrderId(), dto.getCustomerId(), dto.getOrderDate(),dto.getOrderTime(), dto.getCost());
    }

    @Override
    public boolean delete(String orderId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String orderId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}


