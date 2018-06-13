# Insecure direct object reference

## Vulnerability description

https://www.owasp.org/index.php/Top_10_2013-A4-Insecure_Direct_Object_References

Insecure direct object reference vulnerability occurs when the user is granted access to resources (pages, data, files etc.) to which user is not authorized for. An example would be a newsletter page in *draft* state, to which only administrators should have access.

There are two ways to protect against insecure direct object reference vulnerability:

1. **Use per user or session indirect object references**. This prevents attackers from directly targeting unauthorized resources. For example, instead of using the resource’s database key, a drop down list of six resources authorized for the current user could use the numbers 1 to 6 to indicate which value the user selected. The application has to map the per-user indirect reference back to the actual database key on the server. OWASP’s ESAPI includes both sequential and random access reference maps that developers can use to eliminate direct object references.
2. **Check access**. Each use of a direct object reference from an untrusted source must include an access control check to ensure the user is authorized for the requested object.

## Example of attack

1. Login to the insecure-web-app
2. Enter the value of the disabled employee (example id: 11 in JAVA version, or 4 in .NET version) in 'Search employees by id' box.
3. You will see a disabled employee listed in filter results of the employees grid.

## Analysis of the attack

The application allows access to the restricted disabled employees data. User is able to access disabled employee data by simply entering disabled employee's id in a search box.

## Task: Prevent the Insecure direct object reference attack

Prevent the attack by filtering out all disabled employees in EmployeeController class.
