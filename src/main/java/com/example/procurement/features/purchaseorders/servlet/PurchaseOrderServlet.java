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
        // Placeholder: implement purchase order form processing here when ready.
        ServletMessageUtils.redirectWithMessage(request, response, "/purchase-orders/form", "error",
            "Implement purchase order doPost here.");
    }
}
