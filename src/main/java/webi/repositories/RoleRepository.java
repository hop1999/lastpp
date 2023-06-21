package webi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
