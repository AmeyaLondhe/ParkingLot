package com.project.parkinglot.service;

import com.project.parkinglot.dto.ParkRequest;
import com.project.parkinglot.dto.ParkResponse;
import com.project.parkinglot.dto.UnparkResponse;
import com.project.parkinglot.entity.ParkingSlot;
import com.project.parkinglot.entity.SlotStatus;
import com.project.parkinglot.entity.Ticket;
import com.project.parkinglot.entity.VehicleType;
import com.project.parkinglot.exception.InvalidTicketException;
import com.project.parkinglot.exception.NoSlotsAvailableException;
import com.project.parkinglot.factory.FeeFactory;
import com.project.parkinglot.factory.SlotAllocationFactory;
import com.project.parkinglot.repository.ParkingSlotRepository;
import com.project.parkinglot.repository.TicketRepository;
import com.project.parkinglot.strategy.fee.FeeStrategy;
import com.project.parkinglot.strategy.slot.SlotAllocationStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ParkingService {

    @Autowired
    ParkingSlotRepository parkingSlotRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    FeeFactory factory;

    @Autowired
    SlotAllocationFactory slotAllocationFactory;

    @Transactional
    public ParkResponse park(ParkRequest vehicle) {
        SlotAllocationStrategy strategy = slotAllocationFactory.getParkingSlot(vehicle.getStrategy());
        ParkingSlot slot =  strategy.allocateSlot(vehicle.getVehicleType());
        slot.setSlotStatus(SlotStatus.OCCUPIED);
        parkingSlotRepository.save(slot);

        String ticketNumber = "TICKET-" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        Ticket ticket = new Ticket(ticketNumber, vehicle.getVehicleNo(), vehicle.getVehicleType(), slot, LocalDateTime.now());
        ticketRepository.save(ticket);

        return new ParkResponse(ticket.getTicketNumber(), slot.getSlotNumber(), slot.getFloorNumber());
    }

    @Transactional
    public UnparkResponse unpark(String ticketNumber) {
        List<Ticket> tickets = ticketRepository.findAll();
        Ticket ticket = ticketRepository.findByTicketNumberAndExitTimeIsNull(ticketNumber)
                .orElseThrow(() -> new InvalidTicketException("Ticket Expired"));

        ParkingSlot slot = ticket.getParkingSlot();
        slot.setSlotStatus(SlotStatus.FREE);
        parkingSlotRepository.save(slot);
        LocalDateTime exitTime = LocalDateTime.now();

        //Parking base charge = 1 hr amount
        long parkingDuration = Math.max(
                                1,
                                Duration.between(ticket.getEntryTime(), exitTime).toHours()
                                );
        FeeStrategy strategy = factory.getFeeStrategy(ticket.getVehicleType());
        Double amount = strategy.calculateFee(parkingDuration);
        ticket.setExitTime(exitTime);
        ticket.setAmount(amount);
        ticketRepository.save(ticket);

        return new UnparkResponse(ticket.getTicketNumber(), parkingDuration, amount);
    }

    public Map<VehicleType, Long> getAvailableSlots() {
        List<Object[]> availableSlots = parkingSlotRepository.getAvailableSlots()
                .orElseThrow(() -> new NoSlotsAvailableException("No slots available"));
        Map<VehicleType, Long> response = new HashMap<>();
        for(Object[] o: availableSlots) {
            VehicleType vehicleType = (VehicleType) o[0];
            Long count = (Long) o[1];
            response.put(vehicleType, count);
        }
        return response;
    }
}
