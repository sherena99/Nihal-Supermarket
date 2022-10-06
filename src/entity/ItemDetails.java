package entity;

import java.util.Objects;

public class ItemDetails {
    private String itemCode;
    private int qtyForSell;
    private double unitPrice;
    private double totalDiscount;

    public ItemDetails() {
    }

    public ItemDetails(String itemCode, int qtyForSell, double unitPrice, double totalDiscount) {
        this.itemCode = itemCode;
        this.qtyForSell = qtyForSell;
        this.unitPrice = unitPrice;
        this.totalDiscount = totalDiscount;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQtyForSell() {
        return qtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        this.qtyForSell = qtyForSell;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemCode='" + itemCode + '\'' +
                ", qtyForSell=" + qtyForSell +
                ", unitPrice=" + unitPrice +
                ", totalDiscount=" + totalDiscount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDetails that = (ItemDetails) o;
        return qtyForSell == that.qtyForSell && Double.compare(that.unitPrice, unitPrice) == 0 && Double.compare(that.totalDiscount, totalDiscount) == 0 && Objects.equals(itemCode, that.itemCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode, qtyForSell, unitPrice, totalDiscount);
    }
}
