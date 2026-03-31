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
        String pathInfo = request.getPathInfo();

        if ("/delete".equals(pathInfo)) {
            // Instruction: read reqId from request.getParameter, parse it, call delete, then redirect.
            ServletMessageUtils.redirectWithMessage(request, response, "/requisitions", "error",
                "Implement POST /requisitions/delete here: request.getParameter(\"reqId\") -> Integer.parseInt(...) -> requisitionService.deleteRequisition(...), then redirect to /requisitions."
            );
            return;
        }

        // Instruction: read form fields with request.getParameter, map to Requisition, then create or update.
        ServletMessageUtils.redirectWithMessage(request, response, "/requisitions/form", "error",
            "Implement POST /requisitions here: map request.getParameter values to Requisition; if reqId is blank call requisitionService.createRequisition(requisition), else set reqId and call requisitionService.updateRequisition(requisition), then redirect."
        );
    }
}
