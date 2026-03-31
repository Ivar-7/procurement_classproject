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
        String pathInfo = request.getPathInfo();

        if ("/delete".equals(pathInfo)) {
            // Instruction: read paymentId from request.getParameter, parse it, call delete, then redirect.
            ServletMessageUtils.redirectWithMessage(request, response, "/payments", "error",
                "Implement POST /payments/delete here: request.getParameter(\"paymentId\") -> Integer.parseInt(...) -> paymentService.deletePayment(...), then redirect to /payments."
            );
            return;
        }

        // Instruction: read form fields with request.getParameter, map to Payment, then create or update.
        ServletMessageUtils.redirectWithMessage(request, response, "/payments/form", "error",
            "Implement POST /payments here: map request.getParameter values to Payment; if paymentId is blank call paymentService.createPayment(payment), else set paymentId and call paymentService.updatePayment(payment), then redirect."
        );
    }
}
