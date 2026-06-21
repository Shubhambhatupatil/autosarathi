package Autosarthi.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

    @Entity
    @Table(name = "bookings")
    public class Booking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // 👤 Customer Details
        private String customerName;
        private String customerPhone;
        private String customerEmail;
        private String address;
        private String timeSlot;
        private String trackingToken;
        private String bookingToken;


        private String status; // PENDING, VIEWED, CONFIRMED

        // 🔗 Service Mapping
        @ManyToOne
        @JoinColumn(name = "service_id")
        private ServiceEntity service;

        // 🔗 Mechanic Mapping
        @ManyToOne
        @JoinColumn(name = "mechanic_id")
        @JsonIgnore
        private Mechanic mechanic;


        public Booking() {
        }

        public Booking(String customerName, String customerPhone, String customerEmail,
                       String address, String timeSlot, String status) {
            this.customerName = customerName;
            this.customerPhone = customerPhone;
            this.customerEmail = customerEmail;
            this.address = address;
            this.timeSlot = timeSlot;
            this.status = status;
        }


        // Helper Transient Getters so your JavaScript can read flat fields easily
        @Transient
        public String getServiceName() {
            return this.service != null ? this.service.getServiceName() : "Not Assigned";
        }

        @Transient
        public String getMechanicName() {
            return this.mechanic != null ? this.mechanic.getOwnerName() : "Not Assigned";
        }

        @Transient
        public String getGarageName() {
            return this.mechanic != null ? this.mechanic.getGarageName() : "Not Assigned";
        }


        public Long getId() {
            return id;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTimeSlot() {
            return timeSlot;
        }

        public void setTimeSlot(String timeSlot) {
            this.timeSlot = timeSlot;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public ServiceEntity getService() {
            return service;
        }

        public void setService(ServiceEntity service) {
            this.service = service;
        }

        public Mechanic getMechanic() {
            return mechanic;
        }

        public void setMechanic(Mechanic mechanic) {
            this.mechanic = mechanic;
        }

        public String getTrackingToken() {
            return trackingToken;
        }

        public void setTrackingToken(String trackingToken) {
            this.trackingToken = trackingToken;
        }

        public String getBookingToken() {
            return bookingToken;
        }
        public void setBookingToken(){
            this.bookingToken = bookingToken;
        }

        @PrePersist
        public void generateToken() {
            if (this.bookingToken == null) {
                this.bookingToken = java.util.UUID.randomUUID().toString();
            }
        }
    }
