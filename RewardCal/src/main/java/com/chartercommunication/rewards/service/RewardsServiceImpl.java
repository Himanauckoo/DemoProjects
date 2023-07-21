package com.chartercommunication.rewards.service;

import com.chartercommunication.rewards.constants.Constants;
import com.chartercommunication.rewards.entity.Transaction;
import com.chartercommunication.rewards.model.RewardsLastThreeMonths;
import com.chartercommunication.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    TransactionRepository transactionRepository;


    public RewardsLastThreeMonths getRewardsByCustomerId(Long customerId) {

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(Constants.monthDays);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2 * Constants.monthDays);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3 * Constants.monthDays);

        List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
                customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<Transaction> lastSecondMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<Transaction> lastThirdMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
        Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
        Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        RewardsLastThreeMonths customerRewardsLastThreeMonths = new RewardsLastThreeMonths();
        customerRewardsLastThreeMonths.setCustomerId(customerId);
        customerRewardsLastThreeMonths.setLastMonthRewardPoints(lastMonthRewardPoints);
        customerRewardsLastThreeMonths.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
        customerRewardsLastThreeMonths.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
        customerRewardsLastThreeMonths.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

        return customerRewardsLastThreeMonths;

    }

    private Long getRewardsPerMonth(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction))
                .collect(Collectors.summingLong(r -> r.longValue()));
    }

    private Long calculateRewards(Transaction t) {
        if (t.getTransactionAmount() > Constants.rewardLimit_Fifty && t.getTransactionAmount() <= Constants.rewardLimit_Hundred) {
            return Math.round(t.getTransactionAmount() - Constants.rewardLimit_Fifty);
        } else if (t.getTransactionAmount() > Constants.rewardLimit_Hundred) {
            return Math.round(t.getTransactionAmount() - Constants.rewardLimit_Hundred) * 2
                    + (Constants.rewardLimit_Hundred - Constants.rewardLimit_Fifty);
        } else
            return 0l;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }

}
