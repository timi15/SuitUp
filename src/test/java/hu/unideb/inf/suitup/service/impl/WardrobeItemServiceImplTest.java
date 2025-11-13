package hu.unideb.inf.suitup.service.impl;

import hu.unideb.inf.suitup.entity.OutfitEntity;
import hu.unideb.inf.suitup.entity.UserEntity;
import hu.unideb.inf.suitup.entity.WardrobeItemEntity;
import hu.unideb.inf.suitup.repository.OutfitRepository;
import hu.unideb.inf.suitup.repository.UserRepository;
import hu.unideb.inf.suitup.repository.WardrobeItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class WardrobeItemServiceImplTest {

    @Mock
    private WardrobeItemRepository wardrobeItemRepository;

    @Mock
    private OutfitRepository outfitRepository;

    @Mock
    private UserRepository userRepository;

    @Spy
    @InjectMocks
    private WardrobeItemServiceImpl wardrobeItemService;

    private UserEntity user;
    private WardrobeItemEntity wardrobeItem;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setId(1L);
        user.setUsername("testuser");

        wardrobeItem = new WardrobeItemEntity();
        wardrobeItem.setId(10L);
        wardrobeItem.setType("felső");
        wardrobeItem.setColor("piros");
        wardrobeItem.setSeason("tavasz");
        wardrobeItem.setBrand("Zara");
        wardrobeItem.setMaterial("pamut");
    }

    // ---------------------------
    // UT-01: WardrobeItem mentés
    // ---------------------------
    @Test
    void save_ShouldAssignUserAndSaveItem() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(wardrobeItemRepository.save(any(WardrobeItemEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        WardrobeItemEntity saved = wardrobeItemService.save(1L, wardrobeItem);

        // then
        assertNotNull(saved);
        assertEquals(user, saved.getUser());
        verify(wardrobeItemRepository, times(1)).save(wardrobeItem);
    }

    // ---------------------------
    // UT-02: Ruhadarab frissítés
    // ---------------------------
    @Test
    void update_ShouldModifyFieldsAndSave() {
        // given
        WardrobeItemEntity existing = new WardrobeItemEntity();
        existing.setId(10L);
        existing.setUser(user);

        WardrobeItemEntity updated = new WardrobeItemEntity();
        updated.setImageUrl("new.jpg");
        updated.setType("nadrág");
        updated.setColor("kék");
        updated.setSeason("nyár");
        updated.setBrand("Levi’s");
        updated.setMaterial("farmer");

        when(wardrobeItemRepository.findById(10L)).thenReturn(Optional.of(existing));

        when(wardrobeItemRepository.save(any(WardrobeItemEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        WardrobeItemEntity result = wardrobeItemService.update(1L, 10L, updated);

        // then
        assertEquals("nadrág", result.getType());
        assertEquals("kék", result.getColor());
        assertEquals("Levi’s", result.getBrand());
        verify(wardrobeItemRepository).save(existing);
    }

    // ---------------------------
    // UT-03: Ruhadarab törlés
    // ---------------------------
    @Test
    void deleteById_ShouldRemoveItemAndRelatedOutfits() {
        // given
        WardrobeItemEntity item = new WardrobeItemEntity();
        item.setId(10L);
        item.setUser(user);

        OutfitEntity outfit1 = new OutfitEntity();
        outfit1.setWardrobeItems(List.of(item));

        OutfitEntity outfit2 = new OutfitEntity();
        outfit2.setWardrobeItems(List.of());

        when(wardrobeItemRepository.findById(10L)).thenReturn(Optional.of(item));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(outfitRepository.findAllByUser(user)).thenReturn(List.of(outfit1, outfit2));

        // when
        wardrobeItemService.deleteById(1L, 10L);

        // then
        verify(outfitRepository, times(1)).deleteAll(anyList());

        verify(wardrobeItemRepository, times(1)).deleteById(10L);
    }
}
