package Autosarthi.demo.controller;

import Autosarthi.demo.entity.Mechanic;
import Autosarthi.demo.security.JwtUtil;
import Autosarthi.demo.service.MechanicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Autosarthi.demo.dto.LoginRequest;

import java.util.List;


@RestController
@RequestMapping("/api/mechanics")
@CrossOrigin(origins = "*")
public class MechanicController {

    private final MechanicService mechanicService;
    private final JwtUtil jwtUtil;

    public MechanicController(MechanicService mechanicService, JwtUtil jwtUtil) {
        this.mechanicService = mechanicService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Mechanic mechanic) {
        try {
            Mechanic saved = mechanicService.registerMechanic(mechanic);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return mechanicService.login(request.getEmail(), request.getPassword());
    }

    @GetMapping("/profile")
    public Mechanic getProfile(@RequestHeader("Authorization") String token) {

        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        return mechanicService.getByEmail(email);
    }

    @PutMapping("/update")
    public ResponseEntity<Mechanic> updateProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody Mechanic updatedMechanic) {

        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        Mechanic mechanic = mechanicService.getByEmail(email);

        mechanic.setGarageName(updatedMechanic.getGarageName());
        mechanic.setOwnerName(updatedMechanic.getOwnerName());
        mechanic.setPhone(updatedMechanic.getPhone());
        mechanic.setCity(updatedMechanic.getCity());

        return ResponseEntity.ok(mechanicService.save(mechanic));
    }

    @GetMapping("/by-specialization")
    public ResponseEntity<
            List<Mechanic>> getBySpecialization(
            @RequestParam String type) {

        return ResponseEntity.ok(
                mechanicService.getBySpecialization(type)
        );
    }

}

