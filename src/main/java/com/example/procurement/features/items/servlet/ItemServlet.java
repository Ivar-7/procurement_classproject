package com.example.procurement.features.items.servlet;

import com.example.procurement.features.items.model.Item;
import com.example.procurement.features.items.service.ItemService;
import com.example.procurement.shared.util.ParamUtils;
import com.example.procurement.shared.util.ServletMessageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    private final ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("items", itemService.getAllItems());
        request.getRequestDispatcher("/WEB-INF/views/items/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Item item = new Item();
            item.setItemCode(ParamUtils.requireText(request.getParameter("itemCode"), "Item code"));
            item.setItemName(ParamUtils.requireText(request.getParameter("itemName"), "Item name"));
            item.setCategory(ParamUtils.trimToNull(request.getParameter("category")));
            item.setUnitOfMeasure(ParamUtils.trimToNull(request.getParameter("unitOfMeasure")));
            item.setEstimatedUnitPrice(ParamUtils.toBigDecimal(request.getParameter("estimatedUnitPrice")));
            item.setSupplierId(ParamUtils.toInteger(request.getParameter("supplierId")));
            item.setActive("on".equalsIgnoreCase(request.getParameter("isActive")));

            itemService.createItem(item);
            ServletMessageUtils.redirectWithMessage(request, response, "/items", "success",
                "Item created successfully.");

        } catch (RuntimeException ex) {
            ServletMessageUtils.redirectWithMessage(request, response, "/items", "error", ex.getMessage());
        }
    }
}
