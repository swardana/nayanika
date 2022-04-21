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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Unit test for {@link Picture.Of}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
class PictureOfTest {

    @Test
    @DisplayName("verify create picture from file")
    public void testVerifyCreatePictureFromFile() {
        var mockFile = mock(File.class);
        var pic = new Picture.Of("1.jpg", mockFile);
        assertThat(pic.name()).isEqualTo("1.jpg");
    }

    @Test
    @DisplayName("verify create picture from path")
    public void testVerifyCreatePictureFromPath() {
        var mockPath = mock(Path.class);
        var pic = new Picture.Of("1.jpg", mockPath);
        assertThat(pic.name()).isEqualTo("1.jpg");
    }

}
