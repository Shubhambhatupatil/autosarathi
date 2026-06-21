package Autosarthi.demo.controller;

import Autosarthi.demo.entity.Booking;
import Autosarthi.demo.repository.BookingRepository;
import Autosarthi.demo.security.JwtUtil;
import Autosarthi.demo.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;
    private final JwtUtil jwtUtil;

    // Fixed constructor to properly receive and assign the repository
    public BookingController(BookingService bookingService,
                             BookingRepository bookingRepository,
                             JwtUtil jwtUtil) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
        this.jwtUtil = jwtUtil;
    }

    // ✅ USER BOOK SERVICE
    @PostMapping("/{serviceId}")
    public Booking bookService(@PathVariable Long serviceId,
                               @RequestBody Booking booking) {

        return bookingService.createBooking(serviceId, booking);
    }

    // ✅ MECHANIC VIEW BOOKINGS
    @GetMapping("/mechanic")
    public List<Booking> getMechanicBookings(
            @RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractEmail(token.substring(7));
        return bookingService.getMechanicBookings(email);
    }

    // ✅ USER VIEW BOOKINGS
    @GetMapping("/user")
    public List<Booking> getUserBookings(
            @RequestParam String email) {

        return bookingService.getUserBookings(email);
    }

    // ✅ UPDATE STATUS (Mechanic)
    @PutMapping("/{id}")
    public Booking updateStatus(@PathVariable Long id,
                                @RequestParam String status) {

        return bookingService.updateStatus(id, status);
    }

    // ✅ TRACK GUEST BOOKING (No Token/Login Needed)
    @GetMapping("/track/{token}")
    public ResponseEntity<Booking> getBookingByToken(@PathVariable String token) {
        return bookingRepository.findByBookingToken(token)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
