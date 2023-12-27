package org.example.kirana_register_project.crud.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transactions {

    private UUID id;
    private UUID userID;

    private UUID TxnId;
    private String assetType;

    private String transactionType;
    private float price;
    private float quantity;
    private Date createdAt;
    private Date updatedAt;


    public Transactions() {

    }

    public Transactions(UUID userID, UUID txnId, String assetType, float price, float quantity) {
        this.userID = userID;
        this.TxnId = txnId;
        this.assetType = assetType;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public UUID getUserID() {
        return userID;
    }
    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    @Column(name = "txn_id", nullable = false)
    public UUID getTxnId() {
        return TxnId;
    }
    public void setTxnId(UUID txnId) {
        this.TxnId = txnId;
    }

    @Column(name = "asset_type", nullable = false)
    public String getAssetType() {
        return this.assetType;
    }
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    @Column(name = "transaction_type", nullable = false)
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Column(name = "price", nullable = false)
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name = "quantity", nullable = false)
    public float getQuantity() {
        return quantity;
    }
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt ;
    }
    public void setUpdatedAt(Date updatedAt){
        this.updatedAt = updatedAt;
    }
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    public Date getCreatedAt(){
        return createdAt;
    }

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Date getUpdatedAt(){
        return updatedAt;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userID=" + userID + ", assetType=" + assetType + ", price=" + price +", quantity="
                + quantity + "]";
    }

}
