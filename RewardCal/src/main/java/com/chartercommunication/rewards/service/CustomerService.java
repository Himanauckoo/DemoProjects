package com.chartercommunication.rewards.service;

import com.chartercommunication.rewards.entity.Customer;
import com.chartercommunication.rewards.model.RewardsLastThreeMonths;

import java.util.Map;

public interface CustomerService {

    Map<Long, Long> getRewardsOfAllCustomer();
    RewardsLastThreeMonths getRewardsForLastThreeMonths(long customerId);
}
