package hu.unideb.inf.suitup.service.impl;

import hu.unideb.inf.suitup.entity.OutfitEntity;
import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import hu.unideb.inf.suitup.repository.OutfitRepository;
import hu.unideb.inf.suitup.repository.WardrobeItemRepository;
import hu.unideb.inf.suitup.service.OutfitService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    @Override
    public OutfitEntity save(OutfitEntity outfit, Long topId, Long pantsId, Long dressId, Long jacketId, Long shoesId, Long accessoryId) {
        List<WardrobeItemEntity> selectedItems = new ArrayList<>();

        addIfPresent(topId, selectedItems);
        addIfPresent(pantsId, selectedItems);
        addIfPresent(dressId, selectedItems);
        addIfPresent(jacketId, selectedItems);
        addIfPresent(shoesId, selectedItems);
        addIfPresent(accessoryId, selectedItems);

        outfit.setCreatedAt(LocalDate.now());
        outfit.setWardrobeItems(selectedItems);

        return outfitRepository.save(outfit);
    }

    @Override
    public List<OutfitEntity> findAll() {
        return outfitRepository.findAll();
    }

    private void addIfPresent(Long itemId, List<WardrobeItemEntity> selectedItems) {
        if (itemId != null) {
            Optional<WardrobeItemEntity> item = wardrobeItemRepository.findById(itemId);
            item.ifPresent(selectedItems::add);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        outfitRepository.deleteById(id);
    }
}
