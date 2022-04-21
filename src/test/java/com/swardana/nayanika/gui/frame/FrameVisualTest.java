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

package com.swardana.nayanika.gui.frame;

import com.swardana.nayanika.base.Picture;
import com.swardana.nayanika.base.PictureImage;
import com.swardana.nayanika.base.PictureSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link FrameVisual}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
@ExtendWith(ApplicationExtension.class)
class FrameVisualTest {

    private static final String FOLDER = "src/test/resources"
        + "/com/swardana/nayanika/base";

    private static List<PictureImage> pictures;

    @BeforeAll
    public static void setup() {
        var path = Paths.get(FOLDER);
        pictures = new PictureSource.Directory(path).pictures()
            .stream().map(PictureImage::new).collect(Collectors.toList());
    }

    @AfterAll
    public static void tearDown() {
        pictures = null;
    }

    @Test
    @DisplayName("verify exhibit picture gallery")
    public void testVerifyExhibitPictureGallery() {
        var visual = new FrameVisual();
        visual.exhibit(pictures);
        assertThat(visual.size()).isEqualTo(4);
        assertThat(visual.isEmpty()).isFalse();
        assertThat(visual.isBeginning()).isTrue();
        assertThat(visual.isLast()).isFalse();
    }

    @Test
    @DisplayName("verify exhibit picture gallery with active picture")
    public void testVerifyExhibitPictureGalleryWithActivePicture() throws IOException {
        var path = Paths.get(FOLDER);
        var imgPath = Paths.get(path.toString(), "tattoo-wolf-jpg.jpg");
        var active = new PictureImage(
            new Picture.Of(
                "tattoo-wolf-jpg.jpg", imgPath.toFile()
            )
        );
        var visual = new FrameVisual();
        visual.exhibit(pictures, active);
        assertThat(visual.size()).isEqualTo(4);
        assertThat(visual.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("verify move to the next picture")
    public void testVerifyMoveToTheNextPicture() {
        var expected = 1;
        var visual = new FrameVisual();
        visual.exhibit(pictures);
        visual.beginning();
        visual.next();
        assertThat(visual.index()).isEqualTo(expected);
        assertThat(visual.isLast()).isFalse();
        assertThat(visual.isBeginning()).isFalse();
    }

    @Test
    @DisplayName("verify move to the previous picture")
    public void testVerifyMoveToThePreviousPicture() {
        var expected = pictures.size() - 2;
        var visual = new FrameVisual();
        visual.exhibit(pictures);
        visual.last();
        visual.previous();
        assertThat(visual.index()).isEqualTo(expected);
        assertThat(visual.isLast()).isFalse();
        assertThat(visual.isBeginning()).isFalse();
    }

    @Test
    @DisplayName("verify move to the last picture")
    public void testVerifyMoveToTheLastPicture() {
        var expected = pictures.size() - 1;
        var visual = new FrameVisual();
        visual.exhibit(pictures);
        visual.beginning();
        visual.last();
        assertThat(visual.index()).isEqualTo(expected);
        assertThat(visual.isLast()).isTrue();
        assertThat(visual.isBeginning()).isFalse();
    }

    @Test
    @DisplayName("verify move to the beginning picture")
    public void testVerifyMoveToTheBeginningPicture() {
        var expected = 0;
        var visual = new FrameVisual();
        visual.exhibit(pictures);
        visual.last();
        visual.beginning();
        assertThat(visual.index()).isEqualTo(expected);
        assertThat(visual.isLast()).isFalse();
        assertThat(visual.isBeginning()).isTrue();
    }

}
