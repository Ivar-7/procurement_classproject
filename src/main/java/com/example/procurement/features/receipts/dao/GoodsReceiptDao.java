package com.example.procurement.features.receipts.dao;

import com.example.procurement.features.receipts.model.GoodsReceipt;
import com.example.procurement.shared.config.DatabaseManager;
import com.example.procurement.shared.exception.DataAccessException;
import com.example.procurement.shared.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsReceiptDao {

    public List<GoodsReceipt> findAll() {
        String sql = "SELECT grn_id, grn_number, po_id, receipt_date, received_by, delivery_note_ref, total_items, status, notes "
            + "FROM goods_receipts ORDER BY grn_id DESC";
        List<GoodsReceipt> receipts = new ArrayList<GoodsReceipt>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                receipts.add(mapRow(rs));
            }
            return receipts;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch goods receipts.", e);
        }
    }

    public GoodsReceipt findById(int grnId) {
        String sql = "SELECT grn_id, grn_number, po_id, receipt_date, received_by, delivery_note_ref, total_items, status, notes "
            + "FROM goods_receipts WHERE grn_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, grnId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch goods receipt.", e);
        }
    }

    public void create(GoodsReceipt receipt) {
        String sql = "INSERT INTO goods_receipts (grn_number, po_id, receipt_date, received_by, delivery_note_ref, "
            + "total_items, status, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, receipt.getGrnNumber());
            ps.setInt(2, receipt.getPoId());
            JdbcUtils.setNullableString(ps, 3, receipt.getReceiptDate());
            JdbcUtils.setNullableString(ps, 4, receipt.getReceivedBy());
            JdbcUtils.setNullableString(ps, 5, receipt.getDeliveryNoteRef());
            JdbcUtils.setNullableInteger(ps, 6, receipt.getTotalItems());
            ps.setString(7, receipt.getStatus() == null ? "Pending" : receipt.getStatus());
            JdbcUtils.setNullableString(ps, 8, receipt.getNotes());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Failed to create goods receipt.", e);
        }
    }

    public boolean update(GoodsReceipt receipt) {
        String sql = "UPDATE goods_receipts SET grn_number = ?, po_id = ?, receipt_date = ?, received_by = ?, "
            + "delivery_note_ref = ?, total_items = ?, status = ?, notes = ? WHERE grn_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, receipt.getGrnNumber());
            ps.setInt(2, receipt.getPoId());
            JdbcUtils.setNullableString(ps, 3, receipt.getReceiptDate());
            JdbcUtils.setNullableString(ps, 4, receipt.getReceivedBy());
            JdbcUtils.setNullableString(ps, 5, receipt.getDeliveryNoteRef());
            JdbcUtils.setNullableInteger(ps, 6, receipt.getTotalItems());
            ps.setString(7, receipt.getStatus() == null ? "Pending" : receipt.getStatus());
            JdbcUtils.setNullableString(ps, 8, receipt.getNotes());
            ps.setInt(9, receipt.getGrnId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to update goods receipt.", e);
        }
    }

    public boolean deleteById(int grnId) {
        String sql = "DELETE FROM goods_receipts WHERE grn_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, grnId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete goods receipt.", e);
        }
    }

    private GoodsReceipt mapRow(ResultSet rs) throws SQLException {
        GoodsReceipt receipt = new GoodsReceipt();
        receipt.setGrnId(rs.getInt("grn_id"));
        receipt.setGrnNumber(rs.getString("grn_number"));
        receipt.setPoId(rs.getInt("po_id"));
        receipt.setReceiptDate(rs.getString("receipt_date"));
        receipt.setReceivedBy(rs.getString("received_by"));
        receipt.setDeliveryNoteRef(rs.getString("delivery_note_ref"));
        receipt.setTotalItems(JdbcUtils.getNullableInteger(rs, "total_items"));
        receipt.setStatus(rs.getString("status"));
        receipt.setNotes(rs.getString("notes"));
        return receipt;
    }
}
