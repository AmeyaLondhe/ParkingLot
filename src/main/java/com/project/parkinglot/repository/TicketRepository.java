package com.project.parkinglot.repository;

import com.project.parkinglot.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketNumber(String ticketNumber);
    Optional<Ticket> findByTicketNumberAndExitTimeIsNull(
            String ticketNumber
    );
}
