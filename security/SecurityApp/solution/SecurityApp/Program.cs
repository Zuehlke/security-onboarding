using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using SecurityApp.Crypto;

namespace SecurityApp
{
    public class Program
    {
        public static void Main(string[] args)
        {
            BuildWebHost(args).Run();

            // Hash passwords
            //string input = @"credentials.txt";
            //string output = @"cryptedCredentials.txt";

            //HashingPasswords.EncryptFile(input, output);
            //Console.WriteLine("File passwords are hashed!");
        }

        public static IWebHost BuildWebHost(string[] args) =>
            WebHost.CreateDefaultBuilder(args)
               .UseStartup<Startup>()
               .UseUrls("http://localhost:53348")
               .Build();
    }
}
