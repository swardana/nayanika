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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link ExhibitionEvent}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
class ExhibitionEventTest {

    @Test
    @DisplayName("verify when the source location is null")
    public void testVerifySourceIsNull() {
        var event = new ExhibitionEvent(null);
        assertThat(event.source()).isEmpty();
    }

    @Test
    @DisplayName("verify when the source location is not null")
    public void testVerifySourceIsNotNull() {
        var event = new ExhibitionEvent(new File("/foo/bar"));
        assertThat(event.source()).isNotEmpty();
    }

}