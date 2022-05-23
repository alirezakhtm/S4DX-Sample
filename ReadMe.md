# Abstract

This project is a Java class that accepts an array of strings in the constructor. The class will expose a
`find` function that accepts a string. The function will return all strings from the array that contains
the exact same characters as the string passed into it. The order of the characters in the strings is
not relevant.

# Solution

to solve this problem, I used Regex to find specific characters with high performance and functionality. you can find
this solution in `Finder` class.

# Build

To build project you can use this command:

`mvn clean install`


# Test

This project contain unit test to check functionalities, exceptions and Performance.
To measure performance, I considered a String array with 60k length. you can find this 
test case in: `FinderTest.performaceTest`

run test by maven:
``
mvn test
``

and result is:

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.khtm.sdx.sample.FinderTest
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.229 sec

Results :

Tests run: 5, Failures: 0, Errors: 0, Skipped: 0

```
