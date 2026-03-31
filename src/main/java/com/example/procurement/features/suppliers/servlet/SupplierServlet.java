package com.example.procurement.features.suppliers.servlet;

import com.example.procurement.features.suppliers.service.SupplierService;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/suppliers/*")
public class SupplierServlet extends HttpServlet {

    private final SupplierService supplierService = new SupplierService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if ("/form".equals(request.getPathInfo())) {
            request.getRequestDispatcher("/WEB-INF/views/suppliers/form.jsp").forward(request, response);
            return;
        }

        request.setAttribute("suppliers", supplierService.getAllSuppliers());
        request.getRequestDispatcher("/WEB-INF/views/suppliers/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if ("/delete".equals(pathInfo)) {
            // Instruction: read supplierId from request.getParameter, parse it, call delete, then redirect.
            ServletMessageUtils.redirectWithMessage(request, response, "/suppliers", "error",
                "Implement POST /suppliers/delete here: request.getParameter(\"supplierId\") -> Integer.parseInt(...) -> supplierService.deleteSupplier(...), then redirect to /suppliers."
            );
            return;
        }

        // Instruction: read form fields with request.getParameter, map to Supplier, then create or update.
        ServletMessageUtils.redirectWithMessage(request, response, "/suppliers/form", "error",
            "Implement POST /suppliers here: map request.getParameter values to Supplier; if supplierId is blank call supplierService.createSupplier(supplier), else set supplierId and call supplierService.updateSupplier(supplier), then redirect."
        );
    }
}
