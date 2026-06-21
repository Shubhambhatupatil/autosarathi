package Autosarthi.demo.controller;

import Autosarthi.demo.dto.ServiceResponseDTO;
import Autosarthi.demo.entity.ServiceEntity;
import Autosarthi.demo.repository.ServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@CrossOrigin("*")
public class PublicServiceController {

    private final ServiceRepository serviceRepository;

    public PublicServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // ✅ CAR WASH SERVICES
    @GetMapping("/car-wash-services")
    public List<ServiceResponseDTO> getCarWashServices() {
        return serviceRepository.findServicesByType("CAR_WASH");
    }

    // ✅ AUTO SERVICES
    @GetMapping("/auto-services")
    public List<ServiceResponseDTO> getAutoServices() {
        return serviceRepository.findServicesByType("AUTO_SERVICE");
    }
}
