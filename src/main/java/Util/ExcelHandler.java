package Util;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHandler {
    private final File file;

    public ExcelHandler(String filePath) {
        this.file = new File(filePath);
    }

    public List<List<String>> getFileContent() throws IOException {
        List<List<String>> fileContent = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            List<String> rowData = new ArrayList<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = getCellValue(cell);
                rowData.add(cellValue);
            }

            fileContent.add(rowData);
        }

        workbook.close();
        fileInputStream.close();

        return fileContent;    }
    
    private String getCellValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        } else {
            return "";
        }
    }

    //TODO: ADD DELETE METHOD
    
    
	public static void main(String[] args) {
		String path = "C:\\finalProjectTest\\src\\main\\reasorces\\general-reports.xlsx";
		ExcelHandler fileHandler = new ExcelHandler(path);

        try {
            List<List<String>> fileContent = fileHandler.getFileContent();
            System.out.println("File Content:");
            for (List<String> row : fileContent) {
                for (String cellValue : row) {
                    System.out.print(cellValue + "\t");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}



