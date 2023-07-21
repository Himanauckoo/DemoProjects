package com.chartercommunication.rewards.service;

import com.chartercommunication.rewards.entity.Transaction;
import com.chartercommunication.rewards.model.RewardsByTransactionID;
import org.json.JSONObject;

import java.util.List;

public interface TransactionService {

    public List<Transaction> getAllTransactions();
    public RewardsByTransactionID getRewardsByTransactionID(long transactionID);
}
