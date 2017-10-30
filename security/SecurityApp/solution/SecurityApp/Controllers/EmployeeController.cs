using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using SecurityApp.Model;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Cors;

namespace SecurityApp.Controllers
{
    [Produces("application/json")]
    [Route("api/Employee")]
    public class EmployeeController : Controller
    {
        private readonly EmployeeContext _context;

        public EmployeeController(EmployeeContext context)
        {
            _context = context;
        }
        [HttpGet]
        public async Task<IActionResult> EmployeeList()
        {
            var listData = await (from emp in _context.Employee
                                  where (emp.disabled == false && 
                                  emp.Title != "ADMIN")
                                  select new Employee{
                                      EmployeeId = emp.EmployeeId,
                                      FirstName = emp.FirstName,
                                      LastName = emp.LastName,
                                      Title = emp.Title,
                                      JMBG = emp.JMBG,
                                      disabled = emp.disabled
                                  }
                          ).ToListAsync();


            return Json(listData);
        }

        [HttpPost]
        public IActionResult AddEmployee([FromBody]Employee empObj)
        {
            _context.Employee.Add(empObj);
            _context.SaveChanges();
            return Json("OK");
        }

        [HttpGet("{Empid}")]
        public async Task<IActionResult> EmployeeDetails(int Empid)
        {
            var EmpDeatils = await (from emp in _context.Employee
                                    where (emp.EmployeeId == Empid
                                    && emp.disabled == false)
                                    select new Employee
                                    {
                                        EmployeeId = emp.EmployeeId,
                                        FirstName = emp.FirstName,
                                        LastName = emp.LastName,
                                        Title = emp.Title,
                                        JMBG = emp.JMBG,
                                        disabled = emp.disabled
                                    }
                          ).FirstAsync();


            return Json(EmpDeatils);

        }


        [HttpGet("search/{Empid}")]
        public async Task<IActionResult> EmployeeSearch(string Empid)
        {
            string query = "SELECT * FROM Employee WHERE EmployeeId = @p0 AND disabled = 0"; // a1, a4
            var EmpDeatils = await _context.Employee.FromSql(query, Empid).ToListAsync();

            return Json(EmpDeatils);
        }

        [HttpDelete("remove/{Empid}")]
        [ValidateAntiForgeryToken] // A8
        public IActionResult RemoveEmployee(int empId)
        {
            return DeleteEmployee(empId);
        }

        [HttpDelete]
        [Authorize(Roles = "ADMIN")] // A7
        public IActionResult RemoveEmployeeDetails([FromBody]int empId)
        {
            return DeleteEmployee(empId);
        }

        private JsonResult DeleteEmployee(int empId)
        {
            var userExits = _context.Employee.Any(x => x.EmployeeId == empId);

            if (userExits)
            {
                Employee Emp = _context.Employee.Where(x => x.EmployeeId == empId).First();
                if (Emp != null)
                {
                    _context.Employee.Remove(Emp);
                    _context.SaveChanges();
                }
                return Json("OK");
            }
            else return Json("Employee not found");
        }

        [HttpPut]
        public IActionResult EditEmployee([FromBody]Employee empData)
        {
            _context.Entry(empData).State = EntityState.Modified;
            _context.SaveChanges();
            return Json("ok");
        }
    }
}