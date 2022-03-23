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

import com.swardana.nayanika.base.PicturesNavigation;
import javafx.event.EventHandler;

/**
 * A pagination event handler.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class PaginationEventHandler implements EventHandler<PaginationEvent> {
    public PaginationEventHandler(final PicturesNavigation nav) {
        this.nav = nav;
    }


    private final PicturesNavigation nav;

    @Override
    public void handle(final PaginationEvent event) {
        event.movement().ifPresent(m -> {
            switch (m) {
                case NEXT -> this.nav.next();
                case PREVIOUS -> this.nav.previous();
                case BEGINNING -> this.nav.beginning();
                case LAST -> this.nav.last();
                default -> {}
            }
        });
        event.consume();
    }
}
