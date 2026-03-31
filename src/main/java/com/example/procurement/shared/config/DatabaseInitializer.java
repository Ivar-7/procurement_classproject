package com.example.procurement.shared.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseInitializer {

    private static volatile boolean initialized;

    private static final String[] SCHEMA_SQL = {
        "CREATE TABLE IF NOT EXISTS departments ("
            + "dept_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "dept_name TEXT NOT NULL,"
            + "budget_code TEXT,"
            + "annual_budget REAL DEFAULT 0.00,"
            + "head_name TEXT,"
            + "email TEXT,"
            + "created_at TEXT DEFAULT CURRENT_TIMESTAMP"
            + ")",

        "CREATE TABLE IF NOT EXISTS suppliers ("
            + "supplier_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "company_name TEXT NOT NULL,"
            + "contact_person TEXT,"
            + "phone TEXT,"
            + "email TEXT,"
            + "category TEXT,"
            + "status TEXT DEFAULT 'Active' CHECK(status IN ('Active', 'Blacklisted', 'Pending')),"
            + "created_at TEXT DEFAULT CURRENT_TIMESTAMP"
            + ")",

        "CREATE TABLE IF NOT EXISTS items ("
            + "item_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "item_code TEXT UNIQUE NOT NULL,"
            + "item_name TEXT NOT NULL,"
            + "category TEXT,"
            + "unit_of_measure TEXT,"
            + "estimated_unit_price REAL,"
            + "supplier_id INTEGER,"
            + "is_active INTEGER DEFAULT 1,"
            + "FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON DELETE SET NULL"
            + ")",

        "CREATE TABLE IF NOT EXISTS requisitions ("
            + "req_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "req_number TEXT UNIQUE NOT NULL,"
            + "dept_id INTEGER NOT NULL,"
            + "requested_by TEXT,"
            + "request_date TEXT DEFAULT (DATE('now')),"
            + "justification TEXT,"
            + "total_estimated REAL DEFAULT 0.00,"
            + "urgency TEXT DEFAULT 'Medium' CHECK(urgency IN ('Low', 'Medium', 'High', 'Critical')),"
            + "status TEXT DEFAULT 'Draft' CHECK(status IN ('Draft', 'Pending', 'Approved', 'Rejected', 'Converted')),"
            + "approved_by TEXT,"
            + "approved_date TEXT,"
            + "FOREIGN KEY (dept_id) REFERENCES departments(dept_id) ON DELETE RESTRICT"
            + ")",

        "CREATE TABLE IF NOT EXISTS requisition_items ("
            + "req_item_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "req_id INTEGER NOT NULL,"
            + "item_id INTEGER NOT NULL,"
            + "quantity_requested INTEGER DEFAULT 1,"
            + "estimated_price REAL,"
            + "purpose TEXT,"
            + "FOREIGN KEY (req_id) REFERENCES requisitions(req_id) ON DELETE CASCADE,"
            + "FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE RESTRICT"
            + ")",

        "CREATE TABLE IF NOT EXISTS quotations ("
            + "quote_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "quote_ref TEXT UNIQUE NOT NULL,"
            + "req_id INTEGER,"
            + "supplier_id INTEGER NOT NULL,"
            + "quote_date TEXT DEFAULT (DATE('now')),"
            + "validity_date TEXT,"
            + "subtotal REAL DEFAULT 0.00,"
            + "tax_amount REAL DEFAULT 0.00,"
            + "total_amount REAL DEFAULT 0.00,"
            + "delivery_days INTEGER,"
            + "terms TEXT,"
            + "attachment_url TEXT,"
            + "status TEXT DEFAULT 'Requested' CHECK(status IN ('Requested', 'Received', 'Selected', 'Rejected', 'Expired')),"
            + "FOREIGN KEY (req_id) REFERENCES requisitions(req_id) ON DELETE SET NULL,"
            + "FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON DELETE RESTRICT"
            + ")",

        "CREATE TABLE IF NOT EXISTS quotation_items ("
            + "quote_item_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "quote_id INTEGER NOT NULL,"
            + "item_id INTEGER NOT NULL,"
            + "quantity_quoted INTEGER DEFAULT 1,"
            + "unit_price REAL,"
            + "line_total REAL,"
            + "FOREIGN KEY (quote_id) REFERENCES quotations(quote_id) ON DELETE CASCADE,"
            + "FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE RESTRICT"
            + ")",

        "CREATE TABLE IF NOT EXISTS purchase_orders ("
            + "po_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "po_number TEXT UNIQUE NOT NULL,"
            + "req_id INTEGER,"
            + "quote_id INTEGER,"
            + "supplier_id INTEGER NOT NULL,"
            + "dept_id INTEGER NOT NULL,"
            + "order_date TEXT DEFAULT (DATE('now')),"
            + "delivery_date TEXT,"
            + "subtotal REAL DEFAULT 0.00,"
            + "tax_amount REAL DEFAULT 0.00,"
            + "total_amount REAL DEFAULT 0.00,"
            + "status TEXT DEFAULT 'Draft' CHECK(status IN ('Draft', 'Sent', 'Acknowledged', 'Partial', 'Completed', 'Cancelled')),"
            + "notes TEXT,"
            + "created_by TEXT,"
            + "FOREIGN KEY (req_id) REFERENCES requisitions(req_id) ON DELETE SET NULL,"
            + "FOREIGN KEY (quote_id) REFERENCES quotations(quote_id) ON DELETE SET NULL,"
            + "FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON DELETE RESTRICT,"
            + "FOREIGN KEY (dept_id) REFERENCES departments(dept_id) ON DELETE RESTRICT"
            + ")",

        "CREATE TABLE IF NOT EXISTS purchase_order_items ("
            + "po_item_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "po_id INTEGER NOT NULL,"
            + "item_id INTEGER NOT NULL,"
            + "quantity_ordered INTEGER DEFAULT 1,"
            + "unit_price REAL,"
            + "line_total REAL,"
            + "quantity_received INTEGER DEFAULT 0,"
            + "FOREIGN KEY (po_id) REFERENCES purchase_orders(po_id) ON DELETE CASCADE,"
            + "FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE RESTRICT"
            + ")",

        "CREATE TABLE IF NOT EXISTS goods_receipts ("
            + "grn_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "grn_number TEXT UNIQUE NOT NULL,"
            + "po_id INTEGER NOT NULL,"
            + "receipt_date TEXT DEFAULT (DATE('now')),"
            + "received_by TEXT,"
            + "delivery_note_ref TEXT,"
            + "total_items INTEGER DEFAULT 0,"
            + "status TEXT DEFAULT 'Pending' CHECK(status IN ('Pending', 'Complete', 'Partial', 'Rejected')),"
            + "notes TEXT,"
            + "FOREIGN KEY (po_id) REFERENCES purchase_orders(po_id) ON DELETE RESTRICT"
            + ")",

        "CREATE TABLE IF NOT EXISTS goods_receipt_items ("
            + "grn_item_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "grn_id INTEGER NOT NULL,"
            + "po_item_id INTEGER NOT NULL,"
            + "item_id INTEGER NOT NULL,"
            + "quantity_received INTEGER DEFAULT 0,"
            + "quantity_accepted INTEGER DEFAULT 0,"
            + "quantity_rejected INTEGER DEFAULT 0,"
            + "condition_notes TEXT,"
            + "FOREIGN KEY (grn_id) REFERENCES goods_receipts(grn_id) ON DELETE CASCADE,"
            + "FOREIGN KEY (po_item_id) REFERENCES purchase_order_items(po_item_id) ON DELETE RESTRICT,"
            + "FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE RESTRICT"
            + ")",

        "CREATE TABLE IF NOT EXISTS payments ("
            + "payment_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "payment_ref TEXT UNIQUE NOT NULL,"
            + "po_id INTEGER NOT NULL,"
            + "grn_id INTEGER,"
            + "invoice_number TEXT,"
            + "invoice_amount REAL,"
            + "amount_paid REAL,"
            + "payment_date TEXT,"
            + "payment_method TEXT DEFAULT 'Bank Transfer' CHECK(payment_method IN ('Bank Transfer', 'Check', 'Cash', 'Card')),"
            + "transaction_ref TEXT,"
            + "status TEXT DEFAULT 'Pending' CHECK(status IN ('Pending', 'Processing', 'Completed', 'Failed')),"
            + "processed_by TEXT,"
            + "FOREIGN KEY (po_id) REFERENCES purchase_orders(po_id) ON DELETE RESTRICT,"
            + "FOREIGN KEY (grn_id) REFERENCES goods_receipts(grn_id) ON DELETE SET NULL"
            + ")",

        "CREATE INDEX IF NOT EXISTS idx_req_status ON requisitions(status)",
        "CREATE INDEX IF NOT EXISTS idx_req_dept ON requisitions(dept_id)",
        "CREATE INDEX IF NOT EXISTS idx_quotes_supplier ON quotations(supplier_id)",
        "CREATE INDEX IF NOT EXISTS idx_quotes_status ON quotations(status)",
        "CREATE INDEX IF NOT EXISTS idx_po_supplier ON purchase_orders(supplier_id)",
        "CREATE INDEX IF NOT EXISTS idx_po_status ON purchase_orders(status)",
        "CREATE INDEX IF NOT EXISTS idx_grn_po ON goods_receipts(po_id)",
        "CREATE INDEX IF NOT EXISTS idx_payments_po ON payments(po_id)",
        "CREATE INDEX IF NOT EXISTS idx_payments_date ON payments(payment_date)"
    };

    private DatabaseInitializer() {
    }

    public static synchronized void initializeSchema() {
        if (initialized) {
            return;
        }

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {

            for (String sql : SCHEMA_SQL) {
                statement.execute(sql);
            }
            initialized = true;

        } catch (SQLException e) {
            throw new IllegalStateException("Unable to initialize SQLite schema.", e);
        }
    }
}
