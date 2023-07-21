package com.chartercommunication.rewards.service;


import com.chartercommunication.rewards.model.RewardsLastThreeMonths;

public interface RewardsService {
    public RewardsLastThreeMonths getRewardsByCustomerId(Long customerId);
}
