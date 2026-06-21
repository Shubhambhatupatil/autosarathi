package Autosarthi.demo.controller;

import Autosarthi.demo.entity.ServiceEntity;
import Autosarthi.demo.repository.ServiceRepository;
import Autosarthi.demo.security.JwtUtil;
import Autosarthi.demo.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")
public class ServiceController {

    private final ServiceService serviceService;
    private final JwtUtil jwtUtil;

    public ServiceController(ServiceService serviceService, JwtUtil jwtUtil) {
        this.serviceService = serviceService;
        this.jwtUtil = jwtUtil;
    }

    // 🔹 ADD SERVICE
    @PostMapping("/add")
    public ResponseEntity<ServiceEntity> addService(
            @RequestBody ServiceEntity service,
            @RequestHeader("Authorization") String token) {

        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        ServiceEntity saved = serviceService.addService(service, email);
        return ResponseEntity.ok(saved);
    }

    // 🔹 GET MY SERVICES
    @GetMapping("/my-services")
    public ResponseEntity<List<ServiceEntity>> getMyServices(
            @RequestHeader("Authorization") String token) {

        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        return ResponseEntity.ok(
                serviceService.getServicesByMechanic(email)
        );
    }

    // 🔹 DELETE SERVICE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.ok("Deleted");
    }

}