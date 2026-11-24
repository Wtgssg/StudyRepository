package com.success.oracle.service.impl;

import com.success.oracle.entity.Employee;
import com.success.oracle.exception.CustomException;
import com.success.oracle.repository.EmployeesReposrity;
import com.success.oracle.service.EmployeesService;
import com.success.oracle.util.ColumnConfig;
import com.success.oracle.util.ExcelExporter;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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
    public void exportExcel(HttpServletResponse response) throws CustomException, IOException {
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
        ExcelExporter.<Employee>builder()
                .tableName("员工数据表")
                .configs(configs)
                .dataList(dataList)
                // .headerStyle(customHeaderStyle)
                // .contentStyle(customContentStyle)
                .export(response);
    }
}
