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

import com.swardana.nayanika.base.PaginationStatusControl;
import com.swardana.nayanika.gui.topbar.menu.MenuBarVisual;
import com.swardana.nayanika.gui.topbar.menu.MenuView;
import com.swardana.nayanika.gui.topbar.menu.ToolBarVisual;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A top-bar visual.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public class TopBarVisual extends VBox implements TopBarView {

    private static final int MENU_BAR_INDEX = 0;
    private static final int TOOL_BAR_INDEX = 1;

    private final MenuView toolbar;
    private final MenuView menubar;

    /**
     * Creates new TopBarVisual.
     *
     * @param stage the primary stage owner.
     * @param control the pagination observable status control.
     */
    public TopBarVisual(final Stage stage, final PaginationStatusControl control) {
        this.toolbar = new ToolBarVisual(stage, control);
        this.menubar = new MenuBarVisual(stage, control);

        this.initGraphics();
    }

    @Override
    public final Parent parent() {
        return this;
    }

    @Override
    public void showToolBar() {
        this.getChildren().set(TOOL_BAR_INDEX, this.toolbar.parent());
    }

    @Override
    public void hideToolBar() {
        this.getChildren().remove(TOOL_BAR_INDEX);
    }

    @Override
    public void showMenuBar() {
        this.getChildren().set(MENU_BAR_INDEX, this.menubar.parent());
    }

    @Override
    public void hideMenuBar() {
        this.getChildren().remove(MENU_BAR_INDEX);
    }

    private void initGraphics() {
        this.getChildren().setAll(
            this.menubar.parent(),
            this.toolbar.parent()
        );
    }

}
