package hu.unideb.inf.suitup.repository;

import hu.unideb.inf.suitup.entity.OutfitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutfitRepository extends JpaRepository<OutfitEntity, Long> {
}
