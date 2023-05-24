package uz.market.yuksalish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.market.yuksalish.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.userName =:userName ")
    User findByLogin(@Param("userName")String userName);

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String username);
}
