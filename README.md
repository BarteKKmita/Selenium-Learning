## How to run tests on your computer:

1. There are two kind of browsers that you can perform tests against.

   - Chrome
   - Edge

   Please choose one of following and download corresponding `WebDriver` from the internet.  
   If you decided to use `Chrome` put directory with `WebDriver` to `$PATH` as env variable.

   **_Here is an article how to do it:_**
   [Adding WebDriver to \$PATH](https://www.selenium.dev/documentation/en/webdriver/driver_requirements/)

   For `Edge` put `webdriver.exe` to _src->test->java->resources_

   Nextly add **pathToEdgeDriver = /scr/test/java/resources/msedgedriver.exe** to `application-properties.yaml`
