package com.chartercommunication.rewards.service;

import com.chartercommunication.rewards.entity.Transaction;
import com.chartercommunication.rewards.exceptions.TransactionExceptions;
import com.chartercommunication.rewards.model.RewardsByTransactionID;
import com.chartercommunication.rewards.repository.TransactionRepository;
import com.chartercommunication.rewards.utils.RewardsCal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public RewardsByTransactionID getRewardsByTransactionID(long transactionID) {
        Transaction transactionByTransactionId = transactionRepository.findTransactionByTransactionId(transactionID);
        if(transactionByTransactionId==null){
            throw new TransactionExceptions("Invalid Transaction ID", HttpStatus.BAD_REQUEST);
        }
        RewardsByTransactionID rewardsByTransactionID=new RewardsByTransactionID();
        rewardsByTransactionID.setTransactionId(transactionByTransactionId.getTransactionId());
        rewardsByTransactionID.setCustomerId(transactionByTransactionId.getCustomerId());
        rewardsByTransactionID.setDate(transactionByTransactionId.getTransactionDate());
        rewardsByTransactionID.setTotalRewards(RewardsCal.calculateRewards(transactionByTransactionId));
        return rewardsByTransactionID;
    }

}
