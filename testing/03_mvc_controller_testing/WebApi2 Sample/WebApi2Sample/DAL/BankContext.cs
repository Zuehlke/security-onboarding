using System.Data.Entity;

using WebApi2Sample.Interfaces;
using WebApi2Sample.Models;

namespace WebApi2Sample.DAL
{
    public class BankContext : DbContext, IBankContext
    {
        public BankContext()
            : base("BankContext")
        {
        }

        public DbSet<BankAccount> BankAccounts { get; set; }

        public DbSet<Transaction> Transactions { get; set; }

        public void SetModified(object entity)
        {
            Entry(entity).State = EntityState.Modified;
        }
    }
}
