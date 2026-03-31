package com.example.procurement.features.quotations.servlet;

import com.example.procurement.features.quotations.service.QuotationService;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/quotations/*")
public class QuotationServlet extends HttpServlet {

    private final QuotationService quotationService = new QuotationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if ("/form".equals(request.getPathInfo())) {
            request.getRequestDispatcher("/WEB-INF/views/quotations/form.jsp").forward(request, response);
            return;
        }

        request.setAttribute("quotations", quotationService.getAllQuotations());
        request.getRequestDispatcher("/WEB-INF/views/quotations/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Placeholder: implement quotation form processing here when ready.
        ServletMessageUtils.redirectWithMessage(request, response, "/quotations/form", "error",
            "Implement quotation doPost here.");
    }
}
