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

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link SortedPictures.DescSortedPictures}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
class DescSortedPicturesTest {

    @Test
    @DisplayName("verify sort pictures in descending order for name is words")
    public void testSortDescForNameIsWords() {
        var pictures = new ArrayList<Picture>();
        pictures.add(new Picture.Of("manual", new File("foo/manual.jpg")));
        pictures.add(new Picture.Of("credit", new File("foo/credit.png")));
        pictures.add(new Picture.Of("apendix.jpg", new File("foo/apendix.jpg")));
        var actual = new SortedPictures.DescSortedPictures(pictures).sorted();
        assertThat(actual).isNotEmpty().hasSize(3).extracting(Picture::name)
            .containsExactly("manual", "credit", "apendix.jpg")
            .doesNotContainNull();
    }

    @Test
    @DisplayName("verify sort pictures in descending order for name is number")
    public void testSortDescForNameIsNumber() {
        var pictures = new ArrayList<Picture>();
        pictures.add(new Picture.Of("003", new File("foo/003.jpg")));
        pictures.add(new Picture.Of("001", new File("foo/001.png")));
        pictures.add(new Picture.Of("02.jpg", new File("foo/02.jpg")));
        var actual = new SortedPictures.DescSortedPictures(pictures).sorted();
        assertThat(actual).isNotEmpty().hasSize(3).extracting(Picture::name)
            .containsExactly("003", "02.jpg", "001")
            .doesNotContainNull();
    }

    @Test
    @DisplayName("verify sort pictures in descending order for name is mix of words and number")
    public void testSortDescForNameIsMixOfWordsAndNumber() {
        var pictures = new ArrayList<Picture>();
        pictures.add(new Picture.Of("003", new File("foo/003.jpg")));
        pictures.add(new Picture.Of("AAA", new File("foo/AAA.png")));
        pictures.add(new Picture.Of("02.jpg", new File("foo/02.jpg")));
        var actual = new SortedPictures.DescSortedPictures(pictures).sorted();
        assertThat(actual).isNotEmpty().hasSize(3).extracting(Picture::name)
            .containsExactly("AAA", "003", "02.jpg")
            .doesNotContainNull();
    }

    @Test
    @DisplayName(
        "verify sort pictures in descending order for name is combination of words and number"
    )
    public void testSortDescForNameIsCombinationOfWordsAndNumber() {
        var pictures = new ArrayList<Picture>();
        pictures.add(new Picture.Of("XY-003", new File("foo/XY-003.jpg")));
        pictures.add(new Picture.Of("BXS-001", new File("foo/BXS-001.png")));
        pictures.add(new Picture.Of("02.jpg", new File("foo/02.jpg")));
        var actual = new SortedPictures.DescSortedPictures(pictures).sorted();
        assertThat(actual).isNotEmpty().hasSize(3).extracting(Picture::name)
            .containsExactly("XY-003", "02.jpg", "BXS-001")
            .doesNotContainNull();
    }

}
