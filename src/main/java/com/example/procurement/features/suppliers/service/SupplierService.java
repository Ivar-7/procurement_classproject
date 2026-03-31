package com.example.procurement.features.suppliers.service;

import com.example.procurement.features.suppliers.dao.SupplierDao;
import com.example.procurement.features.suppliers.model.Supplier;

import java.util.List;

public class SupplierService {

    private final SupplierDao supplierDao = new SupplierDao();

    public List<Supplier> getAllSuppliers() {
        return supplierDao.findAll();
    }

    public Supplier getSupplierById(int supplierId) {
        return supplierDao.findById(supplierId);
    }

    public void createSupplier(Supplier supplier) {
        supplierDao.create(supplier);
    }

    public boolean updateSupplier(Supplier supplier) {
        return supplierDao.update(supplier);
    }

    public boolean deleteSupplier(int supplierId) {
        return supplierDao.deleteById(supplierId);
    }
}
