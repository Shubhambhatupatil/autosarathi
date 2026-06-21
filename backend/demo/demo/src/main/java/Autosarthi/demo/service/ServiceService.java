package Autosarthi.demo.service;

import Autosarthi.demo.entity.Mechanic;
import Autosarthi.demo.entity.ServiceEntity;
import Autosarthi.demo.repository.MechanicRepository;
import Autosarthi.demo.repository.ServiceRepository;
import Autosarthi.demo.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final MechanicRepository mechanicRepository;

    public ServiceService(ServiceRepository serviceRepository,
                          MechanicRepository mechanicRepository) {
        this.serviceRepository = serviceRepository;
        this.mechanicRepository = mechanicRepository;
    }

    // 🔹 ADD SERVICE
    public ServiceEntity addService(ServiceEntity service, String email) {

        Mechanic mechanic = mechanicRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Mechanic not found"));

        service.setMechanic(mechanic);

        return serviceRepository.save(service);
    }

    // 🔹 GET SERVICES BY LOGGED MECHANIC
    public List<ServiceEntity> getServicesByMechanic(String email) {

        Mechanic mechanic = mechanicRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Mechanic not found"));

        return serviceRepository.findByMechanic(mechanic);
    }

    // 🔹 DELETE
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}