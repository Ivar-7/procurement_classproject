package com.example.procurement.features.requisitions.servlet;

import com.example.procurement.features.requisitions.model.Requisition;
import com.example.procurement.features.requisitions.service.RequisitionService;
import com.example.procurement.shared.util.ParamUtils;
import com.example.procurement.shared.util.ServletMessageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requisitions")
public class RequisitionServlet extends HttpServlet {

    private final RequisitionService requisitionService = new RequisitionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("requisitions", requisitionService.getAllRequisitions());
        request.getRequestDispatcher("/WEB-INF/views/requisitions/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Requisition requisition = new Requisition();
            requisition.setReqNumber(ParamUtils.requireText(request.getParameter("reqNumber"), "Requisition number"));
            requisition.setDeptId(ParamUtils.requireInteger(request.getParameter("deptId"), "Department ID"));
            requisition.setRequestedBy(ParamUtils.trimToNull(request.getParameter("requestedBy")));
            requisition.setRequestDate(ParamUtils.trimToNull(request.getParameter("requestDate")));
            requisition.setJustification(ParamUtils.trimToNull(request.getParameter("justification")));
            requisition.setTotalEstimated(ParamUtils.toBigDecimal(request.getParameter("totalEstimated")));
            requisition.setUrgency(ParamUtils.trimToNull(request.getParameter("urgency")));
            requisition.setStatus(ParamUtils.trimToNull(request.getParameter("status")));
            requisition.setApprovedBy(ParamUtils.trimToNull(request.getParameter("approvedBy")));
            requisition.setApprovedDate(ParamUtils.trimToNull(request.getParameter("approvedDate")));

            requisitionService.createRequisition(requisition);
            ServletMessageUtils.redirectWithMessage(request, response, "/requisitions", "success",
                "Requisition created successfully.");

        } catch (RuntimeException ex) {
            ServletMessageUtils.redirectWithMessage(request, response, "/requisitions", "error", ex.getMessage());
        }
    }
}
