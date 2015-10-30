using System.Data.Entity;
using System.Threading.Tasks;

public interface IGpsContext
{
    //DbSet<Account> Gps { get; }

    int SaveChanges();

    Task<int> SaveChangesAsync();
}