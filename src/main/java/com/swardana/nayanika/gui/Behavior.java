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

package com.swardana.nayanika.gui;

/**
 * A behavior of {@link View}.
 * <p>
 *     The behavior is optional, only introduce
 *     behavior when logic on {@link View} is to
 *     complex or LoC on {@link View} is too long.
 * </p>
 *
 * @param <V> the class that extends {@link View}.
 *
 * @author Sukma Wardana
 * @version 0.1.0
 * @since 0.1.0
 */
public interface Behavior<V extends View> {

    /**
     * The view counterpart.
     *
     * @return {@link View} counterpart.
     */
    V view();

}
