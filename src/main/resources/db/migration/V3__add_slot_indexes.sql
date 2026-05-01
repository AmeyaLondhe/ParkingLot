-- V3__add_slot_indexes.sql

CREATE INDEX idx_slot_search
    ON parking_slot (
                     vehicle_type,
                     slot_status,
                     floor_number
        );