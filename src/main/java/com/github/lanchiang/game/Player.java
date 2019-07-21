package com.github.lanchiang.game;

import com.github.lanchiang.actions.PlayerAction;
import com.github.lanchiang.components.DevelopmentCard;
import com.github.lanchiang.components.Gemstone;
import lombok.Getter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The player class.
 *
 * @author Lan Jiang
 * @since 2019-07-15
 */
public class Player {

    @Getter
    private List<Gemstone> occupiedGemstones;

    /**
     * Current prestige points owned by this player.
     */
    @Getter
    private int prestigePoints = 0;

    private Set<DevelopmentCard> occupiedCards;

    public Player() {
        occupiedGemstones = new LinkedList<>();
        occupiedCards = new HashSet<>();
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
     * @param game the game instance
     */
    public void perform(Game game) {
        PlayerAction action = null;

        game.getPlayerActions().add(action);
    }

    /**
     * Obtain a development card from the board. This process includes three steps: add the card to the 'hand', recalculate the prestige points,
     * and recover the gemstones in the gemstone pool.
     *
     * @param card the card to be obtained.
     * @param game the game instance used to recover the gemstone pool.
     */
    public void obtainDevelopmentCard(DevelopmentCard card, Game game) {
        occupiedCards.add(card);
        card.setState(DevelopmentCard.CardState.Inhand);

        // consume the gemstones.
        prestigePoints += card.getPrestigePoints();

        // recover the gemstones
        game.getGemstonePool().recoverGemstones(getActualGemstoneCosts(card));
    }

    /**
     * Actual gemstone cost is the number of on-paper cost subtracts the number of owned gemstone cards.
     *
     * @return
     */
    private List<Gemstone> getActualGemstoneCosts(DevelopmentCard card) {
        List<Gemstone> actualCosts = new LinkedList<>();
        return actualCosts;
    }

    /**
     * EXTRA: obtain a noble tile.
     */
    synchronized public void obtainNobleTile() {

    }
}
