# Hotel-Management-System
HMS(Hotel Management System)

## Overview
This is a Java-based Hotel Management System that runs in the terminal and connects to a MySQL database using JDBC. It allows hotel staff or admins to:

- Add new reservations
- View all reservations
- View specific room details
- Update reservation details
- Cancel (delete) a reservation

## Technologies Used
- Java
- JDBC
- MySQL
- Command Line Interface (CLI)

## How to Run
## Example Output

```text
HOTEL MANAGEMENT
DRIVER LOADED SUCCESSFULLY
connection successfully done

<---------------------> WELCOME TO HOTEL MANAGEMENT SYSTEM<------------------------->
Services !!!
1-------> New Reservation
2-------> View All Reservation IN Hotel
3-------> View Room Detailed
4-------> Update Reservation detailed
5-------> Close Reservation
0-------> Exist


### Requirements:
- JDK 8 or later
- MySQL server running
- Database named `hotal_management` with a table named `reservations`

### Database Table Structure (example):
```sql
CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(100),
    room_number INT,
    contact_number VARCHAR(20),
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

