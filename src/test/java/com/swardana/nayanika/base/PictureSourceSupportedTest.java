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

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link PictureSource.Supported}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
class PictureSourceSupportedTest {

    @Test
    @DisplayName("verify ALL supported enum data information")
    public void testVerifyAllDataInformation() {
        var regex = "*.{png,PNG,JPG,jpg,JPEG,jpeg,GIF,gif,BMP,bmp}";
        var exts = Arrays.asList("*.bmp", "*.gif", "*.jpg", "*.jpeg", "*.png");
        var all = PictureSource.Supported.ALL;
        assertThat(all.pattern()).isNotNull().isEqualTo(regex);
        assertThat(all.extensions()).isEqualTo(exts);
    }

    @Test
    @DisplayName("verify PNG supported enum data information")
    public void testVerifyPngDataInformation() {
        var regex = "*.{png,PNG}";
        var exts = Arrays.asList("*.png");
        var all = PictureSource.Supported.PNG;
        assertThat(all.pattern()).isNotNull().isEqualTo(regex);
        assertThat(all.extensions()).isEqualTo(exts);
    }

    @Test
    @DisplayName("verify JPG supported enum data information")
    public void testVerifyJpgDataInformation() {
        var regex = "*.{JPG,jpg,JPEG,jpeg}";
        var exts = Arrays.asList("*.jpg", "*.jpeg");
        var all = PictureSource.Supported.JPG;
        assertThat(all.pattern()).isNotNull().isEqualTo(regex);
        assertThat(all.extensions()).isEqualTo(exts);
    }

    @Test
    @DisplayName("verify GIF supported enum data information")
    public void testVerifyGifDataInformation() {
        var regex = "*.{GIF,gif}";
        var exts = Arrays.asList("*.gif");
        var all = PictureSource.Supported.GIF;
        assertThat(all.pattern()).isNotNull().isEqualTo(regex);
        assertThat(all.extensions()).isEqualTo(exts);
    }

    @Test
    @DisplayName("verify BMP supported enum data information")
    public void testVerifyBmpDataInformation() {
        var regex = "*.{BMP,bmp}";
        var exts = Arrays.asList("*.bmp");
        var all = PictureSource.Supported.BMP;
        assertThat(all.pattern()).isNotNull().isEqualTo(regex);
        assertThat(all.extensions()).isEqualTo(exts);
    }

}
