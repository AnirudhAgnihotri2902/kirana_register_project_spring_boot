package org.example.kirana_register_project.crud.repository;

import org.example.kirana_register_project.crud.model.Balance;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Query(value = "SELECT balance FROM Balance b WHERE b.user_ID = :userId AND b.asset_type = :assetType", nativeQuery = true)
    float findByUserAssetBalance(@Param("userId") UUID userId, @Param("assetType") String assetType);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Balance b SET b.balance = :newBalance, b.updated_at = CURRENT_TIMESTAMP WHERE b.user_id = :userId AND b.asset_type = :assetType",  nativeQuery = true)
    void updateBalance(@Param("userId") UUID userId, @Param("assetType") String assetType, @Param("newBalance") float newBalance);



}