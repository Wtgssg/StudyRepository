package com.success.oracle.service;

import com.success.oracle.entity.Employee;
import com.success.oracle.exception.CustomException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface EmployeesService {
    List<Employee> getEmployees();

    void exportExcel(HttpServletResponse response) throws CustomException, IOException;
}
