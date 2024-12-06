package skinsAPI.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skinsAPI.domain.dtos.request.ColourUpdateRequest;
import skinsAPI.domain.entities.Skin;
import skinsAPI.exceptions.SkinNotFoundException;
import skinsAPI.repository.SkinRepository;
import skinsAPI.service.impl.SkinServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkinServiceUnitTest {

    @Mock
    private SkinRepository skinRepository;
    @InjectMocks
    private SkinServiceImpl skinService;
    private Skin skin1;
    private Skin skin2;

    @BeforeEach
    void setUp() {
        skin1 = Skin.builder().id(1L).name("skin1").type("rare").price(10).colour("red").build();
        skin2 = Skin.builder().id(2L).name("skin2").type("ultra").price(20).colour("blue").build();
    }

    @AfterEach
    void tearDown() {
        skin1 = skin2 = null;
    }

    @Test
    void findAll_should_return_skin_list() {
        when(skinRepository.findAll()).thenReturn(Arrays.asList(skin1, skin2));
        List<Skin> skinList = skinService.findAll();
        assertEquals(2, skinList.size());
        verify(skinRepository).findAll();
    }

    @Test
    void findById_should_return_skin() {
        when(skinRepository.findById(1L)).thenReturn(Optional.of(skin1));
        Skin returnedSkin = skinService.findById(1L);
        assertEquals(skin1.getId(), returnedSkin.getId());
        assertEquals(skin1.getName(), returnedSkin.getName());
        verify(skinRepository).findById(1L);
    }

    @Test
    void findById_should_not_return_skin_whenExceptionIsThrown() {
        Skin skin = Skin.builder().id(5L).name("patata").type("not").price(50).colour("pink").build();
        SkinNotFoundException skinNotFoundException = assertThrows(SkinNotFoundException.class,
                () -> skinService.findById(skin.getId()));

        assertEquals("Skin not found: invalid id", skinNotFoundException.getMessage());
    }

    @Test
    void save_should_insert_new_skin() {
        skinService.create(skin1);
        verify(skinRepository, times(1)).save(skin1);
        ArgumentCaptor<Skin> skinArgumentCaptor = ArgumentCaptor.forClass(Skin.class);
        verify(skinRepository).save(skinArgumentCaptor.capture());
        Skin skinCreated = skinArgumentCaptor.getValue();
        assertNotNull(skinCreated.getId());
        assertEquals("skin1", skinCreated.getName());
    }

    @Test
    void save_should_update_existing_Skin() {
        when(skinRepository.findById(1L)).thenReturn(Optional.of(skin1));
        when(skinRepository.save(any(Skin.class))).thenReturn(skin1);
        ColourUpdateRequest request = new ColourUpdateRequest("pink");
        Skin updatedSkin = skinService.update(request, 1L);
        assertEquals("pink", updatedSkin.getColour());
    }

    @Test
    void deleteById_should_delete_skin() {
        skinService.deleteById(skin1.getId());
        verify(skinRepository, times(1)).deleteById(skin1.getId());
        ArgumentCaptor<Long> skinArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(skinRepository).deleteById(skinArgumentCaptor.capture());
        Long skinIdDeleted = skinArgumentCaptor.getValue();
        assertNotNull(skinIdDeleted);
        assertEquals(1L, skinIdDeleted);
    }
}
