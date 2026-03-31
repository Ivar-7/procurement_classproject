package com.example.procurement.features.receipts.model;

public class GoodsReceiptItem {

    private Integer grnItemId;
    private Integer grnId;
    private Integer poItemId;
    private Integer itemId;
    private Integer quantityReceived;
    private Integer quantityAccepted;
    private Integer quantityRejected;
    private String conditionNotes;

    public Integer getGrnItemId() {
        return grnItemId;
    }

    public void setGrnItemId(Integer grnItemId) {
        this.grnItemId = grnItemId;
    }

    public Integer getGrnId() {
        return grnId;
    }

    public void setGrnId(Integer grnId) {
        this.grnId = grnId;
    }

    public Integer getPoItemId() {
        return poItemId;
    }

    public void setPoItemId(Integer poItemId) {
        this.poItemId = poItemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantityReceived() {
        return quantityReceived;
    }

    public void setQuantityReceived(Integer quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    public Integer getQuantityAccepted() {
        return quantityAccepted;
    }

    public void setQuantityAccepted(Integer quantityAccepted) {
        this.quantityAccepted = quantityAccepted;
    }

    public Integer getQuantityRejected() {
        return quantityRejected;
    }

    public void setQuantityRejected(Integer quantityRejected) {
        this.quantityRejected = quantityRejected;
    }

    public String getConditionNotes() {
        return conditionNotes;
    }

    public void setConditionNotes(String conditionNotes) {
        this.conditionNotes = conditionNotes;
    }
}
