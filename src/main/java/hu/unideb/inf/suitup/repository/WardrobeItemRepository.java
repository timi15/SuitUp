package hu.unideb.inf.suitup.repository;

import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardrobeItemRepository extends JpaRepository<WardrobeItemEntity, Long>, JpaSpecificationExecutor<WardrobeItemEntity> {

    List<WardrobeItemEntity> findByUserId(Long useId);

    List<WardrobeItemEntity> findByUserIdAndType(Long userId, String type);

    @Query(value = """
        SELECT wi.*
        FROM outfits_wardrobe_items owi
                 INNER JOIN wardrobe_items wi ON wi.id = owi.wardrobe_id
        WHERE wi.user_id = :user
        GROUP BY wi.id
        ORDER BY COUNT(owi.wardrobe_id) DESC
        LIMIT 10
        """, nativeQuery = true)
    List<WardrobeItemEntity> findFavouriteWardrobeItems(@Param("user") Long userId);

    List<WardrobeItemEntity> findByUserIdAndSeason(Long userId, String season);
}
