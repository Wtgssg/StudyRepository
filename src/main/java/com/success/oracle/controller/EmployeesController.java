package com.success.oracle.controller;

import com.success.oracle.entity.Employee;
import com.success.oracle.exception.CustomException;
import com.success.oracle.service.EmployeesService;
import com.success.oracle.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeesController {
    @Autowired
    EmployeesService employeesService;

    @ResponseBody
    @RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
    public Result<List<Employee>> getEmployees(){
        return Result.ok(employeesService.getEmployees());
    }

    @RequestMapping(value = "/exportEmployees",method = RequestMethod.POST)
    public void exportEmployees(HttpServletResponse response) {
        try {
            this.employeesService.exportExcel(response);
        } catch (CustomException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
