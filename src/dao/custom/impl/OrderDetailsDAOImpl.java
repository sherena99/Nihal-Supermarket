package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailsDAO;
import entity.Item;
import entity.OrderDetail;
import entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean add(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO OrderDetails (itemCode, orderId, orderQty, discount) VALUES (?,?,?,?)", dto.getItemCode(), dto.getOrderId(), dto.getOrderQty(), dto.getDiscount());
    }

    @Override
    public boolean delete(String orderId) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE `Order Detail` SET itemCode=?, orderQty=?, discount=? WHERE  orderId=?",
                dto.getItemCode(), dto.getOrderQty(), dto.getDiscount(), dto.getOrderId());
    }


    @Override
    public OrderDetail search(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM  `Order Detail` WHERE orderID=?", orderId);
        rst.next();
        return new OrderDetail(orderId, rst.getString("itemCode"), rst.getInt("orderQty"), rst.getDouble("discount"));

    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }
}
