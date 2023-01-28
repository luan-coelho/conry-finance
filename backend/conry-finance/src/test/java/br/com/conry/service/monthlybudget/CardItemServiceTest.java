package br.com.conry.service.monthlybudget;

import br.com.conry.domain.model.monthlybudget.CardItem;
import br.com.conry.domain.repository.monthlybudget.CardItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(classes = CardItemService.class)
class CardItemServiceTest {

    @Autowired
    CardItemService cardItemService;

    @MockBean
    CardItemRepository cardItemRepository;

    CardItem cardItem;

    @BeforeEach
    void setUp() {
        cardItem = new CardItem();
        cardItem.setId(1L);
        cardItem.setDescription("Aluguel");
        cardItem.setAmount(new BigDecimal("540.00"));
        cardItem.setEventDateTime(LocalDateTime.now());
    }

    @Test
    @DisplayName("Verifica se está retornando cardItem pelo id")
    void verificaSeEstaRetornandoCardItemPeloId() {
        // Arrange
        when(cardItemRepository.findById(1L)).thenReturn(Optional.ofNullable(cardItem));

        // Act
        CardItem cardItemDataBase = cardItemService.findById(1L);

        // Assert
        assertEquals(cardItemDataBase, cardItem);
    }

    @Test
    @DisplayName("Verifica se está lançando excessão caso não encontre cardItem pelo id")
    void verificaSeEstaLancandoExcessaoCasoNaoEncontreCardItemPeloId() {
        // Arrange
        when(cardItemRepository.findById(2L)).thenReturn(Optional.ofNullable(cardItem));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> cardItemService.findById(1L));
    }

    @Test
    @DisplayName("Verifica integridade de cardItem pelo id")
    void verificaIntegridadeCardItemPeloId() {
        // Arrange
        when(cardItemRepository.findById(1L)).thenReturn(Optional.ofNullable(cardItem));

        // Act
        CardItem cardItemDataBase = cardItemService.findById(1L);
        // Assert
        assertEquals(cardItemDataBase.getId(), 1L);
        assertEquals(cardItemDataBase.getDescription(), "Aluguel");
        assertEquals(cardItemDataBase.getAmount(), new BigDecimal("540.00"));
        assertEquals(cardItemDataBase.getEventDateTime().toLocalTime().truncatedTo(ChronoUnit.MINUTES), LocalDateTime.now().toLocalTime().truncatedTo(ChronoUnit.MINUTES));
    }
}