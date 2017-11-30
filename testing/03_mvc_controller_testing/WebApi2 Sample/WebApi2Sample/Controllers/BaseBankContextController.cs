namespace WebApi2Sample.Controllers
{
    using System.Web.Http;

    using WebApi2Sample.Interfaces;

    public abstract class BaseBankContextController : ApiController
    {
        protected readonly IBankContext DbContext;

        protected BaseBankContextController(IBankContext dbContext)
        {
            this.DbContext = dbContext;
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                DbContext.Dispose();
            }

            base.Dispose(disposing);
        }
    }
}
