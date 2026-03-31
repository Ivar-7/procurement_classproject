package com.example.procurement.features.items.servlet;

import com.example.procurement.features.items.service.ItemService;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/items/*")
public class ItemServlet extends HttpServlet {

    private final ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if ("/form".equals(request.getPathInfo())) {
            request.getRequestDispatcher("/WEB-INF/views/items/form.jsp").forward(request, response);
            return;
        }

        request.setAttribute("items", itemService.getAllItems());
        request.getRequestDispatcher("/WEB-INF/views/items/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if ("/delete".equals(pathInfo)) {
            // Instruction: read itemId from request.getParameter, parse it, call delete, then redirect.
            ServletMessageUtils.redirectWithMessage(request, response, "/items", "error",
                "Implement POST /items/delete here: request.getParameter(\"itemId\") -> Integer.parseInt(...) -> itemService.deleteItem(...), then redirect to /items."
            );
            return;
        }

        // Instruction: read form fields with request.getParameter, map to Item, then create or update.
        ServletMessageUtils.redirectWithMessage(request, response, "/items/form", "error",
            "Implement POST /items here: map request.getParameter values to Item; if itemId is blank call itemService.createItem(item), else set itemId and call itemService.updateItem(item), then redirect."
        );
    }
}
