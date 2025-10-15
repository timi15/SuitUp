package hu.unideb.inf.suitup.service.impl;

import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import hu.unideb.inf.suitup.repository.WardrobeItemRepository;
import hu.unideb.inf.suitup.service.WardrobeItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WardrobeItemServiceImpl implements WardrobeItemService {

    final WardrobeItemRepository wardrobeItemRepository;

    @Override
    public WardrobeItemEntity save(WardrobeItemEntity wardrobeItemEntity) {
        return wardrobeItemRepository.save(wardrobeItemEntity);
    }
}
