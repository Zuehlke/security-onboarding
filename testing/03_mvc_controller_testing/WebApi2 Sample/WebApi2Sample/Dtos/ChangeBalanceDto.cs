using System;

namespace WebApi2Sample.Dtos
{
    public class ChangeBalanceDto
    {
        public Guid AccountId { get; set; }

        public int Amount { get; set; }
    }
}
