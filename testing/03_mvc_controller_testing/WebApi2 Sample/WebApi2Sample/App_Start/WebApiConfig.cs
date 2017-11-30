using System.Web.Http;

using Microsoft.Practices.Unity;

using WebApi2Sample.DAL;
using WebApi2Sample.Interfaces;

namespace WebApi2Sample
{

    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Web API configuration and services

            // Web API routes
            config.MapHttpAttributeRoutes();

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new
                    {
                        id = RouteParameter.Optional
                    });

            var container = new UnityContainer();
            container.RegisterType<IBankContext, BankContext>(new HierarchicalLifetimeManager());
            config.DependencyResolver = new UnityResolver(container);
        }
    }
}
