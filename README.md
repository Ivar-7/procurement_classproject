-- ============================================
-- 🏢 DEPARTMENTAL PROCUREMENT SYSTEM (Simplified)
-- 8 Core Tables for Small-Scale Operations
-- ============================================

CREATE DATABASE IF NOT EXISTS dept_procurement 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE dept_procurement;
SET FOREIGN_KEY_CHECKS = 0;

-- ============================================
-- 1️⃣ DEPARTMENTS (Master Data)
-- ============================================
CREATE TABLE IF NOT EXISTS departments (
    dept_id INT AUTO_INCREMENT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    budget_code VARCHAR(50),
    annual_budget DECIMAL(15,2) DEFAULT 0.00,
    head_name VARCHAR(100),
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ============================================
-- 2️⃣ SUPPLIERS (Vendors Registry)
-- ============================================
CREATE TABLE IF NOT EXISTS suppliers (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(200) NOT NULL,
    contact_person VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    category VARCHAR(50), -- e.g., 'IT', 'Office', 'Services'
    status ENUM('Active', 'Blacklisted', 'Pending') DEFAULT 'Active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ============================================
-- 3️⃣ ITEMS (Catalogue)
-- ============================================
CREATE TABLE IF NOT EXISTS items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_code VARCHAR(50) UNIQUE NOT NULL,
    item_name VARCHAR(200) NOT NULL,
    category VARCHAR(50),
    unit_of_measure VARCHAR(20),
    estimated_unit_price DECIMAL(12,2),
    supplier_id INT,
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- ============================================
-- 4️⃣ REQUISITIONS (Internal Requests)
-- ============================================
CREATE TABLE IF NOT EXISTS requisitions (
    req_id INT AUTO_INCREMENT PRIMARY KEY,
    req_number VARCHAR(50) UNIQUE NOT NULL,
    dept_id INT NOT NULL,
    requested_by VARCHAR(100),
    request_date DATE DEFAULT (CURRENT_DATE),
    justification TEXT,
    total_estimated DECIMAL(15,2) DEFAULT 0.00,
    urgency ENUM('Low', 'Medium', 'High', 'Critical') DEFAULT 'Medium',
    status ENUM('Draft', 'Pending', 'Approved', 'Rejected', 'Converted') DEFAULT 'Draft',
    approved_by VARCHAR(100),
    approved_date DATE,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- Requisition Line Items
CREATE TABLE IF NOT EXISTS requisition_items (
    req_item_id INT AUTO_INCREMENT PRIMARY KEY,
    req_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity_requested INT DEFAULT 1,
    estimated_price DECIMAL(12,2),
    purpose TEXT,
    FOREIGN KEY (req_id) REFERENCES requisitions(req_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ============================================
-- 5️⃣ QUOTATIONS (RFQ & Quotes)
-- ============================================
CREATE TABLE IF NOT EXISTS quotations (
    quote_id INT AUTO_INCREMENT PRIMARY KEY,
    quote_ref VARCHAR(50) UNIQUE NOT NULL,
    req_id INT,
    supplier_id INT NOT NULL,
    quote_date DATE DEFAULT (CURRENT_DATE),
    validity_date DATE,
    subtotal DECIMAL(15,2) DEFAULT 0.00,
    tax_amount DECIMAL(15,2) DEFAULT 0.00,
    total_amount DECIMAL(15,2) DEFAULT 0.00,
    delivery_days INT,
    terms TEXT,
    attachment_url VARCHAR(255),
    status ENUM('Requested', 'Received', 'Selected', 'Rejected', 'Expired') DEFAULT 'Requested',
    FOREIGN KEY (req_id) REFERENCES requisitions(req_id) ON DELETE SET NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- Quotation Line Items
CREATE TABLE IF NOT EXISTS quotation_items (
    quote_item_id INT AUTO_INCREMENT PRIMARY KEY,
    quote_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity_quoted INT DEFAULT 1,
    unit_price DECIMAL(12,2),
    line_total DECIMAL(15,2),
    FOREIGN KEY (quote_id) REFERENCES quotations(quote_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ============================================
-- 6️⃣ PURCHASE ORDERS (POs)
-- ============================================
CREATE TABLE IF NOT EXISTS purchase_orders (
    po_id INT AUTO_INCREMENT PRIMARY KEY,
    po_number VARCHAR(50) UNIQUE NOT NULL,
    req_id INT,
    quote_id INT,
    supplier_id INT NOT NULL,
    dept_id INT NOT NULL,
    order_date DATE DEFAULT (CURRENT_DATE),
    delivery_date DATE,
    subtotal DECIMAL(15,2) DEFAULT 0.00,
    tax_amount DECIMAL(15,2) DEFAULT 0.00,
    total_amount DECIMAL(15,2) DEFAULT 0.00,
    status ENUM('Draft', 'Sent', 'Acknowledged', 'Partial', 'Completed', 'Cancelled') DEFAULT 'Draft',
    notes TEXT,
    created_by VARCHAR(100),
    FOREIGN KEY (req_id) REFERENCES requisitions(req_id) ON DELETE SET NULL,
    FOREIGN KEY (quote_id) REFERENCES quotations(quote_id) ON DELETE SET NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON DELETE RESTRICT,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- PO Line Items
CREATE TABLE IF NOT EXISTS purchase_order_items (
    po_item_id INT AUTO_INCREMENT PRIMARY KEY,
    po_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity_ordered INT DEFAULT 1,
    unit_price DECIMAL(12,2),
    line_total DECIMAL(15,2),
    quantity_received INT DEFAULT 0,
    FOREIGN KEY (po_id) REFERENCES purchase_orders(po_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ============================================
-- 7️⃣ DELIVERIES & RECEIPTS (GRN)
-- ============================================
CREATE TABLE IF NOT EXISTS goods_receipts (
    grn_id INT AUTO_INCREMENT PRIMARY KEY,
    grn_number VARCHAR(50) UNIQUE NOT NULL,
    po_id INT NOT NULL,
    receipt_date DATE DEFAULT (CURRENT_DATE),
    received_by VARCHAR(100),
    delivery_note_ref VARCHAR(100),
    total_items INT DEFAULT 0,
    status ENUM('Pending', 'Complete', 'Partial', 'Rejected') DEFAULT 'Pending',
    notes TEXT,
    FOREIGN KEY (po_id) REFERENCES purchase_orders(po_id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- GRN Line Items
CREATE TABLE IF NOT EXISTS goods_receipt_items (
    grn_item_id INT AUTO_INCREMENT PRIMARY KEY,
    grn_id INT NOT NULL,
    po_item_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity_received INT DEFAULT 0,
    quantity_accepted INT DEFAULT 0,
    quantity_rejected INT DEFAULT 0,
    condition_notes TEXT,
    FOREIGN KEY (grn_id) REFERENCES goods_receipts(grn_id) ON DELETE CASCADE,
    FOREIGN KEY (po_item_id) REFERENCES purchase_order_items(po_item_id) ON DELETE RESTRICT,
    FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- ============================================
-- 8️⃣ PAYMENTS (Finance)
-- ============================================
CREATE TABLE IF NOT EXISTS payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    payment_ref VARCHAR(50) UNIQUE NOT NULL,
    po_id INT NOT NULL,
    grn_id INT,
    invoice_number VARCHAR(100),
    invoice_amount DECIMAL(15,2),
    amount_paid DECIMAL(15,2),
    payment_date DATE,
    payment_method ENUM('Bank Transfer', 'Check', 'Cash', 'Card') DEFAULT 'Bank Transfer',
    transaction_ref VARCHAR(100),
    status ENUM('Pending', 'Processing', 'Completed', 'Failed') DEFAULT 'Pending',
    processed_by VARCHAR(100),
    FOREIGN KEY (po_id) REFERENCES purchase_orders(po_id) ON DELETE RESTRICT,
    FOREIGN KEY (grn_id) REFERENCES goods_receipts(grn_id) ON DELETE SET NULL
) ENGINE=InnoDB;

-- ============================================
-- 🔍 INDEXES
-- ============================================
CREATE INDEX idx_req_status ON requisitions(status);
CREATE INDEX idx_req_dept ON requisitions(dept_id);
CREATE INDEX idx_quotes_supplier ON quotations(supplier_id);
CREATE INDEX idx_quotes_status ON quotations(status);
CREATE INDEX idx_po_supplier ON purchase_orders(supplier_id);
CREATE INDEX idx_po_status ON purchase_orders(status);
CREATE INDEX idx_grn_po ON goods_receipts(po_id);
CREATE INDEX idx_payments_po ON payments(po_id);
CREATE INDEX idx_payments_date ON payments(payment_date);

SET FOREIGN_KEY_CHECKS = 1;
