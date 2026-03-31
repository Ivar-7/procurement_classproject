package com.example.procurement.features.departments.dao;

import com.example.procurement.features.departments.model.Department;
import com.example.procurement.shared.config.DatabaseManager;
import com.example.procurement.shared.exception.DataAccessException;
import com.example.procurement.shared.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {

    public List<Department> findAll() {
        String sql = "SELECT dept_id, dept_name, budget_code, annual_budget, head_name, email, created_at "
            + "FROM departments ORDER BY dept_id DESC";
        List<Department> departments = new ArrayList<Department>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                departments.add(mapRow(rs));
            }
            return departments;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch departments.", e);
        }
    }

    public Department findById(int deptId) {
        String sql = "SELECT dept_id, dept_name, budget_code, annual_budget, head_name, email, created_at "
            + "FROM departments WHERE dept_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, deptId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch department.", e);
        }
    }

    public void create(Department department) {
        String sql = "INSERT INTO departments (dept_name, budget_code, annual_budget, head_name, email) "
            + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, department.getDeptName());
            JdbcUtils.setNullableString(ps, 2, department.getBudgetCode());
            JdbcUtils.setNullableBigDecimal(ps, 3, department.getAnnualBudget());
            JdbcUtils.setNullableString(ps, 4, department.getHeadName());
            JdbcUtils.setNullableString(ps, 5, department.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Failed to create department.", e);
        }
    }

    public boolean update(Department department) {
        String sql = "UPDATE departments SET dept_name = ?, budget_code = ?, annual_budget = ?, head_name = ?, "
            + "email = ? WHERE dept_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, department.getDeptName());
            JdbcUtils.setNullableString(ps, 2, department.getBudgetCode());
            JdbcUtils.setNullableBigDecimal(ps, 3, department.getAnnualBudget());
            JdbcUtils.setNullableString(ps, 4, department.getHeadName());
            JdbcUtils.setNullableString(ps, 5, department.getEmail());
            ps.setInt(6, department.getDeptId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to update department.", e);
        }
    }

    public boolean deleteById(int deptId) {
        String sql = "DELETE FROM departments WHERE dept_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, deptId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete department.", e);
        }
    }

    private Department mapRow(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setDeptId(rs.getInt("dept_id"));
        department.setDeptName(rs.getString("dept_name"));
        department.setBudgetCode(rs.getString("budget_code"));
        department.setAnnualBudget(JdbcUtils.getNullableBigDecimal(rs, "annual_budget"));
        department.setHeadName(rs.getString("head_name"));
        department.setEmail(rs.getString("email"));
        department.setCreatedAt(rs.getString("created_at"));
        return department;
    }
}
