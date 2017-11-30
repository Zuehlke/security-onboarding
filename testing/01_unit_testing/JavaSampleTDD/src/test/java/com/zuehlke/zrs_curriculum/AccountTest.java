package com.zuehlke.zrs_curriculum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AccountTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * "The account should have an ID after opening."
     */
    @Test
    public void newAccountIdTest() {
        fail();
    }

    /**
     * "The account balance should be zero."
     */
    @Test
    public void newAccountBalanceTest() {
        fail();
    }

    /**
     * "The account should have an ID after opening with initial deposit."
     */
    @Test
    public void newAccountWithDepositIdTest() {
        // test with initialDeposit values 100, 200, 300
        fail();
    }

    /**
     * "The account balance should be equal to initial deposit."
     */
    @Test
    public void newAccountWithDepositBalanceTest() {
        // test with initialDeposit values 100, 200, 300
        fail();
    }

    /**
     * "The account balance should be increased by deposit amount."
     */
    @Test
    public void depositAfterCreationWithInitialBalanceTest() {
        // test with deposit values 100, 200, 300
        fail();
    }

    /**
     * "The account balance should be decreased by withdrawal amount."
     */
    @Test
    public void withdrawalTest() {
        fail();
    }

    /**
     * "The withdrawal should not be allowed if there is not enough funds."
     */
    @Test
    public void withdrawalNotAllowedTest() {
        fail();
    }

    /**
     * "The exception message should be 'Insufficient funds' if withdrawal is attempted when there is not enough funds."
     */
    @Test
    public void withdrawalNotAllowedMessageTest() {
        fail();
    }

    /**
     * "The account balance should be decreased by amount which is transferred to another account."
     */
    @Test
    public void firstAccountBalanceAfterTransferTest() {
        fail();
    }

    /**
     * "The account balance should be increased by amount which is transferred from another account."
     */
    @Test
    public void secondAccountBalanceAfterTransferTest() {
        fail();
    }

    /**
     * "Transfer should not be allowed when there is not enough funds."
     */
    @Test
    public void transferNotAllowedTest() {
        fail();
    }

    /**
     * "transaction should be added to transaction log"
     */
    @Test
    public void TransactionAddedToLogTest() {
        fail();
    }
}