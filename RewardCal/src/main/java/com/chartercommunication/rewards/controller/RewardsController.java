package com.chartercommunication.rewards.controller;

import com.chartercommunication.rewards.model.RewardsByTransactionID;
import com.chartercommunication.rewards.model.RewardsLastThreeMonths;
import com.chartercommunication.rewards.repository.CustomerRepository;
import com.chartercommunication.rewards.service.CustomerService;
import com.chartercommunication.rewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RewardsController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/rewards", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Long, Long>> getRewards() {
        return ResponseEntity.ok(customerService.getRewardsOfAllCustomer());
    }

    @GetMapping(value = "/reward/transaction/{transactionId}")
    public ResponseEntity<RewardsByTransactionID> getRewardsByTransactionId(@PathVariable("transactionId") Long transactionId) {
        RewardsByTransactionID rewardsByTransactionID = transactionService.getRewardsByTransactionID(transactionId);
        return ResponseEntity.ok(rewardsByTransactionID);
    }

    @GetMapping(value = "/rewards/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RewardsLastThreeMonths> getRewardsByCustomerId(@PathVariable("customerId") Long customerId) {
        return new ResponseEntity<>(customerService.getRewardsForLastThreeMonths(customerId), HttpStatus.OK);
    }

}
