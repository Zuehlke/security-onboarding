# Insecure direct object reference

 
### Vulnerability description: 
https://www.owasp.org/index.php/s-A4-Insecure_Direct_Object_References

Insecure direct object reference vulnerability occurs when the application user is granted access to resources (data, files etc.) to which he is not authorized for.

There are two ways to protect against insecure direct object reference vulnerability:

1. Use per user or session indirect object references.
2. Check access.


### Example of attack

1. Login to the insecure-web-app
2. Enter the value of the disabled employee (example id: 11) in 'Search employees by id' box.
3. You will see a disabled employee listed in filter results of the employees grid.

### Analysis of the attack
The application allows access to the restricted disabled employees data. User is able to access disabled employee data by simply entering disabled employee's id in a search box.

### Task: Prevent the Insecure direct object reference attack

Prevent the attack by filtering out all disabled employees in EmployeeController class.
