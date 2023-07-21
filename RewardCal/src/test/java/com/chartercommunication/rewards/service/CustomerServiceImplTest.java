package com.chartercommunication.rewards.service;

import com.chartercommunication.rewards.entity.Customer;
import com.chartercommunication.rewards.entity.Transaction;
import com.chartercommunication.rewards.model.RewardsLastThreeMonths;
import com.chartercommunication.rewards.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    RewardsService rewardsService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this.getClass());
    }

    @Test
    public void getRewardsOfAllCustomerTest() {

        Mockito.when(customerRepository.findAll()).thenReturn(prepareCustomerData());
        Map<Long, Long> rewardsMap = customerService.getRewardsOfAllCustomer();
        System.out.println(rewardsMap);
        Assert.assertEquals(rewardsMap.get(Long.valueOf(2001)), Long.valueOf(110));
    }

    @Test
    public void getRewardsForLastThreeMonthsTestSuccessScenario() {
        Mockito.when(customerRepository.findByCustomerId(Mockito.any())).thenReturn(prepareCustomerData().get(0));
        Mockito.when(rewardsService.getRewardsByCustomerId(Mockito.any())).thenReturn(prepareRewardsLastThreeMonthsData());
        RewardsLastThreeMonths customerRewardsLastThreeMonths = customerService.getRewardsForLastThreeMonths(Long.valueOf(2001));
        Assert.assertEquals(customerRewardsLastThreeMonths.getCustomerId(), 2001);
    }

    private RewardsLastThreeMonths prepareRewardsLastThreeMonthsData(){
        RewardsLastThreeMonths rewardsLastThreeMonths=new RewardsLastThreeMonths();
        rewardsLastThreeMonths.setCustomerId(Long.valueOf(2001));
        return rewardsLastThreeMonths;
    }
    private List<Customer> prepareCustomerData() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setCustomerId(Long.valueOf(2001));
        Transaction transaction = new Transaction();
        Set<Transaction> transactions = new HashSet<>();
        transaction.setCustomerId(Long.valueOf(2001));
        transaction.setTransactionAmount(Double.valueOf(130));
        transactions.add(transaction);
        customer.setTransactions(transactions);
        customers.add(customer);
        return customers;
    }
}
