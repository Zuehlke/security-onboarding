#Missing function level access control

## Vulnerability description

OWASP documentation: https://www.owasp.org/index.php/Top_10_2013-A7-Missing_Function_Level_Access_Control

Security issue that is manifested when failing to enforce proper authorization on sub-elements of the system,
that can be directly be accessed and exploited by the malicious user. 

Real world example:

- single page web application (or a mobile application) that is communicating using HTTP protocol with a back-end server that is exposing REST API
- back-end application is not verifying if the user accessing REST endpoint is authorized to do so

How to prevent:

- enforce authorization of the functionality exposed by the REST API 

## Example of attack

1. Using Chrome web browser, login to the [insecure-web-app](http://localhost:3000/) using the following credentials:
    - username: `user`
    - password: `pass`
2. Upon successful login - employee list is displayed

> Note: Since the user is authenticated as a **regular** user, there is no option offered to delete existing employees. However, due to `missing function level access control` security issue existing on the back-end app, this can be overcome by the following steps

3. Open developer tools (either by using keyboard shortcut `F12` or by selecting `More tooles>Developer tools` from the settings)

4. In the developer tools
    1. Select `Application > Local Storage > http://localhost:3000`
    2. Modify the value of **Key** IS_ADMIN to be `true`
5. Reload the page

> Delete button is displayed next to each employee in the list

6. Delete some employees

## Analysis of the attack

This is an example of a `missing function level access control` attack. Employees get deleted, although this should not be possible with **regular** user account, but should be a functionality that is available to **admin** users only

## JAVA Task: Prevent the **missing function level access control** attack

Find and fix the back end service by enforcing proper authorization of the delete user action. Make it available to **admin**
 users only.

> Hint: there is an annotation provided by Spring Security that can be used to fix the issue.

> ```@Secured({"ADMIN"}) // This is put on controller or action```
>
> [Documentation of Spring Security](http://docs.spring.io/spring-security/site/docs/4.1.3.BUILD-SNAPSHOT/reference/htmlsingle/)

## .NET Task: Prevent The Missing Function Level Access Control Attack

Find and fix the back end service by enforcing proper authorization of the delete user action. Make it available to admin users only.

Hint: there is an annotation provided by ASP.NET Core that can be used to fix the issue.
`[Authorize(Roles = "ADMIN")]` // This is put on controller or action