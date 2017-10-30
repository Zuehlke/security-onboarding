using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SecurityApp.Model
{
    public class DbInitializer
    {
        public static void Initialize(EmployeeContext context)
        {
            //context.Database.EnsureCreated();

            // Look for any students.
            if (context.Employee.Any())
            {
                return;   // DB has been seeded
            }

            var employees = new Employee[]
            {
                new Employee { FirstName = "Name1",   LastName = "LastName1",
                    Title = "Expert Software Engineer", JMBG = "2610915225324", disabled = false },
                new Employee { FirstName = "Name2",   LastName = "LastName2",
                    Title = "Software Engineer", JMBG = "2504976258856", disabled = false },
                new Employee { FirstName = "Name3",   LastName = "LastName3",
                    Title = "Junior Software Engineer", JMBG = "1010976258965", disabled = false },
                 new Employee { FirstName = "Name11",   LastName = "LastName11",
                    Title = "Junior Software Engineer", JMBG = "0905978125896", disabled = true },
                 new Employee { FirstName = "Name4",   LastName = "LastName4",
                    Title = "Trainee", JMBG = "2205973125963", disabled = false },
                 new Employee { FirstName = "Name5",   LastName = "LastName5",
                    Title = "Trainee", JMBG = "2912986258963", disabled = true },
                 new Employee { FirstName = "Name9",   LastName = "LastName9",
                    Title = "Junior Software Engineer", JMBG = "0101993125896", disabled = false },
                  new Employee { FirstName = "Name10",   LastName = "LastName10",
                    Title = "Trainee", JMBG = "1909998225659", disabled = false }

            };

            foreach (Employee s in employees)
            {
                context.Employee.Add(s);
            }
            context.SaveChanges();
        }
    }
}
