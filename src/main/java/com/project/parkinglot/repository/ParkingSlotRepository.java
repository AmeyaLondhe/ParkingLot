package com.project.parkinglot.repository;

import com.project.parkinglot.entity.ParkingSlot;
import com.project.parkinglot.entity.SlotStatus;
import com.project.parkinglot.entity.VehicleType;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT p FROM ParkingSlot p
            WHERE p.vehicleType = :vehicleType
            and p.slotStatus = :slotStatus
            order by p.floorNumber asc
        """)
    Optional<List<ParkingSlot>> findAvailableSlotsforUpdate(VehicleType vehicleType, SlotStatus slotStatus);

    @Query("""
        SELECT p.vehicleType, count(p)
        FROM ParkingSlot p
        WHERE p.slotStatus = 'FREE'
        GROUP BY p.vehicleType
     """)
    Optional<List<Object[]>> getAvailableSlots();

}
