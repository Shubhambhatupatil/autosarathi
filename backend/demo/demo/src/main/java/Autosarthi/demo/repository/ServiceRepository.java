package Autosarthi.demo.repository;

import Autosarthi.demo.dto.ServiceResponseDTO;
import Autosarthi.demo.entity.Mechanic;
import Autosarthi.demo.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    List<ServiceEntity> findByMechanic(Mechanic mechanic);

    List<ServiceEntity> findByServiceType(String serviceType);

    @Query("""
       SELECT new Autosarthi.demo.dto.ServiceResponseDTO(
           s.id,
           s.serviceName,
           s.description,
           s.price,
           m.garageName,
           m.phone,
           m.city
       )
       FROM ServiceEntity s
       JOIN s.mechanic m
       WHERE s.serviceType = :type
       """)
    List<ServiceResponseDTO> findServicesByType(@Param("type") String type);
}