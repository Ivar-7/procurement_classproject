package com.example.procurement.features.quotations.service;

import com.example.procurement.features.quotations.dao.QuotationDao;
import com.example.procurement.features.quotations.model.Quotation;

import java.util.List;

public class QuotationService {

    private final QuotationDao quotationDao = new QuotationDao();

    public List<Quotation> getAllQuotations() {
        return quotationDao.findAll();
    }

    public void createQuotation(Quotation quotation) {
        quotationDao.create(quotation);
    }
}
