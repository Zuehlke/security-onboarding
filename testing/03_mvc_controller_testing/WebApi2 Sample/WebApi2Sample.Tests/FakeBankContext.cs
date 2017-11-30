using System.Data.Entity;
using System.Data.Entity.Validation;
using System.Threading.Tasks;

using WebApi2Sample.Interfaces;
using WebApi2Sample.Models;


namespace WebApi2Sample.Tests
{
    public class FakeBankContext : IBankContext
    {
        public FakeBankContext()
        {
            BankAccounts = new FakeDbSet<BankAccount>();
            Transactions = new FakeDbSet<Transaction>();
        }

        public DbSet<BankAccount> BankAccounts { get; private set; }

        public DbSet<Transaction> Transactions { get; private set; }

        public string FailureMessage { get; set; }

        public int SaveChanges()
        {
            if (!string.IsNullOrEmpty(this.FailureMessage))
            {
                throw new DbEntityValidationException(this.FailureMessage);
            }

            return 0;
        }

        public Task<int> SaveChangesAsync()
        {
            if (!string.IsNullOrEmpty(this.FailureMessage))
            {
                throw new DbEntityValidationException(this.FailureMessage);
            }

            var taskCompletion = new TaskCompletionSource<int>();
            taskCompletion.SetResult(0);
            return taskCompletion.Task;
        }

        public void SetModified(object entity)
        {
        }

        public void Dispose()
        {
        }
    }
}
