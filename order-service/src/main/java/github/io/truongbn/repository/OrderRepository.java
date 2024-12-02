package github.io.truongbn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.io.truongbn.entiry.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
