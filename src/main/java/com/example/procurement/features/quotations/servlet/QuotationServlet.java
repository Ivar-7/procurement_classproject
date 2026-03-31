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
        String pathInfo = request.getPathInfo();

        if ("/delete".equals(pathInfo)) {
            // Instruction: read quoteId from request.getParameter, parse it, call delete, then redirect.
            ServletMessageUtils.redirectWithMessage(request, response, "/quotations", "error",
                "Implement POST /quotations/delete here: request.getParameter(\"quoteId\") -> Integer.parseInt(...) -> quotationService.deleteQuotation(...), then redirect to /quotations."
            );
            return;
        }

        // Instruction: read form fields with request.getParameter, map to Quotation, then create or update.
        ServletMessageUtils.redirectWithMessage(request, response, "/quotations/form", "error",
            "Implement POST /quotations here: map request.getParameter values to Quotation; if quoteId is blank call quotationService.createQuotation(quotation), else set quoteId and call quotationService.updateQuotation(quotation), then redirect."
        );
    }
}
