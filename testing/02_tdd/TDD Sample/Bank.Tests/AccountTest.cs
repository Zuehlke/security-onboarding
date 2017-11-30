using Moq;
using NUnit.Framework;

namespace Bank.Tests
{
    [TestFixture]
    public class AccountTest
    {
        private Mock<ITransactionLog> _mockLog;

        /// <summary>
        /// Executed before other tests
        /// </summary>
        [TestFixtureSetUp]
        public void TestFixtureSetUp()
        {
        }

        /// <summary>
        /// Executed after all the other tests
        /// </summary>
        [TestFixtureTearDown]
        public void TestFixtureTearDown()
        {
        }

        /// <summary>
        /// Executed before each test
        /// </summary>
        [SetUp]
        public void SetUp()
        {
            _mockLog = new Mock<ITransactionLog>();
        }

        /// <summary>
        /// Executed after each test
        /// </summary>
        [TearDown]
        public void TearDown()
        {
            _mockLog = null;
        }

        [Test(Description = "The account should have an ID after opening.")]
        public void NewAccountIdTest()
        {
            Assert.Inconclusive();
        }

        [Test(Description = "The account balance should be zero.")]
        public void NewAccountBalanceTest()
        {
            Assert.Inconclusive();
        }

        [Test(Description = "The account should have an ID after opening with initial deposit.")]
        public void NewAccountWithDepositIdTest([Values(100, 200, 300)] int initialDeposit)
        {
            Assert.Inconclusive();
        }

        [Test(Description = "The account balance should be equal to initial deposit.")]
        public void NewAccountWithDepositBalanceTest([Values(100, 200, 300)] int initialDeposit)
        {
            Assert.Inconclusive();
        }

        [Test(Description = "The account balance should be increased by deposit amount.")]
        public void DepositAfterCreationWithInitialBalanceTest([Values(100, 200, 300)] int deposit)
        {
            Assert.Inconclusive();
        }

        [Test(Description = "The account balance should be decreased by withdrawal amount.")]
        public void WithdrawalTest()
        {
            Assert.Inconclusive();
        }

        [Test(Description = "The withdrawal should not be allowed if there is not enough funds.")]
        public void WithdrawalNotAllowedTest()
        {
            Assert.Inconclusive();
        }

        [Test(Description = "The exception message should be 'Insufficient funds' if withdrawal is attempted when there is not enough funds.")]
        public void WithdrawalNotAllowedMessageTest()
        {
            Assert.Inconclusive();
        }

        [Test(Description = "The account balance should be decreased by amount which is transfered to another account.")]
        public void FirstAccountBalanceAfterTransferTest()
        {
            Assert.Inconclusive();
        }

        [Test(Description = "The account balance should be increased by amount which is transfered from another account.")]
        public void SecondAccountBalanceAfterTransferTest()
        {
            Assert.Inconclusive();
        }

        [Test(Description = "Transfer should not be allowed when there is not enough funds.")]
        public void TransferNotAllowedTest()
        {
            Assert.Inconclusive();
        }

        [Test(Description = "Transaction should be added to transaction log")]
        public void TransactionAddedToLogTest()
        {
            Assert.Inconclusive();
        }
    }
}
