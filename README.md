# TestTask
QA Test Project

# Installation and execution instructions

\- Install Java<br>
\- Install Maven<br>
\- Download WebDrivers for Chrome and Firefox<br>
<br>
1. Open TestTask in an IDE (preferably IntelliJ IDEA)
2. Go to "(path-to-this-project)\TestTask\src\test\resources"
3. Modify "path.chromedriver" (path to Chrome WebDriver) and "path.geckodriver" (path to Firefox WebDriver)
4. Open command line (Win + R -> "cmd" -> Enter)
5. Type "cd (path-to-this-project)\TestTask" -> Enter
6. Type "mvn clean test" -> Enter
7. Once tests are done type "mvn allure:serve" -> Enter
