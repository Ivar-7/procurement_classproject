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
        // Placeholder: implement item form processing here when ready.
        ServletMessageUtils.redirectWithMessage(request, response, "/items/form", "error",
            "Implement item doPost here.");
    }
}
