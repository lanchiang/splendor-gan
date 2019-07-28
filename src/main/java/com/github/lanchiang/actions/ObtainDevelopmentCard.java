package com.github.lanchiang.actions;

import com.github.lanchiang.components.DevelopmentCard;
import com.github.lanchiang.components.Gemstone;
import com.github.lanchiang.exceptions.ActionNotExecutableException;
import com.github.lanchiang.game.Player;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lan Jiang
 * @since 2019-07-21
 */
public class ObtainDevelopmentCard extends PlayerAction {

    public ObtainDevelopmentCard(Player player) {
        super(player);
    }

    @Override
    public void execute() throws ActionNotExecutableException {
        Map<Integer, List<DevelopmentCard>> displayedCards = player.getGame().getDevelopmentCardPool().getDisplayedCardsByLevel();

        // Todo: select a card. Now randomly select a card.
        // keep only affordable cards.
        List<DevelopmentCard> affordableCards = displayedCards.values().stream().flatMap(List::stream).filter(this::affordable).collect(Collectors.toList());
        if (affordableCards.isEmpty()) {
            throw new ActionNotExecutableException("This action cannot be executed."); // Wrap a runtime exception, called action not executable exception.
        }
        Collections.shuffle(affordableCards);
        DevelopmentCard obtainedCard = affordableCards.get(0);

        // add the card to the player's hand.
        player.obtainDevelopmentCard(obtainedCard);

        // fill the empty slot with a new development card.
        player.getGame().getDevelopmentCardPool().updateDevelopmentCardDeck(obtainedCard);
    }

    /**
     * Return false if this player does not have enough gemstone resources to purchase this development card.
     * @param developmentCard the development card to be checked.
     * @return false if this player does not have enough gemstone resources to purchase this development card.
     */
    private boolean affordable(DevelopmentCard developmentCard) {
        Map<Gemstone, Integer> gemstones = player.getOccupiedGemstones();
        Map<Gemstone, List<DevelopmentCard>> cardAmountByGemstone = player.getOccupiedCards().stream().collect(Collectors.groupingBy(DevelopmentCard::getBenefit));

        Iterator<Map.Entry<Gemstone, Integer>> costIterator = developmentCard.getCosts().entrySet().iterator();
        boolean affordable = true;
        int remainGoldJoker = player.getOccupiedGemstones().get(Gemstone.GoldJoker);
        while (costIterator.hasNext()) {
            Map.Entry<Gemstone, Integer> nextGemstone = costIterator.next();
            int owned = gemstones.get(nextGemstone.getKey()) + cardAmountByGemstone.getOrDefault(nextGemstone.getKey(), new LinkedList<>()).size();
           ;
            // if not enough
            if (nextGemstone.getValue() > owned) {
                remainGoldJoker -= (nextGemstone.getValue() - owned);
            }
            if (remainGoldJoker < 0) {
                affordable = false;
                break;
            }
        }
        return affordable;
    }
}
