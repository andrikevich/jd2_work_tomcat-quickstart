package it.academy.data;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ProductSpecPrice  implements Serializable {
    public ProductSpecPrice() {
    }

    private long id;
    private Date priceStartDate;
    private Date priceEndDate;
    private int productSpecId;
    private  float productSpecPrice;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPriceStartDate() {
        return priceStartDate;
    }

    public void setPriceStartDate(Date priceStartDate) {
        this.priceStartDate = priceStartDate;
    }

    public Date getPriceEndDate() {
        return priceEndDate;
    }

    public void setPriceEndDate(Date priceEndDate) {
        this.priceEndDate = priceEndDate;
    }

    public int getProductSpecId() {
        return productSpecId;
    }

    public void setProductSpecId(int productSpecId) {
        this.productSpecId = productSpecId;
    }

    public float getProductSpecPrice() {
        return productSpecPrice;
    }

    public void setProductSpecPrice(float productSpecPrice) {
        this.productSpecPrice = productSpecPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSpecPrice that = (ProductSpecPrice) o;
        return id == that.id && productSpecId == that.productSpecId && Float.compare(that.productSpecPrice, productSpecPrice) == 0 && Objects.equals(priceStartDate, that.priceStartDate) && Objects.equals(priceEndDate, that.priceEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priceStartDate, priceEndDate, productSpecId, productSpecPrice);
    }

    @Override
    public String toString() {
        return "ProductSpecPrice{" +
                "id=" + id +
                ", priceStartDate=" + priceStartDate +
                ", priceEndDate=" + priceEndDate +
                ", productSpecId=" + productSpecId +
                ", productSpecPrice=" + productSpecPrice +
                '}';
    }
}
