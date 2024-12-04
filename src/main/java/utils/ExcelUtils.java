package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static String[] getLoginData(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet("Login");
            Row row = sheet.getRow(1); // Primera fila con datos
            return new String[]{
                    row.getCell(0).getStringCellValue(),
                    row.getCell(1).getStringCellValue()
            };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String[]> getProducts(String filePath) {
        List<String[]> products = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet("Productos");
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rows; i++) { // Saltar encabezados

                Row row = sheet.getRow(i);
                String productName = row.getCell(0).getStringCellValue();
                int quantityInt = (int) row.getCell(1).getNumericCellValue();  // Obtener el valor numérico de la celda
                String quantity = String.valueOf(quantityInt);
                products.add(new String[]{productName, quantity});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

}
