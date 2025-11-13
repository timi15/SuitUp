package hu.unideb.inf.suitup.service.impl;

import hu.unideb.inf.suitup.dto.OutfitFilter;
import hu.unideb.inf.suitup.entity.OutfitEntity;
import hu.unideb.inf.suitup.entity.UserEntity;
import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import hu.unideb.inf.suitup.repository.OutfitRepository;
import hu.unideb.inf.suitup.repository.UserRepository;
import hu.unideb.inf.suitup.repository.WardrobeItemRepository;
import hu.unideb.inf.suitup.service.OutfitService;
import hu.unideb.inf.suitup.specification.OutfitSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutfitServiceImpl implements OutfitService {

    private final OutfitRepository outfitRepository;
    private final WardrobeItemRepository wardrobeItemRepository;
    private final UserRepository userRepository;

    @Override
    public OutfitEntity save(Long userId, OutfitEntity outfit, Long topId, Long pantsId, Long dressId, Long jacketId, Long shoesId, Long accessoryId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        List<WardrobeItemEntity> selectedItems = new ArrayList<>();

        addIfPresent(topId, selectedItems);
        addIfPresent(pantsId, selectedItems);
        addIfPresent(dressId, selectedItems);
        addIfPresent(jacketId, selectedItems);
        addIfPresent(shoesId, selectedItems);
        addIfPresent(accessoryId, selectedItems);

        outfit.setCreatedAt(LocalDate.now());
        outfit.setWardrobeItems(selectedItems);
        outfit.setUser(user);

        return outfitRepository.save(outfit);
    }

    @Override
    public List<OutfitEntity> findAll(Long userId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        return outfitRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public void deleteById(Long userId, Long id) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        OutfitEntity outfit = outfitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Outfit not found with id: " + id));

        if (!outfit.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You cannot delete another user's outfit!");
        }

        outfitRepository.deleteById(id);
    }

    private void addIfPresent(Long itemId, List<WardrobeItemEntity> selectedItems) {
        if (itemId != null) {
            Optional<WardrobeItemEntity> item = wardrobeItemRepository.findById(itemId);
            item.ifPresent(selectedItems::add);
        }
    }

    public List<OutfitEntity> filter(Long userId, OutfitFilter filter) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        Specification<OutfitEntity> spec = Specification
        .where(OutfitSpecification.belongsToUser(user))
        .and(OutfitSpecification.titleContains(filter.getTitle()))
        .and(OutfitSpecification.seasonEquals(filter.getSeason()));


        return outfitRepository.findAll(spec);
    }
}
