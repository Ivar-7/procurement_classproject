package com.example.procurement.features.purchaseorders.servlet;

import com.example.procurement.features.purchaseorders.model.PurchaseOrder;
import com.example.procurement.features.purchaseorders.service.PurchaseOrderService;
import com.example.procurement.shared.util.ParamUtils;
import com.example.procurement.shared.util.ServletMessageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/purchase-orders")
public class PurchaseOrderServlet extends HttpServlet {

    private final PurchaseOrderService purchaseOrderService = new PurchaseOrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("purchaseOrders", purchaseOrderService.getAllPurchaseOrders());
        request.getRequestDispatcher("/WEB-INF/views/purchase-orders/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setPoNumber(ParamUtils.requireText(request.getParameter("poNumber"), "PO number"));
            purchaseOrder.setReqId(ParamUtils.toInteger(request.getParameter("reqId")));
            purchaseOrder.setQuoteId(ParamUtils.toInteger(request.getParameter("quoteId")));
            purchaseOrder.setSupplierId(ParamUtils.requireInteger(request.getParameter("supplierId"), "Supplier ID"));
            purchaseOrder.setDeptId(ParamUtils.requireInteger(request.getParameter("deptId"), "Department ID"));
            purchaseOrder.setOrderDate(ParamUtils.trimToNull(request.getParameter("orderDate")));
            purchaseOrder.setDeliveryDate(ParamUtils.trimToNull(request.getParameter("deliveryDate")));
            purchaseOrder.setSubtotal(ParamUtils.toBigDecimal(request.getParameter("subtotal")));
            purchaseOrder.setTaxAmount(ParamUtils.toBigDecimal(request.getParameter("taxAmount")));
            purchaseOrder.setTotalAmount(ParamUtils.toBigDecimal(request.getParameter("totalAmount")));
            purchaseOrder.setStatus(ParamUtils.trimToNull(request.getParameter("status")));
            purchaseOrder.setNotes(ParamUtils.trimToNull(request.getParameter("notes")));
            purchaseOrder.setCreatedBy(ParamUtils.trimToNull(request.getParameter("createdBy")));

            purchaseOrderService.createPurchaseOrder(purchaseOrder);
            ServletMessageUtils.redirectWithMessage(request, response, "/purchase-orders", "success",
                "Purchase order created successfully.");

        } catch (RuntimeException ex) {
            ServletMessageUtils.redirectWithMessage(request, response, "/purchase-orders", "error", ex.getMessage());
        }
    }
}
