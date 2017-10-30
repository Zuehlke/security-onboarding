using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using SecurityApp.Model;
using System.Security.Claims;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Authentication.Cookies;
using System.IO;

namespace SecurityApp.Controllers
{
    [AllowAnonymous]
    [Route("api/Account")]
    public class AccountController : Controller
    {
        [HttpGet]
        [AllowAnonymous]
        public IActionResult Login()
        {
            return View();
        }

        [HttpPost]
        [AllowAnonymous]
        public async Task<IActionResult> Login([FromBody]LoginModel user)
        {
            string role = ""; 

            if (LoginUser(user.username, user.password, ref role))
            {
                var claims = new List<Claim>
            {
                new Claim(ClaimTypes.Name, user.username),
                new Claim(ClaimTypes.Role, role)
            };

                var userIdentity = new ClaimsIdentity(claims, "login");
                try
                {
                    ClaimsPrincipal principal = new ClaimsPrincipal(userIdentity);
                    await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, principal);

                }catch(Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }

                //Just redirect to our index after logging in. 
                //Redirect("/");
                return Ok(new
                {
                    username = userIdentity.Name,
                    isAdmin = role.Equals("ADMIN") ? true : false                   
                });
            }
            //return View();
            return Unauthorized();
        }

        private bool LoginUser(string username, string password, ref string role)
        {
            string path = @"credentials.txt"; 
            FileStream fileStream = new FileStream(path, FileMode.Open);
            using (StreamReader reader = new StreamReader(fileStream))
            {
                while (!reader.EndOfStream)
                {
                    string line = reader.ReadLine();

                    string[] fields = line.Split(' ');
                    string user = fields[0];
                    string pass = fields[1];
                    string rola = fields[2];

                    if (user.Equals(username) && pass.Equals(password)) 
                    {
                        role = rola;
                        return true;
                    }
                }
            }

            return false;
        }

        [HttpGet]
        [AllowAnonymous]
        public async Task<IActionResult> Logout()
        {
            await HttpContext.SignOutAsync("CookieAuthentication");
            return Redirect("/Account/Login");
        }
    }
}