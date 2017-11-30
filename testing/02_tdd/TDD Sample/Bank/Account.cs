using System;

namespace Bank
{
    public class Account
    {
        public Account()
        {
            throw new NotImplementedException();
        }

        public Account(int initialDeposit)
            : this()
        {
            throw new NotImplementedException();
        }

        public int Balance { get; set; }

        public Guid Id { get; set; }

        public void Deposit(int deposit)
        {
            throw new NotImplementedException();
        }

        public void Withdraw(int withdrawal)
        {
            throw new NotImplementedException();
        }

        public Transaction Transfer(Account a2, int amount)
        {
            throw new NotImplementedException();
        }
    }
}
