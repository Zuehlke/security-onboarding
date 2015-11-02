using System;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Description;

using WebApi2Sample.Interfaces;
using WebApi2Sample.Models;


namespace WebApi2Sample.Controllers
{
    using WebApi2Sample.Dtos;

    public class BankAccountsController : BaseBankContextController
    {
        public BankAccountsController(IBankContext dbContext)
            : base(dbContext)
        {
        }

        // GET: api/BankAccounts
        public IQueryable<BankAccount> GetAccounts()
        {
            return DbContext.BankAccounts;
        }

        // GET: api/BankAccounts/5
        [ResponseType(typeof(BankAccount))]
        public async Task<IHttpActionResult> GetBankAccount(Guid id)
        {
            var bankAccount = await DbContext.BankAccounts.FindAsync(id);
            if (bankAccount == null)
            {
                return NotFound();
            }

            return Ok(bankAccount);
        }

        // POST: api/BankAccounts
        [ResponseType(typeof(BankAccount))]
        [HttpPost]
        public async Task<IHttpActionResult> CreateBankAccount()
        {
            return await CreateBankAccount(0);
        }

        // PUT: api/BankAccount
        [HttpPost]
        [ResponseType(typeof(BankAccount))]
        [Route("api/BankAccounts/MakeDeposit")]
        public async Task<IHttpActionResult> MakeDeposit([FromBody] ChangeBalanceDto deposit)
        {
            return await ChangeBalance(deposit.AccountId, deposit.Amount);
        }

        [HttpPost]
        [ResponseType(typeof(BankAccount))]
        [Route("api/BankAccounts/Withdraw")]
        public async Task<IHttpActionResult> Withdraw([FromBody] ChangeBalanceDto withdrawal)
        {
            return await ChangeBalance(withdrawal.AccountId, -withdrawal.Amount);
        }

        // POST: api/BankAccounts
        [ResponseType(typeof(BankAccount))]
        [Route("api/BankAccounts/CreateWithDeposit/{initialDeposit:int}", Name = "CreateWithDeposit")]
        public async Task<IHttpActionResult> PostBankAccount([FromUri] int initialDeposit)
        {
            return await CreateBankAccount(initialDeposit);
        }

        private async Task<IHttpActionResult> CreateBankAccount(int initialDeposit)
        {
            var bankAccount = new BankAccount()
            {
                Id = Guid.NewGuid(),
                Balance = initialDeposit
            };

            DbContext.BankAccounts.Add(bankAccount);

            try
            {
                await DbContext.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (BankAccountExists(bankAccount.Id))
                {
                    return Conflict();
                }

                throw;
            }

            return CreatedAtRoute("CreateWithDeposit", new { id = bankAccount.Id }, bankAccount);
        }

        private async Task<IHttpActionResult> ChangeBalance(Guid accountId, int amount)
        {
            var account = DbContext.BankAccounts.FirstOrDefault(a => a.Id == accountId);
            if (account == null)
            {
                return NotFound();
            }

            if (account.Balance + amount < 0)
            {
                return BadRequest("Insufficient funds");
            }

            account.Balance += amount;

            DbContext.SetModified(account);

            await DbContext.SaveChangesAsync();

            return Ok(account);
        }

        private bool BankAccountExists(Guid id)
        {
            return DbContext.BankAccounts.Any(e => e.Id == id);
        }
    }
}