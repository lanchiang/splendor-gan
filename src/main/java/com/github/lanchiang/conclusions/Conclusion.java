package com.github.lanchiang.conclusions;

import com.github.lanchiang.game.Game;
import com.github.lanchiang.game.Player;
import lombok.Getter;

/**
 * Conclude a game by adding the players' game profiles.
 *
 * @author Lan Jiang
 * @since 2019-07-21
 */
public class Conclusion {

    @Getter
    private Player[] players;

    protected Conclusion(Game game) {
        players = game.getPlayers();
    }
}
