package com.example.procurement.features.items.dao;

import com.example.procurement.features.items.model.Item;
import com.example.procurement.shared.config.DatabaseManager;
import com.example.procurement.shared.exception.DataAccessException;
import com.example.procurement.shared.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

    public List<Item> findAll() {
        String sql = "SELECT item_id, item_code, item_name, category, unit_of_measure, estimated_unit_price, supplier_id, is_active "
            + "FROM items ORDER BY item_id DESC";
        List<Item> items = new ArrayList<Item>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                items.add(mapRow(rs));
            }
            return items;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch items.", e);
        }
    }

    public void create(Item item) {
        String sql = "INSERT INTO items (item_code, item_name, category, unit_of_measure, estimated_unit_price, supplier_id, is_active) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, item.getItemCode());
            ps.setString(2, item.getItemName());
            JdbcUtils.setNullableString(ps, 3, item.getCategory());
            JdbcUtils.setNullableString(ps, 4, item.getUnitOfMeasure());
            JdbcUtils.setNullableBigDecimal(ps, 5, item.getEstimatedUnitPrice());
            JdbcUtils.setNullableInteger(ps, 6, item.getSupplierId());
            ps.setInt(7, Boolean.FALSE.equals(item.getActive()) ? 0 : 1);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Failed to create item.", e);
        }
    }

    private Item mapRow(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setItemId(rs.getInt("item_id"));
        item.setItemCode(rs.getString("item_code"));
        item.setItemName(rs.getString("item_name"));
        item.setCategory(rs.getString("category"));
        item.setUnitOfMeasure(rs.getString("unit_of_measure"));
        item.setEstimatedUnitPrice(JdbcUtils.getNullableBigDecimal(rs, "estimated_unit_price"));
        item.setSupplierId(JdbcUtils.getNullableInteger(rs, "supplier_id"));
        item.setActive(JdbcUtils.getBoolean(rs, "is_active"));
        return item;
    }
}
