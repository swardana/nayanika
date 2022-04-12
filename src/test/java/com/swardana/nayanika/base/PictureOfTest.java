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

import javafx.scene.image.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Unit test for {@link Picture.Of}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
@ExtendWith(ApplicationExtension.class)
class PictureOfTest {

    @Test
    @DisplayName("verify create picture from byte stream data")
    public void testVerifyCreatePictureFromStream() {
        var mockImage = mock(Image.class);
        var pic = new Picture.Of("1.jpg", mockImage);
        assertThat(pic.name()).isEqualTo("1.jpg");
        assertThat(pic.image()).isNotNull();
    }

}
