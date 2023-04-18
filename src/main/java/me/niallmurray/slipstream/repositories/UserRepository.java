package me.niallmurray.slipstream.repositories;


import me.niallmurray.slipstream.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select u from User u"
          + " left join fetch u.authorities"
          + " where u.username = :username")
  User findByUsername(String username);

  User findByEmail(String email);

}
