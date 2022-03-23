/*
 * Nayanika is a picture viewer application.
 * Copyright (C) 2022  Sukma Wardana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.swardana.nayanika.gui.event;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 * Unit test for {@link KeyboardPaginationEventHandler}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
@ExtendWith(ApplicationExtension.class)
class KeyboardPaginationEventHandlerTest {

    @Start
    private void start(final Stage stage) {
        var parent = new StackPane();
        var scene = new Scene(parent, 100, 100);
        scene.addEventFilter(
            KeyEvent.KEY_PRESSED,
            new KeyboardPaginationEventHandler(parent)
        );
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("verify pressed keyboard arrow right")
    public void testVerifyPressedKeyboardArrowRight(final FxRobot robot) {
        robot.press(KeyCode.RIGHT);
    }

}