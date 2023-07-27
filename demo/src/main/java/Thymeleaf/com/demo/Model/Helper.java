package Thymeleaf.com.demo.Model;

import Thymeleaf.com.demo.JPAEntity.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public class Helper {

    public static String [] HEADERS={
            "Id",
            "Name",
            "Email",
            "Department"
    };

    public static String SHEET_NAME = "Employees_Data";

    public static ByteArrayInputStream dataToExcel(List<Employee> list){

        try{
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out  = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);

            for(int i=0;i<HEADERS.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);

            }

            int rowIndex =1;
            for(Employee e:list){
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(e.getId());
                dataRow.createCell(1).setCellValue(e.getName());
                dataRow.createCell(2).setCellValue(e.getEmail());
                dataRow.createCell(3).setCellValue(e.getDepartment());
            }

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());

        }

        catch(IOException e){
            e.printStackTrace();
        }

       return null;
    }
}
