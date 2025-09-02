package uz.pdp.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.doctor.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}