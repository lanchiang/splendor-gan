package com.github.lanchiang.actions;

import com.github.lanchiang.components.DevelopmentCard;
import com.github.lanchiang.exceptions.ActionNotExecutableException;
import com.github.lanchiang.game.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lan Jiang
 * @since 2019-07-21
 */
public class PlayReservedDevelopmentCard extends PlayerAction {

    public PlayReservedDevelopmentCard(Player player) {
        super(player);
    }

    @Override
    public void execute() throws ActionNotExecutableException {
        List<DevelopmentCard> reservedCards = new ArrayList<>(this.player.getReservedCard());

        Collections.shuffle(reservedCards);
        DevelopmentCard developmentCard = reservedCards.get(0);

        // remove the card from the reserved card deck, add it to the occupied card deck.
        this.player.getReservedCard().remove(developmentCard);

        this.player.obtainDevelopmentCard(developmentCard);
    }
}
