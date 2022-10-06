package dao.custom;

import dao.CrudDAO;
import entity.Item;
import entity.Order;
import entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order, String> {
    public String generateOrderId() throws SQLException, ClassNotFoundException;

}
