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
import javafx.event.EventType;

import java.util.Optional;

/**
 * A picture pagination event.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class PaginationEvent extends Event {

    public static final EventType<PaginationEvent> MOVE
        = new EventType<>(ANY, "MOVE");

    public enum Movement {
        NEXT, PREVIOUS, BEGINNING, LAST
    }

    private final Movement movement;

    /**
     * Creates new PaginationEvent.
     * <p>
     *     The default even type is {@link #MOVE},the source and target
     *     of the event is set to {@code NULL_SOURCE_TARGET};
     * </p>
     *
     * @param move the movement to change the picture.
     */
    public PaginationEvent(final Movement move) {
        super(MOVE);
        this.movement = move;
    }

    /**
     * The pagination movement change.
     *
     * @return {@link Optional} of the pagination movement.
     */
    public final Optional<Movement> movement() {
        return Optional.ofNullable(this.movement);
    }

}
