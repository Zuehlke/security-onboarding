Welcome to Selenium course


Here you can find detailed presentation about using Selenium in Java for creating automated UI test cases.
Steps to get started:

1. Install Java on your mashine
2. Install Maven
3. Clone this repository localy

In Selenium.pptx presentation you can find details on how to use selenium. Presentation follows examples from  zLibrary_SeleniumTests scripts.

Example tests are created for zLibrary internal project. Since it uses Microsoft AD logon service for accessing application, logon proces via scripts 
is bit specific (Since we don't have test users in Zuehkle directory for purpose of automated testing :) )

So in order to override this issue, there are several steps you need to change in script:

1. Login into zLibrary application (URL: http://zlibrary.eu-west-1.elasticbeanstalk.com) 
2. Open developer tools, click on "Application", expand "Cookies" click on "http://zlibrary.eu-west-1..." 
     See screenshot: Token.png
3. On right side of screen find cookie named "currentUser"
4. Find src/main/java/zLibrary/PageConstants Interface
	See screenshot: Interface.png
5. Copy "currentUser" cookie entire value into "COOKIE_VALUE" String constant stored in PageConstants interface 
6. Change "USER_NAME" and "USER_SURNAME" String constant values in PageConstants interface to your name and surname
	See screenshot: NameSurname.png
7. Save changes

Changing browser where to execute scripts: 

1. In PageConstants interface find "BROWSER" String constant, change to desired browser name ("firefox", "chrome", "ie")

Running tests:

1. Open console at root folder of zLibrary tests
2. In console enter: mvn clean
3. In console enter: mvn test

Once test execution is completed, surefire test execution reports are automatically generated in folder: target/surefire-reports/index.html



 


