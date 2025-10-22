package hu.unideb.inf.suitup.repository;

import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardrobeItemRepository extends JpaRepository<WardrobeItemEntity, Long> {
    List<WardrobeItemEntity> findByType(String type);
}
