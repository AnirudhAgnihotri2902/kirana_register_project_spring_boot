package org.example.kirana_register_project.crud.service;

import org.example.kirana_register_project.crud.exception.UserNotFoundException;
import org.example.kirana_register_project.crud.model.Transactions;
import org.example.kirana_register_project.crud.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private FxRatesService fxRatesService;
    public Transactions getTransactionByID(long transactionID){
        return transactionRepository.findById(transactionID)
                .orElseThrow(() -> new UserNotFoundException("transction not found with ID: " + transactionID));
    }

    @Transactional
    public List<Transactions> createTransaction(Transactions transactions){
        List<Transactions>Response =new ArrayList<>() ;
        try {
            float price;
            Map<String, Double> map = fxRatesService.getFxRates();
            if (map!= null){
                price = (float) getValueOrThrowError(map, transactions.getAssetType());
            } else {
                throw new RuntimeException("Failed to save users, cannot get response from broker");
            }
            if (transactions.getTransactionType() == "BUY"){
                UUID TxnID = UUID.randomUUID();
                Transactions newTxn1 = new Transactions();
                newTxn1.setTxnId(TxnID);
                newTxn1.setUserID(transactions.getUserID());
                newTxn1.setPrice(1);
                newTxn1.setQuantity(transactions.getQuantity() *price);
                newTxn1.setAssetType("USD");
                newTxn1.setTransactionType("BUY");
                newTxn1.setCreatedAt(new Date());
                newTxn1.setUpdatedAt(new Date());
                Response.add(transactionRepository.save(newTxn1));

                Transactions newTxn2 = new Transactions();
                newTxn2.setTxnId(TxnID);
                newTxn2.setUserID(transactions.getUserID());
                newTxn2.setPrice(price);
                newTxn2.setQuantity(transactions.getQuantity() *-1);
                newTxn2.setAssetType(transactions.getAssetType());
                newTxn2.setTransactionType("SELL");
                newTxn2.setCreatedAt(new Date());
                newTxn2.setUpdatedAt(new Date());
                Response.add(transactionRepository.save(newTxn2));
            } else{
                UUID TxnID = UUID.randomUUID();
                Transactions newTxn1 = new Transactions();
                newTxn1.setTxnId(TxnID);
                newTxn1.setUserID(transactions.getUserID());
                newTxn1.setPrice(1);
                newTxn1.setQuantity(transactions.getQuantity() *price*-1);
                newTxn1.setAssetType("USD");
                newTxn1.setTransactionType("SELL");
                newTxn1.setCreatedAt(new Date());
                newTxn1.setUpdatedAt(new Date());
                Response.add(transactionRepository.save(newTxn1));

                Transactions newTxn2 = new Transactions();
                newTxn2.setTxnId(TxnID);
                newTxn2.setUserID(transactions.getUserID());
                newTxn2.setPrice(price);
                newTxn2.setQuantity(transactions.getQuantity());
                newTxn2.setAssetType(transactions.getAssetType());
                newTxn2.setTransactionType("BUY");
                newTxn2.setCreatedAt(new Date());
                newTxn2.setUpdatedAt(new Date());
                Response.add(transactionRepository.save(newTxn2));
            }
        } catch (DataAccessException e) {
            // Rollback the transaction in case of an exception
            throw new RuntimeException("Failed to save users.", e);
        }
        return Response;
    }

    public List<Transactions> getTransactionsByUserID(UUID userID){
        return  transactionRepository.findAllByUserID(userID);
    }

    public Map<String, Double> getFxRates(){
        return fxRatesService.getFxRates();
    }

    public static double getValueOrThrowError(Map<String, Double> map, String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            throw new IllegalArgumentException("Key '" + key + "' not found in the map.");
        }
    }

    public  List<Transactions>GetAllTransactionsBetweenDates(Date startDate, Date endDate){
        return transactionRepository.findAllBetweenDates(startDate, endDate );
    }
    public  List<Transactions>GetAllTransactionsByTxnID(UUID txnID){
        List<Transactions> txn = transactionRepository.findAllByTxnId(txnID);
        return txn;
    }
}
