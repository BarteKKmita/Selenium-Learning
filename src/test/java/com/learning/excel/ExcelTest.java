package com.learning.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelTest {

  private XSSFWorkbook excelWorkbook;
  public static final String EXCEL_FILE_NAME = "data.xlsx";

  @Test
  void shouldEditExcelFile() throws IOException {
    try (FileInputStream excelFile = new FileInputStream(new File(EXCEL_FILE_NAME))) {
      excelWorkbook = new XSSFWorkbook(excelFile);
      Sheet excelSheet = getFirstSheet();
      printFirstHeader(excelSheet);
      setCellValue(excelSheet.createRow(2));
    }
    saveChangesInExcelFile(excelWorkbook);
  }

  private XSSFSheet getFirstSheet() {
    return excelWorkbook.getSheetAt(0);
  }

  private void printFirstHeader(Sheet excelSheet) {
    System.out.println(excelSheet.getRow(0).getCell(0).getStringCellValue());
  }

  private void setCellValue(Row row) {
    Cell cell = row.createCell(0);
    cell.setCellType(1);
    cell.setCellValue("Just edited excel file!");
    cell.setAsActiveCell();
  }

  private void saveChangesInExcelFile(XSSFWorkbook workbook) throws IOException {
    try (FileOutputStream out = new FileOutputStream(new File("data.xlsx"))) {
      workbook.write(out);
      out.close();
      System.out.println("Excel edited successfully");
    }
  }
}
