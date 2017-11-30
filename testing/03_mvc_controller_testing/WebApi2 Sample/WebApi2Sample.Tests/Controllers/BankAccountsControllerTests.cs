using System;

using Microsoft.VisualStudio.TestTools.UnitTesting;

using WebApi2Sample.Controllers;
using WebApi2Sample.Models;

namespace WebApi2Sample.Tests.Controllers
{
    using System.Linq;
    using System.Threading.Tasks;
    using System.Web.Http.Results;

    using WebApi2Sample.Dtos;

    [TestClass]
    public class BankAccountsControllerTests
    {
        private FakeBankContext _fakeContext;

        private BankAccountsController _controller;

        private BankAccount[] _givenBankAccounts;

        [TestInitialize]
        public void TestInitialize()
        {
            _fakeContext = new FakeBankContext();
            _controller = new BankAccountsController(_fakeContext);
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
            _fakeContext.BankAccounts.AddRange(_givenBankAccounts);
        }

        [TestMethod]
        public void GetAccountsTest()
        {
            var accounts = _controller.GetAccounts().ToArray();

            CollectionAssert.AreEqual(_givenBankAccounts, accounts);
        }

        [TestMethod]
        public async Task GetBankAccountByGuidTest()
        {
            var result = await _controller.GetBankAccount(new Guid("bd877de3-d2c9-4237-b5ca-08bd3ba56cd8"));
            var contentResult = result as OkNegotiatedContentResult<BankAccount>;
            Assert.IsNotNull(contentResult);
            Assert.AreEqual(_givenBankAccounts[0], contentResult.Content);
        }

        [TestMethod]
        public async Task CreateBankAccountTest()
        {
            var result = await _controller.CreateBankAccount();
            var contentResult = result as CreatedAtRouteNegotiatedContentResult<BankAccount>;
            Assert.IsNotNull(contentResult);
            Assert.AreEqual(3, _fakeContext.BankAccounts.Count());
            Assert.AreEqual(_fakeContext.BankAccounts.ElementAt(2), contentResult.Content);
        }

        [TestMethod]
        public async Task CreateBankAccountWithInitialDepositTest()
        {
            var result = await _controller.PostBankAccount(500);
            var contentResult = result as CreatedAtRouteNegotiatedContentResult<BankAccount>;
            Assert.IsNotNull(contentResult);
            Assert.AreEqual(3, _fakeContext.BankAccounts.Count());
            Assert.AreEqual(_fakeContext.BankAccounts.ElementAt(2), contentResult.Content);
            Assert.AreEqual(500, contentResult.Content.Balance);
        }

        [TestMethod]
        public async Task MakeDepositTest()
        {
            var dto = new ChangeBalanceDto
                {
                    AccountId = new Guid("bd877de3-d2c9-4237-b5ca-08bd3ba56cd8"),
                    Amount = 200
                };
            var result = await _controller.MakeDeposit(dto);
            var contentResult = result as OkNegotiatedContentResult<BankAccount>;
            Assert.IsNotNull(contentResult);
            Assert.AreEqual(1200, _fakeContext.BankAccounts.ElementAt(0).Balance);
        }

        [TestMethod]
        public async Task WithdrawalTest()
        {
            var dto = new ChangeBalanceDto
            {
                AccountId = new Guid("bd877de3-d2c9-4237-b5ca-08bd3ba56cd8"),
                Amount = 200
            };

            var result = await _controller.Withdraw(dto);
            var contentResult = result as OkNegotiatedContentResult<BankAccount>;
            Assert.IsNotNull(contentResult);
            Assert.AreEqual(800, _fakeContext.BankAccounts.ElementAt(0).Balance);
        }

        [TestMethod]
        public async Task WithdrawalNotAllowedTest()
        {
            var dto = new ChangeBalanceDto()
                {
                    AccountId = new Guid("bd877de3-d2c9-4237-b5ca-08bd3ba56cd8"),
                    Amount = 2000
                };

            var result = await _controller.Withdraw(dto);
            var badRequestResult = result as BadRequestErrorMessageResult;
            Assert.IsNotNull(badRequestResult);
            Assert.AreEqual("Insufficient funds", badRequestResult.Message);
        }
    }
}
