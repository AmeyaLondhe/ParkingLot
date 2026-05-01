-- V1__create_parking_tables.sql

CREATE TABLE parking_slot (
                              id BIGSERIAL PRIMARY KEY,
                              slot_number VARCHAR(20) UNIQUE NOT NULL,
                              floor_number INT NOT NULL,
                              vehicle_type VARCHAR(20) NOT NULL,
                              slot_status VARCHAR(20) NOT NULL,
                              version BIGINT DEFAULT 0
);

CREATE TABLE ticket (
                        id BIGSERIAL PRIMARY KEY,
                        ticket_number VARCHAR(50) UNIQUE NOT NULL,
                        vehicle_number VARCHAR(30) NOT NULL,
                        vehicle_type VARCHAR(20) NOT NULL,
                        slot_id BIGINT NOT NULL,
                        entry_time TIMESTAMP NOT NULL,
                        exit_time TIMESTAMP NULL,
                        amount DOUBLE PRECISION,

                        CONSTRAINT fk_ticket_slot
                            FOREIGN KEY (slot_id)
                                REFERENCES parking_slot(id)
);