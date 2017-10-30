using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace SecurityApp.Crypto
{
    public static class HashingPasswords
    {
        /// <summary>
        /// Hash passwords in a file by using BCrypt
        /// </summary>
        /// <param name="inputFile"></param>
        /// <param name="outputFile"></param>
        public static void EncryptFile(string inputFile, string outputFile)
        {
   
            FileStream fileStream = new FileStream(inputFile, FileMode.Open);
            using (StreamReader reader = new StreamReader(fileStream))
            {
                using (StreamWriter sw = new StreamWriter(outputFile))
                {
                    while (!reader.EndOfStream)
                    {
                        string line = reader.ReadLine();

                        string[] fields = line.Split(' ');
                        string user = fields[0];
                        string pass = fields[1];
                        string rola = fields[2];

                        string encPass = BCrypt.Net.BCrypt.HashPassword(pass);
                        sw.WriteLine(user + " " + encPass + " " + rola);
                    }
                }
            }
        }

    }
}
