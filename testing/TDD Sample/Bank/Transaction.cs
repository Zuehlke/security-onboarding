using System;

namespace Bank
{
    public class Transaction
    {
        private readonly DateTime _timestamp;

        private readonly Guid _id;

        private readonly Guid _accountFrom;

        private readonly Guid _accountTo;

        private readonly int _amount;

        public Transaction(Guid accountFrom, Guid accountTo, int amount)
        {
            throw new NotImplementedException();
        }

        public Guid Id
        {
            get { return this._id; }
        }

        public DateTime Timestamp
        {
            get { return this._timestamp; }
        }

        public Guid AccountFrom
        {
            get { return this._accountFrom; }
        }

        public Guid AccountTo
        {
            get { return this._accountTo; }
        }

        public int Amount
        {
            get { return this._amount; }
        }
    }
}
