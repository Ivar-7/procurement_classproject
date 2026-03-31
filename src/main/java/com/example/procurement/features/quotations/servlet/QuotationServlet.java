package com.example.procurement.features.quotations.servlet;

import com.example.procurement.features.quotations.model.Quotation;
import com.example.procurement.features.quotations.service.QuotationService;
import com.example.procurement.shared.util.ParamUtils;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/quotations")
public class QuotationServlet extends HttpServlet {

    private final QuotationService quotationService = new QuotationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("quotations", quotationService.getAllQuotations());
        request.getRequestDispatcher("/WEB-INF/views/quotations/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Quotation quotation = new Quotation();
            quotation.setQuoteRef(ParamUtils.requireText(request.getParameter("quoteRef"), "Quotation reference"));
            quotation.setReqId(ParamUtils.toInteger(request.getParameter("reqId")));
            quotation.setSupplierId(ParamUtils.requireInteger(request.getParameter("supplierId"), "Supplier ID"));
            quotation.setQuoteDate(ParamUtils.trimToNull(request.getParameter("quoteDate")));
            quotation.setValidityDate(ParamUtils.trimToNull(request.getParameter("validityDate")));
            quotation.setSubtotal(ParamUtils.toBigDecimal(request.getParameter("subtotal")));
            quotation.setTaxAmount(ParamUtils.toBigDecimal(request.getParameter("taxAmount")));
            quotation.setTotalAmount(ParamUtils.toBigDecimal(request.getParameter("totalAmount")));
            quotation.setDeliveryDays(ParamUtils.toInteger(request.getParameter("deliveryDays")));
            quotation.setTerms(ParamUtils.trimToNull(request.getParameter("terms")));
            quotation.setAttachmentUrl(ParamUtils.trimToNull(request.getParameter("attachmentUrl")));
            quotation.setStatus(ParamUtils.trimToNull(request.getParameter("status")));

            quotationService.createQuotation(quotation);
            ServletMessageUtils.redirectWithMessage(request, response, "/quotations", "success",
                "Quotation created successfully.");

        } catch (RuntimeException ex) {
            ServletMessageUtils.redirectWithMessage(request, response, "/quotations", "error", ex.getMessage());
        }
    }
}
