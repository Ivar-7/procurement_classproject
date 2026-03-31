package com.example.procurement.features.departments.servlet;

import com.example.procurement.features.departments.model.Department;
import com.example.procurement.features.departments.service.DepartmentService;
import com.example.procurement.shared.util.ParamUtils;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/departments/*")
public class DepartmentServlet extends HttpServlet {

    private final DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();

            if (pathInfo == null || "/".equals(pathInfo)) {
                loadListPage(request, response);
                return;
            }

            if ("/form".equals(pathInfo)) {
                loadFormPage(request, response);
                return;
            }

            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (RuntimeException ex) {
            ServletMessageUtils.redirectWithMessage(request, response, "/departments", "error", ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if ("/delete".equals(request.getPathInfo())) {
                deleteDepartment(request, response);
                return;
            }

            saveDepartment(request, response);

        } catch (RuntimeException ex) {
            ServletMessageUtils.redirectWithMessage(request, response, "/departments", "error", ex.getMessage());
        }
    }

    private void loadListPage(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Integer editId = ParamUtils.toInteger(request.getParameter("editId"));
        if (editId != null) {
            Department department = departmentService.getDepartmentById(editId);
            if (department == null) {
                ServletMessageUtils.redirectWithMessage(
                    request,
                    response,
                    "/departments",
                    "error",
                    "Department not found."
                );
                return;
            }
            request.setAttribute("formDepartment", department);
        }

        request.setAttribute("departments", departmentService.getAllDepartments());
        request.getRequestDispatcher("/WEB-INF/views/departments/list.jsp").forward(request, response);
    }

    private void loadFormPage(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Integer deptId = ParamUtils.toInteger(request.getParameter("id"));
        if (deptId != null) {
            Department department = departmentService.getDepartmentById(deptId);
            if (department == null) {
                ServletMessageUtils.redirectWithMessage(
                    request,
                    response,
                    "/departments",
                    "error",
                    "Department not found."
                );
                return;
            }
            request.setAttribute("formDepartment", department);
        }

        request.getRequestDispatcher("/WEB-INF/views/departments/form.jsp").forward(request, response);
    }

    private void saveDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer deptId = ParamUtils.toInteger(request.getParameter("deptId"));

        Department department = new Department();
        department.setDeptId(deptId);
        department.setDeptName(ParamUtils.requireText(request.getParameter("deptName"), "Department name"));
        department.setBudgetCode(ParamUtils.trimToNull(request.getParameter("budgetCode")));
        department.setAnnualBudget(ParamUtils.toBigDecimal(request.getParameter("annualBudget")));
        department.setHeadName(ParamUtils.trimToNull(request.getParameter("headName")));
        department.setEmail(ParamUtils.trimToNull(request.getParameter("email")));

        if (deptId == null) {
            departmentService.createDepartment(department);
            ServletMessageUtils.redirectWithMessage(
                request,
                response,
                "/departments",
                "success",
                "Department created successfully."
            );
            return;
        }

        boolean updated = departmentService.updateDepartment(department);
        if (!updated) {
            ServletMessageUtils.redirectWithMessage(request, response, "/departments", "error", "Department not found.");
            return;
        }

        ServletMessageUtils.redirectWithMessage(
            request,
            response,
            "/departments",
            "success",
            "Department updated successfully."
        );
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer deptId = ParamUtils.requireInteger(request.getParameter("deptId"), "Department id");
        boolean deleted = departmentService.deleteDepartment(deptId);

        if (!deleted) {
            ServletMessageUtils.redirectWithMessage(request, response, "/departments", "error", "Department not found.");
            return;
        }

        ServletMessageUtils.redirectWithMessage(
            request,
            response,
            "/departments",
            "success",
            "Department deleted successfully."
        );
    }
}
