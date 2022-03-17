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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link FrameBehavior}.
 *
 * @author Sukma Wardana
 * @version 0.1.0
 * @since 0.1.0
 */
class FrameBehaviorTest {

    @Test
    @DisplayName("verify zoom on view")
    public void testVerifyViewOnZoomAction() {
        var mockView = mock(FrameView.class);
        when(mockView.horizontalScrollOffset()).thenReturn(0.0);
        when(mockView.verticalScrollOffset()).thenReturn(0.0);

        var behavior = new FrameBehavior(mockView);
        behavior.onViewZoom(0.0, 0.0, 0.0);

        verify(mockView).rescalePicture(anyDouble(), anyDouble());
        verify(mockView).repositionScroller(anyDouble(), anyDouble(), anyDouble());
    }

    @Test
    @DisplayName("verify reset picture scale on double click")
    public void testVerifyResetPictureScaleWithDoubleClick() {
        var mockView = mock(FrameView.class);

        var behavior = new FrameBehavior(mockView);
        behavior.onViewMouseClicked(2);

        verify(mockView).resetPictureScale();
    }

    @Test
    @DisplayName("verify not reset picture scale on single click")
    public void testVerifyNotResetPictureScaleWithOnlySingleClick() {
        var mockView = mock(FrameView.class);

        var behavior = new FrameBehavior(mockView);
        behavior.onViewMouseClicked(1);

        verify(mockView, never()).resetPictureScale();
    }

    @Test
    @DisplayName("verify dragged view on zoomed picture")
    public void testVerifyMouseDraggedOnZoomedPicture() {
        var mockView = mock(FrameView.class);

        var behavior = new FrameBehavior(mockView);
        behavior.onViewMouseDragged(3, 2, 3, 2);

        verify(mockView).horizontalMove(eq(1.0));
        verify(mockView).verticalMove(eq(1.0));
    }

}
