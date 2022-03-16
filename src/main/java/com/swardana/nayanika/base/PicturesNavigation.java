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

/**
 * A pictures navigation movement.
 * <p>
 *     Moving between pictures. Change the inner
 *     state of picture collection.
 * </p>
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public interface PicturesNavigation {

    /**
     * Move to the next picture from this
     * current active picture.
     */
    void next();

    /**
     * Move to the previous picture from this
     * current active picture.
     */
    void previous();

    /**
     * Move to the last picture from the
     * pictures.
     */
    void last();

    /**
     * Move to the beginning picture from the
     * pictures.
     */
    void beginning();

    /**
     * Jump to specific picture from the pictures.
     *
     * @param pic the new active picture.
     * @throws IllegalArgumentException if the new picture is not part of
     * this collection.
     */
    void jump(Picture pic);

}
