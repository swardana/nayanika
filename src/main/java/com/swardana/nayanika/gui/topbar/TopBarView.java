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

package com.swardana.nayanika.gui.topbar;

import com.swardana.nayanika.gui.View;

/**
 * A view contract for top-bar visual.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public interface TopBarView extends View {

    /**
     * Show the tool-bar visual.
     * <p>
     *     This operation to display the tool-bar menu.
     * </p>
     */
    void showToolBar();

    /**
     * Hide the tool-bar visual.
     * <p>
     *     This operation to hide the tool-bar menu.
     * </p>
     */
    void hideToolBar();

    /**
     * Show the menu-bar visual.
     * <p>
     *     This operation to display the menu-bar menu.
     * </p>
     */
    void showMenuBar();

    /**
     * Hide the menu-bar visual.
     * <p>
     *     This operation to hide the menu-bar menu.
     * </p>
     */
    void hideMenuBar();

}
