package br.gsalles.runapi.repository;

import br.gsalles.runapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByFullnameContains(String name, Pageable pageable);

    Optional<User> findByEmail(String email);
}
