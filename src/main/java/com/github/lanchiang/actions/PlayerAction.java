package com.github.lanchiang.actions;

import com.github.lanchiang.game.Player;
import lombok.Getter;

/**
 * Abstract class for all the player actions.
 *
 * @author Lan Jiang
 * @since 2019-07-21
 */
abstract public class PlayerAction {

    /**
     * The player who executes this action.
     */
    protected Player player;

    abstract public void execute();
}
