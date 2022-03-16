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

import com.swardana.nayanika.gui.View;

/**
 * A view contract for Frame visual.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FrameView extends View {

    /**
     * Requesting node focus.
     * <p>
     *     Re-gain the focus onto this view.
     * </p>
     */
    void focus();

    /**
     * Change the scale of picture.
     *
     * @param horizontal the scale factor by which coordinates along the X axis.
     * @param vertical the scale factor by which coordinates along the Y axis.
     */
    void rescalePicture(double horizontal, double vertical);

    /**
     * Reset the picture scale.
     */
    default void resetPictureScale() {
        this.rescalePicture(1, 1);
    }

    /**
     * Figure the horizontal scroll offset position.
     *
     * @return the horizontal scroll offset position.
     */
    double horizontalScrollOffset();

    /**
     * Figure the vertical scroll offset position.
     *
     * @return the vertical scroll offset position.
     */
    double verticalScrollOffset();

    /**
     * Reposition the viewport of Scroller.
     *
     * @param scaleFactor the scale factor.
     * @param scrollXOffset the horizontal offset position.
     * @param scrollYOffset the vertical offset position.
     */
    void repositionScroller(
        double scaleFactor,
        double scrollXOffset,
        double scrollYOffset
    );

    /**
     * Move the node horizontally based on delta x.
     *
     * @param deltaX the delta x.
     */
    void horizontalMove(double deltaX);

    /**
     * Move the node vertically based on delta y.
     *
     * @param deltaY the delta y.
     */
    void verticalMove(double deltaY);

}
