package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import db.DbConnection;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// Load Customer Ids To ComboBox in PlaceOrderForm
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    @Override
    public boolean add(Customer dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Customer (custId, custTitle, custName, custAddress, city , province) VALUES (?,?,?,?,?,?)",
                dto.getId(),dto.getTitle(), dto.getName(), dto.getAddress(),dto.getCity(),dto.getProvince());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE CustId=?", id);
        rst.next();
        return new Customer(id, rst.getString("custTitle"), rst.getString("custName"),
                rst.getString("custAddress"), rst.getString("city"), rst.getString("province"));

    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString("custID"),rst.getString("custTitle"), rst.getString("custName"),
                    rst.getString("custAddress"), rst.getString("city"), rst.getString("province")));
        }
        return allCustomers;
    }


    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Customer SET custTitle=?, custName=?, custAddress=? , city=?, province=? WHERE CustId=?",
                dto.getTitle(), dto.getName(), dto.getAddress(),dto.getCity(),dto.getProvince(), dto.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE custId=?", id);
    }
}
