Parking Lot Management System

A production-style backend project built using Java + Spring Boot + PostgreSQL to simulate a real-world Parking Lot Management System.

This project was designed from an LLD (Low Level Design) perspective and then implemented as a working backend application with clean architecture, design patterns, REST APIs, concurrency handling, and schema migrations.

🚀 Features
    Core Functionalities
    Park a vehicle
    Unpark a vehicle
    Auto-generate parking ticket
    Dynamic fee calculation based on vehicle type
    Slot allocation using pluggable strategies
    View available slots by vehicle type

🚗 Supported Vehicle Types
    CAR
    BIKE
    TRUCK
    
🏗️ Tech Stack
    Java 17
    Spring Boot
    Spring Data JPA
    PostgreSQL
    Flyway
    Maven
    Lombok
    
🧠 Design Patterns Used
    1️⃣ Strategy Pattern
    
    Used for flexible business rules.
    
    Pricing Strategy
    
    Different fee calculation logic for:
    
    Car
    Bike
    Truck
    Slot Allocation Strategy
    
    Different parking slot selection policies:
    
    Nearest Slot Strategy
    Random Slot Strategy
    2️⃣ Factory Pattern
    
    Used to select the correct implementation at runtime.
    
    PricingStrategyFactory
    
    Returns correct fee strategy based on vehicle type.
    
    SlotAllocationFactory
    
    Returns correct slot allocation strategy based on request.

🗄️ Database Design
    Tables
    parking_slot
    Stores parking slot information:
    slot number
    floor number
    vehicle type supported
    slot status
    
    ticket
    Stores parking ticket information:
    ticket number
    vehicle number
    entry time
    exit time
    amount
    allocated slot
    
🔄 Schema Management
    Handled using Flyway Migrations
    
    Migration Files
    V1__create_parking_tables.sql
    V2__seed_parking_slots.sql
    V3__add_slot_indexes.sql
    
🌐 REST APIs
    Park Vehicle
    POST /api/parking/park
    Request Body
    {
      "vehicleNo": "MH12AB1234",
      "vehicleType": "CAR",
      "strategyType": "NEAREST"
    }
    Unpark Vehicle
    POST /api/parking/unpark?ticketNumber=TICKET-123
    Check Availability
    GET /api/parking/availability
    Sample Response
    {
      "CAR": 8,
      "BIKE": 24,
      "TRUCK": 5
    }
    
🔒 Concurrency Handling

    Used database locking + transactions to avoid double booking of the same parking slot during concurrent requests.
    
    Concepts used:
    
    @Transactional
    Pessimistic locking (PESSIMISTIC_WRITE)
    JPA versioning (@Version)
    
📁 Project Structure
    controller/
    dto/
    entity/
    repository/
    service/
    strategy/
    factory/
    exception/
    
🎯 LLD Concepts Covered
    Domain modeling
    Entity relationships
    Extensible architecture
    Separation of concerns
    Strategy-based behavior changes
    Factory-based object selection
    Transaction management
    
💡 Example Flow
    Park Vehicle
    Receive vehicle request
    Choose slot allocation strategy
    Lock and allocate free slot
    Mark slot occupied
    Generate ticket
    Unpark Vehicle
    Fetch ticket
    Calculate parked duration
    Apply pricing strategy
    Free slot
    Save payment details
    
▶️ Run Locally
    1. Clone Repository
    git clone "https://github.com/AmeyaLondhe/ParkingLot.git"
    cd ParkingLot
    2. Configure PostgreSQL

Update:
    application.properties
    
    with your DB credentials.
    
    3. Run Application
    mvn spring-boot:run
    
🔥 Why This Project Is Valuable

    This is not a basic CRUD project.
    
    It demonstrates:
    
    Real backend design
    Practical LLD implementation
    Design patterns in production code
    Concurrency handling
    Database migrations
    Clean REST APIs
    
📌 Future Enhancements
    Admin dashboard
    Reservation system
    EV charging slots
    Notification service
    Kafka event integration
    Payment gateway integration
