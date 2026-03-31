package com.example.procurement.features.receipts.servlet;

import com.example.procurement.features.receipts.service.GoodsReceiptService;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods-receipts/*")
public class GoodsReceiptServlet extends HttpServlet {

    private final GoodsReceiptService goodsReceiptService = new GoodsReceiptService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if ("/form".equals(request.getPathInfo())) {
            request.getRequestDispatcher("/WEB-INF/views/goods-receipts/form.jsp").forward(request, response);
            return;
        }

        request.setAttribute("goodsReceipts", goodsReceiptService.getAllGoodsReceipts());
        request.getRequestDispatcher("/WEB-INF/views/goods-receipts/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Placeholder: implement goods receipt form processing here when ready.
        ServletMessageUtils.redirectWithMessage(request, response, "/goods-receipts/form", "error",
            "Implement goods receipt doPost here.");
    }
}
