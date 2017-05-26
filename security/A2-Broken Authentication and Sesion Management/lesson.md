# Broken Authentication and Session Management


### Vulnerability description:

OWASP documentation: https://www.owasp.org/index.php/Top_10_2013-A2-Broken_Authentication_and_Session_Management

There is a lot of space for potential threats in security when talking about authentication and session management. 
When this attack conducted successfully attacker can do everything that victim could do.
The usual target for this attack are priviledged accounts.

Owasp documentation writes about couple vulnerabilities when it comes to management of user credentials and sessions:
    - user credentials are stored without using any encrypting algorithms or caching
    - credentials can be guessed or overwritten through account management functions
    - session id is exposed in the url
    - session id doesn't timeout, or authentication token is not invalidated after user logged out
    - session id is not changed after successful login
    - password and sensitive info is sent over unecrypted connections
    
There is recommendation that authentication and session management schemas should not be custom built, rather use existing solutions for that. 
The attack that is covered here is fist one from the list.


### Example of attack

Insider or external attacker gains access to file with all accounts. 
In this tutorial application all info is stored into credentials.txt file (database is not used),
so all that the attacker would need to do is get that file.
If we have the file we have all information we need to log in as any person which is registered, 
since in that file all passwords are as is, there is no hashing or encrypting algorithm used.

When you get the code you can find the file under source/be/credentials.txt
The information about all accounts is loaded when the app is initialized, all accounts are held in memory.
You can see how it is done in WebSecurityConfig.java class.

### Analysis of the attack
When we have access to that file we can login as admin and obtain all priviledges he has and do mean things for example manage all accounts 3:)

### Task: Prevent attack
In order to be safe of this kind of attack it would be enough to just have all passwords encrypted. 
In that way attacker would not have plain password he could use instantly.

Idea was to use crypto library of Spring framework which provides us functionality for crypting and decrytping passwords.

We have defined password encoder as a spring bean and configured AuthenticationManagerBuilder to use BCrypt encoding mechanism.
BCrypt use adaptive hash algorithm to store a password.  
BCrypt internally generates a random salt while generating passwords and hence it is obvious to get different encoded results for the same string. 
Only common thing is that it generates a string with length 60
In solution of this attack you can see how it is done. 

Code that is needed for encrypting passwords is in HashingPasswords.java class but since we need to do that once(all existing accounts are stored in file) it is commented out.
Since we are not using database, crypted passwords are again stored in file.

