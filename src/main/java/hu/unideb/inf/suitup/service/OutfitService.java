package hu.unideb.inf.suitup.service;

import hu.unideb.inf.suitup.entity.OutfitEntity;

import java.util.List;

public interface OutfitService {
    OutfitEntity save(OutfitEntity outfit,
                      Long topId,
                      Long pantsId,
                      Long dressId,
                      Long jacketId,
                      Long shoesId,
                      Long accessoryId);

    List<OutfitEntity> findAll();

    void deleteById(Long id);
}
