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

package com.swardana.nayanika.gui.topbar.menu;

import com.swardana.nayanika.gui.View;

/**
 * A view contract for menu visual.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public interface MenuView extends View {

    /**
     * Enabling the slide-show menu.
     * <p>
     *     The slide-show menu should be disabled
     *     when there is no pictures opened. So,
     *     when app already have pictures opened.
     * </p>
     * <p>
     *     Currently, there is no operation to
     *     disabled the slide-show menu. Because,
     *     there is no option to remove the pictures
     *     other than close the application.
     * </p>
     */
    void enableSlideMenu();

    /**
     * Showing the start slide-show menu.
     * <p>
     *     Called this operation when the current
     *     menu is showing stop slide-show menu.
     *     This will replace the UI and behavior
     *     for starting the slide-show.
     * </p>
     */
    void showStartSlideMenu();

    /**
     * Showing the stop slide-show menu.
     * <p>
     *     Called this operation when the current
     *     menu is showing start slide-show menu.
     *     This will replace the UI and behavior
     *     for stoping the slide-show.
     * </p>
     */
    void showStopSlideMenu();

}
