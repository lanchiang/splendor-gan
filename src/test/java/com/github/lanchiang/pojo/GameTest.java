package com.github.lanchiang.pojo;

import com.github.lanchiang.exceptions.ActionNotExecutableException;
import com.github.lanchiang.game.GameBox;
import org.junit.Test;

/**
 * @author Lan Jiang
 * @since 2019-07-25
 */
public class GameTest {

    @Test(expected = ActionNotExecutableException.class)
    public void testAlwaysObtainDevelopmentCard() throws ActionNotExecutableException {
        GameBox gameBox = new GameBox();
        gameBox.run(2);
    }
}
