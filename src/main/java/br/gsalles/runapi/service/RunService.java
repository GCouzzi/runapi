package br.gsalles.runapi.service;

import br.gsalles.runapi.dto.RunDTO;
import br.gsalles.runapi.model.Location;
import br.gsalles.runapi.model.Run;
import br.gsalles.runapi.model.User;
import br.gsalles.runapi.repository.RunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RunService {

    private final RunRepository runRepository;
    private final UserService userService;
    private final LocationService locationService;
    private final RouteService routeService;

    @Transactional
    public Run create(RunDTO runDTO) {

        Run run = new Run();

        Location startLocation = locationService.findByName(runDTO.getStartLocationName());
        Location endLocation = locationService.findByName(runDTO.getEndLocationName());
        User user = userService.findByEmail(runDTO.getUserEmail());

        run.setStartLocation(startLocation);
        run.setEndLocation(endLocation);
        run.setUser(user);
        run.setStartDate(LocalDateTime.now());
        run.setDistance(routeService.getDistance(startLocation, endLocation));

        return runRepository.save(run);
    }

    @Transactional
    public Run end(Long id) {
        Run run = runRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Run not found")
        );
        run.setEndDate(LocalDateTime.now());
        return run;
    }

    @Transactional(readOnly = true)
    public List<Run> findAll() {
        return runRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Run findById(Long id) {
        return runRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Run not found")
        );
    }

    @Transactional(readOnly = true)
    public List<Run> findByUserId(Long id) {
        return runRepository.findByUserId(id);
    }

    @Transactional(readOnly = true)
    public List<Run> findByDistanceGreaterThan(Double distance) {
        return runRepository.findByDistanceGreaterThan(distance);
    }

    @Transactional(readOnly = true)
    public List<Run> findByDistanceLessThan(Double distance) {
        return runRepository.findByDistanceLessThan(distance);
    }

    @Transactional(readOnly = true)
    public List<Run> findByStartDateBetween(LocalDateTime start, LocalDateTime end) {
        return runRepository.findByStartDateBetween(start, end);
    }

    @Transactional(readOnly = true)
    public List<Run> findByUserIdAndDistanceGreaterThan(Long id, Double distance) {
        return runRepository.findByUserIdAndDistanceGreaterThan(id, distance);
    }

    @Transactional(readOnly = true)
    public List<Run> findByUserIdAndDistanceLessThan(Long id, Double distance) {
        return runRepository.findByUserIdAndDistanceLessThan(id, distance);
    }

    @Transactional(readOnly = true)
    public List<Run> findByUserIdAndStartDateBetween(Long id, LocalDateTime start, LocalDateTime end) {
        return runRepository.findByUserIdAndStartDateBetween(id, start, end);
    }
}
