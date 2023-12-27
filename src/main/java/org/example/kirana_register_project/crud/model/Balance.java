package org.example.kirana_register_project.crud.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "balance")
public class Balance {
    private UUID id;
    private UUID userID;
    private String assetType;
    private  float balance;
    private Date createdAt;
    private Date updatedAt;

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

    @Column(name = "asset_type", nullable = false)
    public String getAssetType() {
        return this.assetType;
    }
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    @Column(name = "balance", nullable = false)
    public float getBalance() {
        return this.balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
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
}
