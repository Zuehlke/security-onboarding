# Insecure app readme

## Prerequisites

- Install [JAVA 8 JDK Windows x64](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) with JAVA_HOME environment variable in PATH
- OS: Windows for using `run.bat`
- Install [Node.js latest](https://nodejs.org/en/download/current/)

## Running the app

- Open command prompt in current directory
- Execute: run.bat
- There are two available users:
  - User{username='admin', password='pass', role='ADMIN'}
  - User{username='user', password='pass', role='USER'}

## Making code fixes

- Create your own personal branch for any fixes you want to try out. **Don't work on master or push to master**
- To make **front-end** code changes (TypeScript, HTML, CSS): write your code, save the file, and the changes should be reflected in the browser after a page refresh
- To make **back-end** code changes (JAVA): stop the JAVA app using `CTRL+C`, make the changes, and execute `run.bat` again
  - If for any reason the back-end console tells you the port is already taken, find and kill JAVA process in the Task Manager