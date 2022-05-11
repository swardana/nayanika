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

import java.util.List;

import com.swardana.nayanika.base.SortedPictures.AscSortedPictures;
import com.swardana.nayanika.base.SortedPictures.DescSortedPictures;
import com.swardana.nayanika.base.SortedPictures.NotSortedPictures;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Unit test for {@link SortedPictures.Strategy}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
class SortedPicturesStrategyTest {

    @Test
    @DisplayName("verify generate ascending strategy")
    public void testGenerateAscStrategy() {
        var mockPictures = mock(List.class);
        var actual = SortedPictures.Strategy.ASCENDING.sorted(mockPictures);
        assertThat(actual).isInstanceOf(AscSortedPictures.class);
    }

    @Test
    @DisplayName("verify generate descending strategy")
    public void testGenerateDescStrategy() {
        var mockPictures = mock(List.class);
        var actual = SortedPictures.Strategy.DESCENDING.sorted(mockPictures);
        assertThat(actual).isInstanceOf(DescSortedPictures.class);
    }

    @Test
    @DisplayName("verify generate non-sorted strategy")
    public void testGenerateNoneStrategy() {
        var mockPictures = mock(List.class);
        var actual = SortedPictures.Strategy.NONE.sorted(mockPictures);
        assertThat(actual).isInstanceOf(NotSortedPictures.class);
    }

}
