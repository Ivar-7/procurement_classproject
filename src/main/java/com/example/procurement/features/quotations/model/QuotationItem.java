package com.example.procurement.features.quotations.model;

import java.math.BigDecimal;

public class QuotationItem {

    private Integer quoteItemId;
    private Integer quoteId;
    private Integer itemId;
    private Integer quantityQuoted;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;

    public Integer getQuoteItemId() {
        return quoteItemId;
    }

    public void setQuoteItemId(Integer quoteItemId) {
        this.quoteItemId = quoteItemId;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantityQuoted() {
        return quantityQuoted;
    }

    public void setQuantityQuoted(Integer quantityQuoted) {
        this.quantityQuoted = quantityQuoted;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
}
