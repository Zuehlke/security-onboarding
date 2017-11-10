# Cross-Site Request Forgery (CSRF)

## Vulnerability description

OWASP documentation: https://www.owasp.org/index.php/Top_10_2013-A8-Cross-Site_Request_Forgery_(CSRF)

Imagine you login to your favorite website. You leave this tab with your favorite website open and open another tab to check your e-mails.

You get an e-mail that fools you into clicking an image. The onclick event on this image activates a JavaScript AJAX request to an endpoint that deletes the user in your favorite website.

Since you are logged into the website and have a running session this request is authenticated and will delete the user. You wouldn't be aware that your click caused this.

## Example of attack

1. Login to the insecure web app
2. Leave it open
3. Go to the folder `A8-Cross-Site Request Forgery (CSRF)`
4. Run `java-exploit\run.bat` file or `net-exploit\run.bat`
5. Click on the prize :-)
6. Go back to the insecure web app user page and you will see no user is present anymore

## Analysis of the attack

If you open the `exploit\index.html` you will see this function `callDelete`

It calls the DELETE verb on the employee deletion endpoint, and this deletes the user.

## Task: Prevent the CSRF attack

Have a look into the OWASP documentation and understand how to fix this using the CSRF anti forgery token.

JAVA Hint:
* Read following link: https://spring.io/blog/2015/01/12/the-login-page-angular-js-and-spring-security-part-ii
* CSRF protection in Spring can be disabled by calling the `.csrf().disable()` of the `HttpSecurity`
* This was disabled by "mistake" in the example application

.NET Hint:
Enable global cors policy for ASP.NET core app and remove AllowAllHeaders.

See here: https://damienbod.com/2017/05/09/anti-forgery-validation-with-asp-net-core-mvc-and-angular/