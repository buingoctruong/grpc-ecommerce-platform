package github.io.truongbn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import github.io.truongbn.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Optional<Inventory> findByItemId(String itemId);

    @Transactional
    @Modifying
    @Query("UPDATE Inventory i SET i.quantities = i.quantities - :purchaseQuantities WHERE i.itemId = :itemId")
    int updateQuantitiesByItemId(@Param("itemId") String itemId,
            @Param("purchaseQuantities") long purchaseQuantities);
}
