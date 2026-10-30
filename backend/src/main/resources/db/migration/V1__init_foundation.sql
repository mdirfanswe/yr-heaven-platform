-- 1. Users table for Shop Owner (ROLE_OWNER) and registered Customers (ROLE_CUSTOMER)
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(150) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(120) NOT NULL,
    phone VARCHAR(20) NULL,
    role VARCHAR(30) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT uk_users_email UNIQUE (email),
    INDEX idx_users_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Single-row Shop Settings table for configurable public information & policies
CREATE TABLE shop_settings (
    id BIGINT PRIMARY KEY,
    business_name VARCHAR(150) NOT NULL,
    address_line VARCHAR(255) NOT NULL,
    phone_whatsapp VARCHAR(20) NOT NULL,
    busy_status VARCHAR(30) NOT NULL DEFAULT 'NORMAL',
    opening_hours_text VARCHAR(255) NULL,
    holiday_notice VARCHAR(255) NULL,
    repair_estimate_disclaimer TEXT NOT NULL,
    repair_policy_note TEXT NULL,
    printing_policy_note TEXT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Seed verified shop details (leaving unconfirmed hours and policies NULL so the owner configures them)
INSERT INTO shop_settings (
    id,
    business_name,
    address_line,
    phone_whatsapp,
    busy_status,
    opening_hours_text,
    holiday_notice,
    repair_estimate_disclaimer,
    repair_policy_note,
    printing_policy_note
) VALUES (
    1,
    'YR Heaven Mobile Hub and Sublimation Printing',
    'Opposite Gramin Bank, Chhota Sheikpura, Narhat, Nawada, Bihar, India',
    '9576545759',
    'NORMAL',
    NULL,
    NULL,
    'Displayed prices and turnaround times are indicative estimates only. Final repair charges and completion time are confirmed after physical inspection of the device.',
    NULL,
    NULL
);