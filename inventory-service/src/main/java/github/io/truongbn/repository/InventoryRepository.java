package github.io.truongbn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import github.io.truongbn.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Optional<Inventory> findByItemId(String itemId);

    @Transactional
    @Query("UPDATE Inventory i SET i.quantities = :newQuantity WHERE i.id = :itemId")
    int updateQuantitiesByItemId(String itemId, long newQuantity);
}
