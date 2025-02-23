package uz.alfabu.bookrecommendationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alfabu.bookrecommendationapp.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
