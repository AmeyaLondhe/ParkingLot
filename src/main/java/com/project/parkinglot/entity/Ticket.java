package com.project.parkinglot.entity;

import com.project.parkinglot.dto.ParkRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ticket_number", nullable = false)
    private String ticketNumber;

    @Column(name = "vehicle_number",  nullable = false)
    private String vehicleNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type",  nullable = false)
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id", nullable = false)
    private ParkingSlot parkingSlot;

    @Column(name = "entry_time", nullable = false)
    private LocalDateTime entryTime;

    @Column(name="exit_time", nullable = true)
    private LocalDateTime exitTime;

    @Column(name = "amount", nullable = true)
    private Double amount;

    public Ticket(String ticketNumber, String vehicleNumber, VehicleType vehicleType, ParkingSlot parkingSlot, LocalDateTime entryTime) {
        this.ticketNumber = ticketNumber;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.parkingSlot = parkingSlot;
        this.entryTime = entryTime;
    }
}
