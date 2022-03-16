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

/**
 * A picture gallery.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Gallery {

    /**
     * An exhibition of picture gallery.
     * <p>
     *     This will exhibit a pictures and take the very
     *     first picture as the current active picture.
     * </p>
     *
     * @param pictures the picture collection.
     */
    void exhibit(List<PictureImage> pictures);

    /**
     * An exhibition of picture gallery.
     * <p>
     *     This will exhibit a pictures and make change
     *     the current active picture into the given
     *     picture.
     * </p>
     *
     * @param pictures the picture collection.
     * @param curr the current active picture.
     */
    void exhibit(List<PictureImage> pictures, PictureImage curr);

}
