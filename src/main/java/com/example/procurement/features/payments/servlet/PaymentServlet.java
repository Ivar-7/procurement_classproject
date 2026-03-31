package com.example.procurement.features.payments.servlet;

import com.example.procurement.features.payments.service.PaymentService;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payments/*")
public class PaymentServlet extends HttpServlet {

    private final PaymentService paymentService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if ("/form".equals(request.getPathInfo())) {
            request.getRequestDispatcher("/WEB-INF/views/payments/form.jsp").forward(request, response);
            return;
        }

        request.setAttribute("payments", paymentService.getAllPayments());
        request.getRequestDispatcher("/WEB-INF/views/payments/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Placeholder: implement payment form processing here when ready.
        ServletMessageUtils.redirectWithMessage(request, response, "/payments/form", "error",
            "Implement payment doPost here.");
    }
}
