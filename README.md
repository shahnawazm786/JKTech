Book Management REST API
=========================

This is a Java 17-based RESTful API for managing books. It supports user authentication, secure endpoints using JWT, and includes CI/CD integration with GitHub Actions on Windows OS.

Features:
---------
1. Book Management
    - Create a book
    - Retrieve book(s)
    - Update a book
    - Delete a book

2. User Authentication
    - Sign-up
    - Login
    - JWT token-based access to protected endpoints

3. Testing & Reporting
    - REST Assured for API testing
    - ExtentReports for test report generation

4. CI/CD
    - GitHub Actions pipeline
    - Windows runner
    - Java 17 setup
    - Maven build/test/report
    - ExtentReports uploaded as artifact

Tech Stack:
-----------
- Java 17
- Maven
- REST Assured
- JWT (JSON Web Tokens)
- ExtentReports
- GitHub Actions (CI/CD)

Project Structure:
------------------
/src
└── main
└── java/...     
└── test
└── java/...     -> REST Assured tests

/pom.xml              -> Maven build configuration
/target               -> Compiled files, reports

How to Run Locally:
-------------------
1. Ensure Java 17 and Maven are installed
2. Clone the repo
3. Run:
   mvn clean install
   

How to Test:
------------
Run:
mvn test

Test reports (ExtentReports) are saved to:
/reports/surefire-reports/
/reports/extent-report.html
CI/CD Info:
-----------
GitHub Actions will:
- Run on every push to the main branch
- Build the app on Windows
- Run REST Assured tests
- Generate and upload ExtentReports

Report Location:
----------------
Check GitHub Actions run artifacts to download ExtentReports.

Author:
-------
Mohammad Shahnawaz
mshahnawaz072022@gmail.com
