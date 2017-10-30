using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.SpaServices.Webpack;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using SecurityApp.Model;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Authentication.Cookies;
using System;
using Microsoft.AspNetCore.Antiforgery;
using Microsoft.AspNetCore.Http;
using System.Net;
using System.Threading.Tasks;

namespace SecurityApp
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddCors(options =>
            {
                options.AddPolicy("AllowAllHeaders",
                      builder =>
                      {
                          builder.AllowAnyOrigin()
                                 .AllowAnyHeader()
                                 .AllowAnyMethod();
                      });
            });
        
            services.AddDbContext<EmployeeContext>(options => options.UseSqlite(Configuration.GetConnectionString("DefaultConnection")));

            services.AddAuthentication(Microsoft.AspNetCore.Server.HttpSys.HttpSysDefaults.AuthenticationScheme);

            services.AddMvc();

            services.AddAuthentication(CookieAuthenticationDefaults.AuthenticationScheme)//("CookieAuthenticationScheme")
                    .AddCookie(options =>
                    {
                        options.LoginPath = "/login";
                        options.LogoutPath = "/logout";
                        options.Events = new CookieAuthenticationEvents
                        {
                            OnRedirectToLogin = ctx =>
                            {
                                if (ctx.Request.Path.StartsWithSegments("/api") &&
                                    ctx.Response.StatusCode == (int)HttpStatusCode.OK)
                                {
                                    ctx.Response.StatusCode = (int)HttpStatusCode.Unauthorized;
                                }
                                else
                                {
                                    ctx.Response.Redirect(ctx.RedirectUri);
                                }
                                return Task.FromResult(0);
                            }
                        };
                    });
        }
        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, EmployeeContext context, IAntiforgery antiforgery)
        {
          
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseWebpackDevMiddleware(new WebpackDevMiddlewareOptions
                {
                    HotModuleReplacement = true
                });
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
            }

            app.UseCors("AllowAllHeaders"); 

            app.UseStaticFiles();

            var optionsBuilder = new DbContextOptionsBuilder<EmployeeContext>();
            optionsBuilder.UseSqlite(Configuration.GetConnectionString("DefaultConnection"));

            using (var client = new EmployeeContext(optionsBuilder.Options))
            {
                client.Database.EnsureCreated();
            }

            DbInitializer.Initialize(context);

            app.UseAuthentication();

            app.UseMvc(routes =>
            {
                routes.MapRoute(
                    name: "default",
                    template: "{controller=Home}/{action=Index}/{id?}");

                routes.MapSpaFallbackRoute(
                    name: "spa-fallback",
                    defaults: new { controller = "Home", action = "Index" });
            });

        }
    }
}
