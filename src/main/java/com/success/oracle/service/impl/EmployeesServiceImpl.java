package com.success.oracle.service.impl;

import com.success.oracle.entity.Employee;
import com.success.oracle.exception.CustomException;
import com.success.oracle.repository.EmployeesReposrity;
import com.success.oracle.service.EmployeesService;
import com.success.oracle.util.ColumnConfig;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class EmployeesServiceImpl implements EmployeesService {
    @Autowired
    EmployeesReposrity employeesReposrity;

    @Override
    public List<Employee> getEmployees() {
        return employeesReposrity.getEmployees();
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws CustomException, UnsupportedEncodingException {
        List<Employee> dataList = employeesReposrity.getEmployees();

        if (dataList == null || dataList.isEmpty()) {
            throw new CustomException("当前页面暂无数据，无法导出！");
        }
        List<ColumnConfig<Employee>> configs = new ArrayList<>();
        configs.add(new ColumnConfig<>("序号", 5, new Function<Employee, Object>() {
            private int count = 1;
            @Override
            public Object apply(Employee employee) {
                return count++;
            }
        }));
        configs.add(new ColumnConfig<>("ID", 10, Employee::getId));
        configs.add(new ColumnConfig<>("Last Name", 15, Employee::getLastName));
        configs.add(new ColumnConfig<>("First Name", 15, Employee::getFirstName));
        configs.add(new ColumnConfig<>("Email", 20, Employee::getEmail));
        configs.add(new ColumnConfig<>("Phone", 15, Employee::getPhoneNumber));
        configs.add(new ColumnConfig<>("Hire Date", 15, Employee::getHireDate));
        configs.add(new ColumnConfig<>("Salary", 15, Employee::getSalary));
        configs.add(new ColumnConfig<>("Commission", 15, Employee::getCommissionPct));
        configs.add(new ColumnConfig<>("Manager", 15, Employee::getManager));

        doExport(response,"1",configs,dataList);
    }

    public <T> void doExport(HttpServletResponse response, String tableName,List<ColumnConfig<T>> configs, List<T> dataList) throws UnsupportedEncodingException {

        response.reset();
        try (OutputStream out = response.getOutputStream();
             Workbook workbook = new XSSFWorkbook()) {

            String fileName = URLEncoder.encode(tableName, "UTF-8") + ".xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("APPLICATION/OCTET-STREAM");
            //设置BigDecimal格式
            DecimalFormat df = new DecimalFormat("#,##0.00");
            //设置统一日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Sheet sheet = workbook.createSheet("sheet1");

            Row headerRow = sheet.createRow(0);
            for (int colIndex = 0 ; colIndex < configs.size() ; colIndex++){
                ColumnConfig<T> config = configs.get(colIndex);
                sheet.setColumnWidth(colIndex, config.getWidth() * 256);

                Cell headerCell = headerRow.createCell(colIndex);
                headerCell.setCellValue(config.getTitle());
            }

            for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
                T entity = dataList.get(rowIndex);
                Row row = sheet.createRow(rowIndex + 1);
                for (int colIndex = 0; colIndex < configs.size(); colIndex++) {
                    ColumnConfig<T> config = configs.get(colIndex);
                    Cell cell = row.createCell(colIndex);

                    Object value = config.getValue(entity);
                    if (value == null) {
                        cell.setCellValue("");
                    } else if (value instanceof BigDecimal){
                        cell.setCellValue(df.format(((BigDecimal) value).setScale(2, RoundingMode.HALF_UP)));
                    } else if (value instanceof Date) {
                        cell.setCellValue(dateFormat.format(value));
                    } else {
                        cell.setCellValue(value.toString());
                    }

                }
            }

            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
            response.setHeader("OUTCODE", URLEncoder.encode("导出异常！", "UTF-8"));
        }
    }

    public void setCellStyle(){

    }
}
