package br.gsalles.runapi.repository;

import br.gsalles.runapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFullnameContains(String name);

    Optional<User> findByEmail(String email);
}
