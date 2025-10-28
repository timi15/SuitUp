package hu.unideb.inf.suitup.repository;

import hu.unideb.inf.suitup.entity.OutfitEntity;
import hu.unideb.inf.suitup.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutfitRepository extends JpaRepository<OutfitEntity, Long> {

    List<OutfitEntity> findAllByUser(UserEntity userEntity);

}
