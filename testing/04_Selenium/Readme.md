Welcome to Selenium course


Here you can find detailed presentation about using Selenium in Java for creating automated UI test cases.
Steps to get started:

1. Install Java on your mashine and set JAVA enviroment variables.
2. Download and Install Maven, precondition: JAVA installed, Java path set in system variables
Steps: 
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
| 1. Go to "https://maven.apache.org/download.cgi"																																				|
| 2. Download binary zip archive (-bin.zip) - For WindowsOS,																																|
| 3. Unzip downloaded file to sutable location																																							|
| 4. Copy path where you unzipped maven archive (Folder that contains "bin" folder) and set it as environmental system variable		|
| 5. Setting environmental system variable can be done following steps:																												|
|	 5.1 Go to ThisPC, right click and select poperties																																					|
|	 5.2 On left side og the opened window click "Advanced system settings"																										|
|	 5.3 In "Advanced" tab click "Environment Variables..." button on window bottom																						|
|	 5.4 Click "New..." button under System variables and set name as "MAVEN_HOME" and value as path you copied								|
| 6. Now copy path of "bin" folder from unzipped maven archive and add it to "Path" System variable, see details below:						|
|	 6.1 Find System variable called "Path"																																									|
|	 6.2 Double click on it, on new window click "New" and copy path there																											|
| 7. Test if you have set up maven by entering "mvn --version" in cmd																													|
| 8. If no error is displayed, you are ready to go.																																						|
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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



 


