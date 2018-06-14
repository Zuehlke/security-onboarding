# Sensitive Data Exposure

## Vulnerability description

OWASP documentation: https://www.owasp.org/index.php/Top_10_2013-A6-Sensitive_Data_Exposure

A data breach is the intentional or unintentional release of secure or private/confidential information to an untrusty environment.
Other terms for this phenomenon include unintentional information disclosure, data leak and also data spill.
Incidents range from concerted attack by black hats associated with organized crime, political activist or national governments to careless disposal of used computer equipment or data storage media.

## What is sensitive data

**Personally Identifiable Information** (**PII**) is a category of sensitive information that is associated with an individual person, such as an employee, student, or donor. PII should be accessed only on a strictly need-to-know basis and handled and stored with care.

PII is information that can be used to uniquely identify, contact, or locate a single person. Personal information that is “de-identified” (maintained in a way that does not allow association with a specific person) is not considered sensitive.

## Example of personally sensitive data

* National ID number
* Passport number
* Visa permit number
* Driver's license number
* Bank and credit/debit card numbers
* Disability status
* Ethnicity
* Gender
* The location of an individual at a particular time
* Web sites visited
* Materials downloaded
* Any other information reflecting preferences and behaviors of an individual 
* Internet Protocol (IP) address (source and destination) in conjunction with other PII. IP address may identify an individual originating a transaction as well as the recipient.

# **IMPORTANT**

In context of our company, we have the concept of **internal information**. Any data connected to our customers is sensitive. Examples are:

* Customer name
* Project name
* Project code
* Project documentation
* Project financial information
* Non-public financial information
* Internal Zuehlke documents
* Additional NDA with customer that covers communication with your Zuehlke collegues about projects

If in doubt, ask your line manager (HCU) or the Chief Information Security Office (CISO) if the data is sensitive.

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