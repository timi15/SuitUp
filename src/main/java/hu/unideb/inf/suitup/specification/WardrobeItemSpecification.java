package hu.unideb.inf.suitup.specification;

import hu.unideb.inf.suitup.dto.WardrobeItemFilter;
import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import org.springframework.data.jpa.domain.Specification;

public class WardrobeItemSpecification {

    public static Specification<WardrobeItemEntity> filter(Long userId, WardrobeItemFilter filter) {
        return Specification.where(hasUser(userId))
                .and(hasType(filter.getType()))
                .and(hasSeason(filter.getSeason()))
                .and(hasColor(filter.getColor()))
                .and(hasBrand(filter.getBrand()))
                .and(hasMaterial(filter.getMaterial()));
    }

    private static Specification<WardrobeItemEntity> hasUser(Long userId) {
        return (root, query, builder) -> builder.equal(root.get("user").get("id"), userId);
    }

    private static Specification<WardrobeItemEntity> hasType(String type) {
        return (root, query, builder) ->
                type == null || type.isEmpty() ? null :
                builder.equal(root.get("type"), type);
    }

    private static Specification<WardrobeItemEntity> hasSeason(String season) {
        return (root, query, builder) ->
                season == null || season.isEmpty() ? null :
                builder.equal(root.get("season"), season);
    }

    private static Specification<WardrobeItemEntity> hasColor(String color) {
        return (root, query, builder) ->
                color == null || color.isEmpty() ? null :
                builder.equal(root.get("color"), color);
    }

    private static Specification<WardrobeItemEntity> hasBrand(String brand) {
        return (root, query, builder) ->
                brand == null || brand.isEmpty() ? null :
                builder.equal(root.get("brand"), brand);
    }

    private static Specification<WardrobeItemEntity> hasMaterial(String material) {
        return (root, query, builder) ->
                material == null || material.isEmpty() ? null :
                builder.equal(root.get("material"), material);
    }
}
