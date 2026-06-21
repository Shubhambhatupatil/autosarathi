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

    public BookingService(BookingRepository bookingRepository,
                          ServiceRepository serviceRepository,
                          MechanicRepository mechanicRepository) {
        this.bookingRepository = bookingRepository;
        this.serviceRepository = serviceRepository;
        this.mechanicRepository = mechanicRepository;
    }

    public Booking createBooking(Long serviceId, Booking booking) {

        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        booking.setService(service);
        booking.setMechanic(service.getMechanic());
        booking.setStatus("PENDING");

        return bookingRepository.save(booking);
    }

    public List<Booking> getMechanicBookings(String email) {

        Mechanic mechanic = mechanicRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Mechanic not found"));

        return bookingRepository.findByMechanic(mechanic);
    }

    public List<Booking> getUserBookings(String email) {
        return bookingRepository.findByCustomerEmail(email);
    }

    public Booking updateStatus(Long id, String status) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);
        return bookingRepository.save(booking);
    }
}
