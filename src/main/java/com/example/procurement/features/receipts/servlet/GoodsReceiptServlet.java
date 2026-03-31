package com.example.procurement.features.receipts.servlet;

import com.example.procurement.features.receipts.model.GoodsReceipt;
import com.example.procurement.features.receipts.service.GoodsReceiptService;
import com.example.procurement.shared.util.ParamUtils;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods-receipts")
public class GoodsReceiptServlet extends HttpServlet {

    private final GoodsReceiptService goodsReceiptService = new GoodsReceiptService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("goodsReceipts", goodsReceiptService.getAllGoodsReceipts());
        request.getRequestDispatcher("/WEB-INF/views/goods-receipts/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            GoodsReceipt goodsReceipt = new GoodsReceipt();
            goodsReceipt.setGrnNumber(ParamUtils.requireText(request.getParameter("grnNumber"), "GRN number"));
            goodsReceipt.setPoId(ParamUtils.requireInteger(request.getParameter("poId"), "PO ID"));
            goodsReceipt.setReceiptDate(ParamUtils.trimToNull(request.getParameter("receiptDate")));
            goodsReceipt.setReceivedBy(ParamUtils.trimToNull(request.getParameter("receivedBy")));
            goodsReceipt.setDeliveryNoteRef(ParamUtils.trimToNull(request.getParameter("deliveryNoteRef")));
            goodsReceipt.setTotalItems(ParamUtils.toInteger(request.getParameter("totalItems")));
            goodsReceipt.setStatus(ParamUtils.trimToNull(request.getParameter("status")));
            goodsReceipt.setNotes(ParamUtils.trimToNull(request.getParameter("notes")));

            goodsReceiptService.createGoodsReceipt(goodsReceipt);
            ServletMessageUtils.redirectWithMessage(request, response, "/goods-receipts", "success",
                "Goods receipt created successfully.");

        } catch (RuntimeException ex) {
            ServletMessageUtils.redirectWithMessage(request, response, "/goods-receipts", "error", ex.getMessage());
        }
    }
}
