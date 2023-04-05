package by.beatdev.beatdevtesttask.repositories;





import by.beatdev.beatdevtesttask.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findById(long id);

}