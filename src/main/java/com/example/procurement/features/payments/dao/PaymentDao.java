package com.example.procurement.features.payments.dao;

import com.example.procurement.features.payments.model.Payment;
import com.example.procurement.shared.config.DatabaseManager;
import com.example.procurement.shared.exception.DataAccessException;
import com.example.procurement.shared.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {

    public List<Payment> findAll() {
        String sql = "SELECT payment_id, payment_ref, po_id, grn_id, invoice_number, invoice_amount, amount_paid, "
            + "payment_date, payment_method, transaction_ref, status, processed_by FROM payments ORDER BY payment_id DESC";
        List<Payment> payments = new ArrayList<Payment>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                payments.add(mapRow(rs));
            }
            return payments;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch payments.", e);
        }
    }

    public Payment findById(int paymentId) {
        String sql = "SELECT payment_id, payment_ref, po_id, grn_id, invoice_number, invoice_amount, amount_paid, "
            + "payment_date, payment_method, transaction_ref, status, processed_by FROM payments WHERE payment_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, paymentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch payment.", e);
        }
    }

    public void create(Payment payment) {
        String sql = "INSERT INTO payments (payment_ref, po_id, grn_id, invoice_number, invoice_amount, amount_paid, "
            + "payment_date, payment_method, transaction_ref, status, processed_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, payment.getPaymentRef());
            ps.setInt(2, payment.getPoId());
            JdbcUtils.setNullableInteger(ps, 3, payment.getGrnId());
            JdbcUtils.setNullableString(ps, 4, payment.getInvoiceNumber());
            JdbcUtils.setNullableBigDecimal(ps, 5, payment.getInvoiceAmount());
            JdbcUtils.setNullableBigDecimal(ps, 6, payment.getAmountPaid());
            JdbcUtils.setNullableString(ps, 7, payment.getPaymentDate());
            ps.setString(8, payment.getPaymentMethod() == null ? "Bank Transfer" : payment.getPaymentMethod());
            JdbcUtils.setNullableString(ps, 9, payment.getTransactionRef());
            ps.setString(10, payment.getStatus() == null ? "Pending" : payment.getStatus());
            JdbcUtils.setNullableString(ps, 11, payment.getProcessedBy());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Failed to create payment.", e);
        }
    }

    public boolean update(Payment payment) {
        String sql = "UPDATE payments SET payment_ref = ?, po_id = ?, grn_id = ?, invoice_number = ?, "
            + "invoice_amount = ?, amount_paid = ?, payment_date = ?, payment_method = ?, transaction_ref = ?, "
            + "status = ?, processed_by = ? WHERE payment_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, payment.getPaymentRef());
            ps.setInt(2, payment.getPoId());
            JdbcUtils.setNullableInteger(ps, 3, payment.getGrnId());
            JdbcUtils.setNullableString(ps, 4, payment.getInvoiceNumber());
            JdbcUtils.setNullableBigDecimal(ps, 5, payment.getInvoiceAmount());
            JdbcUtils.setNullableBigDecimal(ps, 6, payment.getAmountPaid());
            JdbcUtils.setNullableString(ps, 7, payment.getPaymentDate());
            ps.setString(8, payment.getPaymentMethod() == null ? "Bank Transfer" : payment.getPaymentMethod());
            JdbcUtils.setNullableString(ps, 9, payment.getTransactionRef());
            ps.setString(10, payment.getStatus() == null ? "Pending" : payment.getStatus());
            JdbcUtils.setNullableString(ps, 11, payment.getProcessedBy());
            ps.setInt(12, payment.getPaymentId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to update payment.", e);
        }
    }

    public boolean deleteById(int paymentId) {
        String sql = "DELETE FROM payments WHERE payment_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, paymentId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete payment.", e);
        }
    }

    private Payment mapRow(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getInt("payment_id"));
        payment.setPaymentRef(rs.getString("payment_ref"));
        payment.setPoId(rs.getInt("po_id"));
        payment.setGrnId(JdbcUtils.getNullableInteger(rs, "grn_id"));
        payment.setInvoiceNumber(rs.getString("invoice_number"));
        payment.setInvoiceAmount(JdbcUtils.getNullableBigDecimal(rs, "invoice_amount"));
        payment.setAmountPaid(JdbcUtils.getNullableBigDecimal(rs, "amount_paid"));
        payment.setPaymentDate(rs.getString("payment_date"));
        payment.setPaymentMethod(rs.getString("payment_method"));
        payment.setTransactionRef(rs.getString("transaction_ref"));
        payment.setStatus(rs.getString("status"));
        payment.setProcessedBy(rs.getString("processed_by"));
        return payment;
    }
}
