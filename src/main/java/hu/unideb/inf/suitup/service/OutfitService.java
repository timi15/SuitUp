package hu.unideb.inf.suitup.service;

import hu.unideb.inf.suitup.dto.OutfitFilter;
import hu.unideb.inf.suitup.entity.OutfitEntity;

import java.util.List;

public interface OutfitService {
    OutfitEntity save(Long userId,
                      OutfitEntity outfit,
                      Long topId,
                      Long pantsId,
                      Long dressId,
                      Long jacketId,
                      Long shoesId,
                      Long accessoryId);

    List<OutfitEntity> findAll(Long userId);

    void deleteById(Long userId, Long id);

    List<OutfitEntity> filter(Long userId, OutfitFilter filter);
}
