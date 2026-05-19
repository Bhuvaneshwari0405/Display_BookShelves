package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public void writeData(List<String>data, String fileName) throws IOException {
		
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("Results");
		
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("S.No");
		header.createCell(1).setCellValue("Output");
		
		for(int i = 0; i < data.size(); i++) {
			Row row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(i + 1);
			row.createCell(1).setCellValue(data.get(i));
		}

		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		
		FileOutputStream fos = new FileOutputStream(fileName);
		wb.write(fos);
		wb.close();
		fos.close();
	}
}
