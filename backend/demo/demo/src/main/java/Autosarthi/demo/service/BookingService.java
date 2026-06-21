package Autosarthi.demo.service;

import Autosarthi.demo.entity.Booking;
import Autosarthi.demo.entity.Mechanic;
import Autosarthi.demo.entity.ServiceEntity;
import Autosarthi.demo.repository.BookingRepository;
import Autosarthi.demo.repository.MechanicRepository;
import Autosarthi.demo.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository;
    private final MechanicRepository mechanicRepository;
    private final EmailService emailService; // Added the missing field declaration

    public BookingService(BookingRepository bookingRepository,
                          ServiceRepository serviceRepository,
                          MechanicRepository mechanicRepository,
                          EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.serviceRepository = serviceRepository;
        this.mechanicRepository = mechanicRepository;
        this.emailService = emailService;
    }

    // CREATE BOOKING
    public Booking createBooking(Long serviceId, Booking booking) {

        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        booking.setService(service);
        booking.setMechanic(service.getMechanic());
        booking.setStatus("PENDING");

        // 1. Save the booking first to ensure the UUID token is generated
        Booking savedBooking = bookingRepository.save(booking);

        // 2. Generate the tracking URL dynamically using the saved token
        // Change "http://localhost:5500/track.html" to your actual frontend URL if different
        String baseTrackingUrl = "http://localhost:5500/track.html?token=";
        String dynamicTrackingLink = baseTrackingUrl + savedBooking.getBookingToken();

        // 3. Trigger the email automatically
        try {
            emailService.sendTrackingEmail(
                    savedBooking.getCustomerEmail(),
                    savedBooking.getCustomerName(),
                    dynamicTrackingLink
            );
        } catch (Exception e) {
            // Log the error but don't fail the whole booking process if email server fails
            System.err.println("Failed to send tracking email: " + e.getMessage());
        }

        return savedBooking;
    }

    // GET BOOKINGS FOR MECHANIC
    public List<Booking> getMechanicBookings(String email) {

        Mechanic mechanic = mechanicRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Mechanic not found"));

        return bookingRepository.findByMechanic(mechanic);
    }

    // GET BOOKINGS FOR USER
    public List<Booking> getUserBookings(String email) {
        return bookingRepository.findByCustomerEmail(email);
    }

    // UPDATE STATUS
    public Booking updateStatus(Long id, String status) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);
        return bookingRepository.save(booking);
    }
}