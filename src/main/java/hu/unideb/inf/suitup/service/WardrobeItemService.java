package hu.unideb.inf.suitup.service;

import hu.unideb.inf.suitup.entity.WardrobeItemEntity;

import java.util.List;

public interface WardrobeItemService {

    WardrobeItemEntity save(WardrobeItemEntity wardrobeItemEntity);

    List<WardrobeItemEntity> findAll();

    WardrobeItemEntity findById(Long id);

    List<WardrobeItemEntity> findByType(String type);

    WardrobeItemEntity update(Long id, WardrobeItemEntity updatedEntity);

    void deleteById(Long id);

}
