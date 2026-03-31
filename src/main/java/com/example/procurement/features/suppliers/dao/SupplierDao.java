package com.example.procurement.features.suppliers.dao;

import com.example.procurement.features.suppliers.model.Supplier;
import com.example.procurement.shared.config.DatabaseManager;
import com.example.procurement.shared.exception.DataAccessException;
import com.example.procurement.shared.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao {

    public List<Supplier> findAll() {
        String sql = "SELECT supplier_id, company_name, contact_person, phone, email, category, status, created_at "
            + "FROM suppliers ORDER BY supplier_id DESC";
        List<Supplier> suppliers = new ArrayList<Supplier>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                suppliers.add(mapRow(rs));
            }
            return suppliers;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch suppliers.", e);
        }
    }

    public void create(Supplier supplier) {
        String sql = "INSERT INTO suppliers (company_name, contact_person, phone, email, category, status) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, supplier.getCompanyName());
            JdbcUtils.setNullableString(ps, 2, supplier.getContactPerson());
            JdbcUtils.setNullableString(ps, 3, supplier.getPhone());
            JdbcUtils.setNullableString(ps, 4, supplier.getEmail());
            JdbcUtils.setNullableString(ps, 5, supplier.getCategory());
            ps.setString(6, supplier.getStatus() == null ? "Active" : supplier.getStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Failed to create supplier.", e);
        }
    }

    private Supplier mapRow(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(rs.getInt("supplier_id"));
        supplier.setCompanyName(rs.getString("company_name"));
        supplier.setContactPerson(rs.getString("contact_person"));
        supplier.setPhone(rs.getString("phone"));
        supplier.setEmail(rs.getString("email"));
        supplier.setCategory(rs.getString("category"));
        supplier.setStatus(rs.getString("status"));
        supplier.setCreatedAt(rs.getString("created_at"));
        return supplier;
    }
}
