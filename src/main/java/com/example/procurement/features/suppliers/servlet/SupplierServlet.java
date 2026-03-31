package com.example.procurement.features.suppliers.servlet;

import com.example.procurement.features.suppliers.model.Supplier;
import com.example.procurement.features.suppliers.service.SupplierService;
import com.example.procurement.shared.util.ParamUtils;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/suppliers")
public class SupplierServlet extends HttpServlet {

    private final SupplierService supplierService = new SupplierService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("suppliers", supplierService.getAllSuppliers());
        request.getRequestDispatcher("/WEB-INF/views/suppliers/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Supplier supplier = new Supplier();
            supplier.setCompanyName(ParamUtils.requireText(request.getParameter("companyName"), "Company name"));
            supplier.setContactPerson(ParamUtils.trimToNull(request.getParameter("contactPerson")));
            supplier.setPhone(ParamUtils.trimToNull(request.getParameter("phone")));
            supplier.setEmail(ParamUtils.trimToNull(request.getParameter("email")));
            supplier.setCategory(ParamUtils.trimToNull(request.getParameter("category")));
            supplier.setStatus(ParamUtils.trimToNull(request.getParameter("status")));

            supplierService.createSupplier(supplier);
            ServletMessageUtils.redirectWithMessage(request, response, "/suppliers", "success",
                "Supplier created successfully.");

        } catch (RuntimeException ex) {
            ServletMessageUtils.redirectWithMessage(request, response, "/suppliers", "error", ex.getMessage());
        }
    }
}
