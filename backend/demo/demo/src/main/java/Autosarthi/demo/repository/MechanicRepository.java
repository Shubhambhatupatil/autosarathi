package Autosarthi.demo.repository;

import Autosarthi.demo.entity.Mechanic;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MechanicRepository extends JpaRepository<Mechanic, Long> {

    Optional<Mechanic> findByEmail(String email);

    @Query("SELECT m FROM Mechanic m LEFT JOIN FETCH m.services WHERE LOWER(m.specialization) = LOWER(:type)")
    List<Mechanic> findWithServicesBySpecialization(@Param("type") String type);

}
