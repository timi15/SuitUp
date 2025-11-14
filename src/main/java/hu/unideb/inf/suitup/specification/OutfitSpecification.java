package hu.unideb.inf.suitup.specification;

import hu.unideb.inf.suitup.entity.OutfitEntity;
import hu.unideb.inf.suitup.entity.UserEntity;

import org.springframework.data.jpa.domain.Specification;

public class OutfitSpecification {

    public static Specification<OutfitEntity> titleContains(String title) {
        return (root, query, cb) -> {
            if (title == null || title.isBlank()) return null;
            return cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
        };
    }

    public static Specification<OutfitEntity> seasonEquals(String season) {
        return (root, query, cb) -> {
            if (season == null || season.isBlank()) return null;
            return cb.equal(root.get("season"), season);
        };
    }

    public static Specification<OutfitEntity> belongsToUser(UserEntity user) {
        return (root, query, cb) -> cb.equal(root.get("user"), user);
    }
}
