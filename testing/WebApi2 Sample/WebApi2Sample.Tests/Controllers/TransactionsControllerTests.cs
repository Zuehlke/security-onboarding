using System;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Http.Results;

using Microsoft.VisualStudio.TestTools.UnitTesting;

using WebApi2Sample.Controllers;
using WebApi2Sample.Dtos;
using WebApi2Sample.Models;

namespace WebApi2Sample.Tests.Controllers
{


    [TestClass]
    public class TransactionsControllerTests
    {
        private FakeBankContext _fakeContext;

        private TransactionsController _controller;

        private Transaction[] _givenTransactions;

        private BankAccount[] _givenBankAccounts;

        [TestInitialize]
        public void TestInitialize()
        {
            _fakeContext = new FakeBankContext();
            _controller = new TransactionsController(_fakeContext);
            _givenBankAccounts =
                new[]
                    {
                        new BankAccount
                            {
                                Id = new Guid("bd877de3-d2c9-4237-b5ca-08bd3ba56cd8"),
                                Balance = 1000
                            }, 
                        new BankAccount
                            {
                                Id = new Guid("29222f0b-dd48-4589-921b-02eb8a4518f0"),
                                Balance = 100
                            }, 
                    };
            _givenTransactions = new[]
                {
                    new Transaction
                        {
                            Id = new Guid("bd877de3-d2c9-4237-b5ca-08bd3ba56ca7"),
                            AccountFrom = _givenBankAccounts[0],
                            AccountTo = _givenBankAccounts[1],
                            Amount = 100,
                            Timestamp = new DateTime(2015, 1, 1)
                        }
                };

            _fakeContext.BankAccounts.AddRange(_givenBankAccounts);
            _fakeContext.Transactions.AddRange(_givenTransactions);
        }

        [TestMethod]
        public void GetTransactionsTest()
        {
            var transactionDtos = _controller.GetTransactions().ToList();
            Assert.AreEqual(_givenTransactions.Count(), transactionDtos.Count);

            for (var i = 0; i < transactionDtos.Count; i++)
            {
                var dto = transactionDtos[i];
                var transaction = _givenTransactions.ElementAt(i);
                Assert.AreEqual(transaction.Id, dto.Id);
                Assert.AreEqual(transaction.Timestamp, dto.Timestamp);
                Assert.AreEqual(transaction.AccountFrom.Id, dto.AccountFromId);
                Assert.AreEqual(transaction.AccountTo.Id, dto.AccountToId);
            }
        }

        [TestMethod]
        public async Task GetTransactionTest()
        {
            var result = await _controller.GetTransaction(new Guid("bd877de3-d2c9-4237-b5ca-08bd3ba56ca7"));
            var contentResult = result as OkNegotiatedContentResult<Transaction>;
            Assert.IsNotNull(contentResult);
            Assert.AreEqual(_givenTransactions.ElementAt(0), contentResult.Content);
        }

        [TestMethod]
        public async Task TransactionAddedToLogOnTransferTest()
        {
            var dto = new TransferDto
            {
                AccountFromId = new Guid("bd877de3-d2c9-4237-b5ca-08bd3ba56cd8"),
                AccountToId = new Guid("29222f0b-dd48-4589-921b-02eb8a4518f0"),
                Amount = 500
            };

            var result = await _controller.Transfer(dto);
            var contentResult = result as CreatedAtRouteNegotiatedContentResult<Transaction>;
            Assert.IsNotNull(contentResult);
            Assert.AreEqual(2, _fakeContext.Transactions.Count());
            Assert.AreEqual(_fakeContext.Transactions.ElementAt(1).AccountFrom.Id, dto.AccountFromId);
            Assert.AreEqual(_fakeContext.Transactions.ElementAt(1).AccountTo.Id, dto.AccountToId);
        }

        [TestMethod]
        public async Task FirstAccountBalanceAfterTransferTest()
        {
            var dto = new TransferDto
                {
                    AccountFromId = new Guid("bd877de3-d2c9-4237-b5ca-08bd3ba56cd8"),
                    AccountToId = new Guid("29222f0b-dd48-4589-921b-02eb8a4518f0"),
                    Amount = 500
                };

            await _controller.Transfer(dto);
            Assert.AreEqual(500, _fakeContext.BankAccounts.ElementAt(0).Balance);
        }

        [TestMethod]
        public async Task SecondAccountBalanceAfterTransferTest()
        {
            var dto = new TransferDto
            {
                AccountFromId = new Guid("bd877de3-d2c9-4237-b5ca-08bd3ba56cd8"),
                AccountToId = new Guid("29222f0b-dd48-4589-921b-02eb8a4518f0"),
                Amount = 500
            };

            await _controller.Transfer(dto);
            Assert.AreEqual(600, _fakeContext.BankAccounts.ElementAt(1).Balance);
        }
    }
}
