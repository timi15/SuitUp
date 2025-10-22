package hu.unideb.inf.suitup.service.impl;

import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import hu.unideb.inf.suitup.repository.WardrobeItemRepository;
import hu.unideb.inf.suitup.service.WardrobeItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WardrobeItemServiceImpl implements WardrobeItemService {

    final WardrobeItemRepository wardrobeItemRepository;

    @Override
    public WardrobeItemEntity save(WardrobeItemEntity wardrobeItemEntity) {
        return wardrobeItemRepository.save(wardrobeItemEntity);
    }

    @Override
    public List<WardrobeItemEntity> findAll() {
        return wardrobeItemRepository.findAll();
    }

    @Override
    public WardrobeItemEntity findById(Long id) {
        return wardrobeItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wardrobe item not found: " + id));
    }

    @Override
    public List<WardrobeItemEntity> findByType(String type) {
        return wardrobeItemRepository.findByType(type);
    }

    @Override
    public WardrobeItemEntity update(Long id, WardrobeItemEntity updatedEntity) {
        WardrobeItemEntity existing = findById(id);
        existing.setImageUrl(updatedEntity.getImageUrl());
        existing.setType(updatedEntity.getType());
        existing.setColor(updatedEntity.getColor());
        existing.setSeason(updatedEntity.getSeason());
        existing.setBrand(updatedEntity.getBrand());
        existing.setMaterial(updatedEntity.getMaterial());
        return wardrobeItemRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        wardrobeItemRepository.deleteById(id);
    }

}
