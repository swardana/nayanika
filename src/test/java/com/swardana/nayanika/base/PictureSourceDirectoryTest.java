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
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link PictureSource.Directory}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
@ExtendWith(ApplicationExtension.class)
class PictureSourceDirectoryTest {

    private static final String FOLDER = "src/test/resources"
        + "/com/swardana/nayanika/base";

    @Test
    @DisplayName("verify fetch all supported pictures")
    public void testFetchAllSupportedPictures() {
        var expected = 4;
        var path = Paths.get(FOLDER);
        var actual = new PictureSource.Directory(path);
        assertThat(actual.pictures()).isNotEmpty().hasSize(expected);
    }

    @Test
    @DisplayName("verify fetch JPG only pictures")
    public void testFetchJpgPictures() {
        var expected = 2;
        var path = Paths.get(FOLDER);
        var actual = new PictureSource.Directory(path, PictureSource.Supported.JPG);
        assertThat(actual.pictures()).isNotEmpty().hasSize(expected);
    }

    @Test
    @DisplayName("verify fetch PNG only pictures")
    public void testFetchPngPictures() {
        var expected = 2;
        var path = Paths.get(FOLDER);
        var actual = new PictureSource.Directory(path, PictureSource.Supported.PNG);
        assertThat(actual.pictures()).isNotEmpty().hasSize(expected);
    }

}
