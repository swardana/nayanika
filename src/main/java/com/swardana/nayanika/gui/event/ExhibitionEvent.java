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

import java.io.File;
import java.util.Optional;

/**
 * A picture gallery exhibition event.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class ExhibitionEvent extends Event {

    /**
     * The only valid EventType for {@link ExhibitionEvent}.
     */
    public static final EventType<ExhibitionEvent> EXHIBIT =
        new EventType<>(ANY, "EXHIBIT");

    private final File src;

    /**
     * Creates new ExhibitionEvent.
     * <p>
     *     The default even type is {@link #EXHIBIT},the source and target
     *     of the event is set to {@code NULL_SOURCE_TARGET};
     * </p>
     *
     * @param source the picture gallery source location.
     */
    public ExhibitionEvent(final File source) {
        super(EXHIBIT);
        this.src = source;
    }

    /**
     * The picture gallery source location.
     *
     * @return the {@link Optional} source location.
     */
    public final Optional<File> source() {
        return Optional.ofNullable(this.src);
    }

}
