package com.project.parkinglot.controller;

import com.project.parkinglot.dto.ParkRequest;
import com.project.parkinglot.dto.ParkResponse;
import com.project.parkinglot.dto.UnparkResponse;
import com.project.parkinglot.entity.VehicleType;
import com.project.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @PostMapping("/park")
    public ResponseEntity<ParkResponse> park(@RequestBody ParkRequest vehicle) {
        return ResponseEntity.ok(
                parkingService.park(vehicle)
        );
    }

    @PostMapping("/unpark")
    public ResponseEntity<UnparkResponse> unpark(@RequestParam String ticketNumber) {
        return ResponseEntity.ok(
                parkingService.unpark(ticketNumber)
        );
    }

    @GetMapping("/availability")
    public ResponseEntity<Map<VehicleType, Long>> getAvailability(){
        return ResponseEntity.ok(
                parkingService.getAvailableSlots()
        );
    }
}
