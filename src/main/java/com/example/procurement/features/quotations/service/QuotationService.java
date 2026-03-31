package com.example.procurement.features.quotations.service;

import com.example.procurement.features.quotations.dao.QuotationDao;
import com.example.procurement.features.quotations.model.Quotation;

import java.util.List;

public class QuotationService {

    private final QuotationDao quotationDao = new QuotationDao();

    public List<Quotation> getAllQuotations() {
        return quotationDao.findAll();
    }

    public Quotation getQuotationById(int quoteId) {
        return quotationDao.findById(quoteId);
    }

    public void createQuotation(Quotation quotation) {
        quotationDao.create(quotation);
    }

    public boolean updateQuotation(Quotation quotation) {
        return quotationDao.update(quotation);
    }

    public boolean deleteQuotation(int quoteId) {
        return quotationDao.deleteById(quoteId);
    }
}
