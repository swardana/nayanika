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

package com.swardana.nayanika.base;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Unit test for {@link ArchivedPicture}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
class ArchivedPictureTest {

    @Test
    @DisplayName("verify create picture from comic book")
    public void testVerifyCreatePictureFromFile() {
        var comic = new File(
            "src/test/resources/com/swardana/nayanika/base/archive.zip"
        );
        var pic = new ArchivedPicture("tatto-wolf-jpg.jpg", comic);
        assertThat(pic.name()).isEqualTo("tatto-wolf-jpg.jpg");
    }

    @Test
    @DisplayName("verify throw exception when picture not found")
    public void testVerifyWhenPictureNotFound() {
        var comic = new File(
            "src/test/resources/com/swardana/nayanika/base/archive.zip"
        );
        var pic = new ArchivedPicture("1.jpg", comic);
        assertThatThrownBy(() -> pic.stream())
            .isInstanceOf(RuntimeException.class)
            .hasMessage("java.io.IOException: Can't find the picture!");
    }

}
