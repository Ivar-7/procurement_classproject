package com.example.procurement.features.requisitions.dao;

import com.example.procurement.features.requisitions.model.Requisition;
import com.example.procurement.shared.config.DatabaseManager;
import com.example.procurement.shared.exception.DataAccessException;
import com.example.procurement.shared.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequisitionDao {

    public List<Requisition> findAll() {
        String sql = "SELECT req_id, req_number, dept_id, requested_by, request_date, justification, total_estimated, "
            + "urgency, status, approved_by, approved_date FROM requisitions ORDER BY req_id DESC";
        List<Requisition> requisitions = new ArrayList<Requisition>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                requisitions.add(mapRow(rs));
            }
            return requisitions;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch requisitions.", e);
        }
    }

    public Requisition findById(int reqId) {
        String sql = "SELECT req_id, req_number, dept_id, requested_by, request_date, justification, total_estimated, "
            + "urgency, status, approved_by, approved_date FROM requisitions WHERE req_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, reqId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch requisition.", e);
        }
    }

    public void create(Requisition requisition) {
        String sql = "INSERT INTO requisitions (req_number, dept_id, requested_by, request_date, justification, "
            + "total_estimated, urgency, status, approved_by, approved_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, requisition.getReqNumber());
            ps.setInt(2, requisition.getDeptId());
            JdbcUtils.setNullableString(ps, 3, requisition.getRequestedBy());
            JdbcUtils.setNullableString(ps, 4, requisition.getRequestDate());
            JdbcUtils.setNullableString(ps, 5, requisition.getJustification());
            JdbcUtils.setNullableBigDecimal(ps, 6, requisition.getTotalEstimated());
            ps.setString(7, requisition.getUrgency() == null ? "Medium" : requisition.getUrgency());
            ps.setString(8, requisition.getStatus() == null ? "Draft" : requisition.getStatus());
            JdbcUtils.setNullableString(ps, 9, requisition.getApprovedBy());
            JdbcUtils.setNullableString(ps, 10, requisition.getApprovedDate());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Failed to create requisition.", e);
        }
    }

    public boolean update(Requisition requisition) {
        String sql = "UPDATE requisitions SET req_number = ?, dept_id = ?, requested_by = ?, request_date = ?, "
            + "justification = ?, total_estimated = ?, urgency = ?, status = ?, approved_by = ?, approved_date = ? "
            + "WHERE req_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, requisition.getReqNumber());
            ps.setInt(2, requisition.getDeptId());
            JdbcUtils.setNullableString(ps, 3, requisition.getRequestedBy());
            JdbcUtils.setNullableString(ps, 4, requisition.getRequestDate());
            JdbcUtils.setNullableString(ps, 5, requisition.getJustification());
            JdbcUtils.setNullableBigDecimal(ps, 6, requisition.getTotalEstimated());
            ps.setString(7, requisition.getUrgency() == null ? "Medium" : requisition.getUrgency());
            ps.setString(8, requisition.getStatus() == null ? "Draft" : requisition.getStatus());
            JdbcUtils.setNullableString(ps, 9, requisition.getApprovedBy());
            JdbcUtils.setNullableString(ps, 10, requisition.getApprovedDate());
            ps.setInt(11, requisition.getReqId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to update requisition.", e);
        }
    }

    public boolean deleteById(int reqId) {
        String sql = "DELETE FROM requisitions WHERE req_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, reqId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete requisition.", e);
        }
    }

    private Requisition mapRow(ResultSet rs) throws SQLException {
        Requisition requisition = new Requisition();
        requisition.setReqId(rs.getInt("req_id"));
        requisition.setReqNumber(rs.getString("req_number"));
        requisition.setDeptId(rs.getInt("dept_id"));
        requisition.setRequestedBy(rs.getString("requested_by"));
        requisition.setRequestDate(rs.getString("request_date"));
        requisition.setJustification(rs.getString("justification"));
        requisition.setTotalEstimated(JdbcUtils.getNullableBigDecimal(rs, "total_estimated"));
        requisition.setUrgency(rs.getString("urgency"));
        requisition.setStatus(rs.getString("status"));
        requisition.setApprovedBy(rs.getString("approved_by"));
        requisition.setApprovedDate(rs.getString("approved_date"));
        return requisition;
    }
}
