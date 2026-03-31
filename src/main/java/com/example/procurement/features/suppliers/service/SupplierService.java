package com.example.procurement.features.suppliers.service;

import com.example.procurement.features.suppliers.dao.SupplierDao;
import com.example.procurement.features.suppliers.model.Supplier;

import java.util.List;

public class SupplierService {

    private final SupplierDao supplierDao = new SupplierDao();

    public List<Supplier> getAllSuppliers() {
        return supplierDao.findAll();
    }

    public void createSupplier(Supplier supplier) {
        supplierDao.create(supplier);
    }
}
