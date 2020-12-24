package com.learning;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learning.pages.ChromeDriverGenerator;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ExtentReportsTest {

  private ExtentReports extentReports;
  private WebDriver chromeDriver;

  @BeforeSuite
  public void setUp() {
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
    extentReports = new ExtentReports();
    extentReports.attachReporter(htmlReporter);
    chromeDriver = new ChromeDriverGenerator().getChromeDriver();
  }

  @SneakyThrows
  @Test
  public void shouldOpenGoogle() {
    ExtentTest testCase = extentReports.createTest("Extent report exploration", "Creating first extent report for learning purpose.");
    testCase.addScreenCaptureFromPath("screenshot.png");
    chromeDriver.get("https://google.com");
    testCase.log(Status.INFO, "This step shows usage of log(status, details)");
    testCase.info("This step shows usage of info(details)");
    testCase.pass("Some details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
  }

  @SneakyThrows
  @Test
  public void shouldFailTest() {
    ExtentTest testCase = extentReports.createTest("Extent report exploration", "Creating first extent report for learning purpose.");
    testCase.log(Status.INFO, "This step shows usage of log(status, details)");
    testCase.info("This step shows usage of info(details)");
    testCase.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
    testCase.addScreenCaptureFromPath("screenshot.png");
  }

  @AfterSuite
  public void tearDown() {
    extentReports.flush();
  }
}
