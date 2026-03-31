package com.example.procurement.features.requisitions.service;

import com.example.procurement.features.requisitions.dao.RequisitionDao;
import com.example.procurement.features.requisitions.model.Requisition;

import java.util.List;

public class RequisitionService {

    private final RequisitionDao requisitionDao = new RequisitionDao();

    public List<Requisition> getAllRequisitions() {
        return requisitionDao.findAll();
    }

    public void createRequisition(Requisition requisition) {
        requisitionDao.create(requisition);
    }
}
