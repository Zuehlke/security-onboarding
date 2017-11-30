using System;

using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebApi2Sample.Models
{
    [Table("Transactions")]
    public class Transaction
    {
        [Key]
        public Guid Id { get; set; }

        public DateTime Timestamp { get; set; }

        public virtual BankAccount AccountFrom { get; set; }

        public virtual BankAccount AccountTo { get; set; }

        public int Amount { get; set; }
    }
}
