package com.example.procurement.features.purchaseorders.dao;

import com.example.procurement.features.purchaseorders.model.PurchaseOrder;
import com.example.procurement.shared.config.DatabaseManager;
import com.example.procurement.shared.exception.DataAccessException;
import com.example.procurement.shared.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderDao {

    public List<PurchaseOrder> findAll() {
        String sql = "SELECT po_id, po_number, req_id, quote_id, supplier_id, dept_id, order_date, delivery_date, "
            + "subtotal, tax_amount, total_amount, status, notes, created_by FROM purchase_orders ORDER BY po_id DESC";
        List<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                orders.add(mapRow(rs));
            }
            return orders;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch purchase orders.", e);
        }
    }

    public void create(PurchaseOrder order) {
        String sql = "INSERT INTO purchase_orders (po_number, req_id, quote_id, supplier_id, dept_id, order_date, "
            + "delivery_date, subtotal, tax_amount, total_amount, status, notes, created_by) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, order.getPoNumber());
            JdbcUtils.setNullableInteger(ps, 2, order.getReqId());
            JdbcUtils.setNullableInteger(ps, 3, order.getQuoteId());
            ps.setInt(4, order.getSupplierId());
            ps.setInt(5, order.getDeptId());
            JdbcUtils.setNullableString(ps, 6, order.getOrderDate());
            JdbcUtils.setNullableString(ps, 7, order.getDeliveryDate());
            JdbcUtils.setNullableBigDecimal(ps, 8, order.getSubtotal());
            JdbcUtils.setNullableBigDecimal(ps, 9, order.getTaxAmount());
            JdbcUtils.setNullableBigDecimal(ps, 10, order.getTotalAmount());
            ps.setString(11, order.getStatus() == null ? "Draft" : order.getStatus());
            JdbcUtils.setNullableString(ps, 12, order.getNotes());
            JdbcUtils.setNullableString(ps, 13, order.getCreatedBy());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Failed to create purchase order.", e);
        }
    }

    private PurchaseOrder mapRow(ResultSet rs) throws SQLException {
        PurchaseOrder order = new PurchaseOrder();
        order.setPoId(rs.getInt("po_id"));
        order.setPoNumber(rs.getString("po_number"));
        order.setReqId(JdbcUtils.getNullableInteger(rs, "req_id"));
        order.setQuoteId(JdbcUtils.getNullableInteger(rs, "quote_id"));
        order.setSupplierId(rs.getInt("supplier_id"));
        order.setDeptId(rs.getInt("dept_id"));
        order.setOrderDate(rs.getString("order_date"));
        order.setDeliveryDate(rs.getString("delivery_date"));
        order.setSubtotal(JdbcUtils.getNullableBigDecimal(rs, "subtotal"));
        order.setTaxAmount(JdbcUtils.getNullableBigDecimal(rs, "tax_amount"));
        order.setTotalAmount(JdbcUtils.getNullableBigDecimal(rs, "total_amount"));
        order.setStatus(rs.getString("status"));
        order.setNotes(rs.getString("notes"));
        order.setCreatedBy(rs.getString("created_by"));
        return order;
    }
}
