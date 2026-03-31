package com.example.procurement.features.requisitions.service;

import com.example.procurement.features.requisitions.dao.RequisitionDao;
import com.example.procurement.features.requisitions.model.Requisition;

import java.util.List;

public class RequisitionService {

    private final RequisitionDao requisitionDao = new RequisitionDao();

    public List<Requisition> getAllRequisitions() {
        return requisitionDao.findAll();
    }

    public Requisition getRequisitionById(int reqId) {
        return requisitionDao.findById(reqId);
    }

    public void createRequisition(Requisition requisition) {
        requisitionDao.create(requisition);
    }

    public boolean updateRequisition(Requisition requisition) {
        return requisitionDao.update(requisition);
    }

    public boolean deleteRequisition(int reqId) {
        return requisitionDao.deleteById(reqId);
    }
}
