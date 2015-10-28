#Unit Testing

##What is a unit test?

A unit test is an automated piece of code that invokes a unit of work  in the system and and then checks some assumptions about the behavior of that unit of work.
A unit of work is a single logical functional use case in the system that can be invoked by some public interface (in most cases). A unit of work can span a single method, a whole class or multiple classes working together to achieve one single logical purpose that can be verified.
The Art of Unit Testing by Roy Osherove

##Characteristics

* Able to be fully automated
* Has full control over all the pieces running (Use mocks or stubs to achieve this isolation when needed)
* Can be run in any order  if part of many other tests
* Runs in memory (no DB or File access, for example)
* Consistently returns the same result (You always run the same test, so no random numbers, for example)
* Runs fast
* Tests a single logical concept in the system
* Readable
* Maintainable
* Trustworthy (when you see its result, you don’t need to debug the code just to be sure)

##Traditional vs Unit testing

###Traditional testing

* Test the system as a whole
* Individual components rarely tested
* Errors go undetected
* Isolation of errors difficult to track down

###Unit testing

* Each part tested individually
* All components tested at least once
* Errors detected earlier
* Smaller scope, easier to fix errors

##Why unit tests?

* Faster Debugging
* Faster Development
* Better Design
* Excellent Regression Tool
* Reduce Future Cost
* Unit testing allows the programmer to refactor code at a later date, and make sure the module still works correctly. 
* By testing the parts of a program first and then testing the sum of its parts, integration testing becomes much easier.
* Unit testing provides a sort of living documentation of the system.

##Guidelines

* Keep unit tests small and fast
    * Ideally the entire test suite should be executed before every code check in. Keeping the tests fast reduce the development turnaround time.
* Unit tests should be fully automated and non-interactive
    * The test suite is normally executed on a regular basis and must be fully automated to be useful. If the results require manual inspection the tests are not proper unit tests.
* Make unit tests simple to run
    * Configure the development environment so that single tests and test suites can be run by a single command or a one button click.
* Measure the tests
    * Apply coverage analysis to the test runs so that it is possible to read the exact execution coverage and investigate which parts of the code is executed and not.
* Start off simple
    * One simple test is infinitely better than no tests at all. A simple test class will establish the target class test framework, it will verify the presence and correctness of both the build environment, the unit testing environment, the execution environment and the coverage analysis tool, and it will prove that the target class is part of the assembly and that it can be accessed.
* Keep tests independent
    * To ensure testing robustness and simplify maintenance, tests should never rely on other tests nor should they depend on the ordering in which tests are executed.
* Name tests properly
    * Make sure each test method test one distinct feature of the class being tested and name the test methods accordingly. The typical naming convention is Test[what] such As TestSaveAs(), TestAddListener(), TestDeleteProperty() etc.
* Test public API
    * Unit testing can be defined as testing classes through their public API. Some testing tools makes it possible to test private content of a class, but this should be avoided as it makes the test more verbose and much harder to maintain. If there is private content that seems to need explicit testing, consider refactoring it into public methods in utility classes instead. But do this to improve the general design, not to aid testing.
* Provide negative tests
    * Negative tests intentionally misuse the code and verify robustness and appropriate error handling.
* Design code with testing in mind
    * Writing and maintaining unit tests are costly, and minimizing public API and reducing complexity in the code are ways to reduce this cost and make high-coverage test code faster to write and easier to maintain.
* Don't connect to predefined external resources
    * Unit tests should be written without explicit knowledge of the environment context in which they are executed so that they can be run anywhere at anytime. In order to provide required resources for a test these resources should instead be made available by the test itself.
* Write tests to reproduce bugs
    * When a bug is reported, write a test to reproduce the bug (i.e. a failing test) and use this test as a success criteria when fixing the code.
* Know the limitations
    * Unit tests can never prove the correctness of code.