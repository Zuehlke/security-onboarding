# Cross-Site Scripting (XSS)

 
### Vulnerability description:

OWASP documentation: https://www.owasp.org/index.php/Top_10_2013-A3-Cross-Site_Scripting_(XSS)

XSS flaws occur when an application includes user supplied data in a page sent to the browser without properly validating or escaping that content.

There are two different types of XSS flaws: 

1. Stored - when the XSS script is stored in the database, usually through user input form (e.g. forum text fields that don't exclude script input)
2. Reflected, when user input is immediately returned by a web application in an error message, search result, or any other response that includes some or all of the input provided by the user

For instance, if the attacker enters following JavaScript into the user forum input `alert('XSS attack successfull')`, a popup window with the message will be shown d to each user that visits the forum topic with this message

There are 2 solutions in general:
1. To allow only safe characters in input fields - white list approach that is recommended
2. *Sanitize* user input by removing unsafe characters - black list approach that should be implemented using existing XSS *sanitizers* or framework mechanisms.

**Never create your own sanitizers**, since you will not be able to cover all cases.


### Example of attack

1. Login to the insecure-web-app
2. Add a new employee with following name `<img src="error.jpg" onerror="javascript:alert('xss')"></img> `
3. Refresh
4. You will see a popup window each time you open the page

### Analysis of the attack

This is an example of a stored XSS attack. The input has been stored in the repository for employees and will be rendered on the page.

### Task: Prevent the XSS attack

AngularJS 2 has its own script sanitization. Reason that it is not activated is that we are using a custom directive `*zPrint` in `insecure-web-app\source\fe\app\employee-list\employee-list.html`.

The way to fix this is to not use the custom directive but use existing Angular way of binding values.

You can replace `<span *zPrint="employee.firstName"></span>`, with `{{employee.firstName}}`. This will use Angular sanitization.

In general you should always use framework XSS sanitization. If the framework does not support XSS sanitization you can use following:

* In JAVA use [`HtmlUtils.htmlEscape`](http://docs.spring.io/spring/docs/3.0.x/javadoc-api/org/springframework/web/util/HtmlUtils.html#htmlEscape%28java.lang.String%29) method to sanitize user inputs.
* In .NET use [`System.Web.Security.AntiXss.AntiXssEncoder`](https://msdn.microsoft.com/en-us/library/system.web.security.antixss.antixssencoder(v=vs.110).aspx) class to sanitize user inputs.