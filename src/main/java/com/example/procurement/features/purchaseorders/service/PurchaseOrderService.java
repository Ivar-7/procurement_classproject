package com.example.procurement.features.purchaseorders.service;

import com.example.procurement.features.purchaseorders.dao.PurchaseOrderDao;
import com.example.procurement.features.purchaseorders.model.PurchaseOrder;

import java.util.List;

public class PurchaseOrderService {

    private final PurchaseOrderDao purchaseOrderDao = new PurchaseOrderDao();

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderDao.findAll();
    }

    public void createPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderDao.create(purchaseOrder);
    }
}
