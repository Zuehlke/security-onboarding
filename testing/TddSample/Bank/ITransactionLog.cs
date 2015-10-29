using System;
using System.Collections.Generic;

namespace Bank
{
    public interface ITransactionLog
    {
        IReadOnlyCollection<Transaction> Transactions { get; }

        Transaction AddTransactionToLog(Guid account1, Guid account2, int amount);
    }
}
