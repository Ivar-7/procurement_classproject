package com.example.procurement.features.purchaseorders.servlet;

import com.example.procurement.features.purchaseorders.service.PurchaseOrderService;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/purchase-orders/*")
public class PurchaseOrderServlet extends HttpServlet {

    private final PurchaseOrderService purchaseOrderService = new PurchaseOrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if ("/form".equals(request.getPathInfo())) {
            request.getRequestDispatcher("/WEB-INF/views/purchase-orders/form.jsp").forward(request, response);
            return;
        }

        request.setAttribute("purchaseOrders", purchaseOrderService.getAllPurchaseOrders());
        request.getRequestDispatcher("/WEB-INF/views/purchase-orders/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if ("/delete".equals(pathInfo)) {
            // Instruction: read poId from request.getParameter, parse it, call delete, then redirect.
            ServletMessageUtils.redirectWithMessage(request, response, "/purchase-orders", "error",
                "Implement POST /purchase-orders/delete here: request.getParameter(\"poId\") -> Integer.parseInt(...) -> purchaseOrderService.deletePurchaseOrder(...), then redirect to /purchase-orders."
            );
            return;
        }

        // Instruction: read form fields with request.getParameter, map to PurchaseOrder, then create or update.
        ServletMessageUtils.redirectWithMessage(request, response, "/purchase-orders/form", "error",
            "Implement POST /purchase-orders here: map request.getParameter values to PurchaseOrder; if poId is blank call purchaseOrderService.createPurchaseOrder(purchaseOrder), else set poId and call purchaseOrderService.updatePurchaseOrder(purchaseOrder), then redirect."
        );
    }
}
