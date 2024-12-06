package github.io.truongbn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import github.io.truongbn.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.balance = u.balance - :orderAmount WHERE u.id = :userId")
    int updateUserBalanceByUserId(@Param("userId") String userId,
            @Param("orderAmount") double orderAmount);
}
