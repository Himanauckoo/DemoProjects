package com.chartercommunication.rewards.model;

import lombok.Data;

import java.util.Date;

@Data
public class RewardsByTransactionID {
    private long totalRewards;
    private long customerId;
    private long transactionId;
    private Date date;
}
