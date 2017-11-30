using System;

namespace WebApi2Sample.Dtos
{
    public class TransferDto
    {
        public Guid AccountFromId { get; set; }

        public Guid AccountToId { get; set; }

        public int Amount { get; set; }
    }
}
