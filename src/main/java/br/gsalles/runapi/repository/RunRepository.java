package br.gsalles.runapi.repository;

import br.gsalles.runapi.model.Run;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RunRepository extends JpaRepository<Run, Long> {

    Page<Run> findByUserId(Long id, Pageable pageable);
    Page<Run> findByDistanceGreaterThan(Double distance, Pageable pageable);
    Page<Run> findByDistanceLessThan(Double distance, Pageable pageable);
    Page<Run> findByStartDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
    Page<Run> findByUserIdAndDistanceGreaterThan(Long id, Double distance, Pageable pageable);
    Page<Run> findByUserIdAndDistanceLessThan(Long id, Double distance, Pageable pageable);
    Page<Run> findByUserIdAndStartDateBetween(Long id, LocalDateTime start, LocalDateTime end, Pageable pageable);
}
