package com.chartercommunication.rewards.utils;

import com.chartercommunication.rewards.constants.Constants;
import com.chartercommunication.rewards.entity.Transaction;

public class RewardsCal {

    public static Long calculateRewards(Transaction t) {
        if (t.getTransactionAmount() > Constants.rewardLimit_Fifty && t.getTransactionAmount() <= Constants.rewardLimit_Hundred) {
            return Math.round(t.getTransactionAmount() - Constants.rewardLimit_Fifty);
        } else if (t.getTransactionAmount() > Constants.rewardLimit_Hundred) {
            return Math.round(t.getTransactionAmount() - Constants.rewardLimit_Hundred) * 2
                    + (Constants.rewardLimit_Hundred - Constants.rewardLimit_Fifty);
        } else
            return 0l;

    }
}
