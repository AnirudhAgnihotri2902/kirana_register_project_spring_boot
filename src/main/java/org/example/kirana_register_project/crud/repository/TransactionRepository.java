package org.example.kirana_register_project.crud.repository;

import org.example.kirana_register_project.crud.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findAllByUserID(UUID userID);

    List<Transactions> findAllByTxnId(UUID txnId);


    @Query(value = "SELECT SUM(t.price * t.quantity) FROM Transactions t WHERE t.user_id = :userId", nativeQuery = true)
    BigDecimal getSumOfPriceQuantityByUserId(UUID userId);

    @Query(value = "SELECT t FROM Transactions t WHERE t.created_at BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Transactions> findAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
