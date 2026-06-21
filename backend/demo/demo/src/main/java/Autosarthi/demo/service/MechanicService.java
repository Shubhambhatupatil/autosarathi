package Autosarthi.demo.service;

import Autosarthi.demo.entity.Mechanic;
import Autosarthi.demo.repository.MechanicRepository;
import org.springframework.stereotype.Service;
import Autosarthi.demo.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@Service
public class MechanicService {

    private final MechanicRepository mechanicRepository;
    private JwtUtil jwtUtil;

    public MechanicService(MechanicRepository mechanicRepository, JwtUtil jwtUtil) {
        this.mechanicRepository = mechanicRepository;
        this.jwtUtil = jwtUtil;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Mechanic registerMechanic(Mechanic mechanic) {

        if (mechanicRepository.findByEmail(mechanic.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        mechanic.setPassword(encoder.encode(mechanic.getPassword()));

        return mechanicRepository.save(mechanic);
    }


    public String login(String email, String password) {

        Mechanic mechanic = mechanicRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(password, mechanic.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(email);
    }

    public Mechanic getByEmail(String email) {
        return mechanicRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public Mechanic save(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public List<Mechanic> getBySpecialization(String type) {
        return mechanicRepository.findWithServicesBySpecialization(type);
    }
}
