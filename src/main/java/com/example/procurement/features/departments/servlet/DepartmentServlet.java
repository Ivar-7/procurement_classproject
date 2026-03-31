package com.example.procurement.features.departments.servlet;

import com.example.procurement.features.departments.model.Department;
import com.example.procurement.features.departments.service.DepartmentService;
import com.example.procurement.shared.util.ParamUtils;
import com.example.procurement.shared.util.ServletMessageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {

    private final DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("departments", departmentService.getAllDepartments());
        request.getRequestDispatcher("/WEB-INF/views/departments/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Department department = new Department();
            department.setDeptName(ParamUtils.requireText(request.getParameter("deptName"), "Department name"));
            department.setBudgetCode(ParamUtils.trimToNull(request.getParameter("budgetCode")));
            department.setAnnualBudget(ParamUtils.toBigDecimal(request.getParameter("annualBudget")));
            department.setHeadName(ParamUtils.trimToNull(request.getParameter("headName")));
            department.setEmail(ParamUtils.trimToNull(request.getParameter("email")));

            departmentService.createDepartment(department);
            ServletMessageUtils.redirectWithMessage(request, response, "/departments", "success",
                "Department created successfully.");

        } catch (RuntimeException ex) {
            ServletMessageUtils.redirectWithMessage(request, response, "/departments", "error", ex.getMessage());
        }
    }
}
