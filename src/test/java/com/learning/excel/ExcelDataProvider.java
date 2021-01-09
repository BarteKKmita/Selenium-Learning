package com.learning.excel;

import static com.learning.excel.ExcelTest.EXCEL_FILE_NAME;

import com.learning.browsers.Browser;
import com.learning.configuration.PropertiesReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider {

  private SheetWorkbookHandler sheetWorkbookHandler;
  private WebDriver driver;

  @BeforeClass
  void setUp() throws IOException {
    try (FileInputStream excelFile = new FileInputStream(new File(EXCEL_FILE_NAME))) {
      sheetWorkbookHandler = new SheetWorkbookHandler(new XSSFWorkbook(excelFile).getSheetAt(0));
    }
  }

  @BeforeSuite
  void driverSetUp() {
    driver = new Browser( new PropertiesReader()).getWebDriver();
  }

  @Test(dataProvider = "firstDataProvider")
  void shouldPutDataIntoWebsite(String username, String password) throws InterruptedException {
    driver.get("https://opensource-demo.orangehrmlive.com/");
    driver.findElement(By.name("txtUsername")).sendKeys(username);
    driver.findElement(By.name("txtPassword")).sendKeys(username);
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
  }

  @DataProvider(name = "firstDataProvider")
  private Object[][] getData() {
    int rowCount = sheetWorkbookHandler.getRowCount();
    int cellCount = sheetWorkbookHandler.getColumnCountInRow(1);
    Object[][] data = new Object[rowCount][cellCount];
    for (int i = 0; i < rowCount; i++) {
      for (int j = 0; j < cellCount; j++) {
        data[i][j] = sheetWorkbookHandler.getCellValue(i, j);
      }
    }
    return data;
  }

  @AfterClass(alwaysRun = true)
  void tearDown() {
    driver.close();
    driver.quit();
  }
}
