package br.gsalles.runapi.repository;

import br.gsalles.runapi.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RunRepository extends JpaRepository<Run, Long> {

    List<Run> findByUserId(Long id);
    List<Run> findByDistanceGreaterThan(Double distance);
    List<Run> findByDistanceLessThan(Double distance);
    List<Run> findByStartDateBetween(LocalDateTime start, LocalDateTime end);
    List<Run> findByUserIdAndDistanceGreaterThan(Long id, Double distance);
    List<Run> findByUserIdAndDistanceLessThan(Long id, Double distance);
    List<Run> findByUserIdAndStartDateBetween(Long id, LocalDateTime start, LocalDateTime end);
}
