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

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

/**
 * A pagination event handler from keyboard arrow button
 * <p>
 *     The purpose of this event handler is to intercept
 *     OS key strokes for arrow navigation to change the
 *     picture on gallery.
 * </p>
 * <p>
 *     This event handler is recommend to define on {@link javafx.scene.Scene}.
 *     e.g.
 *     <pre>{@code
 *     scene.addEventFilter(
 *         KeyEvent.KEY_PRESSED,
 *         new KeyboardPaginationEventHandler(view.parent())
 *     );
 *     }</pre>
 * </p>
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class KeyboardPaginationEventHandler implements EventHandler<KeyEvent> {

    private final Node target;

    /**
     * Creates new KeyboardPaginationEventHandler.
     *
     * @param tgt the {@link Node} target.
     */
    public KeyboardPaginationEventHandler(final Node tgt) {
        this.target = tgt;
    }

    @Override
    public void handle(final KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
            case DOWN:
                Event.fireEvent(
                    this.target,
                    new PaginationEvent(PaginationEvent.Movement.NEXT)
                );
                break;
            case LEFT:
            case UP:
                Event.fireEvent(
                    this.target,
                    new PaginationEvent(PaginationEvent.Movement.PREVIOUS)
                );
                break;
            default:
                break;
        }
        event.consume();
    }
}
