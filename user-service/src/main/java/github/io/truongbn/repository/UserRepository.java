package github.io.truongbn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import github.io.truongbn.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    @Query("UPDATE User i SET i.balance = :newBalance WHERE i.id = :userId")
    int updateUserBalanceByUserId(String userId, double newBalance);
}
