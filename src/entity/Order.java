package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    private String orderId;
    private String customerId;
    private String orderDate;
    private String orderTime;
    private double cost;
    private ArrayList<ItemDetails> items;

    public Order() {
    }

    public Order(String orderId, String customerId, String orderDate, String orderTime, double cost) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
    }

    public Order(String orderId, String customerId, String orderDate, String orderTime, double cost, ArrayList<ItemDetails> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<ItemDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetails> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", cost=" + cost +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.cost, cost) == 0 && Objects.equals(orderId, order.orderId) && Objects.equals(customerId, order.customerId) && Objects.equals(orderDate, order.orderDate) && Objects.equals(orderTime, order.orderTime) && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, orderDate, orderTime, cost, items);
    }
}