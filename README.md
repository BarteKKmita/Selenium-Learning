## How to run tests on your computer:

1) There are two kind of browsers that you can perform tests against. <br />
   * Chrome
   * Edge <br />
   <br />

   Please choose one of following and download corresponding ` WebDriver ` from the internet. <br />
   If you decided to use `Chrome` put directory with `WebDriver` to `$PATH` as env variable. <br />

   ***Here is an article how to do it:***
   [Adding WebDriver to $PATH](https://www.selenium.dev/documentation/en/webdriver/driver_requirements/) <br />

   For `Edge` put `webdriver.exe` to *src->test->java->resources* <br />

   Nextly add **pathToEdgeDriver = /scr/test/java/resources/msedgedriver.exe** to `application-properties.yaml`