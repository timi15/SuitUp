package hu.unideb.inf.suitup.service;

import hu.unideb.inf.suitup.entity.WardrobeItemEntity;

import java.util.List;

public interface WardrobeItemService {

    WardrobeItemEntity save(Long userId, WardrobeItemEntity wardrobeItemEntity);

    List<WardrobeItemEntity> findAll(Long userId);

    WardrobeItemEntity findById(Long userId, Long id);

    List<WardrobeItemEntity> findByType(Long userId, String type);

    WardrobeItemEntity update(Long userId, Long id, WardrobeItemEntity updatedEntity);

    void deleteById(Long userId, Long id);

}
