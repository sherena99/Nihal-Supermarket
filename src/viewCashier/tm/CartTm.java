package viewCashier.tm;

import java.util.Objects;

public class CartTm {
    private String code;
    private String description;
    private String packSize;
    private int qtyForCustomer;
    private double unitPrice;
    private int discountPercentage;
    private double grossTotal;
    private double discount;
    private double total;

    public CartTm() {
    }

    public CartTm(String code, String description, String packSize, int qtyForCustomer, double unitPrice, int discountPercentage, double grossTotal, double discount, double total) {
        this.code = code;
        this.description = description;
        this.packSize = packSize;
        this.qtyForCustomer = qtyForCustomer;
        this.unitPrice = unitPrice;
        this.discountPercentage = discountPercentage;
        this.grossTotal = grossTotal;
        this.discount = discount;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public int getQtyForCustomer() {
        return qtyForCustomer;
    }

    public void setQtyForCustomer(int qtyForCustomer) {
        this.qtyForCustomer = qtyForCustomer;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(double grossTotal) {
        this.grossTotal = grossTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}