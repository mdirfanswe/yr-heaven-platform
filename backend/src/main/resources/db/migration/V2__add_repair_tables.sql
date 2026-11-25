-- 1. Public Repair Service Catalog (Owner-managed indicative prices & turnaround times)
CREATE TABLE repair_service_catalog (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(150) NOT NULL,
    category VARCHAR(80) NOT NULL,
    description TEXT NULL,
    price_min DECIMAL(10, 2) NULL,
    price_max DECIMAL(10, 2) NULL,
    estimated_turnaround_text VARCHAR(100) NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    display_order INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_repair_catalog_active_order (active, display_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Master Repair Jobs table (Separates public-safe fields from internal shop notes)
CREATE TABLE repair_jobs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_reference VARCHAR(30) NOT NULL,
    tracking_token VARCHAR(64) NOT NULL,
    customer_name VARCHAR(120) NOT NULL,
    customer_phone VARCHAR(20) NOT NULL,
    customer_user_id BIGINT NULL,
    device_brand VARCHAR(60) NOT NULL,
    device_model VARCHAR(100) NOT NULL,
    reported_issue TEXT NOT NULL,
    condition_notes TEXT NULL,
    diagnostic_findings TEXT NULL,
    internal_notes TEXT NULL,
    customer_visible_message VARCHAR(500) NULL,
    estimated_cost_min DECIMAL(10, 2) NULL,
    estimated_cost_max DECIMAL(10, 2) NULL,
    approved_quote DECIMAL(10, 2) NULL,
    final_charge DECIMAL(10, 2) NULL,
    advance_paid DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    payment_status VARCHAR(30) NOT NULL DEFAULT 'UNPAID',
    current_status VARCHAR(40) NOT NULL DEFAULT 'RECEIVED',
    expected_completion_at TIMESTAMP NULL,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uk_repair_jobs_reference UNIQUE (job_reference),
    CONSTRAINT uk_repair_jobs_token UNIQUE (tracking_token),
    CONSTRAINT fk_repair_jobs_customer_user FOREIGN KEY (customer_user_id) REFERENCES users(id) ON DELETE SET NULL,
    INDEX idx_repair_jobs_status (current_status),
    INDEX idx_repair_jobs_phone (customer_phone),
    INDEX idx_repair_jobs_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Repair Status History (Timeline of every status update)
CREATE TABLE repair_status_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    repair_job_id BIGINT NOT NULL,
    old_status VARCHAR(40) NULL,
    new_status VARCHAR(40) NOT NULL,
    public_comment VARCHAR(500) NULL,
    internal_comment TEXT NULL,
    changed_by_user_id BIGINT NULL,
    changed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_repair_history_job FOREIGN KEY (repair_job_id) REFERENCES repair_jobs(id) ON DELETE CASCADE,
    CONSTRAINT fk_repair_history_changed_by FOREIGN KEY (changed_by_user_id) REFERENCES users(id) ON DELETE SET NULL,
    INDEX idx_repair_history_job_time (repair_job_id, changed_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;