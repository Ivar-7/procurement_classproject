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
        String pathInfo = request.getPathInfo();

        if ("/delete".equals(pathInfo)) {
            // Instruction: read grnId from request.getParameter, parse it, call delete, then redirect.
            ServletMessageUtils.redirectWithMessage(request, response, "/goods-receipts", "error",
                "Implement POST /goods-receipts/delete here: request.getParameter(\"grnId\") -> Integer.parseInt(...) -> goodsReceiptService.deleteGoodsReceipt(...), then redirect to /goods-receipts."
            );
            return;
        }

        // Instruction: read form fields with request.getParameter, map to GoodsReceipt, then create or update.
        ServletMessageUtils.redirectWithMessage(request, response, "/goods-receipts/form", "error",
            "Implement POST /goods-receipts here: map request.getParameter values to GoodsReceipt; if grnId is blank call goodsReceiptService.createGoodsReceipt(goodsReceipt), else set grnId and call goodsReceiptService.updateGoodsReceipt(goodsReceipt), then redirect."
        );
    }
}
