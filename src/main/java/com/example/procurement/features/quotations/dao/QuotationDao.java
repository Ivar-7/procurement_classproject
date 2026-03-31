package com.example.procurement.features.quotations.dao;

import com.example.procurement.features.quotations.model.Quotation;
import com.example.procurement.shared.config.DatabaseManager;
import com.example.procurement.shared.exception.DataAccessException;
import com.example.procurement.shared.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuotationDao {

    public List<Quotation> findAll() {
        String sql = "SELECT quote_id, quote_ref, req_id, supplier_id, quote_date, validity_date, subtotal, tax_amount, "
            + "total_amount, delivery_days, terms, attachment_url, status FROM quotations ORDER BY quote_id DESC";
        List<Quotation> quotations = new ArrayList<Quotation>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                quotations.add(mapRow(rs));
            }
            return quotations;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch quotations.", e);
        }
    }

    public void create(Quotation quotation) {
        String sql = "INSERT INTO quotations (quote_ref, req_id, supplier_id, quote_date, validity_date, subtotal, "
            + "tax_amount, total_amount, delivery_days, terms, attachment_url, status) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, quotation.getQuoteRef());
            JdbcUtils.setNullableInteger(ps, 2, quotation.getReqId());
            ps.setInt(3, quotation.getSupplierId());
            JdbcUtils.setNullableString(ps, 4, quotation.getQuoteDate());
            JdbcUtils.setNullableString(ps, 5, quotation.getValidityDate());
            JdbcUtils.setNullableBigDecimal(ps, 6, quotation.getSubtotal());
            JdbcUtils.setNullableBigDecimal(ps, 7, quotation.getTaxAmount());
            JdbcUtils.setNullableBigDecimal(ps, 8, quotation.getTotalAmount());
            JdbcUtils.setNullableInteger(ps, 9, quotation.getDeliveryDays());
            JdbcUtils.setNullableString(ps, 10, quotation.getTerms());
            JdbcUtils.setNullableString(ps, 11, quotation.getAttachmentUrl());
            ps.setString(12, quotation.getStatus() == null ? "Requested" : quotation.getStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Failed to create quotation.", e);
        }
    }

    private Quotation mapRow(ResultSet rs) throws SQLException {
        Quotation quotation = new Quotation();
        quotation.setQuoteId(rs.getInt("quote_id"));
        quotation.setQuoteRef(rs.getString("quote_ref"));
        quotation.setReqId(JdbcUtils.getNullableInteger(rs, "req_id"));
        quotation.setSupplierId(rs.getInt("supplier_id"));
        quotation.setQuoteDate(rs.getString("quote_date"));
        quotation.setValidityDate(rs.getString("validity_date"));
        quotation.setSubtotal(JdbcUtils.getNullableBigDecimal(rs, "subtotal"));
        quotation.setTaxAmount(JdbcUtils.getNullableBigDecimal(rs, "tax_amount"));
        quotation.setTotalAmount(JdbcUtils.getNullableBigDecimal(rs, "total_amount"));
        quotation.setDeliveryDays(JdbcUtils.getNullableInteger(rs, "delivery_days"));
        quotation.setTerms(rs.getString("terms"));
        quotation.setAttachmentUrl(rs.getString("attachment_url"));
        quotation.setStatus(rs.getString("status"));
        return quotation;
    }
}
