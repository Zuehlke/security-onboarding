using System;
using System.Collections.Generic;

namespace Bank
{
    public class TransactionLog : ITransactionLog
    {
        private readonly List<Transaction> _transactions = new List<Transaction>();

        public IReadOnlyCollection<Transaction> Transactions
        {
            get { return this._transactions.AsReadOnly(); }
        }

        public Transaction AddTransactionToLog(Guid accountFrom, Guid accountTo, int amount)
        {
            throw new NotImplementedException();
        }
    }
}
