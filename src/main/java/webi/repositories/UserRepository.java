package webi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webi.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer>{
    @Query("select u from User u join fetch u.roles where u.email=:email")
    Optional<User> findUsersByEmail(String email);
}
