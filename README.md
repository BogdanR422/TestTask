# TestTask
QA Test Project

# Installation and execution instructions

Install Java
Install Maven
Download WebDrivers for Chrome and Firefox

Open TestTask in an IDE (preferably IntelliJ IDEA)
Open "(path-to-this-project)\TestTask\src\test\resources"
Modify "path.chromedriver" (path to Chrome WebDriver) and "path.geckodriver" (path to Firefox WebDriver)

Open command line (Win + R -> "cmd" -> Enter)
Type "cd (path-to-this-project)\TestTask" -> Enter
Type "mvn clean test" -> Enter
Once tests are done type "mvn allure:serve" -> Enter
