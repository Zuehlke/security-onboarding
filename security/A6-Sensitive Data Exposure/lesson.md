# Sensitive Data Exposure

## Vulnerability description

OWASP documentation: https://www.owasp.org/index.php/Top_10_2013-A6-Sensitive_Data_Exposure

A data breach is the intentional or unintentional release of secure or private/confidential information to an untrusty environment.
Other terms for this phenomenon include unintentional information disclosure, data leak and also data spill.
Incidents range from concerted attack by black hats associated with organized crime, political activist or national governments to careless disposal of used computer equipment or data storage media.

## Example of Attack

1. Login to the insecure-web-app
2. Using browser's Network monitor in the Developer tools (F12), inspect the response with JSON array of users that are retrieved after login and displayed in the table
3. Each JSON should contain confidential JMBG field - this is sensitive data that is exposed!

## Analysis of the Attack

In JAVA, find the file Employee.java. The employee class should have this field present:

```java
@Column(name = "JMBG", nullable = false)
    private String jmbg;
```

In .NET, find the file Employee.cs. The employee class should have this field present:

```cs
public string JMBG { get; set; }
```

It should also contain appropriate getters and setters.
This is all fine, but we need to find a way to not leak this field into the HTTP responses that return employees.

## Task: Prevent Sensitive Data Exposure

Instruct the JSON serializer to ignore the sensitive field. This can often be done declaratively (e.g. in Java using annotations and .NET using attributes).