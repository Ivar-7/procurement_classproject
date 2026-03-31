package com.example.procurement.features.requisitions.servlet;

import com.example.procurement.features.requisitions.service.RequisitionService;
import com.example.procurement.shared.util.ServletMessageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requisitions/*")
public class RequisitionServlet extends HttpServlet {

    private final RequisitionService requisitionService = new RequisitionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if ("/form".equals(request.getPathInfo())) {
            request.getRequestDispatcher("/WEB-INF/views/requisitions/form.jsp").forward(request, response);
            return;
        }

        request.setAttribute("requisitions", requisitionService.getAllRequisitions());
        request.getRequestDispatcher("/WEB-INF/views/requisitions/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Placeholder: implement requisition form processing here when ready.
        ServletMessageUtils.redirectWithMessage(request, response, "/requisitions/form", "error",
            "Implement requisition doPost here.");
    }
}
