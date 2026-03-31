package com.example.procurement.features.payments.servlet;

import com.example.procurement.features.payments.model.Payment;
import com.example.procurement.features.payments.service.PaymentService;
import com.example.procurement.shared.util.ParamUtils;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payments")
public class PaymentServlet extends HttpServlet {

    private final PaymentService paymentService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("payments", paymentService.getAllPayments());
        request.getRequestDispatcher("/WEB-INF/views/payments/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Payment payment = new Payment();
            payment.setPaymentRef(ParamUtils.requireText(request.getParameter("paymentRef"), "Payment reference"));
            payment.setPoId(ParamUtils.requireInteger(request.getParameter("poId"), "PO ID"));
            payment.setGrnId(ParamUtils.toInteger(request.getParameter("grnId")));
            payment.setInvoiceNumber(ParamUtils.trimToNull(request.getParameter("invoiceNumber")));
            payment.setInvoiceAmount(ParamUtils.toBigDecimal(request.getParameter("invoiceAmount")));
            payment.setAmountPaid(ParamUtils.toBigDecimal(request.getParameter("amountPaid")));
            payment.setPaymentDate(ParamUtils.trimToNull(request.getParameter("paymentDate")));
            payment.setPaymentMethod(ParamUtils.trimToNull(request.getParameter("paymentMethod")));
            payment.setTransactionRef(ParamUtils.trimToNull(request.getParameter("transactionRef")));
            payment.setStatus(ParamUtils.trimToNull(request.getParameter("status")));
            payment.setProcessedBy(ParamUtils.trimToNull(request.getParameter("processedBy")));

            paymentService.createPayment(payment);
            ServletMessageUtils.redirectWithMessage(request, response, "/payments", "success",
                "Payment created successfully.");

        } catch (RuntimeException ex) {
            ServletMessageUtils.redirectWithMessage(request, response, "/payments", "error", ex.getMessage());
        }
    }
}
