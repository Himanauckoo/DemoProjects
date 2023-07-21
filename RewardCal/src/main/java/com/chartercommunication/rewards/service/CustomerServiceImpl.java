package com.chartercommunication.rewards.service;

import com.chartercommunication.rewards.entity.Customer;
import com.chartercommunication.rewards.entity.Transaction;
import com.chartercommunication.rewards.exceptions.CustomerExceptions;
import com.chartercommunication.rewards.model.RewardsLastThreeMonths;
import com.chartercommunication.rewards.repository.CustomerRepository;
import com.chartercommunication.rewards.utils.RewardsCal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RewardsService rewardsService;


    @Override
    public Map<Long, Long> getRewardsOfAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        Map<Long, Long> rewardMap = new HashMap<>();

        for (Customer customer : customers) {
            long customerId = customer.getCustomerId();
            Set<Transaction> transactions = customer.getTransactions();
            long rewardAmount = 0;
            for (Transaction transaction : transactions) {
                rewardAmount += RewardsCal.calculateRewards(transaction);
            }
            rewardMap.put(customerId, rewardAmount);
        }
        return rewardMap;
    }

    @Override
    public RewardsLastThreeMonths getRewardsForLastThreeMonths(long customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer==null)
            throw new CustomerExceptions("Invalid Customer ID", HttpStatus.BAD_REQUEST);
        RewardsLastThreeMonths customerRewardsLastThreeMonths = rewardsService.getRewardsByCustomerId(customerId);
        return customerRewardsLastThreeMonths;
    }
}
