package com.example.procurement.features.receipts.service;

import com.example.procurement.features.receipts.dao.GoodsReceiptDao;
import com.example.procurement.features.receipts.model.GoodsReceipt;

import java.util.List;

public class GoodsReceiptService {

    private final GoodsReceiptDao goodsReceiptDao = new GoodsReceiptDao();

    public List<GoodsReceipt> getAllGoodsReceipts() {
        return goodsReceiptDao.findAll();
    }

    public void createGoodsReceipt(GoodsReceipt goodsReceipt) {
        goodsReceiptDao.create(goodsReceipt);
    }
}
