using System;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Description;

using WebApi2Sample.Models;

namespace WebApi2Sample.Controllers
{
    using WebApi2Sample.Dtos;
    using WebApi2Sample.Interfaces;

    public class TransactionsController : BaseBankContextController
    {
        // GET: api/Transactions
        public TransactionsController(IBankContext dbContext)
            : base(dbContext)
        {
        }

        public IQueryable<TransactionDto> GetTransactions()
        {
            return DbContext.Transactions
                .Include("AccountFrom")
                .Include("AccountTo")
                .Select(transaction => new TransactionDto()
                    {
                        Id = transaction.Id,
                        AccountFromId = transaction.AccountFrom.Id,
                        AccountToId = transaction.AccountTo.Id,
                        Amount = transaction.Amount,
                        Timestamp = transaction.Timestamp
                    });
        }

        // GET: api/Transactions/5
        [ResponseType(typeof(Transaction))]
        public async Task<IHttpActionResult> GetTransaction(Guid id)
        {
            var transaction = await DbContext.Transactions.FindAsync(id);
            if (transaction == null)
            {
                return NotFound();
            }

            return Ok(transaction);
        }

        // POST: api/Transactions
        [ResponseType(typeof(Transaction))]
        [HttpPost]
        [Route("api/Transactions/Transfer", Name = "Transfer")]
        public async Task<IHttpActionResult> Transfer([FromBody] TransferDto transferDto)
        {
            var account1 = DbContext.BankAccounts.FirstOrDefault(a => a.Id == transferDto.AccountFromId);
            var account2 = DbContext.BankAccounts.FirstOrDefault(a => a.Id == transferDto.AccountToId);

            if (account1 == null || account2 == null)
            {
                return BadRequest();
            }

            if (account1.Balance < transferDto.Amount)
            {
                return BadRequest();
            }

            var transaction = new Transaction()
                {
                    Id = Guid.NewGuid(),
                    AccountFrom = account1,
                    AccountTo = account2,
                    Amount = transferDto.Amount,
                    Timestamp = DateTime.Now
                };

            account1.Balance -= transferDto.Amount;
            account2.Balance += transferDto.Amount;
            DbContext.SetModified(account1);
            DbContext.SetModified(account2);
            DbContext.Transactions.Add(transaction);

            try
            {
                await DbContext.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (TransactionExists(transaction.Id))
                {
                    return Conflict();
                }

                throw;
            }

            return CreatedAtRoute("Transfer", new { id = transaction.Id }, transaction);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                DbContext.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool TransactionExists(Guid id)
        {
            return DbContext.Transactions.Any(e => e.Id == id);
        }
    }
}