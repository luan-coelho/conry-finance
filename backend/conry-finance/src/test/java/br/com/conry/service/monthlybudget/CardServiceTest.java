package br.com.conry.service.monthlybudget;

import br.com.conry.commons.MontlyBudgetComponents;
import br.com.conry.domain.model.monthlybudget.Card;
import br.com.conry.domain.model.monthlybudget.CardItem;
import br.com.conry.domain.repository.monthlybudget.CardItemRepository;
import br.com.conry.domain.repository.monthlybudget.CardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * This class is responsible for testing the card service
 */
@ActiveProfiles("test")
@SpringBootTest
class CardServiceTest {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardItemRepository cardItemRepository;

    Card card;

    @BeforeEach
    void setup() {
        card = MontlyBudgetComponents.buildCard("Test");
        card = cardRepository.save(card);
    }

    @Test
    @DisplayName("Validates the creation and insertion of an item on a card")
    void testAddItem_whenValidInput_CardItemAddedSucessfully() {
        // Arrange
        CardItem cardItem = MontlyBudgetComponents.buildCardItem("Test");

        // Act
        card.addItem(cardItem);
        card = cardRepository.save(card);

        // Assert
        Assertions.assertThat(card.getCardItems()).isNotNull();
        Assertions.assertThat(card.getCardItems().size()).isEqualTo(2);
        Assertions.assertThat(card.getCardItems().get(1).getDescription()).isEqualTo("Test");
    }

    @Test
    void removeItem() {
    }
}