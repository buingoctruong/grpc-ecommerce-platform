package github.io.truongbn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.io.truongbn.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
