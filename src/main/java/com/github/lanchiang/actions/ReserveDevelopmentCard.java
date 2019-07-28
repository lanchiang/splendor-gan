package com.github.lanchiang.actions;

import com.github.lanchiang.components.DevelopmentCard;
import com.github.lanchiang.exceptions.ActionNotExecutableException;
import com.github.lanchiang.game.Player;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lan Jiang
 * @since 2019-07-21
 */
public class ReserveDevelopmentCard extends PlayerAction {

    public ReserveDevelopmentCard(Player player) {
        super(player);
    }

    @Override
    public void execute() throws ActionNotExecutableException {
        // Todo: choose a card to reserve.
        Map<Integer, List<DevelopmentCard>> displayedCards = player.getGame().getDevelopmentCardPool().getDisplayedCardsByLevel();

        if (player.getReservedCard().size() == 3) {
            throw new ActionNotExecutableException("Cannot reserve more than three cards at the same time.");
        }

        List<DevelopmentCard> developmentCards = displayedCards.values().stream().flatMap(List::stream).collect(Collectors.toList());
        Collections.shuffle(developmentCards);
        DevelopmentCard developmentCard = developmentCards.get(0);

        // add the card to the player's reservation set.
        player.obtainReservedCard(developmentCard);

        // fill the empty slot with a new development card.
        player.getGame().getDevelopmentCardPool().updateDevelopmentCardDeck(developmentCard);
    }
}
