package br.gsalles.runapi.service;

import br.gsalles.runapi.dto.RunDTO;
import br.gsalles.runapi.exception.EntityNotFoundException;
import br.gsalles.runapi.model.Location;
import br.gsalles.runapi.model.Run;
import br.gsalles.runapi.model.User;
import br.gsalles.runapi.repository.RunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                () -> new EntityNotFoundException("Run not found")
        );
        run.setEndDate(LocalDateTime.now());
        return run;
    }

    @Transactional(readOnly = true)
    public Page<Run> findAll(int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return runRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Run findById(Long id) {
        return runRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Run not found")
        );
    }

    @Transactional(readOnly = true)
    public Page<Run> findByUserId(Long id, int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return runRepository.findByUserId(id, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Run> findByDistanceGreaterThan(Double distance, int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return runRepository.findByDistanceGreaterThan(distance, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Run> findByDistanceLessThan(Double distance, int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return runRepository.findByDistanceLessThan(distance, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Run> findByStartDateBetween(LocalDateTime start, LocalDateTime end, int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return runRepository.findByStartDateBetween(start, end, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Run> findByUserIdAndDistanceGreaterThan(Long id, Double distance, int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return runRepository.findByUserIdAndDistanceGreaterThan(id, distance, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Run> findByUserIdAndDistanceLessThan(Long id, Double distance, int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return runRepository.findByUserIdAndDistanceLessThan(id, distance, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Run> findByUserIdAndStartDateBetween(Long id, LocalDateTime start, LocalDateTime end, int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return runRepository.findByUserIdAndStartDateBetween(id, start, end, pageable);
    }
}
