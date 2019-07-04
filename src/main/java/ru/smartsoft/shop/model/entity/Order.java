package ru.smartsoft.shop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purchase_date")
    private Timestamp purchaseDate;

    @JsonBackReference(value = "user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference(value = "order")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<BuyItem> buyItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BuyItem> getBuyItems() {
        return buyItems;
    }

    public void setBuyItems(List<BuyItem> buyItems) {
        this.buyItems = buyItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return purchaseDate.equals(order.purchaseDate) &&
                user.equals(order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseDate, user);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
