package com.chartercommunication.rewards.repository;

import com.chartercommunication.rewards.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
     List<Transaction> findAllByCustomerId(Long customerId);

     Transaction findTransactionByTransactionId(long transactionId);

     List<Transaction> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from, Timestamp to);
}
