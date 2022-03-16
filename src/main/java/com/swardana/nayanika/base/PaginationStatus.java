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
 * A pagination status of the picture collection.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public interface PaginationStatus {

    /**
     * Current index position of the active picture.
     * <p>
     *     The index is start from zero (0) value.
     * </p>
     *
     * @return the current active picture index position.
     */
    int index();

    /**
     * The total size of pictures.
     *
     * @return the size of picture collection.
     */
    int size();

    /**
     * Check whether the current active position is on the beginning or not.
     *
     * @return {@code true} if current position is on the beginning.
     */
    default boolean isBeginning() {
        return this.index() == 0;
    }

    /**
     * Check whether the current active position is on the last or not.
     *
     * @return {@code true} if current position is on the last.
     */
    default boolean isLast() {
        return this.index() == (this.size() - 1);
    }

    /**
     * Check whether the current picture collection have item or not.
     *
     * @return {@code true} if current picture collection don't have any item.
     */
    default boolean isEmpty() {
        return this.size() == 0;
    }

}
