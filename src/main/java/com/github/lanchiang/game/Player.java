package com.github.lanchiang.game;

import com.github.lanchiang.actions.PlayerAction;
import com.github.lanchiang.components.DevelopmentCard;
import com.github.lanchiang.components.Gemstone;
import com.github.lanchiang.components.NobleTile;
import com.github.lanchiang.message.GemstoneCostMessage;
import lombok.Getter;

import java.util.*;

/**
 * The player class.
 *
 * @author Lan Jiang
 * @since 2019-07-15
 */
public class Player {

    @Getter
    private Map<Gemstone, Integer> occupiedGemstones;

    /**
     * Current prestige points owned by this player.
     */
    @Getter
    private int prestigePoints = 0;

    /**
     * The development cards this player has bought so far.
     */
    @Getter
    private Set<DevelopmentCard> occupiedCards;

    @Getter
    private Set<NobleTile> occupiedNobleTiles;

    /**
     * The game that this player attends.
     */
    @Getter
    private Game game;

    public Player(Game game) {
        occupiedGemstones = new HashMap<>();
        occupiedGemstones.putIfAbsent(Gemstone.Emerald, 0);
        occupiedGemstones.putIfAbsent(Gemstone.Diamond, 0);
        occupiedGemstones.putIfAbsent(Gemstone.Sapphire, 0);
        occupiedGemstones.putIfAbsent(Gemstone.Onyx, 0);
        occupiedGemstones.putIfAbsent(Gemstone.Ruby, 0);
        occupiedGemstones.putIfAbsent(Gemstone.GoldJoker, 0);

        occupiedCards = new HashSet<>();

        this.game = game;
    }

    /**
     * The player performs an action according to the current game situation. There are four optional actions:
     * 1) obtain gemstones: a player can obtain three gemstones all in different colors, or two in the same color if there are greater or equal to four remain.
     *      a player may only keep at maximum 10 gemstones in hand.
     * 2) reserve a card: a player can reserve a card in the displayed cards and take a gold joker if available.
     *      A player cannot have more than three reserved cards at the same time.
     * 3) obtain a card from the displayed cards if the player has enough gemstone resources.
     * 4) play out a reserved card if the player has enough gemstone resources.
     *
     * EXTRA: at the end of a player's turn, he/she may get a noble tile if having enough gemstone cards for it.
     *      A player may get only one noble tile per turn, even if multiple ones are available for him/her. (Note: this is not considered as an action)
     *
     */
    public void perform() {
        PlayerAction action = null;

        action.execute();
        game.getPlayerActions().add(action);
        tryObtainNobleTile();
    }

    /**
     * Obtain a development card from the board. This process includes three steps: add the card to the 'hand', recalculate the prestige points,
     * and recover the gemstones in the gemstone pool.
     *
     * @param card the card to be obtained.
     */
    public void obtainDevelopmentCard(DevelopmentCard card) {
        occupiedCards.add(card);
        card.setState(DevelopmentCard.CardState.Inhand);

        // consume the gemstones.
        prestigePoints += card.getPrestigePoints();

        // rebalance the gemstones
        GemstoneCostMessage actualCost = getActualGemstoneCosts(card);
        this.getOccupiedGemstones().entrySet().forEach(entry -> entry.setValue(entry.getValue() - actualCost.getCost(entry.getKey())));
        game.getGemstonePool().recoverGemstones(actualCost);
    }

    /**
     * Actual gemstone cost is the number of on-paper cost subtracts the number of owned gemstone cards.
     *
     * @return
     */
    private GemstoneCostMessage getActualGemstoneCosts(DevelopmentCard card) {
        Map<Gemstone, Integer> actualCosts = new HashMap<>();
        int goldJokerConsumption = 0;
        for (Gemstone gemstone : Gemstone.values()) {
            int remains = card.getCosts().get(gemstone) - occupiedGemstones.get(gemstone);
            if (remains < 0) {
                actualCosts.putIfAbsent(gemstone, occupiedGemstones.get(gemstone));
                goldJokerConsumption += (0 - remains);
            } else {
                actualCosts.putIfAbsent(gemstone, card.getCosts().get(gemstone));
            }
        }
        actualCosts.putIfAbsent(Gemstone.GoldJoker, goldJokerConsumption);

        return new GemstoneCostMessage(
                actualCosts.get(Gemstone.Emerald),
                actualCosts.get(Gemstone.Diamond),
                actualCosts.get(Gemstone.Sapphire),
                actualCosts.get(Gemstone.Onyx),
                actualCosts.get(Gemstone.Ruby),
                actualCosts.get(Gemstone.GoldJoker)
        );
    }

    /**
     * EXTRA: check if it's possible to obtain a noble tile. When yes, take one.
     */
    synchronized public void tryObtainNobleTile() {
        Optional<NobleTile> firstPossible = game.getDisplayedNobleTiles().stream().filter(this::nobleTileAffordable).sorted().findFirst();
        if (firstPossible.isPresent()) {
            this.occupiedNobleTiles.add(firstPossible.get());
            this.prestigePoints += firstPossible.get().getPrestigePoints();
        }
    }

    public boolean nobleTileAffordable(NobleTile nobleTile) {
        return nobleTile.getCosts().entrySet().stream().anyMatch(entry -> occupiedGemstones.get(entry.getKey()) < entry.getValue());
    }
}
