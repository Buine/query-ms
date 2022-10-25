CREATE TABLE IF NOT EXISTS "query"
(
    id BIGSERIAL PRIMARY KEY,
    code UUID UNIQUE NOT NULL,
    integration_code UUID NOT NULL,
    name TEXT NOT NULL,
    status TEXT NOT NULL,
    sql TEXT NOT NULL,
    schema JSONB,
    json JSONB,
    checksum TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_query_integration_code
    ON query(integration_code);