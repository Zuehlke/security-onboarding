using System.Data.Entity;
using System.Threading.Tasks;

using WebApi2Sample.Models;

namespace WebApi2Sample.Interfaces
{
    using System;

    public interface IBankContext : IDisposable
    {
        DbSet<BankAccount> BankAccounts { get; }

        DbSet<Transaction> Transactions { get; }

        int SaveChanges();

        Task<int> SaveChangesAsync();

        void SetModified(object entity);
    }
}
