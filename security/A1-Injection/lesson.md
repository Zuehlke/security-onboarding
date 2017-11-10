# Injection

## Vulnerability description

[OWASP documentation](https://www.owasp.org/index.php/Top_10_2013-A1-Injection)

Data backend receives from the frontend cannot be unconditionally trusted - malicious users can modify frontend or even not use provided frontend at all when communicating to the server.

If the data received from the user interacts with database, it should be validated first. Reception of invalid and/or malicious data should be discarded and possibly logged.

## Example of Attack

1. Login to the insecure-web-app
2. In the employee id search field, type in the following: `1%3B INSERT INTO EMPLOYEE (FIRSTNAME, LASTNAME, TITLE, DISABLED, JMBG) VALUES ('JAMES', 'BOND', 'SPY', 0, 007)`
3. Click search
4. Delete everything from employee id search field and search again
5. James Bond has infiltrated your database as one of the employees :O

## Analysis of the Attack

In JAVA app, find the file EmployeeController.java and in it find the following method:

```java
@RequestMapping(value = "/{id}", method = RequestMethod.GET)
@ResponseBody
@Secured("USER")
Employee findById(@PathVariable String id) {
	String sql = "SELECT * FROM EMPLOYEE WHERE ID = " + id;
	try {
		return (Employee) jdbcTemplate.queryForObject(sql,
				(RowMapper) (rs, num) -> new Employee(rs.getLong("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("TITLE")));
	} catch(EmptyResultDataAccessException e) {
		return null;
	}
}
```

In .NET app, find the file EmployeeController.cs and in it find the following method:

```cs
[HttpGet("search/{Empid}")]
public async Task<IActionResult> EmployeeSearch(string Empid)
{
	string query = "SELECT * FROM Employee WHERE EmployeeId = " + Empid;
	var EmpDeatils = await _context.Employee.FromSql(query).ToListAsync();


	return Json(EmpDeatils);
}
```

This method is responsible for retrieving employee from the database and is the main enabler of security injection.

## JAVA Task: Prevent Injection

Approach 1 (GOOD):
Instead of concatenating query strings, use prepared statements. This will take care of SQL injection attempts.

Approach 2 (THE BEST, WHEN AVAILABLE):
Use the ORM solution provided by Spring (employeeRepository.findOne()) to search for employee by id. Validation is done implicitly.

## .NET Task: Prevent Injection

Approach 1 (GOOD): Instead of concatenating query strings, use prepared statements. This will take care of SQL injection attempts.

Approach 2 (THE BEST, WHEN AVAILABLE): Use the parametrized query with Entity Framework (WHERE EmployeeId = @p0) to search for employee by id. Validation is done implicitly.