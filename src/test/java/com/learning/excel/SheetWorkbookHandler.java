package com.learning.excel;

import java.io.FileInputStream;
import java.io.IOException;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Getter
public class SheetWorkbookHandler {

  private final Sheet excelSheet;

  public SheetWorkbookHandler(Sheet excelSheet) {
    this.excelSheet = excelSheet;
  }

  int getRowCount() {
    return excelSheet.getLastRowNum();
  }

  int getColumnCountInRow(int rowNumber) {
    return excelSheet.getRow(rowNumber).getLastCellNum();
  }

  public String getCellValue(int rowNumber, int cellNumber) {
    return getCell(rowNumber, cellNumber).getStringCellValue();
  }

  public Double getCellNumericValue(int rowNumber, int cellNumber) throws NumberFormatException {
    return getCell(rowNumber, cellNumber).getNumericCellValue();
  }

  private Cell getCell(int rowNumber, int cellNumber) {
    return excelSheet.getRow(rowNumber).getCell(cellNumber);
  }

  public XSSFWorkbook getWorkbook(FileInputStream excelFile) throws IOException {
    return new XSSFWorkbook(excelFile);
  }

}
