using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebApi2Sample.Models
{
    [Table("BankAccounts")]
    public class BankAccount
    {
        [Key]
        public Guid Id { get; set; }

        public int Balance { get; set; }
    }
}
