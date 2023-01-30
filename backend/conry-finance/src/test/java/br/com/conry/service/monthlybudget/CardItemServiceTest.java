package br.com.conry.service.monthlybudget;

import br.com.conry.domain.model.monthlybudget.CardItem;
import br.com.conry.domain.repository.monthlybudget.CardItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = CardItemService.class)
class CardItemServiceTest {

    @Autowired
    CardItemService cardItemService;

    @Autowired
    CardItemRepository cardItemRepository;

    CardItem cardItem;


}