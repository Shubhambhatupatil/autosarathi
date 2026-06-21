package Autosarthi.demo.repository;

import Autosarthi.demo.entity.Booking;
import Autosarthi.demo.entity.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    //Get bookings for mechanic
    List<Booking> findByMechanic(Mechanic mechanic);

    //Get bookings for user
    List<Booking> findByCustomerEmail(String email);

    Optional<Booking>
    findByTrackingToken(String token);

    Optional<Booking> findByBookingToken(String bookingToken);
}
