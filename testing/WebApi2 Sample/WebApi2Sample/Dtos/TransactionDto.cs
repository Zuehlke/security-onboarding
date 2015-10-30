using System;

namespace WebApi2Sample.Dtos
{
    public class TransactionDto : TransferDto
    {
        public Guid Id { get; set; }

        public DateTime Timestamp { get; set; }
    }
}
