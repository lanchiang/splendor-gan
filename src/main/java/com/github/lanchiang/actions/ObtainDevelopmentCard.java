package com.github.lanchiang.actions;

import com.github.lanchiang.components.DevelopmentCard;
import com.github.lanchiang.game.Game;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Lan Jiang
 * @since 2019-07-21
 */
public class ObtainDevelopmentCard extends PlayerAction {

    @Override
    public void execute(Game game) {
        Map<Integer, List<DevelopmentCard>> displayedCards = game.getDevelopmentCardPool().getDisplayedCardsByLevel();

        // Todo: select a card. Now randomly select a card.
        // keep only affordable cards.
        List<DevelopmentCard> cards = displayedCards.values().stream().flatMap(List::stream).filter(this::affordable).collect(Collectors.toList());

        DevelopmentCard obtainedCard = cards.get(new Random(System.currentTimeMillis()).nextInt(cards.size()));

        // add the card to the player's hand.
        game.getCurrentPlayer().obtainDevelopmentCard(obtainedCard);

        // fill the empty slot with a new development card.
        game.getDevelopmentCardPool().displayNewCard(obtainedCard.getCardLevel());
    }

    private boolean affordable(DevelopmentCard developmentCard) {
        return false;
    }
}
