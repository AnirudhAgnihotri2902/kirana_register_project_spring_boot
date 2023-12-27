package org.example.kirana_register_project.crud.service;

import org.example.kirana_register_project.crud.exception.UserNotFoundException;
import org.example.kirana_register_project.crud.model.Transactions;
import org.example.kirana_register_project.crud.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private FxRatesService fxRatesService;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTransactionByID() {
        // Arrange
        long transactionID = 1L;
        Transactions transaction = new Transactions();
        when(transactionRepository.findById(transactionID)).thenReturn(Optional.of(transaction));

        // Act
        Transactions result = transactionService.getTransactionByID(transactionID);

        // Assert
        assertNotNull(result);
        assertEquals(transaction, result);
    }

    @Test
    void testGetTransactionByIDNotFound() {
        // Arrange
        long transactionID = 1L;
        when(transactionRepository.findById(transactionID)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> transactionService.getTransactionByID(transactionID));
    }

    @Test
    void testCreateTransaction() {
        // Arrange
        Transactions transaction = new Transactions();
        transaction.setUserID(UUID.randomUUID());
        transaction.setQuantity(10);
        transaction.setAssetType("AAPL");
        transaction.setTransactionType("BUY");

        Map<String, Double> fxRates = new HashMap<>();
        fxRates.put("USD", 1.0);
        when(fxRatesService.getFxRates()).thenReturn(fxRates);

        when(transactionRepository.save(any(Transactions.class))).thenReturn(new Transactions());

        // Act
        List<Transactions> result = transactionService.createTransaction(transaction);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        verify(transactionRepository, times(2)).save(any(Transactions.class));
    }

    @Test
    void testCreateTransactionWithInvalidFxRates() {
        // Arrange
        Transactions transaction = new Transactions();
        transaction.setUserID(UUID.randomUUID());
        transaction.setQuantity(10);
        transaction.setAssetType("AAPL");
        transaction.setTransactionType("BUY");

        when(fxRatesService.getFxRates()).thenReturn(null);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> transactionService.createTransaction(transaction));
    }

    @Test
    void testGetTransactionsByUserID() {
        // Arrange
        UUID userID = UUID.randomUUID();
        List<Transactions> transactions = Arrays.asList(new Transactions(), new Transactions());
        when(transactionRepository.findAllByUserID(userID)).thenReturn(transactions);

        // Act
        List<Transactions> result = transactionService.getTransactionsByUserID(userID);

        // Assert
        assertNotNull(result);
        assertEquals(transactions, result);
    }

    @Test
    void testGetTransactionsByUserIDEmptyList() {
        // Arrange
        UUID userID = UUID.randomUUID();
        when(transactionRepository.findAllByUserID(userID)).thenReturn(Collections.emptyList());

        // Act
        List<Transactions> result = transactionService.getTransactionsByUserID(userID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllTransactionsBetweenDates() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        List<Transactions> transactions = Arrays.asList(new Transactions(), new Transactions());
        when(transactionRepository.findAllBetweenDates(startDate, endDate)).thenReturn(transactions);

    }

    @Test
    void testGetAllTransactionsByTxnID() {
        // Arrange
        UUID txnID = UUID.randomUUID();
        List<Transactions> transactions = Arrays.asList(new Transactions(), new Transactions());
        when(transactionRepository.findAllByTxnId(txnID)).thenReturn(transactions);

    }
}
