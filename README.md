# airline-management-system
A Java Swing–based Airline Management System that provides an interactive GUI for managing airline operations such as passenger registration, flight booking, ticket cancellation, and payment processing.
This project is developed as a college mini project and focuses on Java Swing GUI development, event handling, and basic application workflow.

Features

Passenger registration and management
Flight booking using PNR
Flight class selection:
Economy Class
Business Class
First Class
Ticket viewing and cancellation

Payment module with multiple options:
UPI
Credit Card
Debit Card
Net Banking
UPI QR Code–based payment simulation
UPI payment screenshot upload
Payment confirmation after successful upload
User-friendly GUI using Java Swing
Input validation and basic error handling

Technologies Used
Java
Java Swing
AWT
JDBC 
MySQL 

Project Structure

airline-management-system/
├── src/
│   ├── Login.java
│   ├── AddCustomer.java
│   ├── BookFlight.java
│   ├── JourneyDetails.java
│   ├── TicketCancellation.java
│   ├── Payment.java
│   ├── PaymentUPI.java
│   ├── PaymentCard.java
│   └── Main.java
├── icons/
├── screenshots/
└── README.md

How to Run the Project
1.Clone the repository
git clone https://github.com/anushkaraghav24/airline-management-system.git
2.Open the project in any Java IDE
Eclipse
IntelliJ IDEA
NetBeans
3.Make sure Java (JDK 8 or above) is installed on your system.
4.Run the project by executing:
Main.java

## Screenshots
## Home Screen

The welcome screen of the Airline Management System displaying a landing aircraft image and a greeting message.  
This screen serves as the main dashboard entry point after login.

![Home Screen](screenshots/home-welcome-screen.png)

### Login Page
![Login Page](screenshots/login.png)

### Passenger Details
![Passenger Details](screenshots/passenger-details.png)

### Flight Booking & Class Selection
![Flight Booking](screenshots/class-selection.png)

### Payment Options
![Payment Options](screenshots/payment-options.png)

### UPI Payment & Screenshot Upload
![UPI Payment](screenshots/upi-upload-success.png)

Project Reference

This project was initially developed by following a Java Airline Management System playlist for learning purposes.

The tutorial was used strictly as a learning reference, and the project was later extended and customized independently.

Enhancements Made by Me

Added Flight Class Selection (Economy, Business, First Class)
Implemented UPI Payment Module
Integrated QR Code Scanner simulation for UPI payments
Added UPI payment screenshot upload feature
Displayed successful payment confirmation message
Improved payment workflow without affecting existing icons or images

All enhancements were independently implemented to extend the original project functionality.

Project Use Case
Java Mini Project
Academic Assignment
Internship / Resume Project
Learning Java Swing & GUI Development

Author
Anushka Raghav
B.Tech CSE Cyber Security Student

GitHub: https://github.com/anushkaraghav24

License
This project is created for educational purposes only.
No commercial use intended.
