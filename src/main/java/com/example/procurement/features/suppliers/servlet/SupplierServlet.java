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
        // Placeholder: implement supplier form processing here when ready.
        ServletMessageUtils.redirectWithMessage(request, response, "/suppliers/form", "error",
            "Implement supplier doPost here.");
    }
}
