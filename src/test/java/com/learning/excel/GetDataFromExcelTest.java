package com.learning.excel;

import static com.learning.excel.ExcelTest.EXCEL_FILE_NAME;
import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetDataFromExcelTest {

  private SheetWorkbookHandler sheetWorkbookHandler;

  @BeforeClass
  void setUp() throws IOException {
    try (FileInputStream excelFile = new FileInputStream(new File(EXCEL_FILE_NAME))) {
      sheetWorkbookHandler = new SheetWorkbookHandler(new XSSFWorkbook(excelFile).getSheetAt(0));
    }
  }

  @Test
  void shouldGetRowCount() throws IOException {
    //Given
    int expectedRowCount = 2;
    //When
    int actualRowCount = sheetWorkbookHandler.getRowCount();
    //Then
    assertEquals(expectedRowCount, actualRowCount);
  }


  @Test
  void shouldGetColumnCount() throws IOException {
    //Given
    int expectedColumnCount = 2;
    //When
    int actualRowCount = sheetWorkbookHandler.getRowCount();
    //Then
    assertEquals(expectedColumnCount, actualRowCount);
  }


  @Test
  void shouldGetCellValue() throws IOException {
    //Given
    var expectedCellValue = "Password";
    int cellNumber = 1;
    int rowNumber = 0;
    //When
    String actualCellValue = sheetWorkbookHandler.getCellValue(rowNumber, cellNumber);
    //Then
    assertEquals(expectedCellValue, actualCellValue);
  }

  @Test
  void shouldThrowWhenGettingNotNumericDataAsNumeric() throws IOException {
    //Given
    int cellNumber = 1;
    int rowNumber = 1;
    //Then
    assertThrows(() -> sheetWorkbookHandler.getCellNumericValue(rowNumber, cellNumber));
  }
}
