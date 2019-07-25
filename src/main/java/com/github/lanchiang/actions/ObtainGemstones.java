package com.github.lanchiang.actions;

import com.github.lanchiang.exceptions.ActionNotExecutableException;
import com.github.lanchiang.game.Player;

/**
 * @author Lan Jiang
 * @since 2019-07-21
 */
public class ObtainGemstones extends PlayerAction {

    public ObtainGemstones(Player player) {
        super(player);
    }

    @Override
    public void execute() throws ActionNotExecutableException {

    }
}
