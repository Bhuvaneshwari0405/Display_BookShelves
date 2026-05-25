package utils;
 
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.testng.*;
 
import java.io.*;
import java.util.*;
 
public class ExcelReportListener implements ITestListener {
 
    private List<String[]> testResults = new ArrayList<>();
    private int serialNo = 1;
 
    @Override
    public void onTestSuccess(ITestResult result) {
        testResults.add(new String[]{
            String.valueOf(serialNo++),
            result.getTestClass().getRealClass().getSimpleName(),
            result.getName(),
            "PASS"
        });
    }
 
    @Override
    public void onTestFailure(ITestResult result) {
        testResults.add(new String[]{
            String.valueOf(serialNo++),
            result.getTestClass().getRealClass().getSimpleName(),
            result.getName(),
            "FAIL"
        });
    }
 
    @Override
    public void onTestSkipped(ITestResult result) {
        testResults.add(new String[]{
            String.valueOf(serialNo++),
            result.getTestClass().getRealClass().getSimpleName(),
            result.getName(),
            "SKIP"
        });
    }
 
    @Override
    public void onFinish(ITestContext context) {
        writeToExcel();
    }
 
    private void writeToExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Test Results");
 
        
 
        // Header Row
        String[] columns = {"S.No", "Class Name", "Test Case", "Status"};
        Row header = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
        }
 
        // Data Rows
        int rowNum = 1;
        for (String[] result : testResults) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < result.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(result[i]);
            }
        }
 
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
 
        try (FileOutputStream fos = new FileOutputStream("Output-Data/TestReport.xlsx")) {
            workbook.write(fos);
            workbook.close();
            System.out.println("Excel Report Generated: Output-Data/TestReport.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}