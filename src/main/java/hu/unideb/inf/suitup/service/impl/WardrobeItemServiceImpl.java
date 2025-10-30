package hu.unideb.inf.suitup.service.impl;

import hu.unideb.inf.suitup.entity.OutfitEntity;
import hu.unideb.inf.suitup.entity.UserEntity;
import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import hu.unideb.inf.suitup.repository.OutfitRepository;
import hu.unideb.inf.suitup.repository.UserRepository;
import hu.unideb.inf.suitup.repository.WardrobeItemRepository;
import hu.unideb.inf.suitup.service.WardrobeItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WardrobeItemServiceImpl implements WardrobeItemService {

    private final WardrobeItemRepository wardrobeItemRepository;
    private final OutfitRepository outfitRepository;
    private final UserRepository userRepository;


    @Override
    public WardrobeItemEntity save(Long userId, WardrobeItemEntity wardrobeItemEntity) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        wardrobeItemEntity.setUser(user);
        return wardrobeItemRepository.save(wardrobeItemEntity);
    }

    @Override
    public List<WardrobeItemEntity> findAll(Long userId) {
        return wardrobeItemRepository.findByUserId(userId);
    }

    @Override
    public WardrobeItemEntity findById(Long userId, Long id) {

        WardrobeItemEntity item = wardrobeItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wardrobe item not found: " + id));

        if (!item.getUser().getId().equals(userId)) {
            throw new SecurityException("Access denied for this item!");
        }

        return item;
    }

    @Override
    public List<WardrobeItemEntity> findByType(Long userId, String type) {
        return wardrobeItemRepository.findByUserIdAndType(userId, type );
    }

    @Override
    public WardrobeItemEntity update(Long userId, Long id, WardrobeItemEntity updatedEntity) {
        WardrobeItemEntity existing = findById(userId, id);

        existing.setImageUrl(updatedEntity.getImageUrl());
        existing.setType(updatedEntity.getType());
        existing.setColor(updatedEntity.getColor());
        existing.setSeason(updatedEntity.getSeason());
        existing.setBrand(updatedEntity.getBrand());
        existing.setMaterial(updatedEntity.getMaterial());

        return wardrobeItemRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteById(Long userId,Long id) {
        WardrobeItemEntity item = findById(userId, id);
        UserEntity user = userRepository.findById(userId).get();

        List<OutfitEntity> outfitsToDelete = outfitRepository.findAllByUser(user).stream()
                .filter(outfit -> outfit.getWardrobeItems().contains(item))
                .toList();

        outfitRepository.deleteAll(outfitsToDelete);
        wardrobeItemRepository.deleteById(id);

    }

}
